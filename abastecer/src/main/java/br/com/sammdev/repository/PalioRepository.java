package br.com.sammdev.repository;

import br.com.sammdev.entity.Palio;

public interface PalioRepository {
    void salvar(Palio palio);

    Palio buscarPorId(Long id);

    Palio buscarEstadoAtual();
}
