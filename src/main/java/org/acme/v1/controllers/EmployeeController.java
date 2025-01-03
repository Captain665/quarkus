package org.acme.v1.controllers;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("v1/employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger("v1.employeeController");
	@Inject
	private EmployeeResourceHandler resourceHandler;

	@POST
	public RestResponse<?> create(EmployeeResource resource) {
		logger.info(" requested json : " + resource);
		EmployeeResource responseResource = resourceHandler.createOrUpdate(resource);
		logger.info(" response :" + responseResource);
		if (responseResource != null) {
			return RestResponse.ResponseBuilder
					.create(RestResponse.Status.OK, responseResource)
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		logger.error("incorrect company id");
		return RestResponse.ResponseBuilder
				.create(RestResponse.Status.BAD_REQUEST, new ApiFailure("Incorrect company id", new ErrorCode("", "COMPANY_ID", "Incorrect company id")))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}


}
