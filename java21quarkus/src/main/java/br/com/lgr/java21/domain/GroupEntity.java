package br.com.lgr.java21.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

@Entity
@Table(name = "tb_group")
@Schema(description = "Entidade completa do grupo de usuário")
public class GroupEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id do grupo")
    public UUID groupId;

    @Schema(description = "nome do grupo")
    public String name;

    public GroupEntity() {
    }

    public GroupEntity(UUID groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }
}
