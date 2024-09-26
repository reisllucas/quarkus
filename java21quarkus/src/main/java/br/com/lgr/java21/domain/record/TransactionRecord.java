package br.com.lgr.java21.domain.record;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Informações básicas da transação")
public record TransactionRecord(@Schema(description = "descrição da transação")
                                String dsTransaction) {
}
