package br.com.sammdev.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.sammdev.entity.Abastecimento;
import br.com.sammdev.entity.Palio;
import br.com.sammdev.entity.Posto;
import br.com.sammdev.repository.AbastecimentoRepository;
import br.com.sammdev.repository.PalioRepository;
import br.com.sammdev.repository.PostoRepository;

public class AbastecimentoService {
    private AbastecimentoRepository abastecimentoRepo;
    private PalioRepository palioRepo;
    private PostoRepository postoRepo;

    public AbastecimentoService(AbastecimentoRepository abastecimento, PalioRepository palio, PostoRepository posto) {
        this.abastecimentoRepo = abastecimento;
        this.palioRepo = palio;
        this.postoRepo = posto;
    }

    public void registrarAbastecimento(
            long postoId,
            Double precoLitro,
            Double litrosAbastecidos,
            Double valorTotal,
            String observacao) {
        Palio palio = palioRepo.buscarEstadoAtual();
        if (palio == null) {
            throw new IllegalStateException("Estado atual do Palio não encontrado.");
        }

        Posto posto = postoRepo.buscarPorId(postoId);
        if (posto == null) {
            throw new IllegalArgumentException("Posto com ID " + postoId + " não encontrado.");
        }

        Abastecimento abastecimento = new Abastecimento(
                LocalDateTime.now(),
                posto,
                palio,
                precoLitro,
                litrosAbastecidos,
                valorTotal,
                palio.getKmAtual(),
                palio.getTracosAtuais(),
                palio.getConsumoPainelAtual());
        abastecimento.setObservacao(observacao);
        abastecimentoRepo.salvar(abastecimento);
    }

    public void ignorarAbastecimento(Long abastecimentoId) {
        abastecimentoRepo.ignorarAbastecimento(abastecimentoId);
    }

    public void reiniciaCalculo(Long abastecimentoId) {
        abastecimentoRepo.marcarReinicio(abastecimentoId);
    }

    public Double calcularConsumoEntreAbastecimentos(Abastecimento atual, Abastecimento anterior) {
        if (anterior == null) {
            throw new IllegalArgumentException("Abastecimento anterior não pode ser nulo.");
        }

        int kmPercorridos = atual.getKmNoMomento() - anterior.getKmNoMomento();
        if (kmPercorridos <= 0) {
            throw new IllegalArgumentException(
                    "Quilometragem do abastecimento atual deve ser maior que a do anterior.");
        }

        return (kmPercorridos / atual.getLitrosAbastecidos());
    }

    public Abastecimento buscarAbastecimentoBase(Palio palio) {

        List<Abastecimento> lista = abastecimentoRepo.listarAbastecimentosValidos(palio);

        if (lista.size() < 2) {
            return null;
        }

        Abastecimento atual = lista.get(0);

        if (atual.isMarcaReinicio()) {
            return null;
        }

        return lista.get(1);
    }

    public Double calcularConsumoUltimoAbastecimento(Palio palio) {

        Abastecimento atual = abastecimentoRepo.buscarUltimoAbastecimentoValido(palio);

        if (atual == null) {
            return null;
        }

        Abastecimento base = buscarAbastecimentoBase(palio);

        if (base == null) {
            return null;
        }

        return calcularConsumoEntreAbastecimentos(atual, base);
    }

    public Double calcularConsumoMedio(Palio palio) {

        List<Abastecimento> lista = abastecimentoRepo.listarAbastecimentosValidos(palio);

        double soma = 0;
        int contador = 0;

        for (int i = 0; i < lista.size() - 1; i++) {

            Abastecimento atual = lista.get(i);
            Abastecimento anterior = lista.get(i + 1);

            if (atual.isMarcaReinicio()) {
                break;
            }

            Double consumo = calcularConsumoEntreAbastecimentos(atual, anterior);

            if (consumo != null) {
                soma += consumo;
                contador++;
            }
        }

        return contador == 0 ? null : soma / contador;
    }

}
