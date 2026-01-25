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
- Definir relacionamentos JPA
- Definir fluxo de uso
- Criar uma versão mínima funcional
- Evoluir depois para API REST
- Criar uma versão mobile (Flutter)
