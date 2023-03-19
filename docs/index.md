<table>
<tr>
<td>
<a href= "https://ael.com.br/"><img src="https://www.ael.com.br/images/ael.png" alt="AEL Sistemas" border="0" width="70%"></a>
</td>
<td><a href= "https://www.inteli.edu.br/"><img src="https://www.inteli.edu.br/wp-content/uploads/2021/08/20172028/marca_1-2.png" alt="Inteli - Instituto de Tecnologia e Liderança" border="0" width="30%"></a>
</td>
</tr>
</table>

<font size="+12"><center>
Planejador de trajetórias para voos em baixa altitude.
</center></font>

**Conteúdo**

- [Autores](#autores)
- [Visão Geral do Projeto](#visão-geral-do-projeto)
  - [Empresa](#empresa)
  - [O problema](#o-problema)
    - [Contexto do problema a ser resolvido](#contexto-do-problema-a-ser-resolvido)
    - [Modelagem do problema (variáveis consideradas)](#modelagem-do-problema-variáveis-consideradas)
  - [Objetivos](#objetivos)
    - [Objetivos gerais](#objetivos-gerais)
    - [Objetivos específicos](#objetivos-específicos)
  - [Partes interessadas](#partes-interessadas)
- [Análise do Problema](#análise-do-problema)
  - [Contexto da indústria](#contexto-da-indústria)
  - [As Cinco Forças de Porter:](#as-cinco-forças-de-porter)
    - [a) Poder de barganha dos clientes:](#a-poder-de-barganha-dos-clientes)
    - [b) Poder de barganha dos fornecedores](#b-poder-de-barganha-dos-fornecedores)
    - [c) Risco de entrada de novos competidores:](#c-risco-de-entrada-de-novos-competidores)
    - [d) Ameaça de produtos substitutos:](#d-ameaça-de-produtos-substitutos)
    - [e) Rivalidade entre concorrentes:](#e-rivalidade-entre-concorrentes)
  - [Análise do cenário: Matriz SWOT](#análise-do-cenário-matriz-swot)
    - [Pontos Fortes](#pontos-fortes)
    - [Pontos Fracos](#pontos-fracos)
    - [Oportunidades](#oportunidades)
    - [Ameaças](#ameaças)
  - [Descrição da solução a ser desenvolvida](#descrição-da-solução-a-ser-desenvolvida)
    - [Problema a ser resolvido](#problema-a-ser-resolvido)
    - [Solução proposta (visão de negócios)](#solução-proposta-visão-de-negócios)
    - [Como a solução deve ser usada](#como-a-solução-deve-ser-usada)
    - [Benefícios trazidos pela solução proposta](#benefícios-trazidos-pela-solução-proposta)
    - [Objetivos](#objetivos-1)
    - [Benefícios](#benefícios)
    - [Conclusão](#conclusão)
  - [Proposta de Valor: Value Proposition Canvas](#proposta-de-valor-value-proposition-canvas)
  - [Matriz de Risco](#matriz-de-risco)
- [Requisitos do Sistema](#requisitos-do-sistema)
  - [Personas](#personas)
    - [Persona primária](#persona-primária)
    - [Persona secundária](#persona-secundária)
  - [Histórias dos usuários (user stories)](#histórias-dos-usuários-user-stories)
- [Análise de Dados](#análise-de-dados)
  - [Formato](#formato)
  - [Biblioteca utilizada](#biblioteca-utilizada)
  - [Características dos arquivos](#características-dos-arquivos)
  - [Características dos dados](#características-dos-dados)
  - [Inputs](#inputs)
  - [Outputs](#outputs)
  - [Localização dos dados no github](#localização-dos-dados-no-github)
  - [Representação visual inicial (Neo4j)](#representação-visual-inicial-neo4j)
- [Limitações e modelagem matemática](#limitações-e-modelagem-matemática)
  - [Modelagem matemática](#modelagem-matemática)
    - [Variáveis de decisão](#variáveis-de-decisão)
    - [Função objetivo](#função-objetivo)
    - [Restrições](#restrições)
    - [Detalhamento das restrições](#detalhamento-das-restrições)
    - [Conclusão](#conclusão-1)
- [Referências](#referências)


# Autores

* Beny Frid
* Elias Biondo
* Luiz Carlos jr
* Pedro Munhoz
* Pedro Romão
* Rafael Cabral
* Thomas Barton
* Vinicius Santos


# Visão Geral do Projeto

## Empresa

A AEL Sistemas é uma empresa localizada em Porto Alegre, no Brasil, especializada no design, produção, manutenção e suporte logístico de sistemas eletrônicos militares para uso em plataformas aéreas, marítimas e terrestres. Com capacidade para fornecer, projetar e desenvolver aviônicos, sistemas terrestres e soluções para segurança pública, a empresa também é uma participante ativa em programas da indústria espacial.

## O problema

### Contexto do problema a ser resolvido

Este projeto tem como principal área de negócio a aviação militar. Ele é liderado por Conrad Pilotto (cpilotto@ael.com.br) como líder do projeto e Norton Lima Barbieri (nbarbieri@ael.com.br) como líder técnico. Esta iniciativa relaciona-se diretamente com o uso de uma solução de aviação já existente — o terrain following, que permite uma aeronave manter sua altitude constante de acordo com as variações do terreno, através da utilização de sensores que fornecem informações precisas sobre as condições do setor aéreo e zona de voo. Apesar de robusto, esse sistema não conta com a capacidade de calcular rotas e trajetórias, e o acréscimo de tais funcionalidades aumentariam, expressivamente, a sua efetividade, na medida que minimizariam os riscos de exposições e colisões.
Dado o exposto, fica evidente que o problema principal é encontrar o equilíbrio ideal entre o risco iminente de colisão com o solo (CFIT) e a exposição da aeronave durante missões de voo a baixa altitude. O sistema de Terrain Following tem como objetivo ajudar a mitigar esse risco, mas para isso é necessário levar em consideração uma série de fatores de voo e geográficos. Infere-se que as causas desse problema são as dificuldades e desafios enfrentados por um piloto durante uma operação a baixa altitude, como a necessidade de considerar diversos aspectos de voo, a possibilidade de colisão com o solo, a possibilidade de ser abatido por forças opositoras, entre outros. A construção de uma trajetória de voo de referência é uma estratégia para minimizar essas causas e, por consequência, os riscos decorrentes disso.

### Modelagem do problema (variáveis consideradas)

A análise de variáveis de decisão é fundamental para garantir o sucesso e a segurança do projeto descrito. A aviação militar é uma área complexa e repleta de desafios, onde o risco iminente de colisão com o solo (CFIT) e a exposição são preocupações constantes. Para minimizar esse risco, é necessário levar em consideração uma série de fatores de voo e geográficos, como altitude de voo, condições do setor aéreo e zona de voo, terreno, possibilidade de colisão com o solo, exposição e probabilidade de ser abatido, sistemas de monitoramento, visibilidade dada a presença de obstáculos como montanhas, florestas, entre outros e visada dadas as características do relevo. A análise dessas variáveis permite a construção de trajetórias de voo seguras e eficientes, atendendo às expectativas e necessidades dos stakeholders e alcançando as metas específicas do projeto.
Com isso em mente, a seguir, apresentamos, ainda em uma fase inicial, uma avaliação detalhada dos fatores mencionados anteriormente, na ordem em que foram escritos, sem prejuízos ao resultado ou priorização:

- Altitude de voo: O projeto tem como foco voos de baixa altitude, o que é uma restrição importante a ser considerada. Um voo a baixa altitude é definido como um voo realizado a uma altitude inferior a certa altitude estabelecida, geralmente determinada pela regulamentação aeronáutica ou pelas normas de segurança de voo. Em geral, um voo a baixa altitude pode ser considerado entre 500 a 1000 pés (cerca de 152 a 305 metros) acima do solo.

- Confiabilidade do setor aéreo e zona de voo: A confiabilidade do setor aéreo e da zona de voo é mensurada através de indicadores de segurança como o índice de acidentes por hora de voo, índice de incidências climáticas e restrições de tráfego aéreo. Valores acima de 90% são considerados aceitáveis para garantir a segurança do voo.

- Terreno: A altura do terreno é uma consideração importante para voos de baixa altitude, pois afeta a segurança do voo. É necessário verificar a presença de obstáculos, como montanhas, torres, prédios, árvores, entre outros, que possam prejudicar a segurança do voo e a capacidade do piloto de realizar manobras. Uma distância mínima recomendada em relação aos obstáculos pode ser estabelecida pela regulamentação aeronáutica ou pelas normas de segurança de voo.

- Possibilidade de colisão com o solo (CFIT): Este é o principal problema a ser resolvido pelo projeto, já que o objetivo é minimizar o risco de CFIT durante voos de baixa altitude. Não há uma taxa de CFIT aceitável universalmente aceita. A taxa é determinada pelas regulamentações aeronáuticas relevantes, às necessidades da operação e as circunstâncias específicas.

- Exposição e probabilidade de ser abatido: O projeto visa minimizar a taxa de exposição a riscos de abate durante voos de baixa altitude. Esta taxa pode ser quantificada com base em fatores como o número de horas de voo, as condições do setor aéreo, a altitude de voo e o perfil de voo. Esta taxa é calculada com base em estatísticas históricas, simulações de voo e análises de risco, e é usada como indicador da segurança do voo.

- Sistemas de monitoramento: A interferência e detecção por sistemas de monitoramento e radares durante as trajetórias de voo também é uma variável a ser considerada. Isso pode ser quantificado por meio da avaliação da taxa de interferência e detecção em relação ao número total de rotas e trajetórias calculadas.

- Visibilidade da aeronave: O projeto visa minimizar a visibilidade da aeronave considerando obstáculos como montanhas, florestas, etc., durante a construção de trajetórias de voo de referência.

- Visada de relevo: A caracterização do relevo é importante para garantir uma visada adequada durante voos de baixa altitude, permitindo que a aeronave mantenha a segurança ao evitar obstáculos. O projeto considera as características do relevo na construção de trajetórias de voo de referência.


## Objetivos

### Objetivos gerais

O principal objetivo desta iniciativa é proporcionar uma interface humano-computador para o cálculo de trajetórias, por meio de grafos, para voos de baixa altitude, de modo que estas respeitem uma série de restrições com o fim de atenuar possíveis situações de risco.

### Objetivos específicos

São expectativas e necessidades dos stakeholders a criação de um processo mensurável e objetivo de planejamento de trajetórias e rotas de voo, que leve em conta critérios apropriados de otimização e restrições, de modo a reduzir o risco de colisão com o solo durante voos a baixa altitude, prover orientação segura aos pilotos, balancear as probabilidades de colisão com o solo e exposição, e a construção de trajetórias de voo de referência eficientes e seguras para missões.
São metas específicas do projeto, no processo de planejamento de trajetórias e rotas de voo, a inclusão de fatores geográficos e de segurança apropriados.

## Partes interessadas

- AEL Sistemas
- Inteli - Instituto de Tecnologia e Liderança

# Análise do Problema

## Contexto da indústria

O setor de tecnologia e defesa aeroespacial no Brasil apresenta uma baixa variedade de participantes, com apenas 11 empresas. Isso se deve ao fato de ser um mercado estruturado e conservador, já que soluções nesta área afetam diretamente a segurança nacional. No entanto, o governo tem prestado atenção histórica a este mercado e, segundo a Associação de Indústrias Aeroespaciais do Brasil, houve um programa de incentivo para projetos aeroespaciais no valor de 120 milhões de reais, além de outros investimentos previstos no orçamento público. O orçamento nacional de Defesa é revisado anualmente e projeta o futuro do mercado para as empresas envolvidas, pois permite ter uma ideia do valor disponível para o principal cliente destes produtos, o próprio governo.

**Figura 1 – Orçamento total da Defesa Nacional**

https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/orcamentoGoverno.png

Fonte: Portal da Transparência<br>

Com base nas informações apresentadas, examinaremos mais detalhadamente a participação de cada uma das empresas líderes neste mercado, e, a seguir, esclarecer como isso impacta a AEL Sistemas.

A Embraer, uma das principais fabricantes de aviões do mundo, tem uma divisão de Defesa e Segurança especializada em soluções para esse setor. A empresa oferece uma ampla gama de produtos, incluindo aviões militares e sistemas de defesa aérea, e registrou uma receita líquida de R$ 22,6 bilhões em 2022. Com 20% do market share nacional, ela também exporta tecnologia para 40 países em todo o mundo.

A Atech, competitora direta da empresa aqui em foco, em 2020, registrou uma receita líquida de R$ 234,6 milhões e detém 10% do market share brasileiro. Ela oferece serviços em países como França, Portugal e Espanha, mas sua principal atuação se concentra no Brasil e na América Latina.

A Avibras, fundada em 1971, atua nacional e internacionalmente, com escritórios em São Paulo, Distrito Federal e Arábia Saudita. A empresa enfrentou dificuldades financeiras durante a pandemia, mas vem se recuperando e registrou uma receita líquida de R$ 220 milhões em 2021.

A AEL Sistemas é uma empresa privada que opera com um modelo B&G (business to government). Infelizmente, ela não divulga seu demonstrativo financeiro publicamente. No entanto, pesquisando o site do portal da transparência, pode-se verificar que a empresa formalizou 5 contratos com a administração pública em 2022, abrangendo as esferas de defesa pública: exército, aeronáutica e marinha.

**Figura 2 – Montante do valor formalizado de contratos públicos**

https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/contratos.png

Fonte: Portal da Transparência

## As Cinco Forças de Porter:

### a) Poder de barganha dos clientes:
A Ael está estreitamente ligada aos órgãos públicos de defesa, uma vez que eles representam a maior parte de sua receita no Brasil, de acordo com dados apresentados pela própria instituição. Assim, o governo é o seu único cliente, o que depende da aprovação do orçamento no congresso e da realização de licitações. Além disso, existem outras 10 empresas que atuam no mesmo setor e têm o mesmo cliente governamental. No entanto, a Ael afirma que sua receita atual tem uma composição de 50% no exterior, mas não há dados públicos que comprovem isso. Dessa forma, com base nas informações disponíveis, pode-se concluir que o poder de barganha com o cliente é baixo, uma vez que ele é único (o governo) e pode buscar melhores opções se for necessário.

### b) Poder de barganha dos fornecedores
O produto "X-86", um computador embarcado de alto desempenho, é o único item do portfólio da AEL Sistemas que tem um fornecedor evidente. Esse tipo de computador depende de componentes eletrônicos que são produzidos por várias fábricas localizadas principalmente na Ásia, destacando-se Taiwan e China. Embora tenha havido interrupções no fornecimento devido à pandemia, o mercado de componentes eletrônicos é estável e bem diversificado. Assim, podemos concluir que o poder de barganha dos fornecedores é alto.

### c) Risco de entrada de novos competidores:
A indústria tecnológica e aeroespacial de defesa é altamente restritiva à entrada de novos competidores, pois os contratos priorizam a segurança institucionalizada e são conservadores. Além disso, os custos de operação e P&D são extremamente elevados devido aos critérios rigorosos que precisam ser atendidos. Ainda há uma predisposição política em relação às empresas já estabelecidas na indústria, devido ao histórico governamental com elas. Dessa forma, a ameaça de entrada de novos competidores é baixa, o que pode ser bem aproveitado pela empresa, que tem consciência da segurança deste aspecto.

### d) Ameaça de produtos substitutos:
A AEL Sistemas tem em seu portfólio produtos altamente especializados, como o Terrain Following, um sistema de bordo de aeronaves que, sincronizadamente, detecta a posição territorial da aeronave e orienta o piloto através de sensores baseados no terreno. Devido às suas necessidades altamente específicas, é pequena a possibilidade de haver um produto substituto. No entanto, existe a possibilidade de outras empresas oferecerem soluções similares com precisões aprimoradas ou um avanço tecnológico nesse sentido. Mas, como o mercado é conservador, a ameaça é considerada baixa. Portanto, a ameaça de produtos substitutos no mercado é considerada baixa.

### e) Rivalidade entre concorrentes:
Existem algumas características que indicam uma concorrência moderada, incluindo o alto custo de entrada no mercado e o fato de que apenas 11 empresas atuam neste setor. Além disso, a demanda é estável ao longo do ano e o Brasil não tem histórico de conflitos armados internacionais, o que torna o mercado previsível para as empresas estabelecidas. Dessa forma, podemos dizer que a rivalidade entre as empresas é moderada e até mesmo há colaboração entre elas em áreas em que não têm especialização.

## Análise do cenário: Matriz SWOT

Para ter uma compreensão mais clara da situação atual da empresa parceira, realizamos uma análise SWOT. A análise SWOT é uma ferramenta de gestão que ajuda a fazer o planejamento estratégico para empresas e projetos. Com ela, podemos nos conectar de forma mais efetiva com a empresa, entender seus problemas e identificar suas forças a serem exploradas.

### Pontos Fortes
- Capacidade de produzir soluções de alta qualidade
- Ampla variedade de produtos e serviços
- Faz parte do grupo Elbit Systems, líder global no setor de defesa

### Pontos Fracos
- Produtos direcionados a um mercado limitado
- Modo de trabalho limitado, com sede fora do polo tecnológico aeronáutico do Brasil
- Qualidade dos materiais de divulgação poderia ser melhorada

### Oportunidades
- Maior investimento em tecnologia militar, com o Brasil possuindo a segunda maior frota de aeronaves militares na América
- Possibilidade de aumentar investimentos na área aeroespacial devido ao crescimento global do mercado

### Ameaças
- Redução de investimento governamental nas áreas da empresa
- Clientes majoritariamente conservadores podem limitar contratos de fornecimento de materiais com outras empresas
- Tecnologias utilizadas podem rapidamente se tornar obsoletas devido ao maior investimento em P&D no cenário internacional.


## Descrição da solução a ser desenvolvida

### Problema a ser resolvido
A AEL Sistemas possui atualmente um algoritmo para missões de voo de baixa altitude que considera diversos aspectos de voo para encontrar um equilíbrio entre a probabilidade de colisão com o solo e a probabilidade de ser atacado. No entanto, a empresa não dispõe de um algoritmo que possa identificar a rota mais benéfica para a missão. Isso é complicado por diversos obstáculos como regiões altamente povoadas, regiões com tropas inimigas, entre outras ameaças, tornando necessário o desenvolvimento de uma solução.

### Solução proposta (visão de negócios)
A solução proposta é o desenvolvimento de uma aplicação em Java que permita ao cliente planejar trajetórias para voos de baixa altitude. A aplicação utilizará dados de elevação para criar uma representação gráfica da área de voo e suas características geográficas, possibilitando ao cliente encontrar a rota mais otimizada entre o ponto de partida e o destino. A aplicação será desenvolvida de forma a seguir as restrições impostas pelo cliente, garantindo a segurança e eficiência dos voos.

### Como a solução deve ser usada
Quando receber informações sobre uma missão que precise de planejamento, a equipe poderá inserir o ponto de partida e chegada na interface. O algoritmo então procurará, com base nos dados topográficos entre as regiões dos pontos, a melhor trajetória para manter baixa altitude e evitar ser detectado. Após o cálculo da rota, a solução retornará ao usuário uma trajetória tracejada no mapa conectando os dois pontos e um arquivo de texto com um vetor de coordenadas que pode ser utilizado em outra aplicação.

### Benefícios trazidos pela solução proposta
A implementação deste projeto pode melhorar os percursos de missões críticas, elevando a probabilidade de sucesso, e servir como base para futuros projetos que envolvam tanto a plataforma embarcada quanto sistemas de planejamento de missões no solo. Com a adoção desta solução, a empresa estará preparada para encarar desafios futuros e aproveitar novas oportunidades, utilizando-a em conjunto com um produto já existente e criando ainda mais valor.

### Objetivos

1. Definir a melhor rota segundo os parâmetros estabelecidos, como:
   - Menor altitude
   - Percurso com baixa visibilidade
   - Exclusão de zonas mais perigosas
   - Ângulo de curva possível para um avião
   - Demorar menos de um minuto para entregar o output
2. Desenvolver um algoritmo eficiente
3. Agregar valor a AEL

### Benefícios

1. Vantagens econômicas: gera trajetórias de voo ótimas, economizando combustível e tempo na viagem.
2. Economia de tempo: particularmente importante nas operações militares, aumentando as chances de sucesso em missões de interceptação e reconhecimento de inimigos.
3. Segurança dos pilotos: evita rotas perigosas, reduzindo o risco de acidentes.

### Conclusão

O software desenvolvido visa ser uma ferramenta de suporte valiosa para pilotos que realizam voos em altitudes baixas, fornecendo-lhes uma rota eficiente e segura. A solução é um MVP e precisará de algumas alterações para se adequar aos sistemas da AEL, mas pode apresentar grandes benefícios para os usuários.


## Proposta de Valor: Value Proposition Canvas

Para melhor representar as dores e necessidades do cliente, criamos a `Value Proposition Canvas`, ou `Proposta de Valor`. O Value Proposition Canvas trata-se de uma representação visual e fácil de entender dos principais motivos do cliente ter a necessidade de nosso produto. VEmos as dores do cliente, como resolveríamos elas com nosso produto e os ganhos que o cliente teria com isso.

![Value Proposition Canvas](https://raw.githubusercontent.com/2023M5T1-Inteli/grupo2/master/docs/img/Value%20Proposition%20Canvas.png)

## Matriz de Risco

A matriz de risco, com o fim de facilitar a formatação e edição, pode ser acessada [clicando aqui](https://docs.google.com/spreadsheets/d/1IckJ2GDFogIn9OFoECq05htuEOgNn2KIkfMbei2BZPs/edit?usp=sharing) ou através do seguinte link: https://docs.google.com/spreadsheets/d/1IckJ2GDFogIn9OFoECq05htuEOgNn2KIkfMbei2BZPs/edit?usp=sharing.

# Requisitos do Sistema

## Personas

Para compreender o projeto com mais profundidade, criamos as personas. As personas são uma representação fictícia dos usuários finais do projeto. Seu objetivo principal é conhecer melhor os usuários, estabelecer uma conexão mais próxima com eles e garantir que o sistema atenda às suas necessidades. Para isso, criamos duas personas distintas.

### Persona primária

![Persona primária](https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/persona-primaria.png?raw=true)

### Persona secundária

![Persona secundária](https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/persona-secund%C3%A1ria.png?raw=true)

## Histórias dos usuários (user stories)

|Número | Descrição | Complexidade | Prioridade | Status
|--|--|--|--|--|
| 1 | Como piloto militar, quero ter acesso a mapas atualizados com os trajetos mais eficientes para poder navegar com precisão em missões. |Baixa|Alta|Pendente|
| 2 | Como piloto militar, quero ter acesso a informações sobre o terreno e as condições adversas para tomar decisões de voo e certificar que a missão será um sucesso . |Baixa|Grande|Pendente|
| 3 | Como piloto militar, quero ter uma trajetória que minimize a minha visibilidade, para inimigos não me identificarem facilmente. |Baixo|Alta|Pendente|
| 4 | Como piloto militar , quero poder me comunicar com colegas e comandante em caso de emergências durante minhas missões. |Média|Baixa|Pendente|
| 5 | Como piloto militar, quero saber o que embasou a escolha da rota para a missão, para estar a par. |Baixa|Baixa|Pendente|
| 6 | Eu, como inteligência militar, quero garantir que a missão seja executada com a altitude mais baixa possível, para evitar que meu piloto e avião sejam abatidos. |Baixa|Alta|Pendente|
| 7 | Eu, como inteligência militar, quero fazer uma rota que seja compatível com a autonomia do avião, pois o combustível não pode terminar antes do pouso. |Média|Alta|Pendente|
| 8 | Eu, como inteligência militar, quero que o trajeto tenha a menor variação de altitude possível, para evitar o gasto desnecessário de combustível. |Média|Baixa|Pendente|
| 9 | Eu, como inteligência militar, quero traçar caminhos que evitem áreas indesejadas, como bases inimigas, para evitar a exposição da aeronave. |Média|Alta|Pendente|
| 10 | Eu, como inteligência militar, quero uma aplicação que dê uma rota em menos de 1 minuto, para garantir que o planejamento emergencial aconteça dentro do tempo limite. |Alta|Baixa|Pendente|

# Análise de Dados

## Formato
Os dados repassados pela AEL são dados geoespaciais, eles estão em formato DTED2 (.dt2) e precisam de algumas bibliotecas específicas para serem manipulados e visualizados.

## Biblioteca utilizada
GDAL - biblioteca tradutora para formatos de dados geoespaciais vetoriais e raster que é lançada sob uma licença de código aberto estilo MIT pela Open Source Geospatial Foundation.

## Características dos arquivos

| REGIÃO | NOME | MB |
| ------ | ---- | --- |
| SP | W045_S23.dt2 | 24.8 mb |
| SP | W045_S24.dt2 | 24.8 mb |
| SP | W046_S23.dt2 | 24.8 mb |
| SP | W046_S24.dt2 | 24.8 mb |
| SP | W047_S23.dt2 | 24.8 mb |
| SP | W047_S24.dt2 | 24.8 mb |
| RJ | W043_S23.dt2 | 24.8 mb |
| RJ | W043_S24.dt2 | 24.8 mb |
| RJ | W044_S23.dt2 | 24.8 mb |
| RJ | W044_S24.dt2 | 24.8 mb |

## Características dos dados

Para processar esses dados, precisamos incluir duas informações: as coordenadas de longitude e latitude de um determinado local. O resultado será a altura prevista para essa área.

Além disso, é possível incluir áreas que devem ser evitadas, conhecidas como zonas de exclusão. Também é possível especificar pontos obrigatórios a serem percorridos, chamados de zonas essenciais.

Outros dados importantes que devem ser incluídos são o ponto de partida e o destino, para que o algoritmo possa calcular a melhor rota.

## Inputs

| CARACTERÍSTICA | TIPO | EXEMPLO | UNIDADE |
|----------------|------|---------|---------|
| 1 - Longitude  | NUMBER(double) | -23.0696792891117 | Graus (°), minutos (′) e segundos (″)
| 2 - Latitude   | NUMBER(double) | -43.5573428666663 |Graus (°), minutos (′) e segundos (″)
| 3 - Zonas de exclusão | Coordenada | (-23.560228285839322, -46.74303723386254), (-23.55157365449871, -46.731364260272024), (-23.568606999438785, -46.73282338197084), (-23.558969465794238, -46.71578599036997) | Graus (°), minutos (′) e segundos (″)
| 4 - Zonas essenciais | Coordenada | (-23.544964279357846, -46.73359585813633), (-23.54110865719845, -46.7284460168464), (-23.546774022201166, -46.71660138187955), (-23.551337610929664, -46.7229528528038) | Graus (°), minutos (′) e segundos (″)
| 5 - Ponto de partida | Coordenada | (-23.588333, -46.658890) | Graus (°), minutos (′) e segundos (″)
| 6 - Ponto de destino | Coordenada | (-23.5767, -46.6878) | Graus (°), minutos (′) e segundos (″)

## Outputs

| CARACTERÍSTICA | TIPO | EXEMPLO | UNIDADE |
|----------------|------|---------|---------|
| Altura(esperada) | NUMBER(double) | 1928 (valor esperado) | Metros
| Trajetória | Imagem | ------------------------| Km

## Localização dos dados no github

|--> src/main<br>
  &emsp;| --> resources/dted <br>
  &emsp;&emsp;| -->T3_G3_V5_IoT_Document.pdf<br>
  &emsp;&emsp;&emsp;| Rio<br>
  &emsp;&emsp;&emsp;| SP<br>
<br>

## Representação visual inicial (Neo4j)

A representação visual inicial do problema pode ser visualizada abaixo ou, se preferível, gerada através dos comandos [descritos aqui](https://github.com/2023M5T1-Inteli/grupo2/blob/master/src/cql/db.cql) ou no link https://github.com/2023M5T1-Inteli/grupo2/blob/master/src/cql/db.cql.

![Representação visual](https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/graph.png?raw=true)

## Persistência dos dados

Os dados são salvos e persistidos em uma instância AuraDB do Neo4j. Apenas os caminhos calculados são enviados, e sua representação é feita através de diversas relações "CONNECT_TO" entre os vértices, assim como demonstrado nas imagens abaixo:

![Map](https://user-images.githubusercontent.com/64558682/221622242-c15b6b79-a711-45df-9dbd-33a2f7b17e4d.png)
![Zoom](https://user-images.githubusercontent.com/64558682/221622318-bbc18bf8-7803-47c6-9fb7-6d5ba7a76582.png)
![Graph](https://user-images.githubusercontent.com/64558682/221622367-7556c8b9-77e4-4b05-8b52-13c0af0d4d4c.png)


# Limitações e modelagem matemática

Desenvolver uma solução eficaz para o problema de caminho mínimo apresenta complexidade elevada, ainda mais quando adicionamos features como optar pela menor altura possível. A memória é intensivamente usada durante o desenvolvimento dos algoritmos, o que pode causar desempenho lento e dificultar a escalabilidade da solução.

Devido à alta complexidade da solução e à falta de familiaridade da equipe de desenvolvimento com as ferramentas em uso, erros podem ocorrer na escolha do melhor algoritmo e na otimização de sua eficiência. Fatores externos, como dificuldades de terreno e condições climáticas, também devem ser levados em consideração ao planejar o trajeto.
<br>
<br>
## Modelagem matemática

Definimos o grafo como um conjunto de vértices e arestas, onde cada vértice representa um ponto no caminho e cada aresta representa o custo (ou distância) entre dois pontos. O problema consiste em encontrar o caminho mínimo do ponto A ao ponto F, ou seja, o caminho que minimize a soma dos custos das arestas percorridas.
<br>
<br>
### Variáveis de decisão
As variáveis de decisão são x_ij, variáveis binárias que indicam se a aresta que liga o vértice i ao vértice j faz parte do caminho ou não.
<br>
<br>
### Função objetivo
O objetivo é minimizar a soma dos custos das arestas selecionadas. Para isso, utilizamos a seguinte fórmula:

min Z = ∑(i,j)∈E c_ij * x_ij

Onde E é o conjunto de todas as arestas do grafo, c_ij é o custo da aresta que liga o vértice i ao vértice j e x_ij é a variável de decisão que indica se a aresta (i,j) é selecionada ou não.
<br>
<br>

### Restrições
Para as restrições, devemos garantir que cada vértice tenha exatamente uma aresta de entrada e uma de saída, exceto pelos vértices A e F, que podem ter apenas uma aresta de entrada ou uma de saída, respectivamente. Para isso, utilizamos as seguintes restrições:

∑(i,j)∈E x_ij = 1, para todo vértice i ≠ A,F

∑(A,j)∈E x_Aj = 1

∑(i,F)∈E x_iF = 1

Onde x_Aj é a variável de decisão que indica se a aresta que liga o ponto A ao vértice j é selecionada e x_iF é a variável de decisão que indica se a aresta que liga o vértice i ao ponto F é selecionada.

Também devemos garantir que não haja ciclos no caminho selecionado, para isso utilizamos a seguinte restrição de fluxo:

∑(i,j)∈P x_ij ≤ |P| - 1, para todo subconjunto de vértices P ⊆ V

Onde |P| é o número de vértices em P.
<br>
<br>

### Detalhamento das restrições
A primeira restrição, que afirma que cada vértice deve ter exatamente uma aresta de entrada e uma de saída, exceto pelos vértices A e F, que podem ter apenas uma aresta de entrada ou uma de saída, respectivamente, garante que o caminho encontrado seja uma rota de A a F sem retornos ou desvios desnecessários.

As restrições adicionais referentes a x_Aj e x_iF asseguram que a rota inicie em A e termine em F, pois essas arestas são as únicas que podem ter uma ponta livre e, portanto, devem ser incluídas no caminho.

A restrição de fluxo, por sua vez, evita a formação de ciclos no caminho, garantindo que cada subconjunto de vértices selecionados tenha no máximo |P| - 1 arestas, onde |P| é o número de vértices no subconjunto. Isso significa que o caminho não pode "voltar" em si mesmo, passando por um mesmo vértice duas vezes, por exemplo.

Dessa forma, as restrições garantem que a solução encontrada respeite as regras do problema de caminho mínimo em um grafo e que a solução seja ótima, minimizando a soma dos custos das arestas selecionadas.
<br>
<br>

### Conclusão
Dessa forma, o problema matemático pode ser resolvido por um algoritmo de caminho mínimo, como o algoritmo de Dijkstra ou o algoritmo de Bellman-Ford, que irá encontrar o caminho mínimo entre o ponto A e o ponto F.
<br>
<br>


# Referências
