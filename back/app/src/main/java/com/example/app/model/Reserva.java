package com.example.app.model;
import java.time.LocalDate;
public class Reserva {
    private int id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String status;
    public Reserva() {
    }


    public Reserva(int id, LocalDate dataEntrada, LocalDate dataSaida, String status) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }


    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    


    public void  registrarDataEntrada() {
        
    }
    public void  registrarDataSaida() {
        
    }
    public void reservarQuarto() {
        
    }   
    public void manterStatus() {
        
    }
}
