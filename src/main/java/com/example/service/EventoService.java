package com.example.service;

import com.example.dto.EventoDTO;
import com.example.dto.EventoResponseDTO;

import java.util.List;

public interface EventoService {
    EventoResponseDTO criar(EventoDTO dto);
    List<EventoResponseDTO> listarTodos();
    EventoResponseDTO buscarPorId(Long id);
    EventoResponseDTO atualizar(Long id, EventoDTO dto);
    void remover(Long id);
}
