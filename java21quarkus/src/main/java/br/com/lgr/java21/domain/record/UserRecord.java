package br.com.lgr.java21.domain.record;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;
import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(description = "Usuário com as informações básicas")
public record UserRecord(
        @Schema(description = "Nome do usuário", nullable = false)
        @NotBlank
        String username,
        @Schema(description = "Grupo do usuário")
        @ProjectedFieldName("group.name") String groupName) {
}
