package org.acme.v1.Controllers;

import io.quarkus.runtime.Application;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.common.response.ApiResponse;
import org.acme.common.response.ApiSuccess;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/v1/company")
public class CompanyController {

	@Path("/{id}/details")
	@GET
	public RestResponse<?> getDetails(@PathParam("id") Long id) {
		return RestResponse.ResponseBuilder.ok(new ApiSuccess(id), MediaType.APPLICATION_JSON).build();
	}

}
