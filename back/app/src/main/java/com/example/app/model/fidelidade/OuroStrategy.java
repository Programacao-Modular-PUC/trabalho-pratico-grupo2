package com.example.app.model.fidelidade;

import com.example.app.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class OuroStrategy implements BeneficioStrategy {

    @Override
    public List<Beneficio> obterBeneficios(Cliente cliente) {

        List<Beneficio> lista = new ArrayList<>();

        lista.add(new Beneficio("10% de desconto"));
        lista.add(new Beneficio("Check-out estendido"));

        return lista;
    }
}