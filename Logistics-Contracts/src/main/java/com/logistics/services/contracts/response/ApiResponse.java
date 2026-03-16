package com.logistics.services.contracts.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

	private boolean success;
	private String message;
	private T data;
	private String errorCode;
	private long timestamp = System.currentTimeMillis();

	public static <T> ApiResponse<T> success(T data) {
		ApiResponse<T> r = new ApiResponse<>();
		r.success = true;
		r.data = data;
		return r;
	}

	public static <T> ApiResponse<T> success(String message, T data) {
		ApiResponse<T> r = new ApiResponse<>();
		r.success = true;
		r.message = message;
		r.data = data;
		return r;
	}

	public static <T> ApiResponse<T> error(String message, String errorCode) {
		ApiResponse<T> r = new ApiResponse<>();
		r.success = false;
		r.message = message;
		r.errorCode = errorCode;
		return r;

	}
}
