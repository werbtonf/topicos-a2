package com.example.repository;

import com.example.model.Inscricao;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface InscricaoRepository extends CrudRepository<Inscricao, Long> {
    Optional<Inscricao> findByEventoIdAndParticipanteId(Long eventoId, Long participanteId);
}
