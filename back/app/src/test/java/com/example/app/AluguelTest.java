package com.example.app;

import com.example.app.model.Aluguel;
import com.example.app.model.Cliente;
import com.example.app.model.Pagamento;
import com.example.app.model.QuartoIndividual;
import com.example.app.model.StatusAluguel;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AluguelTest {

    @Test
    public void testPagamentoInicialDeveSerPendente() {
        Aluguel aluguel = new Aluguel();
        Pagamento pag = new Pagamento();
        pag.setStatus("PENDENTE");
        aluguel.setPagamento(pag);

        assertEquals("PENDENTE", aluguel.getPagamento().getStatus());
    }

    @Test
    void deveCalcularValorFinalConsiderandoArEHidroENumeroDeDiarias() {
        QuartoIndividual quarto = new QuartoIndividual(200, true, true, 1);

        Aluguel aluguel = new Aluguel(
                new Date(),
                new Date(),
                3,
                1,
                quarto,
                new Cliente()
        );

        assertEquals(840, aluguel.calcularValorFinal());
    }

    @Test
    void aluguelDeveSerCriadoComStatusAtivo() {
        QuartoIndividual quarto = new QuartoIndividual(200, false, false, 1);

        Aluguel aluguel = new Aluguel(
                new Date(),
                new Date(),
                1,
                1,
                quarto,
                new Cliente()
        );

        assertEquals(StatusAluguel.ATIVO, aluguel.getStatus());
    }
}