package com.example.dto;

import java.time.LocalDateTime;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record EventoDTO(
        @NotBlank String nome,
        @NotNull LocalDateTime data,
        @NotBlank String local,
        String descricao) {
}