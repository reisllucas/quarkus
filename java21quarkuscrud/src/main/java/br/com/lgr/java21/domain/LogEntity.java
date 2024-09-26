package br.com.lgr.java21.domain;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_log")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id do log")
    public UUID id;

    public String dsLog;

    public ZonedDateTime dateTime;

}
