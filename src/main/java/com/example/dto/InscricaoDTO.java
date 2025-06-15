package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record InscricaoDTO(
    @NotNull Long eventoId,
    @NotNull Long participanteId
) {}
