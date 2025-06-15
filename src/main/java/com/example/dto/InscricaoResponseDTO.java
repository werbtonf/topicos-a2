package com.example.dto;

import com.example.model.Inscricao;
import com.example.model.StatusInscricao;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
@Introspected
public record InscricaoResponseDTO(
    Long id,
    Long eventoId,
    String eventoNome,
    Long participanteId,
    String participanteNome,
    StatusInscricao status,
    LocalDateTime dataInscricao
) {
    public static InscricaoResponseDTO valueOf(Inscricao inscricao) {
        return new InscricaoResponseDTO(
            inscricao.getId(),
            inscricao.getEvento().getId(),
            inscricao.getEvento().getNome(),
            inscricao.getParticipante().getId(),
            inscricao.getParticipante().getNome(),
            inscricao.getStatus(),
            inscricao.getDataInscricao()
        );
    }
}
