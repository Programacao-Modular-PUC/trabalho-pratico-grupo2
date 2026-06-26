package com.example.app.model.fidelidade;

import com.example.app.model.Cliente;

public class BeneficioFactory {

    public static BeneficioStrategy getStrategy(Cliente cliente){

        int hospedagens = cliente.getQuantidadeHospedagens();

        if(hospedagens >= 20)
            return new DiamanteStrategy();

        if(hospedagens >= 10)
            return new OuroStrategy();

        if(hospedagens >= 5)
            return new PrataStrategy();

        return new BronzeStrategy();
    }
}
