package com.example.service;

import com.example.dto.InscricaoDTO;
import com.example.dto.InscricaoResponseDTO;
import com.example.model.Evento;
import com.example.model.Inscricao;
import com.example.model.Participante;
import com.example.model.StatusInscricao;
import com.example.repository.EventoRepository;
import com.example.repository.InscricaoRepository;
import com.example.repository.ParticipanteRepository;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class InscricaoServiceImpl implements InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;

    public InscricaoServiceImpl(
        InscricaoRepository inscricaoRepository,
        EventoRepository eventoRepository,
        ParticipanteRepository participanteRepository
    ) {
        this.inscricaoRepository = inscricaoRepository;
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
    }

    @Override
    public InscricaoResponseDTO criar(InscricaoDTO dto) {
        Optional<Inscricao> existente = inscricaoRepository.findByEventoIdAndParticipanteId(dto.eventoId(), dto.participanteId());
        if (existente.isPresent()) {
            throw new IllegalStateException("Participante já inscrito neste evento.");
        }

        Evento evento = eventoRepository.findById(dto.eventoId()).orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
        Participante participante = participanteRepository.findById(dto.participanteId()).orElseThrow(() -> new IllegalArgumentException("Participante não encontrado"));

        Inscricao nova = new Inscricao();
        nova.setEvento(evento);
        nova.setParticipante(participante);
        nova.setStatus(StatusInscricao.CONFIRMADA);
        nova.setDataInscricao(LocalDateTime.now());

        Inscricao salva = inscricaoRepository.save(nova);
        return InscricaoResponseDTO.valueOf(salva);
    }

    @Override
    public List<InscricaoResponseDTO> listarTodas() {
        List<Inscricao> inscricoes = (List<Inscricao>) inscricaoRepository.findAll();
        return inscricoes.stream().map(InscricaoResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public InscricaoResponseDTO buscarPorId(Long id) {
        return inscricaoRepository.findById(id).map(InscricaoResponseDTO::valueOf).orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada"));
    }

    @Override
    public void cancelar(Long id) {
        Inscricao inscricao = inscricaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada"));
        inscricao.setStatus(StatusInscricao.CANCELADA);
        inscricaoRepository.update(inscricao);
    }
}
