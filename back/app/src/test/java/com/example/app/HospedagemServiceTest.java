package com.example.app;

import com.example.app.exception.CapacidadeExcedidaException;
import com.example.app.exception.DataInvalidaException;
import com.example.app.exception.QuartoIndisponivelException;
import com.example.app.exception.RecursoNaoPermitidoException;
import com.example.app.model.*;
import com.example.app.repository.AluguelRepository;
import com.example.app.repository.ClienteRepository;
import com.example.app.repository.QuartoRepository;
import com.example.app.service.HospedagemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HospedagemServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AluguelRepository aluguelRepository;

    @Mock
    private QuartoRepository quartoRepository;

    @InjectMocks
    private HospedagemService hospedagemService;

    private Date dataEntrada;
    private Date dataSaida;

    @BeforeEach
    void setUp() {
        dataEntrada = new Date(System.currentTimeMillis() + 86_400_000L);
        dataSaida = new Date(System.currentTimeMillis() + 3 * 86_400_000L);
    }

    private QuartoDuplo criarQuartoDuplo(Long id, boolean disponivel, boolean possuiBerco, boolean solicitouBerco) {
        QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.QUEEN, possuiBerco, solicitouBerco);
        quarto.setId(id);
        quarto.setDisponivel(disponivel);
        return quarto;
    }

    @Test
    void registrarAluguel_deveLancarQuartoIndisponivel_quandoQuartoNaoDisponivel() {
        QuartoDuplo quarto = criarQuartoDuplo(1L, false, false, false);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        Aluguel aluguel = new Aluguel(dataEntrada, dataSaida, 2, 2, quarto, new Cliente());

        assertThrows(QuartoIndisponivelException.class, () -> hospedagemService.registrarAluguel(aluguel));
        verify(aluguelRepository, never()).save(any());
    }

    @Test
    void registrarAluguel_deveLancarDataInvalida_quandoDataSaidaNaoForPosteriorAEntrada() {
        QuartoDuplo quarto = criarQuartoDuplo(1L, true, false, false);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        Aluguel aluguel = new Aluguel(dataSaida, dataEntrada, 2, 2, quarto, new Cliente());

        assertThrows(DataInvalidaException.class, () -> hospedagemService.registrarAluguel(aluguel));
    }

    @Test
    void registrarAluguel_deveLancarCapacidadeExcedida_quandoHospedesExcedemCapacidadeDoQuarto() {
        QuartoDuplo quarto = criarQuartoDuplo(1L, true, false, false);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        Aluguel aluguel = new Aluguel(dataEntrada, dataSaida, 2, 5, quarto, new Cliente());

        assertThrows(CapacidadeExcedidaException.class, () -> hospedagemService.registrarAluguel(aluguel));
    }

    @Test
    void registrarAluguel_deveLancarRecursoNaoPermitido_quandoSolicitarBercoSemQuartoPossuir() {
        QuartoDuplo quarto = criarQuartoDuplo(1L, true, false, true);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        Aluguel aluguel = new Aluguel(dataEntrada, dataSaida, 2, 2, quarto, new Cliente());

        assertThrows(RecursoNaoPermitidoException.class, () -> hospedagemService.registrarAluguel(aluguel));
    }

    @Test
    void registrarAluguel_deveRegistrarComSucesso_eTornarQuartoIndisponivel() {
        QuartoDuplo quarto = criarQuartoDuplo(1L, true, true, true);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));
        when(aluguelRepository.save(any(Aluguel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Aluguel aluguel = new Aluguel(dataEntrada, dataSaida, 2, 2, quarto, new Cliente());

        Aluguel resultado = hospedagemService.registrarAluguel(aluguel);

        assertEquals(StatusAluguel.ATIVO, resultado.getStatus());
        assertFalse(quarto.isDisponivel());
        verify(quartoRepository).save(quarto);
        verify(aluguelRepository).save(aluguel);
    }

    @Test
    void cancelarAluguel_deveAlterarStatusELiberarQuarto() {
        QuartoDuplo quarto = criarQuartoDuplo(1L, false, false, false);
        Aluguel aluguel = new Aluguel(dataEntrada, dataSaida, 2, 2, quarto, new Cliente());
        aluguel.setId(10L);

        when(aluguelRepository.findById(10L)).thenReturn(Optional.of(aluguel));
        when(aluguelRepository.save(any(Aluguel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Aluguel resultado = hospedagemService.cancelarAluguel(10L);

        assertEquals(StatusAluguel.CANCELADO, resultado.getStatus());
        assertTrue(quarto.isDisponivel());
        verify(quartoRepository).save(quarto);
    }

    @Test
    void cancelarAluguel_deveLancarRecursoNaoPermitido_quandoAluguelJaCancelado() {
        QuartoDuplo quarto = criarQuartoDuplo(1L, true, false, false);
        Aluguel aluguel = new Aluguel(dataEntrada, dataSaida, 2, 2, quarto, new Cliente());
        aluguel.setId(10L);
        aluguel.setStatus(StatusAluguel.CANCELADO);

        when(aluguelRepository.findById(10L)).thenReturn(Optional.of(aluguel));

        assertThrows(RecursoNaoPermitidoException.class, () -> hospedagemService.cancelarAluguel(10L));
        verify(aluguelRepository, never()).save(any());
    }

    @Test
    void cancelarAluguel_deveLancarNoSuchElement_quandoAluguelNaoEncontrado() {
        when(aluguelRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> hospedagemService.cancelarAluguel(99L));
    }

    @Test
    void buscarHistoricoPorCliente_deveLancarNoSuchElement_quandoClienteNaoExiste() {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> hospedagemService.buscarHistoricoPorCliente(1L));
    }

    @Test
    void buscarHistoricoPorCliente_deveRetornarListaDeAlugueisDoCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(5L);

        QuartoDuplo quarto = criarQuartoDuplo(1L, false, false, false);
        Aluguel aluguel = new Aluguel(dataEntrada, dataSaida, 2, 2, quarto, cliente);

        when(clienteRepository.existsById(5L)).thenReturn(true);
        when(aluguelRepository.findByClienteIdOrderByDataEntradaDesc(5L)).thenReturn(List.of(aluguel));

        List<Aluguel> historico = hospedagemService.buscarHistoricoPorCliente(5L);

        assertEquals(1, historico.size());
        assertEquals(cliente, historico.get(0).getCliente());
    }
}
