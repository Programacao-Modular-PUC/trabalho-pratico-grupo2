package com.example.app.model;

public class Quarto {
    private int id; 
    private String tipo;
    private double valorBase;
    private boolean disponivel;
    private boolean adicional;
    public Quarto(int id, String tipo, double valorBase, boolean disponivel, boolean adicional) {
        this.id = id;
        this.tipo = tipo;
        this.valorBase = valorBase;
        this.disponivel = disponivel;
        this.adicional = adicional;
    }   
    public void setId(int id) {
        this.id = id;
    }   
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }
    public int getId() {
        return id;
    }   
    public String getTipo() {
        return tipo;
    }
    public double getValorBase() {
        return valorBase;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public boolean isAdicional() {
        return adicional;
    }

    public void definirTipo(){

    }
    public void definirValorDiaria(){

    }  
    public void controlarDisponibilidade(){

    }   
    public void indicarAdicional(){

    }    
}
