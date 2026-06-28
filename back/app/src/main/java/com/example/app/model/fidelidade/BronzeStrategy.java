package com.example.app.model.fidelidade;

import com.example.app.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class BronzeStrategy implements BeneficioStrategy {

    @Override
    public List<Beneficio> obterBeneficios(Cliente cliente) {

        return new ArrayList<>();

    }
}
