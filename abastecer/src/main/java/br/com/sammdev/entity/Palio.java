package br.com.sammdev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Palio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacidadeTanqueLitros;
    private int totalTracos;
    private int kmAtual;
    private Double tracosAtuais;
    private Double consumoPainel;
    
    protected Palio() {
    }

    public Palio(int kmAtual, Double tracosAtuais, Double consumoPainel) {
        this.capacidadeTanqueLitros = 48;
        this.totalTracos = 16;
        this.kmAtual = kmAtual;
        this.tracosAtuais = tracosAtuais;
        this.consumoPainel = consumoPainel;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCapacidadeTanqueLitros() {
        return capacidadeTanqueLitros;
    }
    public void setCapacidadeTanqueLitros(int capacidadeTanqueLitros) {
        this.capacidadeTanqueLitros = capacidadeTanqueLitros;
    }
    public int getTotalTracos() {
        return totalTracos;
    }
    public void setTotalTracos(int totalTracos) {
        this.totalTracos = totalTracos;
    }
    public int getKmAtual() {
        return kmAtual;
    }
    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }
    public Double getTracosAtuais() {
        return tracosAtuais;
    }
    public void setTracosAtuais(Double tracosAtuais) {
        this.tracosAtuais = tracosAtuais;
    }
    public Double getConsumoPainel() {
        return consumoPainel;
    }
    public void setConsumoPainel(Double consumoPainel) {
        this.consumoPainel = consumoPainel;
    }
}
