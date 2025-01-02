package org.acme.common.response;

import org.acme.common.enums.Status;

public class ApiSuccess extends ApiResponse {
	public ApiSuccess(Object result) {
		super(Status.success, "", result, null);
	}
}
