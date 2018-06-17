package com.milaboratory.mitoola.api.resource;

import com.milaboratory.mitoola.api.dto.DataSetDto;
import com.milaboratory.mitoola.api.dto.DataSetFilterDto;
import com.milaboratory.mitoola.api.dto.UpdateResultDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Alexei Zakharov (ayza)
 */
@Path("/dataset")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface DataSetApiService {
    @GET
    @Path("/{id}")
    DataSetDto get(@PathParam("id") long id);

    @GET
    @Path("/")
    List<DataSetDto> list(@BeanParam DataSetFilterDto filter);

    @POST
    @Path("/")
    DataSetDto create(DataSetDto dataSet);

    @PUT
    @Path("/{id}")
    DataSetDto update(@PathParam("id") long id, DataSetDto dataSet);

    @DELETE
    @Path("/{id}")
    DataSetDto delete(@PathParam("id") long id);
}
