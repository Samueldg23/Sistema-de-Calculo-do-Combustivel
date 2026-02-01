package br.com.sammdev.repository;

import java.util.List;

import br.com.sammdev.entity.Abastecimento;
import br.com.sammdev.entity.Palio;

public interface AbastecimentoRepository {
    void salvar(Abastecimento abastecimento);

    Abastecimento buscarPorId(Long id);

    Abastecimento buscarUltimoAbastecimentoRegistrado(Palio palio);

    // abastecimento que não teve erro no registro ou meu pai abasteceu sem eu saber
    Abastecimento buscarUltimoAbastecimentoValido(Palio palio);

    List<Abastecimento> listarAbastecimentos(Palio palio);

    List<Abastecimento> listarAbastecimentosValidos(Palio palio);

    void ignorarAbastecimento(Long id);

    void marcarReinicio(Long id);
}