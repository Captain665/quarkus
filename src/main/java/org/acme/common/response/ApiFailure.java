package org.acme.common.response;

import org.acme.common.enums.Status;

public class ApiFailure extends ApiResponse {
	public ApiFailure(String message, Object error) {
		super(Status.failure, message, null, error);
	}
}
