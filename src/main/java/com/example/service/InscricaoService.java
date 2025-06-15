package com.example.service;

import com.example.dto.InscricaoDTO;
import com.example.dto.InscricaoResponseDTO;

import java.util.List;

public interface InscricaoService {
    InscricaoResponseDTO criar(InscricaoDTO dto);
    List<InscricaoResponseDTO> listarTodas();
    InscricaoResponseDTO buscarPorId(Long id);
    void cancelar(Long id);
}
