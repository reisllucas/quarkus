package br.com.lgr.java21.domain.record;

import jakarta.validation.constraints.NotBlank;

public record LogRecord(
        @NotBlank
        String dsLog) {
}
