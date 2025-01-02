package org.acme.v1.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.common.response.ApiFailure;
import org.acme.common.response.ApiSuccess;
import org.acme.common.response.ErrorCode;
import org.acme.v1.models.CompanyModel;
import org.acme.v1.repositories.CompanyRepository;
import org.acme.v1.resourceHandlers.CompanyResourceHandler;
import org.acme.v1.resources.CompanyResponseResource;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/v1/company")
public class CompanyController {

	@Inject
	private CompanyResourceHandler resourceHandler;

	@Path("/{id}/details")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse<?> getDetails(@PathParam("id") Long id) {
		System.out.println("Request id is " + id);
		CompanyResponseResource responseResource = resourceHandler.getCompanyDetails(id);
		if (responseResource != null) {
			System.out.println("response resource " + responseResource);
			return RestResponse.ResponseBuilder
					.create(RestResponse.Status.OK, new ApiSuccess(responseResource))
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
