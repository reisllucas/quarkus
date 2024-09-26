package br.com.lgr.java21.domain;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.util.UUID;


@Entity
@Table(name = "tb_user")
@Schema(description = "Entidade completa do usuário")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id do usuário")
    public UUID userId;
    @Schema(description = "nome do usuário")
    public String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    @Schema(description = "grupo do usuário")
    public GroupEntity group;

    public UserEntity() {
    }

    public UserEntity(UUID userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
