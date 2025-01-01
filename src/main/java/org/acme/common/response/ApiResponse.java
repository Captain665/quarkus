package org.acme.common.response;

import org.acme.common.enums.Status;

public class ApiResponse {
	public Status status;
	public String message;
	public Object result;
	public Object error;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public ApiResponse() {
	}

	public ApiResponse(Status status, String message, Object result, Object error) {
		this.status = status;
		this.message = message;
		this.result = result;
		this.error = error;
	}
}
