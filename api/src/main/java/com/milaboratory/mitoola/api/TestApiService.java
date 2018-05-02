package com.milaboratory.mitoola.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author Alexei Zakharov (ayza)
 */
@Path("/test")
public interface TestApiService {
  @GET
  String hello(@QueryParam("name") String name);
}
