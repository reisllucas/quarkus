package br.com.lgr.java21.domain.record;

import java.time.ZonedDateTime;

public record TransactionMessageRecord(String username,
                                       ZonedDateTime dateTime,
                                       TransactionRecord transction) {
}
