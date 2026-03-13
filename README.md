# Sistema de Gerenciamento de Abastecimento e Consumo do Fiat Palio Attractive 2011 1.4
Este projeto foi desenvolvido em 2025 na faculdade mas não salvei o código no github, por isso estou refazendo do 0 esse sistema com os meus novos conhecimentos em POO + JPA + Um maior entendimento da regra de negócio. No desenvolvimento do sistema utilizei o chat gpt somente para me auxiliar nos insigths das funcionalidades, na arquitetura do projeto(Me informando se estar correto as pastas e os arquivos criados) e se o meu código que fiz está aceitável para um nível de um desenvolvedor júnior, pedindo para ele somente me dar um norte na produção dos códigos mas não produzindo o código em si, este é um projeto para resolver um problema meu e praticar o que aprendi na faculdade.

# Obejetivo desse projeto
- Desenvolver um sistema que irá registrar abastecimentos e as medições do carro
- Calcular o consumo real
- Comparar com o consumo indicado pelo veículo e gerar um histórico por posto para avaliar qualidade da gasolina e possível roubo na bomba.
Posteriomente irei desenvolver mais umas funcionalidades que me vier na mente de ser bacana de implementar, atualmente além dessas funcionalidades principais eu pensei também em 
- Lembrete de troca de óleo do motor, da água, do fluído do freio, e mais alguns componentes que são importantes de fazer manutenção
- Fazer um guia de manutenção preventiva do carro e o sistema irá me lembrar de fazer e me instruir na manutenção
- Registro de gastos do carro(Seguro, IPVA, Licenciamento etc)
- Verificar se vale mais a pena Alcool ou Gasolina
- Validade se a quantidade de combústivel que saiu da bomba for a mesma que que apontou no painel

# As Situações Previstas
- Abastecimento: O sistema deve registrar o dado do posto(nome) e os dados do abastecimento(Preço do combústivel, Quantidade de litros informado pela bomba, Valor Total Pago)
- Carro: No momento do abastecimento registrar a situação do carro(KM atual do carro, Combústivel presente no tanque, Consumo indicado no painel)
- Próximo Abastecimento: Quando eu realizar um novo abastecimento o sistema irá comparar a diferença no KM, Calcular o consumo estimado baseado na quantidade de combustivel consumida pelo KM percorrido e fazer uma comparação do consumo real vs o indicado pelo carro
- Avaliação do posto: Com base eu for abastecendo no mesmo posto o sistema: Calcula o consumo médio real daquele posto e a diferença média em relação ao consumo indicado. Indicar se o consumo está abaixo ou acima do esperado, se há uma variação fora do padrão. Gerar uma tag do posto de Bom, Médio ou Suspeito

# Regras importantes
No palio é informado o combústivel através de traços no painel, o tanque cabe 48 litros e existem 16 traços, então cada traço representa 3 litros e eu irei adicionar a opção de 1/2 traço que representa 1,5 litros, ele será para eu dizer se já faz muito ou pouco tempo que o combútivel está no traço, se ele tiver a um tempo que estou acostumado eu direi 3 1/2 traços que vai significar 10,5 litros. O consumo real só é calculado entre dois abastecimentos.

# Próximos Passos
- Definir entidades e responsabilidades
  ![Diagrama de Entidade-Relacionamento](https://github.com/user-attachments/assets/1221ecc0-d411-48cc-a658-b1fa0e1ad00e)
- Definir relacionamentos JPA
- Definir fluxo de uso
- Criar uma versão mínima funcional
- Evoluir depois para API REST
- Criar uma versão mobile (Flutter)


# Primeira versão do código (O que foi feito)
- Arquitetura Geral
  entity
  repository
    repository.impl
  service
A ideia foi no entity ter as entidades e o modelo de domínio, no repository o acesso ao banco e a persistência, no impl a implementação das query, no service as regras do negócio e o main eu utilizei o copilot pra completar a maioria dos prints

- Entidades: Palio, Posto e Abastecimento
  Palio: Estado atual do carro -> id, kmAtual, tracosAtuais, consumoPainelAtual
  * No desenvolvimento vi que o palio seria utilizado como estado atual das condições, o histórico deveria ficar no abastecimento pra comparação
  Posto: local que abasteci -> id e nome | Abastecimento -> ManyToOne -> Posto
  Abastecimento: ela é a entidade principal -> id, dataHora, precoLitro, litrosAbastecidos, valorTotal, kmNoMomento, tracosNoMomento, consumoPainelNoMomento, participaCalculo, marcaReinicio, observacao | ManyToOne -> Posto e Palio

  Essa parte do abastecimento não sei se foi a melhor implementação mas foi pelo o seguinte motivo:
  Eu salvo no abastecimento uma "foto" do palio(km, traços e consumo do painel) pois se eu salvasse no palio essas informações irão mudar, então salvei no abastecimento pra assim ficar imutável *** Pensando melhor hoje eu salvaria no palio pois vou estar repetindo informações no banco mas o pensamento no dia era não salvar o palio no banco de dados, eu só ia digitar o estado atual dele ***

  - Repository : PalioRepository, PostoRepository e AbastecimentoRepository
    PalioRepository -> salvar, buscarPorId, buscarEstadoAtual e atualizarEstadoAtual
    PostoRepository -> salvar, buscarPorId, buscarPorNome e listarTodos
    AbastecimentoRepository -> salvar, buscarPorId, buscarUltimoAbastecimento, buscarUltimoAbastecimentoValido, listarAbastecimentos, listarAbastecimentosValidos, ignorarAbastecimento e marcarReinicio
  
  - Service: PalioService, PostoService e AbastecimentoService

   *registrarAbastecimento()* -> Busca estado atual do Palio, Busca posto, Cria Abastecimento, Copia estado do Palio, Salva no banco e Adiciona observação
   posso depois colocar pra calcular automaticamente = valorTotal = precoLitro * litros

   *ignorarAbastecimento(id)* -> participaCalculo = false | isso foi feito pra quando eu fizer um abastecimento e esquecer de registrar ou o meu pai abasstecer sem me falar

   *marcarReinicio(id)* -> marcaReinicio = true | é pra reiniciar os cálculos caso troque de combustível ou erra no abstecimento, aí não calcula a média com o abastecimento anterior

   *calcularConsumoEntreAbastecimentos(atual, anterior)* -> kmPercorridos = kmAtual - kmAnterior
   consumo = kmPercorridos / litrosAbastecidosAnterior

    *buscarUltimoAbastecimentoValido*
    *buscarAbastecimentoBase* -> reinicio de cálculo
    *calcularConsumo*

    *calcularConsumoMedio* -> é pra calcular a cada par de abastecimento, acumular esse valor e dividir pela quantidade -> se marcaReinicio parar
    
    *Map<Posto, RelatorioPosto>* -> cada consumo atribui ao posto do abastecimento pra saber qual gasolina o palio economiza 

***Conseguir aplicar conceitos de POO, não utilizei ia para desenvolver(só no main), acabei utilizando muito os materiais que tinha de apoio, como as antações que fiz nas aulas da Alura e alguns e-books do professor Rômulo. Não conseguir apronfundar na parte de lógica sem apoio didático e também apoio do gpt para ele me falar o que eu deveria fazer na classe(Não deixei ele cuspir código somente ideia do que deveria ser feito). Acredito que devo melhorar minha lógica, vou estudar mais essa parte, talvez com conteúdos de concurso e leetcode. Irei desenvolver outra aplicação dessa, seguindo a mesma lógica de negócio, talvez focando em menos coisas na aplicação, vou criar outra branch e lá vou desenvolver as coisas, quero criar um web app então talvez vou pra spring e next.js, vou analisar isso antes***