package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
	@NotBlank
	private String name;
	private String description;
	private String parentCategoryId; // null = root category
	private Integer displayOrder;
}