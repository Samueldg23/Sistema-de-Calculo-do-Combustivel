package br.com.sammdev;

import br.com.sammdev.entity.Palio;
import br.com.sammdev.entity.Posto;
import br.com.sammdev.repository.AbastecimentoRepository;
import br.com.sammdev.repository.PalioRepository;
import br.com.sammdev.repository.PostoRepository;
import br.com.sammdev.repository.impl.AbastecimentoRepositoryImpl;
import br.com.sammdev.repository.impl.PalioRepositoryImpl;
import br.com.sammdev.repository.impl.PostoRepositoryImpl;
import br.com.sammdev.service.AbastecimentoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//essa classe eu fiz mais ou menos um esboço usando os códigos que tenho da faculdade
//não conseguir criar ela sozinho, depois pedi pro gpt ajustar o código para não dar erro
//ele ajustou algumas partes, principalmente a parte de EntityManager e EntityManagerFactory que ainda não entendi direito como funciona
//o código tá funcional e sem erro até agora, mas falta formatar como os dados vem no terminal
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controle-consumo-pu");
        EntityManager em = emf.createEntityManager();

        PalioRepository palioRepo = new PalioRepositoryImpl(em);
        PostoRepository postoRepo = new PostoRepositoryImpl(em);
        AbastecimentoRepository abastecimentoRepo = new AbastecimentoRepositoryImpl(em);

        AbastecimentoService abastecimentoService = new AbastecimentoService(
                abastecimentoRepo,
                palioRepo,
                postoRepo
        );

        em.getTransaction().begin();

        Palio palio = new Palio(100000, 7.0, 10.5);
        em.persist(palio);

        Posto valentim = new Posto("Valentim");
        Posto moxuara = new Posto("Moxuara");
        em.persist(valentim);
        em.persist(moxuara);

        em.getTransaction().commit();

        System.out.println("Sistema de Controle de Consumo de Combustível iniciado.");
        palio.setKmAtual(100000);
        palio.setTracosAtuais(7.0);
        palio.setConsumoPainelAtual(10.5);

        abastecimentoService.registrarAbastecimento(valentim.getId(), 5.79, 40.0, 231.60, "Abastecimento inicial.");

        System.out.println("Abastecimento registrado com sucesso.");
        
        palio.setKmAtual(100500);
        palio.setTracosAtuais(6.5);
        palio.setConsumoPainelAtual(10.6);

        abastecimentoService.registrarAbastecimento(moxuara.getId(), 5.89, 35.0, 206.15, "Segundo abastecimento.");

        System.out.println("Segundo abastecimento registrado com sucesso.");

        Double consumo1 = abastecimentoService.calcularConsumoUltimoAbastecimento(palio);
        System.out.printf("Consumo do último abastecimento: %.2f km/l%n", consumo1);

        palio.setKmAtual(101200);
        palio.setTracosAtuais(6.0);
        palio.setConsumoPainelAtual(10.8);

        abastecimentoService.registrarAbastecimento(valentim.getId(), 5.95, 50.0, 297.50, "Pai abasteceu e esqueceu de anotar direito.");

        abastecimentoRepo.ignorarAbastecimento(abastecimentoRepo.buscarUltimoAbastecimentoRegistrado(palio).getId());
        System.out.println("Terceiro abastecimento registrado e ignorado com sucesso.");

        palio.setKmAtual(101700);
        palio.setTracosAtuais(5.5);
        palio.setConsumoPainelAtual(10.6);

        abastecimentoService.registrarAbastecimento(moxuara.getId(), 6.05, 45.0, 272.25, "Quarto abastecimento após o erro.");

        Double consumo2 = abastecimentoService.calcularConsumoUltimoAbastecimento(palio);
        Double consumoMedio = abastecimentoService.calcularConsumoMedio(palio);
        System.out.printf("Consumo do último abastecimento válido: %.2f km/l%n", consumo2);
        System.out.printf("Consumo médio considerando abastecimentos válidos: %.2f km/l%n", consumoMedio);

        abastecimentoService.reiniciaCalculo(abastecimentoRepo.buscarUltimoAbastecimentoRegistrado(palio).getId());
        System.out.println("Cálculo reiniciado a partir do último abastecimento registrado.");
        em.close();
        emf.close();
        System.out.println("Sistema finalizado.");
        
    }
}