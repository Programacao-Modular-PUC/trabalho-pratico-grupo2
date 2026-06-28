package com.example.app.service;

import com.example.app.model.Cliente;
import com.example.app.model.fidelidade.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FidelidadeService {

    public List<Beneficio> listarBeneficios(Cliente cliente){

        BeneficioStrategy strategy =
                BeneficioFactory.getStrategy(cliente);

        return strategy.obterBeneficios(cliente);
    }

}
