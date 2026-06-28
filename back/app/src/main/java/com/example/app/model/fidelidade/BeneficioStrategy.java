package com.example.app.model.fidelidade;

import com.example.app.model.Cliente;
import java.util.List;

public interface BeneficioStrategy {

    List<Beneficio> obterBeneficios(Cliente cliente);

}
