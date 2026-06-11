package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.app.model.QuartoFamilia;

public class QuartoFamiliaTest {
  

        @Test
        void deveCalcularDiariaSemDesconto() {

            QuartoFamilia quarto = new QuartoFamilia(200, true, false, 4, 2);

            double resultado = quarto.calcularDiaria();

            assertEquals(480, resultado);

        }

        @Test
        void deveCalcularDiariaComDesconto() {

            QuartoFamilia quarto = new QuartoFamilia(200, true, false, 5, 2);

            double resultado = quarto.calcularDiaria();

            assertEquals(495, resultado);
            
        }


}
