package com.example.service;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dto.EventoDTO;
import com.example.dto.EventoResponseDTO;
import com.example.model.Evento;
import com.example.repository.EventoRepository;

@Singleton
public class EventoServiceImpl implements EventoService {
    
    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EventoResponseDTO criar(EventoDTO dto) {
        Optional<Evento> existente = eventoRepository.findByNomeAndDataAndLocal(dto.nome(), dto.data(), dto.local());
        if (existente.isPresent()) {
            throw new IllegalStateException("Evento já existe com mesmo nome, data e local.");
        }

        Evento novo = new Evento();
        novo.setNome(dto.nome());
        novo.setData(dto.data());
        novo.setLocal(dto.local());
        novo.setDescricao(dto.descricao());

        Evento salvo = eventoRepository.save(novo);
        return EventoResponseDTO.valueOf(salvo);
    }

    public List<EventoResponseDTO> listarTodos() {
        List<Evento> eventos = (List<Evento>) eventoRepository.findAll();
        return eventos.stream().map(EventoResponseDTO::valueOf).collect(Collectors.toList());
    }

    public EventoResponseDTO buscarPorId(Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.map(EventoResponseDTO::valueOf).orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
    }

    public EventoResponseDTO atualizar(Long id, EventoDTO dto) {
        Optional<Evento> optEvento = eventoRepository.findById(id);
        Evento evento = optEvento.orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        evento.setNome(dto.nome());
        evento.setData(dto.data());
        evento.setLocal(dto.local());
        evento.setDescricao(dto.descricao());

        Evento atualizado = eventoRepository.update(evento);
        return EventoResponseDTO.valueOf(atualizado);
    }

    public void remover(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException("Evento não encontrado");
        }

        eventoRepository.deleteById(id);
    }
}
