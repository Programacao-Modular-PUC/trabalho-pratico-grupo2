package com.example.app;

import com.example.app.exception.RecursoNaoPermitidoException;
import com.example.app.model.Quarto;
import com.example.app.repository.QuartoRepository;
import com.example.app.service.QuartoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuartoServiceTest {

    @Mock
    private QuartoRepository quartoRepository;

    @InjectMocks
    private QuartoService quartoService;

    @Test
    void listarTodosDeveRetornarTodosOsQuartos() {
        List<Quarto> quartos = List.of(mock(Quarto.class), mock(Quarto.class));

        when(quartoRepository.findAll()).thenReturn(quartos);

        List<Quarto> resultado = quartoService.listarTodos();

        assertEquals(2, resultado.size());
        verify(quartoRepository).findAll();
    }

    @Test
    void listarDisponiveisDeveRetornarQuartosDisponiveis() {
        List<Quarto> quartos = List.of(mock(Quarto.class));

        when(quartoRepository.findByDisponivelTrue()).thenReturn(quartos);

        List<Quarto> resultado = quartoService.listarDisponiveis();

        assertEquals(1, resultado.size());
        verify(quartoRepository).findByDisponivelTrue();
    }

    @Test
    void buscarPorTipoIndividualDeveRetornarQuartosIndividuais() {
        List<Quarto> quartos = List.of(mock(Quarto.class));

        when(quartoRepository.findAllIndividuais()).thenReturn(quartos);

        List<Quarto> resultado = quartoService.buscarPorTipo("individual");

        assertEquals(1, resultado.size());
        verify(quartoRepository).findAllIndividuais();
    }

    @Test
    void buscarPorTipoCasalDeveRetornarQuartosDuplos() {
        List<Quarto> quartos = List.of(mock(Quarto.class));

        when(quartoRepository.findAllDuplos()).thenReturn(quartos);

        List<Quarto> resultado = quartoService.buscarPorTipo("casal");

        assertEquals(1, resultado.size());
        verify(quartoRepository).findAllDuplos();
    }

    @Test
    void buscarPorTipoNuloDeveLancarExcecao() {
        assertThrows(
                RecursoNaoPermitidoException.class,
                () -> quartoService.buscarPorTipo(null)
        );
    }

    @Test
    void buscarPorTipoVazioDeveLancarExcecao() {
        assertThrows(
                RecursoNaoPermitidoException.class,
                () -> quartoService.buscarPorTipo("")
        );
    }

    @Test
    void buscarPorTipoInvalidoDeveLancarExcecao() {
        assertThrows(
                RecursoNaoPermitidoException.class,
                () -> quartoService.buscarPorTipo("luxo")
        );
    }
}