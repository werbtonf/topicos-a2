package com.example.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import com.example.dto.EventoDTO;
import com.example.dto.EventoResponseDTO;
import com.example.service.EventoService;

@Tag(name = "Eventos")
@Controller("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastra um novo evento")
    public HttpResponse<?> criar(@Body @Valid EventoDTO dto) {
        try {
            EventoResponseDTO response = eventoService.criar(dto);
            return HttpResponse.created(response);
        } catch (IllegalStateException e) {
            return HttpResponse.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao criar evento: " + e.getMessage());
        }
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista todos os eventos")
    public HttpResponse<?> listarTodos() {
        try {
            List<EventoResponseDTO> lista = eventoService.listarTodos();
            return HttpResponse.ok(lista);
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao listar eventos: " + e.getMessage());
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca evento por ID")
    public HttpResponse<?> buscarPorId(@PathVariable Long id) {
        try {
            EventoResponseDTO dto = eventoService.buscarPorId(id);
            return HttpResponse.ok(dto);
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao buscar evento: " + e.getMessage());
        }
    }

    @Put("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Atualiza um evento existente")
    public HttpResponse<?> atualizar(@PathVariable Long id, @Body @Valid EventoDTO dto) {
        try {
            EventoResponseDTO atualizado = eventoService.atualizar(id, dto);
            return HttpResponse.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao atualizar evento: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    @Operation(summary = "Remove um evento por ID")
    public HttpResponse<?> remover(@PathVariable Long id) {
        try {
            eventoService.remover(id);
            return HttpResponse.noContent();
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao remover evento: " + e.getMessage());
        }
    }
}
