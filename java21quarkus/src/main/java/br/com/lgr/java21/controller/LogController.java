package br.com.lgr.java21.controller;

import br.com.lgr.java21.domain.record.LogEntityRecord;
import br.com.lgr.java21.domain.record.LogRecord;
import br.com.lgr.java21.service.LogService;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/logs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
@Authenticated
public class LogController {

    @Inject
    LogService logService;

    @POST
    public LogEntityRecord create(@Valid LogRecord record) {
        return logService.create(record);
    }

    @PUT
    @Path("/{id}")
    public LogEntityRecord update(@PathParam("id") UUID id, @Valid LogRecord record) {
        return logService.update(id,record);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
        logService.delete(id);
    }

    @GET
    @Path("/{id}")
    public LogEntityRecord findById(@PathParam("id") UUID id) {

        return logService.findById(id);
    }

    @GET
    public List<LogEntityRecord> findAll(@QueryParam("dslog") String dsLog) {

        return logService.findAll(dsLog);
    }

    @GET
    @Path("/like/{dslog}")
    public List<LogEntityRecord> findByDsLog(@PathParam("dslog") String dsLog) {

        return logService.findByDsLog(dsLog);
    }

}
