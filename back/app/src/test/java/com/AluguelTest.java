package com.example.app;

import com.example.app.model.Aluguel;
import com.example.app.model.Pagamento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AluguelTest {

    @Test
    public void testPagamentoInicialDeveSerPendente() {
        Aluguel aluguel = new Aluguel();
        Pagamento pag = new Pagamento();
        pag.setStatus("PENDENTE");
        aluguel.setPagamento(pag);

        assertEquals("PENDENTE", aluguel.getPagamento().getStatus(), "O pagamento deve iniciar como PENDENTE");
    }
}