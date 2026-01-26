package br.com.sammdev.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Abastecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumn(name = "posto_id", nullable = false)
    private Posto posto;
    @ManyToOne
    @JoinColumn(name = "palio_id", nullable = false)
    private Palio palio;
    private Double precoLitro;
    private Double litrosAbastecidos;
    private Double valorTotal;
    private int kmNoMomento;
    private Double tracosNoMomento;
    private Double consumoPainelMomento;

    protected Abastecimento() {
    }

    public Abastecimento(LocalDateTime dataHora, Posto posto, Palio palio, Double precoLitro, Double litrosAbastecidos, Double valorTotal, int kmNoMomento, Double tracosNoMomento, Double consumoPainelMomento) {
        this.dataHora = dataHora;
        this.posto = posto;
        this.palio = palio;
        this.precoLitro = precoLitro;
        this.litrosAbastecidos = litrosAbastecidos;
        this.valorTotal = valorTotal;
        this.kmNoMomento = kmNoMomento;
        this.tracosNoMomento = tracosNoMomento;
        this.consumoPainelMomento = consumoPainelMomento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    public Palio getPalio() {
        return palio;
    }

    public void setPalio(Palio palio) {
        this.palio = palio;
    }

    public Double getPrecoLitro() {
        return precoLitro;
    }

    public void setPrecoLitro(Double precoLitro) {
        this.precoLitro = precoLitro;
    }

    public Double getLitrosAbastecidos() {
        return litrosAbastecidos;
    }

    public void setLitrosAbastecidos(Double litrosAbastecidos) {
        this.litrosAbastecidos = litrosAbastecidos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getKmNoMomento() {
        return kmNoMomento;
    }

    public void setKmNoMomento(int kmNoMomento) {
        this.kmNoMomento = kmNoMomento;
    }

    public Double getTracosNoMomento() {
        return tracosNoMomento;
    }

    public void setTracosNoMomento(Double tracosNoMomento) {
        this.tracosNoMomento = tracosNoMomento;
    }

    public Double getConsumoPainelMomento() {
        return consumoPainelMomento;
    }

    public void setConsumoPainelMomento(Double consumoPainelMomento) {
        this.consumoPainelMomento = consumoPainelMomento;
    }
}