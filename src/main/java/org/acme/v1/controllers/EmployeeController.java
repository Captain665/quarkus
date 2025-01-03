package org.acme.v1.controllers;

import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.common.response.ApiFailure;
import org.acme.common.response.ErrorCode;
import org.acme.v1.resourceHandlers.EmployeeResourceHandler;
import org.acme.v1.resources.EmployeeResource;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.Optional;


@Path("v1/employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

	@Inject
	private EmployeeResourceHandler resourceHandler;

	@POST
	public RestResponse<?> create(EmployeeResource resource) {
		EmployeeResource responseResource = resourceHandler.createOrUpdate(resource);
		if (responseResource != null) {
			return RestResponse.ResponseBuilder
					.create(RestResponse.Status.OK, responseResource)
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		System.out.println("incorrect company id");
		return RestResponse.ResponseBuilder
				.create(RestResponse.Status.BAD_REQUEST, new ApiFailure("Incorrect company id", new ErrorCode("", "COMPANY_ID", "Incorrect company id")))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}


}
