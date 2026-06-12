package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.app.model.QuartoDuplo;
import com.example.app.model.TipoCama;

public class QuartoDuploTest {

        @Test
        void deveCalcularDiariaQueenSemBerco() {
                QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.QUEEN, false, false);

                double resultado = quarto.calcularDiaria();
                
                assertEquals(280, resultado);
        }

        @Test
        void deveCalcularDiariaQueenComBerco() {
          
                QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.QUEEN, true, true);

                double resultado = quarto.calcularDiaria();
                
                assertEquals(320, resultado);
        }

        @Test
        void deveCalcularDiariaKingSemBerco() {
              
                QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.KING, false, false);

                double resultado = quarto.calcularDiaria();

                assertEquals(350, resultado);
        }

        @Test
        void deveCalcularDiariaKingComBerco() {
               
                QuartoDuplo quarto = new QuartoDuplo(200, true, false, TipoCama.KING, true, true);

                double resultado = quarto.calcularDiaria();

                assertEquals(390, resultado);
        }
}