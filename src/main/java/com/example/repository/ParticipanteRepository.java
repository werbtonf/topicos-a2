package com.example.repository;

import com.example.model.Participante;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface ParticipanteRepository extends CrudRepository<Participante, Long> {
    Optional<Participante> findByEmail(String email);
}
