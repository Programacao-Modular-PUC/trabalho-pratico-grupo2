package com.example.app.model;

import jakarta.persistence.*;

@Entity
public class QuartoDuplo extends Quarto {

    @Enumerated(EnumType.STRING)
    private TipoCama tipoCama;

    private boolean possuiBerco;

    public QuartoDuplo() {
    }

    public QuartoDuplo(double valorBase,
                       boolean possuiAR,
                       boolean possuiHidro,
                       TipoCama tipoCama,
                       boolean possuiBerco) {

        super(valorBase, possuiAR, possuiHidro);

        this.tipoCama = tipoCama;
        this.possuiBerco = possuiBerco;
    }

    @Override
    public double calcularDiaria() {

        double total = valorBase;

        if (tipoCama == TipoCama.QUEEN) {
            total += 80;
        }

        if (tipoCama == TipoCama.KING) {
            total += 150;
        }

        if (possuiBerco) {
            total += 40;
        }

        return total;
    }

    public TipoCama getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(TipoCama tipoCama) {
        this.tipoCama = tipoCama;
    }

    public boolean isPossuiBerco() {
        return possuiBerco;
    }

    public void setPossuiBerco(boolean possuiBerco) {
        this.possuiBerco = possuiBerco;
    }
}