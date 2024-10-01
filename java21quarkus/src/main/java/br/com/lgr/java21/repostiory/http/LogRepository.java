package br.com.lgr.java21.repostiory.http;

import br.com.lgr.java21.domain.record.LogEntityRecord;
import br.com.lgr.java21.domain.record.LogRecord;
import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;
import java.util.UUID;

@RegisterRestClient
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
@ApplicationScoped
@Path("/logs")
public interface LogRepository {

    @POST
    LogEntityRecord create(LogRecord record);

    @PUT
    @Path("/{id}")
    LogEntityRecord update(@PathParam("id") UUID id, LogRecord record);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") UUID id);

    @GET
    @Path("/{id}")
    LogEntityRecord findById(@PathParam("id") UUID id);

    @GET
    List<LogEntityRecord> findAll(@QueryParam("dslog") String dsLog);

    @GET
    @Path("/like/{dslog}")
    List<LogEntityRecord> findByDsLog(@PathParam("dslog") String dsLog);


}
