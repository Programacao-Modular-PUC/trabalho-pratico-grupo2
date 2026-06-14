package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.app.exception.RecursoNaoPermitidoException;
import com.example.app.model.QuartoDuplo;
import com.example.app.model.TipoCama;

public class QuartoDuploBercoTest {

    @Test
    void deveLancarExcecaoQuandoSolicitarBercoEQuartoNaoPossuiBerco() {
        QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.CASAL, false, true);

        assertThrows(RecursoNaoPermitidoException.class, quarto::validarSolicitacaoBerco);
    }

    @Test
    void naoDeveLancarExcecaoQuandoSolicitarBercoEQuartoPossuiBerco() {
        QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.CASAL, true, true);

        assertDoesNotThrow(quarto::validarSolicitacaoBerco);
    }

    @Test
    void naoDeveLancarExcecaoQuandoNaoSolicitarBerco() {
        QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.CASAL, false, false);

        assertDoesNotThrow(quarto::validarSolicitacaoBerco);
    }
}
