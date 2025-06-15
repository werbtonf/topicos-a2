package com.example.dto;

import java.time.LocalDateTime;

import com.example.model.Evento;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record EventoResponseDTO(
        Long id,
        String nome,
        LocalDateTime data,
        String local,
        String descricao) {
    public static EventoResponseDTO valueOf(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getData(),
                evento.getLocal(),
                evento.getDescricao());
    }
}