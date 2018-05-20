package com.milaboratory.mitoola.api;

import com.milaboratory.mitoola.api.dto.HelloDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author Alexei Zakharov (ayza)
 */
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public interface TestApiService {
  @GET
  HelloDto hello(@QueryParam("name") String name);
}
