package com.logistics.services.contracts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ContractsLogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractsLogisticsApplication.class, args);
	}

}
/* a Spring Data JPA annotation used to activate automatic auditing for database entities. 
It enables tracking of creation/modification times and user creators/modifiers (@CreatedDate, @LastModifiedBy, etc.) by adding it to a configuration class, 
often combined with @EntityListeners(AuditingEntityListener.class). 
*/