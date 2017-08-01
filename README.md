#Burguer Test App

Travis CI for this code (Sonar and compile)
Como ambiente de CI, escolhi dois serviços da nuvem como o Travis e o Sonar IO.

Build Status ![alt text](https://travis-ci.org/mateuscoradini/burguer-test.svg?branch=master)

<h5> Sonar Status: </h5>
https://sonarcloud.io/dashboard?id=br.com.burguer.test%3Aburguer-test


<h1>Compile </h1>

mvn clean install

<h1> Execute </h1>
java -jar target/burguer-test-0.0.1-SNAPSHOT.jar
or
mvn spring-boot:run

See: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html


<h2> A spring boot web application, based on a simple hamburger shop model</h2>

Como idéia inicial, para entender e definir o negócio, foi decidido como escolha inicial usar as tecnologias de Java 8, Maven, HTML, CSS (Bootstrap) e Javascript, JQuery.
Então iniciei esse projeto como prototipação do negócio e estudo do framework e tecnologias escolhidas.

- Java 8
	- Implementação das novas features da linguagem.

- Spring Boot
  - Com o spring boot, toda parte de configuração pode ser realizar com um simples projeto de foundation gerado a partir da própria pagina do spring initilizer. https://start.spring.io/
  - Modelo MVC, Controllers, Models e Services são as camadas que foram definidas neste projeto.
  - Base em memória, usando H2
  
- Thymeleaf
	- Motor de regras para ambientes web ou standalone.
  
- Bootsrap Layout
  - Layout responsivo aplicado a qualquer plataforma
  - Boa documentação
 
- JQuery
  - Mais conhecido e mais rica biblioteca de javascript.

- Regras de negocio proposto como modelo, especialidade de venda de lanches.

	- Inicialização de carga inicial com os lanches e ingredientes padrões PiratesBurguerLoader.java
	- Java packages inclusos: Controller, Service, Repository, Model.
		- Composições das camadas de cada negócio MVC do négocio.
	- Regras Implementadas e teste unitários
		- Valor dos lanches de cardápio e soma dos ingredientes que compõe o lanche, PedidoServiceTest.testPriceFinal.class
		- Promoção Light PedidoServiceTest.testPriceFinalDesconto10.class
		- Promoção Muito Queijo PedidoServiceTest.testPriceFinalComMuitoQueijo.class
		- Promoção Muitar Carne PedidoServiceTest.testPriceFinalComMuitaCarne.class
		
		

		
#Bugs to fix
 Os bugs listados aqui, estão abertos como Issues desse projeto.

 - List default hamburguer cart - Precisa de tratamento na lista de hamburguers padrões que esta salvando como alteração após inserir ingrediente.
 - Edit ingredient value front end page - Necessita de um tratamento para enviar ao controler as informações para editar e salvar o valor do ingrediente a ser editado.
 - Layout CSS - Necessita de um tratamento para estilizar melhor a página, por enquanto encontra-se despadronizada.
 - Sonar code quality - Necessita de tratamento para garantir a qualidade de código acima de 80% de cobertura e tratamento das issues geradas.
 - Refresh Lists - Necessita de tratamento no front para garantir a maneira correta de realizar o refresh após a requisição ajax.
 - Order Lists - Necessita de ordenação, pois estão retornando diretamente um Set sem qualquer tipo de tratamento.
 
 



