package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@Introspected
public record ParticipanteDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String telefone) {
}
