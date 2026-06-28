package com.example.app.model.fidelidade;

import com.example.app.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class PrataStrategy implements BeneficioStrategy {

    @Override
    public List<Beneficio> obterBeneficios(Cliente cliente) {

        List<Beneficio> lista = new ArrayList<>();

        lista.add(new Beneficio("5% de desconto"));

        return lista;
    }
}
