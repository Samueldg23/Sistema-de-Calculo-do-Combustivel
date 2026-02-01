package br.com.sammdev.repository.impl;

import java.util.List;

import br.com.sammdev.entity.Posto;
import br.com.sammdev.repository.PostoRepository;
import jakarta.persistence.EntityManager;

public class PostoRepositoryImpl implements PostoRepository {
    private EntityManager em;

    public PostoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salvar(Posto posto) {
        em.getTransaction().begin();
        em.persist(posto);
        em.getTransaction().commit();
    }

    @Override
    public Posto buscarPorId(Long id) {
        return em.find(Posto.class, id);
    }

    @Override
    public Posto buscarPorNome(String nome) {
        return em.createQuery(
                "select p from Posto p where p.nome = :nome",
                Posto.class
        )
        .setParameter("nome", nome)
        .getResultStream()
        .findFirst()
        .orElse(null);
    }

    @Override
    public List<Posto> listarTodos() {
        return em.createQuery(
                "select p from Posto p",
                Posto.class
        )
        .getResultList();
    }
}
