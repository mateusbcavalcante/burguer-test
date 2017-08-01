#Burguer Test App

Travis CI

<h5> Build Status: </h5>
![alt text](https://travis-ci.org/mateuscoradini/burguer-test.svg?branch=master)

<h5> Sonar Status: </h5>
https://sonarcloud.io/dashboard?id=br.com.burguer.test%3Aburguer-test


<h1>Compile </h1>

mvn clean install

<h1> Execute </h1>
run


<h2> A spring boot web application, based on a simple hamburger shop model</h2>

Como idéia inicial, para entender e definir o negócio, foi decidido como escolha inicial usar as tecnologias de Java 8, Maven, HTML, CSS (Bootstrap) e Javascript, JQuery.
Então iniciei esse projeto como prototipação do negócio e estudo do framework e tecnologias escolhidas.


- Spring Boot
  - Com o spring boot, toda parte de configuração pode ser realizar com um simples projeto de foundation gerado a partir da própria pagina do spring initilizer. https://start.spring.io/
  - Modelo MVC, Controllers, Models e Services são as camadas que foram definidas neste projeto.
  - Base em memória, usando H2
  

- Negocio proposto
Especialidade de venda de lanches

- Inicialização de carga inicial com os lanches e ingredientes padrões
  PiratesBurguerLoader.java


- Regras Implementadas e teste unitários
		- Valor dos lanches de cardápio e soma dos ingredientes que compõe o lanche, PedidoServiceTest.testPriceFinal.class
		- Promoção Light PedidoServiceTest.testPriceFinalDesconto10.class
		- Promoção Muito Queijo PedidoServiceTest.testPriceFinalComMuitoQueijo.class
		- Promoção Muitar Carne PedidoServiceTest.testPriceFinalComMuitaCarne.class
 



