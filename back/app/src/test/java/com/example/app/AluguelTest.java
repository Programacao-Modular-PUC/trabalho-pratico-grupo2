package com.example.app;

import com.example.app.model.Aluguel;
import com.example.app.model.Cliente;
import com.example.app.model.Pagamento;
import com.example.app.model.QuartoIndividual;
import com.example.app.model.ServicoAdicional;
import com.example.app.model.StatusAluguel;
import com.example.app.model.TipoCobrancaServico;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

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

    @Test
    void deveCalcularValorFinalComServicosAdicionaisCombinaveis() {
        QuartoIndividual quarto = new QuartoIndividual(100, false, false, 2);

        Aluguel aluguel = new Aluguel(
                new Date(),
                new Date(),
                2,
                3,
                quarto,
                new Cliente()
        );

        ServicoAdicional cafe = new ServicoAdicional(
                "Cafe da manha",
                "Buffet diario",
                35,
                TipoCobrancaServico.POR_DIARIA
        );
        ServicoAdicional traslado = new ServicoAdicional(
                "Traslado aeroporto-hospedagem",
                "Transporte unico",
                100,
                TipoCobrancaServico.UNICA
        );
        ServicoAdicional passeio = new ServicoAdicional(
                "Passeios turisticos",
                "Passeio por hospede",
                120,
                TipoCobrancaServico.POR_HOSPEDE
        );

        aluguel.setServicosAdicionais(List.of(cafe, traslado, passeio));

        assertEquals(830, aluguel.calcularValorFinal());
    }
}
