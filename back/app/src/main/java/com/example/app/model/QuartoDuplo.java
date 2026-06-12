package com.example.app.model;

import jakarta.persistence.*;

@Entity
public class QuartoDuplo extends Quarto {

    @Enumerated(EnumType.STRING) 
    private TipoCama tipoCama;
    private boolean possuiBerco;
    private boolean solicitouBerco; 

    public QuartoDuplo() {}

    public QuartoDuplo(double valorBase, boolean possuiAR, boolean possuiHidro, TipoCama tipoCama, boolean possuiBerco, boolean solicitouBerco) {
        super(valorBase, possuiAR, possuiHidro);
        this.tipoCama = tipoCama;
        this.possuiBerco = possuiBerco;
        this.solicitouBerco = solicitouBerco;
    }

    @Override
    public double calcularDiaria() {
        double total = valorBase;

        if (tipoCama == TipoCama.QUEEN) {
            total += 80.0;
        } else if (tipoCama == TipoCama.KING) {
            total += 150.0;
        }

        if (possuiBerco && solicitouBerco) {
            total += 40.0;
        }

        return total;
    }

    public TipoCama getTipoCama() { return tipoCama; }
    public void setTipoCama(TipoCama tipoCama) { this.tipoCama = tipoCama; }
    public boolean isPossuiBerco() { return possuiBerco; }
    public void setPossuiBerco(boolean possuiBerco) { this.possuiBerco = possuiBerco; }
    public boolean isSolicitouBerco() { return solicitouBerco; }
    public void setSolicitouBerco(boolean solicitouBerco) { this.solicitouBerco = solicitouBerco; }
}