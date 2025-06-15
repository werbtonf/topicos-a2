package com.example.service;

import com.example.dto.ParticipanteDTO;
import com.example.dto.ParticipanteResponseDTO;

import java.util.List;

public interface ParticipanteService {
    ParticipanteResponseDTO criar(ParticipanteDTO dto);
    List<ParticipanteResponseDTO> listarTodos();
    ParticipanteResponseDTO buscarPorId(Long id);
    ParticipanteResponseDTO atualizar(Long id, ParticipanteDTO dto);
    void remover(Long id);
}
