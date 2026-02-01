package br.com.sammdev.repository.impl;

import java.util.List;

import br.com.sammdev.entity.Abastecimento;
import br.com.sammdev.entity.Palio;
import br.com.sammdev.repository.AbastecimentoRepository;
import jakarta.persistence.EntityManager;

public class AbastecimentoRepositoryImpl implements AbastecimentoRepository{
    private EntityManager em;

    public AbastecimentoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salvar(Abastecimento abastecimento) {
        em.getTransaction().begin();
        em.merge(abastecimento);
        em.getTransaction().commit();
    }

    @Override
    public Abastecimento buscarPorId(Long id) {
        return em.find(Abastecimento.class, id);
    }

    @Override
    public Abastecimento buscarUltimoAbastecimentoRegistrado(Palio palio) {
        return em.createQuery(
                "select a from Abastecimento a " +
                "where a.palio = :palio " +
                "order by a.dataHora desc",
                Abastecimento.class
        )
        .setParameter("palio", palio)
        .setMaxResults(1)
        .getResultStream()
        .findFirst()
        .orElse(null);
    }

    @Override
    public Abastecimento buscarUltimoAbastecimentoValido(Palio palio) {
        return em.createQuery(
                "select a from Abastecimento a " +
                "where a.palio = :palio and a.participaCalculo = true " +
                "order by a.dataHora desc",
                Abastecimento.class
        )
        .setParameter("palio", palio)
        .setMaxResults(1)
        .getResultStream()
        .findFirst()
        .orElse(null);
    }

    @Override
    public List<Abastecimento> listarAbastecimentos(Palio palio) {
        return em.createQuery(
                "select a from Abastecimento a " +
                "where a.palio = :palio " +
                "order by a.dataHora desc",
                Abastecimento.class
        )
        .setParameter("palio", palio)
        .getResultList();
    }

    @Override
    public List<Abastecimento> listarAbastecimentosValidos(Palio palio) {
        return em.createQuery(
                "select a from Abastecimento a " +
                "where a.palio = :palio and a.participaCalculo = true " +
                "order by a.dataHora desc",
                Abastecimento.class
        )
        .setParameter("palio", palio)
        .getResultList();
    }

    @Override
    public void ignorarAbastecimento(Long id) {
        Abastecimento abastecimento = buscarPorId(id);
        if (abastecimento != null) {
            em.getTransaction().begin();
            abastecimento.setParticipaCalculo(false);
            em.getTransaction().commit();
        }
    }

    @Override
    public void marcarReinicio(Long id) {
        Abastecimento abastecimento = buscarPorId(id);
        if (abastecimento != null) {
            em.getTransaction().begin();
            abastecimento.setMarcaReinicio(true);
            em.getTransaction().commit();
        }
    }
}