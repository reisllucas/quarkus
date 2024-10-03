package br.com.lgr.java21.controller;

import br.com.lgr.java21.domain.UserEntity;
import br.com.lgr.java21.domain.record.UserRecord;
import br.com.lgr.java21.service.UserService;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
@Authenticated
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Operation(summary = "Retorna todos os usuários cadastrados")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = UserEntity.class,
                            type = SchemaType.ARRAY
                    )
            )
    )
    public Response findAll(@Parameter(description = "Número da página", required = false)
                            @QueryParam("page") @DefaultValue("0") Integer page,
                            @Parameter(description = "Quantidade de registros por página", required = false)
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {

        return Response.ok(userService.findAll(page,pageSize)).build();
    }

    @GET
    @Operation(summary = "Retorna todos os usuários cadastrados")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = UserRecord.class,
                            type = SchemaType.ARRAY
                    )
            )
    )
    @Path("/projection")
    public Response findAllProjection() {

        return Response.ok(userService.findAllProjection()).build();
    }

    @GET
    @Operation(summary = "Retorna todos os usuários cadastrados")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = UserRecord.class,
                            type = SchemaType.ARRAY
                    )
            )
    )
    @Path("/hql")
    public Response findAllHQL() {

        return Response.ok(userService.findAllHQL()).build();
    }

    @GET
    @Operation(summary = "Retorna o usuário do id de referência")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = UserRecord.class,
                            type = SchemaType.OBJECT
                    )
            )
    )
    @Path("/{id}")
    public Response findById(
            @Parameter(description = "Id do usuário a ser retornado", required = true)
            @PathParam("id") UUID userId) {

        return Response.ok(userService.findById(userId)).build();
    }

    @POST
    @Operation(summary = "Cadastra novo usuário")
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
    public Response create(
            @RequestBody(required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    implementation = UserRecord.class)))
            @Valid
            UserRecord user) {

        return Response.status(Response.Status.CREATED)
                .entity(userService.create(user))
                .build();

    }

    @PUT
    @Operation(summary = "Altera usuário")
    @APIResponse(
            responseCode = "200",
            description = "Registro alterado com sucesso",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = UserEntity.class,
                            type = SchemaType.OBJECT
                    )
            )
    )
    @Path("/{id}")
    public Response update(
            @Parameter(description = "Id do usuário a ser retornado", required = true)
            @PathParam("id") UUID id,
            @RequestBody(required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    implementation = UserRecord.class)))
            @Valid
            UserRecord user) {
        return Response.ok(userService.update(id, user)).build();

    }

    @DELETE
    @Operation(summary = "Excluir usuário")
    @APIResponse(
            responseCode = "204",
            description = "Registro excluído com sucesso",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,

                    schema = @Schema(
                            implementation = Void.class,
                            type = SchemaType.OBJECT
                    )
            )
    )
    @Path("/{id}")
    public Response delete(@Parameter(description = "Id do usuário a ser excluído", required = true)
                           @PathParam("id") UUID id) {

        userService.delete(id);
        return Response.noContent().build();
    }

}
