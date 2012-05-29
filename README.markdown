JSF Issue Tracker Project
=========================

- Projeto simples de uma Issue Tracker com `JSF 2.x`, `Spring 3.x` e `Hibernate 3.6.x`;
- Projeto construído durante o curso de JSF 2, Spring e Hibernate na [TriadWorks](http://www.triadworks.com.br);
- Como eu sou um péssimo Web designer, o design da aplicação foi copiado (e levemente modificado) do projeto [vraptor-blank-project](http://vraptor.caelum.com.br/en) da Caelum;

Configurando o projeto e banco de dados.
----------------------------------------

Por padrão o projeto está configurado para o banco de dados `PostgreSQL`, mas já que se trata de uma aplicação com Hibernate, você pode simplesmente configura-lo para trabalhar com qualquer outro banco.

Os passos básicos são:

1. Importe o projeto no **Eclipse Java EE IDE for Web Developers (Indigo)** ou superior; 
2. Adicione o JDBC driver no diretório `/WebContent/WEB-INF/lib`;
3. Configure as informações do banco no arquivo `src/jdbc.properties`;
4. Crie o banco de dados **issuetracker** com a ferramenta de sua preferência (como o PGAdmin, no caso do PostgreSQL);
5. Faça o deploy no `Apache Tomcat 7.x` e inicie o servidor;
6. Cadastre no banco de dados um novo usuário (tabela `USUARIO`) para que seja possível logar na aplicação;
7. Acesse a aplicação através da url [http://localhost:8080/issuetracker](http://localhost:8080/issuetracker) ;
8. Faça o login com o usuário criado;

** Dentro do diretório `/etc/lib/jdbc-drivers` é possível encontrar alguns drivers já disponíveis, como MySQL, PostgreSQL e Oracle;

Gerando .war da aplicação
------------------------
1. Para gerar o `.war` da aplicação basta executar o ant script (`build.xml`) no Eclipse ou na linha de comando:

		$ ant

2. Após ter executado o ant script o `.war` será gerado em `/target/war/snapshot/issuetracker.war`;

Mais informações
----------------

**TriadWorks**
- http://www.triadworks.com.br