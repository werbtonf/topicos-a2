package com.example.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.model.Evento;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {
    Optional<Evento> findByNomeAndDataAndLocal(String nome, LocalDateTime data, String local);
}
