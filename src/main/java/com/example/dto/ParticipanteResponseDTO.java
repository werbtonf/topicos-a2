package com.example.dto;

import com.example.model.Participante;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record ParticipanteResponseDTO(
    Long id,
    String nome,
    String email,
    String telefone
) {
    public static ParticipanteResponseDTO valueOf(Participante participante) {
        return new ParticipanteResponseDTO(
            participante.getId(),
            participante.getNome(),
            participante.getEmail(),
            participante.getTelefone()
        );
    }
}
