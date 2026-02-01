package br.com.sammdev.repository;

import java.util.List;

import br.com.sammdev.entity.Posto;

public interface PostoRepository {
    void salvar(Posto posto);

    Posto buscarPorId(Long id);

    Posto buscarPorNome(String nome);

    List<Posto> listarTodos();
}
