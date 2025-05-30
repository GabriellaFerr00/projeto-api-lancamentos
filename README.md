# API Lancamentos

Esta API permite realizar lançamentos bancários (débito e crédito) e consultar saldos de contas de clientes. Vale salientar que atualmente o projeto está rodando com uma base de dados em memória.

#### **PROJETO**
### **Versões usadas**
* Java: 21
* Spring Boot: 3.5.0
### **Requisitos para rodas o projeto**
* Ter instalado na sua máquina o JDK 21: [Download JDK](https://www.oracle.com/br/java/technologies/downloads/#java21).
* IDE da sua preferência para rodar o código, no meu caso usei o Intellij: [Download Intellij IDEA](https://pages.github.com/](https://www.jetbrains.com/idea/download/?section=windows)https://www.jetbrains.com/idea/download/?section=windows).
* Software para consumir as requisições HTTP, no meu caso usei o postman: [Download Postman](https://www.postman.com/downloads/).
* Ferramenta de versionamento, usei o git: [Download Git](https://git-scm.com/)
* Maven instalado: [Download Maven](https://maven.apache.org/download.cgi)

### **Para rodar o projeto localmente, siga este passo a passo:**
* Clone o repositório do projeto para a sua máquina: [Clone HTTPS](https://github.com/GabriellaFerr00/projeto-api-lancamentos.git)
* Abra esse projeto na IDE que você preferir(No intellij ele automaticamente baixa as dependências e configurações necessárias para o projeto rodar)
* Import a Collection anexada nesse projeto no postman ou onde preferir.
* Rode a aplicação na branch Main e comece os testes.
##### **OBS: A aplicação usa banco de dados em memória, para efetuar os testes o projeto precisa ficar rodando. Ao matar o processo os dados serão perdidos.**

### **Para acessar o banco H2 em memória:**
* Inicie a aplicação
* Acesse o navegador e coloque o link: http://localhost:8080/h2-console/
* Certifique-se que a url seja: jdbc:h2:mem:banco, o user=SA e o password em branco
* Clique em connect e veja em tempo real as mudanças fazendo o select nas tabelas.

### **Links úteis do projeto**
* Documentação: https://github.com/GabriellaFerr00/projeto-api-lancamentos
* Swagger UI (documentação interativa):
  ➡️ http://localhost:8080/swagger-ui/index.html
* OpenAPI JSON (esquema):
  ➡️ http://localhost:8080/v3/api-docs


