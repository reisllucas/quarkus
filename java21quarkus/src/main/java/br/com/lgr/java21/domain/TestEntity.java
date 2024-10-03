package br.com.lgr.java21.domain;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;


@Entity
@Table(name = "tb_test")
@Schema(description = "Entidade completa do teste")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id de teste")
    public UUID id;
    @Schema(description = "descrição do teste")
    public String description;

}
