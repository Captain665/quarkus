package org.acme.v1.controllers;

import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.v1.resources.EmployeeResource;
import org.jboss.resteasy.reactive.RestResponse;


@Path("v1/employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {
	@POST
	public RestResponse<?> createUpdate(EmployeeResource resource) {
		return RestResponse.ResponseBuilder
				.create(RestResponse.Status.OK, resource)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}


}
