package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.app.model.QuartoIndividual;

public class QuartoIndividualTest {


        QuartoIndividual quarto = new QuartoIndividual(200, true, false, 3);

        @Test
        void calcularDiaria() {

            double resultado = quarto.calcularDiaria();
            
            assertEquals(300, resultado);

        }
    
}
