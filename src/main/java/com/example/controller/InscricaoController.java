package com.example.controller;

import com.example.dto.InscricaoDTO;
import com.example.dto.InscricaoResponseDTO;
import com.example.service.InscricaoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@Tag(name = "Inscrições")
@Controller("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Realiza uma nova inscrição")
    public HttpResponse<?> criar(@Body @Valid InscricaoDTO dto) {
        try {
            InscricaoResponseDTO response = inscricaoService.criar(dto);
            return HttpResponse.created(response);
        } catch (IllegalStateException e) {
            return HttpResponse.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao realizar inscrição: " + e.getMessage());
        }
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista todas as inscrições")
    public HttpResponse<?> listarTodas() {
        try {
            List<InscricaoResponseDTO> lista = inscricaoService.listarTodas();
            return HttpResponse.ok(lista);
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao listar inscrições: " + e.getMessage());
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca inscrição por ID")
    public HttpResponse<?> buscarPorId(@PathVariable Long id) {
        try {
            InscricaoResponseDTO dto = inscricaoService.buscarPorId(id);
            return HttpResponse.ok(dto);
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao buscar inscrição: " + e.getMessage());
        }
    }

    @Put("/{id}/cancelar")
    @Operation(summary = "Cancela uma inscrição existente")
    public HttpResponse<?> cancelar(@PathVariable Long id) {
        try {
            inscricaoService.cancelar(id);
            return HttpResponse.noContent();
        } catch (IllegalArgumentException e) {
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.serverError("Erro ao cancelar inscrição: " + e.getMessage());
        }
    }
}
