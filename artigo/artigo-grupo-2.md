---


title: Modelo para o artigo do Módulo 5
author: 
    - "Beny Frid"
    - "Elias Biondo"
    - "Luiz Carlos"
    - "Pedro Munhoz"
    - "Pedro Romão"
    - "Rafael Cabral"
    - "Thomas Barton"
    - "Vinícius Souza" 
date: Janeiro de 2023
abstract: Neste estudo, apresentamos um algoritmo de planejamento de trajetórias de aeronaves, em baixa altitude. A abordagem é baseada em grafos, utilizando o algoritmo AStar em conjunto com a estrutura de dados TreeSet, permitindo otimizar a rota da aeronave para se manter em baixa altitude e evitar a detecção por radares inimigos. A solução proposta demonstrou ser eficiente e robusta, exibindo tempos de execução significativamente menores e um desempenho superior em comparação a outras abordagens, como a PriorityQueue. Esta pesquisa aprimora o voo em condições restritivas e contribui para o avanço das tecnologias de planejamento de trajetórias em ambientes incertos.
---

# Introdução

<p> Para lidar com esses desafios, algoritmos de planejamento de trajetórias têm sido desenvolvidos para auxiliar na melhor execução de voos sobre condições restritivas, como a baixa atitude. A exemplo, M. Ryll et al. (2019) [1] evidencia os estudos para o planejamento de rotas para micro veículos aéreos em ambientes incertos e sem acesso a GPS, permitindo um voo com altas velocidades e sem riscos de colisão. De forma análoga, o algoritmo do presente artigo visa minimizar os impactos decorrentes de erros estratégicos na elaboração das rotas, e, para isso, é explorada uma metodologia baseada em grafos, o que permite representar o ambiente do voo em um conjunto de nós e arestas, onde cada nó representa uma posição possível do avião e cada aresta representa uma possível rota entre duas posições. </p>

# Descrição do problema

<p> O parceiro de projeto cujo problema foi solucionado pelo presente artigo, AEL Sistemas, trouxe um projeto em que deve-se receber um arquivo de altitude, mapear esse arquivo como um grafo e, dado pontos de partida e de saída, traçar uma trajetória que otimize a exposição da aeronave enquanto ela se mantém na menor altitude possível, ou seja, vale-se utilizar do terreno, como montanhas, para manter a aeronave escondida ao mesmo tempo em que ela se mantém em uma baixa altitude para não ser reconhecida por radares inimigos.</p>

<p> Em ordem a definir o melhor caminho, um arquivo DT2 foi lido por uma classe confeccionada (Dted), a fim de obter-se uma visualização das coordenadas utilizadas como referência no estudo. Com o arquivo lido, foi possível criar um array e salvar nele todos os nós do grafo com suas respectivas distâncias até dado referencial, em metros, ordenando-os com base nas coordenadas de latitude e longitude. Na sequência, foi definido o peso de cada vértice, baseado na lógica matemática de representar o peso de cada aresta com base na altitude do nó final da conexão somado com a diferença de altitude do nó inicial com o nó final, para que o algoritmo possa escolher o menor caminho viável, isto é, aquele que possui a menor somatória dos pesos dos vértices. Após a ponderação, a classe Graph foi criada e nela foram adicionados todos os pontos lidos como classe Vertex, que armazena a latitude, longitude e altitude dos nós. Com todos os pontos adicionados, a classe Edge, pertencente a classe Vertex - que tem como variável o Vertex final da conexão e o peso -, foi desenvolvida para cada conexão. Com isso, foi possível enviar as informações para o algoritmo escolhido A* achar a rota com menor altitude para aviões. A escolha desse algoritmo é sustentada por testes executados (que serão abordados em seções futuras) em que foram comparadas rotas geradas entre a origem e o destino de diferentes algoritmos, sendo o A* o que obteve os resultados mais satisfatórios e eficazes. </p>

# Trabalhos relacionados

<p> Nosso projeto leva em conta a otimização de uma trajetória para aviões com a menor altitude possível, fazendo uso do algoritmo A* (A estrela), com uma lógica semelhante ao artigo de T. Chen et al. (2018) [2] que faz uso do mesmo algoritmo para aeronaves não tripuladas e considera consumo de combustível e menor perigo possível para a criação do seu algoritmo.</p>

# Descrição da estratégia adotada para resolver o problema

<p> A estratégia inicial de resolução do problema foi o melhor entendimento do contexto que cerca nosso software e, utilizando metódos ágeis, dividiu-se o trabalho em sprints. Foi feita uma análise da indústria, a fim de entender todos os pontos do mercado em que a empresa AEL Sistemas está envolvida, uma análise SWOT, para compreender o contexto do cliente inserido na respectiva arena competitiva, um Value Proposition Canvas, uma matriz de riscos, para ajudar a equipe no processo de decisões e, também, na documentação do projeto, uma descrição da solução a ser desenvolvida, com o objetivo de entender melhor o escopo e contexto do projeto desenvolvido neste artigo.</p>

<p> Na sequência, foi feita a modelagem e a representação do problema. Após estudo dos tipos existentes de grafos, confeccionamos um modelo matemático inicial para sermos capazes de entender a parte formal do projeto. A biblioteca escolhida para gerar a visualização do banco de dados, assim como para compreender como seria a entrega e o que o nosso cliente veria no produto final foi o Neo4J.</p>

<p> A nível de experiência do usuário, personas e user stories foram elaboradas. A saber, as duas personas que foram elaboradas como utilizadores da solução proposta neste artigo são: Gabriel Elfer, 35 anos, piloto militar que interage com a aplicação pela sua necessidade de ter uma trajetória eficiente e segura, e Fernando Alberto, 45 anos, militar na área de reconhecimento, que tem seu contato com o projeto pelo desafio atual que ele enfrenta, desenhar um caminho que ele acredite ser o mais seguro possível para que sua equipe de pilotos consiga fazer suas missões em segurança e agilidade. As user stories foram desenvolvidas no modelo INVEST (independente, negociável, de valor, estimável, pequena e testável), tendo como foco as personas e suas necessidades específicas para atingir a satisfação do usuário.</p>

<p> Uma vez feito esse primeiro contato com a solução, a segunda sprint de desenvolvimento foi direcionada para a criação em Java do que seria o começo do algoritmo. A biblioteca GDAL foi utilizada para ler os arquivos de altitude no formato DTED nível 2, que foi fornecido pela empresa parceira. Esse arquivo gera vértices em todo o espaço disponível, com uma distância definida de 180 metros entre cada ponto, que é utilizada para criar um grafo utilizando tipos de dados criados por nós, vértices e arestas.</p>

<p> O próximo passo natural foi a criação de um algoritmo que faça uma busca entre o menor caminho do vértice A até o B, embasado no algoritmo A* (A estrela). O algoritmo A* tem finalidade é de buscar entre os vértices caminhos que ligam um ponto inicial e um final do grafo, baseado em funções heurísticas. O objetivo em cada resolução de problema está na definição da função heurística que retorne um menor custo de deslocamento de um ponto de origem A até o destino B.</p>

# Análise da complexidade da solução proposta

<p> Define-se grafo como um conjunto de vértices e arestas, onde cada vértice representa um ponto no caminho e cada aresta representa o custo (ou distância) entre dois pontos. O problema consiste em encontrar o caminho mínimo do ponto $A$ ao ponto $F$, ou seja, o caminho que minimize a soma dos custos das arestas percorridas.</p>

<p> O objetivo é minimizar a soma dos custos das arestas selecionadas. Para isso, a seguinte fórmula foi utilizada: $\min Z = \sum_{(i,j)\in E} c_{ij} \cdot x_{ij}$. Onde $E$ é o conjunto de todas as arestas do grafo, $c_{ij}$ é o custo da aresta que liga o vértice $i$ ao vértice $j$ e $x_{ij}$ é a variável de decisão que indica se a aresta (i,j) é selecionada ou não. As variáveis de decisão são $x_{ij}$, variáveis binárias que indicam se a aresta que liga o vértice $i$ ao vértice $j$ faz parte do caminho ou não.</p>

<p> Para as restrições, deve-se garantir que cada vértice tenha exatamente uma aresta de entrada e uma de saída, exceto pelos vértices $A$ e $F$, que podem ter apenas uma aresta de entrada ou uma de saída, respectivamente. Para isso, as seguintes restrições foram redigidas:</p>
<p> - $\sum_{(i,j)\in E} x_{ij} = 1$, para todo vértice $i \neq A,F$</p>
<p> - $\sum_{(A,j)\in E} x_{Aj} = 1$</p>
<p> - $\sum_{(i,F)\in E} x_{iF} = 1$</p>
<p> Onde $x_{Aj}$ é a variável de decisão que indica se a aresta que liga o ponto $A$ ao vértice $j$ é selecionada e $x_{iF}$ é a variável de decisão que indica se a aresta que liga o vértice $i$ ao ponto $F$ é selecionada.</p>

<p>Também deve-se garantir que não haja ciclos no caminho selecionado, então, para isso, foi utilizada a seguinte restrição de fluxo:</p>
<p>$\sum_{(i,j)\in P} x_{ij} \leq P - 1$, para todo subconjunto de vértices $P \subseteq V$ onde $\lVert P \rVert$ é o número de vértices em $P$.</p>

# Análise da corretude da solução proposta

<p>Aqui vamos provar a corretude da solução proposta, mas antes de começar, é importante definir algumas notações que serão utilizadas na prova:</p>

<h4>&#x2022; g(n) representa o custo para chegar ao nó n a partir do nó inicial</h4>
<h4>&#x2022; h(n) é uma heurística que estima o custo para chegar do nó n até o objetivo</h4>
<h4>&#x2022; f(n) é a função de avaliação que combina g(n) e h(n), ou seja, f(n) = g(n) + h(n)</h4>
<h4>&#x2022; s é o nó inicial</h4>
<h4>&#x2022; t é o nó objetivo</h4>
<h4>&#x2022; n é qualquer nó do grafo</h4>
<br><br>

<p>A seguir, apresentaremos uma prova formal da corretude do algoritmo A*.</p>

<p><b>Teorema:</b> O algoritmo A* encontra sempre o menor caminho entre o nó inicial e o nó objetivo em um grafo com arestas de peso não negativo, desde que a heurística utilizada seja admissível. Para que a heurística seja admissível, ela não pode superestimar em nenhum momento o custo real do nó inicial ao nó objetivo, e como, na heurística, utilizamos um valor que também é utilizado calcular o custo real, a heurística é admissível.</p>

<p><b>Prova:</b>
Vamos provar este teorema por indução. Suponha que o nó objetivo t foi adicionado à lista de nós abertos e que o algoritmo A* escolheu um caminho p* para chegar até ele. Isto é, p* é o caminho mínimo que conecta s a t, de acordo com a função de avaliação f(n) = g(n) + h(n).</p>

<p><b>Base:</b> Quando o algoritmo A* escolhe o nó inicial s, a afirmação é trivialmente verdadeira, pois s é o único nó aberto naquele momento.
Hipótese: Suponha que a afirmação seja verdadeira para todos os nós até o nó k, ou seja, o algoritmo A* encontrou o caminho mínimo para cada nó na lista de nós abertos até o nó k.</p>

<p><b>Passo de indução:</b> Vamos provar que a afirmação é verdadeira para o nó k+1. Seja p* o caminho mínimo que conecta s a t, de acordo com a função de avaliação f(n) = g(n) + h(n). O nó k+1 é adicionado à lista de nós abertos e o algoritmo A* escolhe o caminho mínimo p que conecta s a k+1.</p>

<b>Temos duas possibilidades:</b>
<h4>&#x2022; Se o nó k+1 não estiver no caminho p*, então o algoritmo A* continua a busca até encontrar o nó objetivo t, adicionando os nós abertos à lista. Pela hipótese de indução, o algoritmo A* encontrará o caminho mínimo para cada nó na lista de nós abertos até o nó k+1. Portanto, o caminho mínimo de s a t será o caminho mínimo de s a k+1 (p) seguido do caminho mínimo de k+1 a t.</h4><br>

<h4>&#x2022; Se o nó k+1 estiver no caminho p*, então podemos escrever o caminho p* como p* = p1 + (k+1) + p2, onde p1 é o caminho mínimo de s a k+1 e p2 é o caminho mínimo de k+1 a t. Como p é o caminho mínimo de s a k+1, temos que g(k+1) + h(k+1) <= g(n) + h(n) para qualquer nó n na lista de nós abertos que ainda não foi expandido.</h4>

<p>Assim, temos que f(k+1) = g(k+1) + h(k+1) <= g(n) + h(n) para qualquer nó n na lista de nós abertos que ainda não foi expandido. Além disso, sabemos que f(n) >= f(k+1) para qualquer nó n na lista de nós abertos que já foi expandido. Portanto, a função de avaliação de qualquer nó na lista de nós abertos é maior ou igual a f(k+1).</p>

<p>Como a heurística é admissível, temos que h(k+1) <= h(n) + d(n,k+1) para qualquer nó n no grafo, onde d(n,k+1) é a distância do nó n até o nó k+1. Substituindo na equação anterior, temos que f(k+1) <= g(n) + h(n) + d(n,k+1). Como p é o caminho mínimo de s a k+1, temos que g(k+1) = g(p1) + d(p1,k+1), onde d(p1,k+1) é a distância entre os nós p1 e k+1. Assim, temos que f(k+1) <= g(p1) + h(n) + d(n,k+1) + d(p1,k+1).</p>

<p>Agora, seja p* um caminho mínimo de s a t que passa por k+1, ou seja, p* = p1 + (k+1) + p2. Podemos escrever f(t) = g(t) + h(t) <= g(p*) + h(p*) = g(p1) + d(p1,k+1) + g(p2) + h(k+1) + h(t). Como a heurística é admissível, temos que h(k+1) <= h(t) + d(k+1,t), onde d(k+1,t) é a distância entre os nós k+1 e t. Substituindo na equação anterior, temos que f(t) <= g(p1) + d(p1,k+1) + g(p2) + h(t) + d(k+1,t).</p>

<p>Comparando as duas últimas equações, temos que f(t) >= f(k+1) - d(n,k+1) + d(p1,k+1) + g(p2) - d(k+1,t). Como o peso das arestas é não negativo, temos que d(p1,k+1) + g(p2) >= d(p1,k+1) + g(p2) - d(k+1,t) >= 0. Portanto, temos que f(t) >= f(k+1) - d(n,k+1), ou seja, f(k+1) + d(n,k+1) <= f(t).</p>

<p>Como o algoritmo A* escolhe sempre o nó com menor valor de f(n) na lista de nós abertos, ele encontrará o caminho mínimo de s a t</p>

# Resultados obtidos

<p>No presente estudo, foram realizados experimentos para avaliar a eficácia do algoritmo proposto de menor caminho baseado em grafos. Os resultados obtidos demonstram a capacidade do algoritmo em solucionar problemas de menor caminho em diferentes cenários e configurações de grafos. Nesta seção, apresentaremos uma análise detalhada dos resultados experimentais, destacando a performance do algoritmo em comparação com outras abordagens existentes, bem como as principais vantagens e limitações identificadas.</p>

<p>Com base nos estudos já referenciados neste artigo, foi constatado que a melhor abordagem para a nossa solução seria o uso do algoritmo AStar, que, por sua natureza, otimiza o tempo de execução e uso de memória. Sendo assim, buscamos maneiras de incrementar a sua performance através do uso de diferentes estruturas de dados para busca e ordenação, como a _PriorityQueue_ e a _TreeSet_. Sabendo disso, todas os testes têm, por competência, a função de comparar essas duas estruturas.</p>

<p>O ambiente de teste utilizado foi um notebook Acer Aspire 5, com core I5-9300h e 16 Gb de memória RAM a 2400 GHz em dual channel. Isso será relevante para exemplificar o tempo de execução, que foi uma das maneiras usadas para analisar a escolha final do código, contudo, para uma metodologia mais assertiva, usamos também a experimentação baseada em passos de processamento, ou seja, quantas voltas o algoritmo realizou para finalizar uma tarefa e isso torna a análise mais assertiva.</p>

<p>Após essa introdução, podemos inserir o primeiro teste experimental, que visa comparar o tempo de execução. Para isso, selecionamos o ponto inicial do mapa, cujo vértice tem índice 0 (zero) e um ponto final qualquer, que segue uma sequência lógica que será exemplificada logo.</p>

![Temp execução Priority](https://raw.githubusercontent.com/2023M5T1-Inteli/grupo2/sprint4/docs/img/execPriority.jpg)

![Temp execução Tree](https://raw.githubusercontent.com/2023M5T1-Inteli/grupo2/sprint4/docs/img/execTree.jpg)

<p>Nesses dois gráficos, é possível observar que as linhas de tendências têm características distintas. Na PrioriryQueue o crescimento do tempo é exponencial de complexidade O(e^n), sendo n a quantidade de dados de entrada e os resultados vão até a distância de 4,4 Km,  pois esse foi o limite em que enocntramos dado nosso ambiente de trabalho, após isso o sistema acusou um erro de quantidade de memória. Enquanto que na TreeSet nós vemos um crescimento linear de complexidade O(n). Dada essas duas informações, é importante resaaltar a magnitude dos dados coletados, o pior caso da primeira estrutura levou 123,3 segundos para ser processado enquanto que a segunda levou apenas 0,006 segundos.</p>

<p>Com base nesse experimento, podemos concluir que a melhor opção de escolha é a TreeSet, por dois motivos, o primeiro é que ela foi a única que conseguiu processar todos os dados que precisamos trabalhar. A segunda é que para 518.400 pontos, que significam X distância, foi necessário apenas 1,045 segundos, que está dentro das restrições impostas pelo parceiro (O caminho ser gerado em até 1 minuto).</p>

![Temp execução Tree](https://raw.githubusercontent.com/2023M5T1-Inteli/grupo2/sprint4/docs/img/execTree518400.jpg)

<p>Por fim, é válido, também, verificar a quantidade de passos do algoritmo. Essa é uma verificação interessante, pois, não fica limitada ao ambiente de trabalho usado neste experimento e sua análise será feita logo após o experimento abaixo.</p>

PriorityQueue:

![Temp execução Tree](https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/passosMelhor.jpg)

TreeSet:

![Temp execução Tree](https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/passosMelhor.jpg)

<p>No melhor caso, a quantidade de passos entre as duas estruturas é exatamente a mesma, ou seja, o Ômega é constante em 4 passos. Isso ocorre pela estrutura pela qual o código foi desenhado, em que, para qualquer tamanho de entrada do grafo, o melhor caso sempre estará ao seu lado e, para identificálo, o loop fará 4 verificações, que são os 4 vértices adjascêntes.</p>

PriorityQueue:

![Temp execução Tree](https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/passosPiorPriority.jpg)

TreeSet:

![Temp execução Tree](https://github.com/2023M5T1-Inteli/grupo2/blob/master/docs/img/passosPiorTree.jpg)

<p>Contudo, como observado acima, para o pior caso a quantidade de passos da PriorityQueue é 34,27 vezes maior que a TreeSet e essa evidência justifica a nossa escolha final pelo segundo caso, pois, em qualquer ambiente essa será a opção menos custosa. Além disso, poderíamos, também, observar o Theta, que é relativo a média de entradas, contudo, os dados atuais não permitem que esse cálculo seja realizado, pois, como o nosso algoritmo não foi implementado em produção ainda, não conseguimos ter acesso a uma quantidade significativa de entradas.</p>

# Conclusão

<p>Os resultados obtidos neste estudo indicam que a utilização do algoritmo AStar, em combinação com a estrutura de dados TreeSet, proporciona uma solução eficiente e robusta para problemas de menor caminho em grafos. A análise dos experimentos realizados demonstrou que a implementação com TreeSet apresenta um desempenho superior, tanto em termos de tempo de execução quanto de passos de processamento, quando comparada à implementação com PriorityQueue.</p>

<p>Os tempos de execução observados para a solução baseada em TreeSet foram significativamente menores, com um crescimento linear em relação ao aumento do número de pontos. Além disso, essa abordagem foi capaz de processar todos os dados necessários, cumprindo as restrições de tempo estabelecidas pelo parceiro. No pior caso, a quantidade de passos da TreeSet foi 34,27 vezes menor que a PriorityQueue, o que também reforça sua escolha como a opção menos custosa. E, por isso, essa escolha se prova eficiente.</p>

# Referências Bibliográficas

<p> [1] M. Ryll, J. Ware, J. Carter and N. Roy, "Efficient Trajectory Planning for High Speed Flight in Unknown Environments," 2019 International Conference on Robotics and Automation (ICRA), Montreal, QC, Canada, 2019, pp. 732-738, doi: 10.1109/ICRA.2019.8793930. </p>

<p> [2] T. Chen, G. Zhang, X. Hu and J. Xiao, "Unmanned aerial vehicle route planning method based on a star algorithm," 2018 13th IEEE Conference on Industrial Electronics and Applications (ICIEA), Wuhan, China, 2018, pp. 1510-1514, doi: 10.1109/ICIEA.2018.8397948. </p>
