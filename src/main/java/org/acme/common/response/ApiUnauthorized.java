package org.acme.common.response;

import org.acme.common.enums.Status;

public class ApiUnauthorized extends ApiResponse {
	public ApiUnauthorized() {
		super(Status.unauthorized, "not authorized to access this", null, null);
	}
}
