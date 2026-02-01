package br.com.sammdev.service;

import java.util.List;

import br.com.sammdev.entity.Posto;
import br.com.sammdev.repository.PostoRepository;

public class PostoService {

    private PostoRepository postoRepo;

    public PostoService(PostoRepository postoRepository) {
        this.postoRepo = postoRepository;
    }

    public void cadastrarPosto(String nome) {
        Posto existente = postoRepo.buscarPorNome(nome);
        if (existente != null) {
            throw new IllegalArgumentException("Posto já cadastrado");
        }

        Posto posto = new Posto(nome);
        postoRepo.salvar(posto);
    }

    public List<Posto> listarPostos() {
        return postoRepo.listarTodos();
    }
}