package br.com.lgr.java21.domain;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction")
@Schema(description = "Entidade de transação")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id da transação")
    public UUID id;

    @Column(name = "ds_transation")
    public String dsTransaction;

    public String username;

    public ZonedDateTime dateTime;

}
