package com.logistics.services.security;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.logistics.services.entity.Roles;
import com.logistics.services.entity.User;
import com.logistics.services.entity.UserStatus;
import com.logistics.services.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService  {

	 private final UserRepository userRepository;

	 // Called by Spring Security after OAuth2 provider returns user info
	 @Override
	 @Transactional
	 public OAuth2User loadUser(OAuth2UserRequest userRequest)
			 throws OAuth2AuthenticationException{
	 
     // Let Spring fetch the user attributes from Google/GitHub 	 
	 OAuth2User oAuth2User = super.loadUser(userRequest);
	 
	 // Which provider? "google", "github"
	 String provider = userRequest.getClientRegistration().getRegistrationId();
	 
	// Extract attributes from provider response
	 Map<String, Object> attributes = oAuth2User.getAttributes();
	 
	 String email = (String) attributes.get("email");
	 String name = (String) attributes.getOrDefault("name", email);
	 String providerId = oAuth2User.getName();
	 
	 if(email==null) {
		 throw new OAuth2AuthenticationException("Email not provided by OAuth2 provider : "+provider);
	 }
	 
	// Find existing user or create new one
    // OAuth2 users don't have passwords (they authenticate via provider)
	  User user = userRepository.findByEmail(email)
              .orElseGet(() -> {
                  log.info("Creating new user via OAuth2 provider={} email={}",
                           provider, email);
                  
               // Split name into first/last
			String[] nameParts = name.split(" ", 2);
			User newUser = User.builder()
							.email(email)
							.firstName(nameParts[0])
							.lastName(nameParts.length > 1 ? nameParts[1] : "")
							.provider(provider)
							.providerId(providerId)
							.status(UserStatus.ACTIVE)
							.roles(new HashSet<>(Set.of(Roles.CUSTOMER)))
							.totalOrders(0)
							.totalSpent(java.math.BigDecimal.ZERO)
							.failedLoginAttempts(0)
							.profileImageUrl((String) attributes.get("picture"))
							.build();
					return userRepository.save(newUser);
				});
		// Update profile picture from provider if changed
		String picture = (String) attributes.get("picture");

		if (picture != null && !picture.equals(user.getProfileImageUrl())) {
			user.setProfileImageUrl(picture);
			userRepository.save(user);
		}

		// Return UserDetailsImpl with both User entity and OAuth2 attributes
		return new UserDetailsImpl(user, attributes);
	}

}
//WHY this class?
//Spring Security's DefaultOAuth2UserService fetches user info from
//Google/GitHub after the OAuth2 dance completes.
//We extend it to: find or create our User entity from OAuth2 attributes.
//
//FLOW:
//User clicks "Login with Google"
//→ Spring redirects to Google
//→ Google authenticates user
//→ Google redirects back with authorization code
//→ Spring exchanges code for access token
//→ Spring calls Google UserInfo endpoint
//→ THIS CLASS processes the returned user info
//→ We find or create User in our DB
//→ Return UserDetailsImpl (carries our User entity + OAuth2 attributes)
//→ OAuth2SuccessHandler issues our JWT