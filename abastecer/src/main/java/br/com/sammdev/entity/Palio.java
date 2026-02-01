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
    // Conversa com o chat ele me disse que não é usual deixar as constantes como atributos
    //private int capacidadeTanqueLitros;
    //private int totalTracos;
    private int kmAtual;
    private Double tracosAtuais;
    private Double consumoPainelAtual;
    
    protected Palio() {
    }

    public Palio(int kmAtual, Double tracosAtuais, Double consumoPainelAtual) {
        //this.capacidadeTanqueLitros = 48;
        //this.totalTracos = 16;
        this.kmAtual = kmAtual;
        this.tracosAtuais = tracosAtuais;
        this.consumoPainelAtual = consumoPainelAtual;
    }

    public Long getId() {
        return id;
    }
    /*public int getCapacidadeTanqueLitros() {
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
    }*/
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
    public Double getConsumoPainelAtual() {
        return consumoPainelAtual;
    }
    public void setConsumoPainelAtual(Double consumoPainelAtual) {
        this.consumoPainelAtual = consumoPainelAtual;
    }
}
