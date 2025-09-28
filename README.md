# Endereco_jUnit
# 📍 API de Gerenciamento de Endereços

API RESTful para cadastro, consulta, atualização e remoção de endereços, desenvolvida em **Java 17+** com **Spring Boot**.  
O projeto enfatiza **testes unitários** com **JUnit 5** e **Mockito**, garantindo qualidade e confiabilidade no código.

---

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Web & Spring Data JPA**
- **Hibernate Validator** (Bean Validation)
- **Banco de Dados**: H2 (padrão, em memória) ou MySQL
- **JUnit 5** e **Mockito** para testes
- **Lombok** para redução de boilerplate
- **Swagger/OpenAPI** para documentação

---

## ⚙️ Requisitos
- **Java 17+**
- **Maven 3.8+**
- Banco de dados configurado (ou use H2 para testes)

---

## 🧩 Rotas da API

Base URL: **`http://localhost:8080/endereco`**

| Método     | Endpoint        | Descrição                       |
|------------|-----------------|----------------------------------|
| **POST**   | `/add`          | Cadastra um novo endereço        |
| **PUT**    | `/edit/{id}`    | Atualiza um endereço existente   |
| **GET**    | `/show-All`     | Lista todos os endereços         |
| **GET**    | `/show/{id}`    | Busca um endereço por ID         |
| **DELETE** | `/delete/{id}`  | Remove um endereço por ID        |
| **DELETE** | `/delete-all`   | Remove todos os endereços        |

---
## ▶️ Inicialização do Projeto

### Clonar o repositório
```bash
git clone https://github.com/devSalles/Endereco_jUnit.git

cd Endereco_jUnit

spring.datasource.url=jdbc:mysql://localhost:3306/endereco
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update

mvn spring-boot:run

http://localhost:8080/endereco
