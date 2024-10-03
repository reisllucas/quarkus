package br.com.lgr.java21.controller;

import br.com.lgr.java21.domain.UserEntity;
import br.com.lgr.java21.domain.record.TransactionMessageRecord;
import br.com.lgr.java21.domain.record.TransactionRecord;
import br.com.lgr.java21.service.TransactionService;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.time.ZonedDateTime;

@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @Inject
    JsonWebToken jsonWebToken;

    @POST
    @Operation(summary = "Cria nova transação")
    @APIResponse(
            responseCode = "201",
            description = "Novo registro criado com sucesso",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = UserEntity.class,
                            type = SchemaType.OBJECT
                    )
            )
    )
    @RolesAllowed("create-transaction")
    public Response create(
            @RequestBody(required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    implementation = TransactionRecord.class)))
            @Valid TransactionRecord transactionRecord) {

        return Response.status(Response.Status.CREATED).entity(
                transactionService.send(
                        new TransactionMessageRecord(jsonWebToken.getName(),
                                ZonedDateTime.now(),
                                transactionRecord)
                )).build();

    }

}
