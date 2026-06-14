package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.app.model.Aluguel;
import com.example.app.model.Cliente;
import com.example.app.model.QuartoIndividual;
import com.example.app.model.StatusAluguel;

import java.util.Date;

public class AluguelTest {

    @Test
    void deveCalcularValorFinalConsiderandoArEHidroENumeroDeDiarias() {
        QuartoIndividual quarto = new QuartoIndividual(200, true, true, 1);

        Aluguel aluguel = new Aluguel(new Date(), new Date(), 3, 1, quarto, new Cliente());

        double resultado = aluguel.calcularValorFinal();

        assertEquals(840, resultado);
    }

    @Test
    void aluguelDeveSerCriadoComStatusAtivo() {
        QuartoIndividual quarto = new QuartoIndividual(200, false, false, 1);

        Aluguel aluguel = new Aluguel(new Date(), new Date(), 1, 1, quarto, new Cliente());

        assertEquals(StatusAluguel.ATIVO, aluguel.getStatus());
    }
}
