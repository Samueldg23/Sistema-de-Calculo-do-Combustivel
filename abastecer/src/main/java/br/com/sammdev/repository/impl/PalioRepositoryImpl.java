package br.com.sammdev.repository.impl;

import br.com.sammdev.entity.Palio;
import br.com.sammdev.repository.PalioRepository;
import jakarta.persistence.EntityManager;

public class PalioRepositoryImpl implements PalioRepository{
    private EntityManager em;

    public PalioRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salvar(Palio palio) {
        em.getTransaction().begin();
        em.persist(palio);
        em.getTransaction().commit();
    }

    @Override
    public Palio buscarPorId(Long id) {
        return em.find(Palio.class, id);
    }

    @Override
    public Palio buscarEstadoAtual() {
        return em.createQuery(
                "select p from Palio p",
                Palio.class
        )
        .setMaxResults(1)
        .getResultStream()
        .findFirst()
        .orElse(null);
    }
}
