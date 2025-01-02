package org.acme.v1.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.common.response.ApiFailure;
import org.acme.common.response.ApiSuccess;
import org.acme.common.response.ErrorCode;
import org.acme.v1.models.CompanyModel;
import org.acme.v1.repositories.CompanyRepository;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/v1/company")
public class CompanyController {

	@Inject
	public CompanyRepository repository;

	@Path("/{id}/details")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse<?> getDetails(@PathParam("id") Long id) {
		System.out.println("Request id is " + id);
		CompanyModel model = repository.findById(id);
		if (model != null) {
			System.out.println("response model " + model);
			return RestResponse.ResponseBuilder
					.create(RestResponse.Status.OK, new ApiSuccess(model))
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		System.out.println("incorrect company id");
		return RestResponse.ResponseBuilder
				.create(RestResponse.Status.BAD_REQUEST, new ApiFailure("Incorrect company id", new ErrorCode(id.toString(), "COMPANY_ID", "Incorrect company id")))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
