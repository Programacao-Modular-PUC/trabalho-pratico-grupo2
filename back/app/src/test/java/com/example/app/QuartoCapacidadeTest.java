package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.app.model.QuartoIndividual;
import com.example.app.model.QuartoDuplo;
import com.example.app.model.QuartoFamilia;
import com.example.app.model.TipoCama;

public class QuartoCapacidadeTest {

    @Test
    void individualDeveTerCapacidadeIgualAQuantidadeDeCamas() {
        QuartoIndividual quarto = new QuartoIndividual(150, false, false, 3);

        assertEquals(3, quarto.getCapacidadeMaxima());
    }

    @Test
    void duploSemBercoDeveTerCapacidadeParaDuasPessoas() {
        QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.CASAL, false, false);

        assertEquals(2, quarto.getCapacidadeMaxima());
    }

    @Test
    void duploComBercoDeveTerCapacidadeParaTresPessoas() {
        QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.QUEEN, true, true);

        assertEquals(3, quarto.getCapacidadeMaxima());
    }

    @Test
    void familiaDeveTerCapacidadeIgualAQuantidadeDeHospedesConfigurada() {
        QuartoFamilia quarto = new QuartoFamilia(200, true, false, 5, 2);

        assertEquals(5, quarto.getCapacidadeMaxima());
    }
}
