package com.logistics.services.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
	private String uuid;
	private String name;
	private String description;
	private String imageUrl;
	private String parentCategoryId;
	private List<CategoryResponse> children;
}