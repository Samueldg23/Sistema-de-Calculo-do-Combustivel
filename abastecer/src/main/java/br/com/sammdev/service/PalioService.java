package br.com.sammdev.service;

import br.com.sammdev.entity.Palio;
import br.com.sammdev.repository.PalioRepository;

public class PalioService {
    private PalioRepository palioRepo;

    public PalioService(PalioRepository palioRepo) {
        this.palioRepo = palioRepo;
    }

    public void salvarPalio(int kmAtual, Double consumoPainelAtual, Double tracosAtuais) {
        Palio palio = new Palio(kmAtual, consumoPainelAtual, tracosAtuais);
        palioRepo.salvar(palio);
    }

    public Palio buscarEstadoAtual() {
        return palioRepo.buscarEstadoAtual();
    }

    public void atualizarEstadoAtual(int kmAtual, Double consumoPainelAtual, Double tracosAtuais) {
        Palio palio = palioRepo.buscarEstadoAtual();

        if (palio == null) {
            throw new IllegalStateException("Estado atual do Palio não encontrado.");
        }
        palio.setKmAtual(kmAtual);
        palio.setConsumoPainelAtual(consumoPainelAtual);
        palio.setTracosAtuais(tracosAtuais);
        palioRepo.salvar(palio);
    }
}
