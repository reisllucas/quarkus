package br.com.lgr.java21.domain.record;

import java.time.ZonedDateTime;
import java.util.UUID;

public record LogEntityRecord(UUID id,
                              String dsLog,
                              ZonedDateTime dateTime) {
}
