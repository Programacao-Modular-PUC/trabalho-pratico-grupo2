package com.example.app.model.fidelidade;

import com.example.app.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class DiamanteStrategy implements BeneficioStrategy {

    @Override
    public List<Beneficio> obterBeneficios(Cliente cliente) {

        List<Beneficio> lista = new ArrayList<>();

        lista.add(new Beneficio("20% de desconto"));
        lista.add(new Beneficio("Upgrade de quarto"));
        lista.add(new Beneficio("Check-out estendido"));
        lista.add(new Beneficio("Diária gratuita"));

        return lista;
    }
}
