package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.common.response.ApiSuccess;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/hello")
public class GreetingResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse<?> hello() {

		return RestResponse.ResponseBuilder.ok(
						new ApiSuccess("Hello from Quarkus REST"), MediaType.APPLICATION_JSON)
				.build();
	}
}
