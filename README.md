# Backlisp-pool

Esse projeto tem como objeto demonstrar a criação de uma api REST, utilizando o as seguintes ferramentas: 

* Framework Spring-boot
* Maven
* Banco de dados em memória H2
* Autenticação utilizando JWT
* BDD com o framework Spock, que possibilita a facilidade de escrever testes com a linguagem Groovy

A aplicação é um simples cadastros de boletos, com as seguintes operações: criação, consulta, cancelamento e pagamento. 

## Run app 

Após o build convencional da aplicação utilizando o Maven, o seguinte comando pode ser rodado dentro do diretório target para rodar a aplicação:  

````````````
 $ java -jar bankslips-pool-0.0.1-SNAPSHOT.jar
````````````
Além disso, aplicação pode gerar um war, alterando as configurações no pom.xml, tornando possível o deploy da mesma em um servidor de aplicação como o Tomcat, por exemplo. Outra alternativa seria rodar de dentro da IDE Eclipse, por meio de da opção _spring boot app_ ou _spring dev tools client_. Por fim, também dentro da IDE Eclipse, é possivel rodar o main que se encontra na classe  com.luanpontes.bankslipspool.BankslipsPoolApplication.

## Run testes

Todos os testes podem ser executados por meio do Maven, utilizando a opção _Run Maven Testes_, ou mesmo por meio do build do projeto. Todos os testes também podem ser executados individualmente utilizando o JUnit. 

## Postman

Dentro do diretório resources foi disponibilizado um arquivo _.json_ que pode ser importado pela ferramenta [Postman](https://www.getpostman.com), que facilita o teste e documentação da api.

Para todas as operações é necessário a realização do login para obtenção do token de acesso, que deve ser enviado como um cabeçalho com o nome _access-token_. 

### TODOs

 * Implementar testes de integração para os Controllers.
 * Revisar documentação de classes e métodos. 
