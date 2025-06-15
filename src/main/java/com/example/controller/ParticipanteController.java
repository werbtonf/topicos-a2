package com.example.controller;

import com.example.dto.ParticipanteDTO;
import com.example.dto.ParticipanteResponseDTO;
import com.example.service.ParticipanteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@Tag(name = "Participantes")
@Controller("/participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastra um novo participante")
    public HttpResponse<?> criar(@Body @Valid ParticipanteDTO dto) {
        try {
            ParticipanteResponseDTO response = participanteService.criar(dto);
            return HttpResponse.created(response);
        } catch (IllegalStateException e) {
            return HttpResponse.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao criar participante: " + e.getMessage());
        }
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista todos os participantes")
    public HttpResponse<?> listarTodos() {
        try {
            List<ParticipanteResponseDTO> lista = participanteService.listarTodos();
            return HttpResponse.ok(lista);
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao listar participantes: " + e.getMessage());
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca participante por ID")
    public HttpResponse<?> buscarPorId(@PathVariable Long id) {
        try {
            ParticipanteResponseDTO dto = participanteService.buscarPorId(id);
            return HttpResponse.ok(dto);
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao buscar participante: " + e.getMessage());
        }
    }

    @Put("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Atualiza um participante existente")
    public HttpResponse<?> atualizar(@PathVariable Long id, @Body @Valid ParticipanteDTO dto) {
        try {
            ParticipanteResponseDTO atualizado = participanteService.atualizar(id, dto);
            return HttpResponse.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao atualizar participante: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    @Operation(summary = "Remove um participante por ID")
    public HttpResponse<?> remover(@PathVariable Long id) {
        try {
            participanteService.remover(id);
            return HttpResponse.noContent();
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao remover participante: " + e.getMessage());
        }
    }
}
