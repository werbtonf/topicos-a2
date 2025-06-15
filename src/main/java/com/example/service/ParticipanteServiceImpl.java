package com.example.service;

import com.example.dto.ParticipanteDTO;
import com.example.dto.ParticipanteResponseDTO;
import com.example.model.Participante;
import com.example.repository.ParticipanteRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class ParticipanteServiceImpl implements ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public ParticipanteResponseDTO criar(ParticipanteDTO dto) {
        Optional<Participante> existente = participanteRepository.findByEmail(dto.email());
        if (existente.isPresent()) {
            throw new IllegalStateException("E-mail já cadastrado.");
        }

        Participante novo = new Participante();
        novo.setNome(dto.nome());
        novo.setEmail(dto.email());
        novo.setTelefone(dto.telefone());

        Participante salvo = participanteRepository.save(novo);
        return ParticipanteResponseDTO.valueOf(salvo);
    }

    public List<ParticipanteResponseDTO> listarTodos() {
        List<Participante> participantes = (List<Participante>) participanteRepository.findAll();
        return participantes.stream()
                .map(ParticipanteResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    public ParticipanteResponseDTO buscarPorId(Long id) {
        return participanteRepository.findById(id)
                .map(ParticipanteResponseDTO::valueOf)
                .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado"));
    }

    public ParticipanteResponseDTO atualizar(Long id, ParticipanteDTO dto) {
        Participante participante = participanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado"));

        participante.setNome(dto.nome());
        participante.setEmail(dto.email());
        participante.setTelefone(dto.telefone());

        Participante atualizado = participanteRepository.update(participante);
        return ParticipanteResponseDTO.valueOf(atualizado);
    }

    public void remover(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new IllegalArgumentException("Participante não encontrado");
        }
        participanteRepository.deleteById(id);
    }
}
