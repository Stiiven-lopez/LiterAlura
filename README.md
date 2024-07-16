# LiterAlura

LiterAlura é um projeto Spring Boot que conecta você ao vasto mundo da literatura através da API do Project Gutenberg. Descubra, explore e salve seus livros e autores favoritos para criar sua própria biblioteca digital personalizada.

<center><img src="./.github/assets/badge literalura.png"></center>

## Recursos Principais:

- **Busca de Livros:** Encontre livros por título e explore detalhes como autores, gênero e data de publicação.
- **Salvar Livros e Autores:** Salve seus achados literários para fácil acesso e referência futura.
- **Listar Livros e Autores:** Navegue por sua biblioteca digital organizada.
- **Busca de Autores:** Encontre autores por nome e visualize os livros que eles escreveram.
- **Autores Vivos em Determinado Ano:** Descubra quais autores da sua biblioteca estavam vivos em um ano específico.

## Como Usar:

1. Configure o PostgreSQL:

   - Instale o PostgreSQL em seu sistema.
   - Crie um banco de dados para o LiterAlura.
   - Atualize as configurações de conexão no arquivo application.properties:

   ```bash
   spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/db
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

2. Clone o Repositório:

```bash
git clone https://github.com/rxpt/LiterAlura.git
```

3. Execute a Aplicação:

```bash
./mvnw spring-boot:run
```

4. Explore o Menu: A aplicação apresenta um menu interativo com opções para buscar livros, salvar favoritos, listar sua biblioteca e muito mais. Siga as instruções na tela para navegar pelas funcionalidades.

## Tecnologias Utilizadas:

- Spring Boot: Framework para desenvolvimento rápido de aplicações Java.
- Project Gutenberg API: Acesso a uma vasta coleção de livros de domínio público.
- JPA/Hibernate: Mapeamento objeto-relacional para persistência de dados.
- PostgreSQL: Sistema de gerenciamento de banco de dados relacional.
- Maven: Gerenciamento de dependências e build do projeto.

## Estrutura do Projeto:

- br.dev.rx.literalura.model: Modelos de domínio para representar livros e autores.
- br.dev.rx.literalura.repository: Repositórios para interagir com o banco de dados.
- br.dev.rx.literalura.service: Lógica de negócio para consumir a API e gerenciar dados.
- br.dev.rx.literalura.dto: Data Transfer Objects para transferência de dados entre camadas.

---

LiterAlura foi desenvolvido como parte do Desafio de gerenciamento de livros, proposto pela Alura em colaboração com a Oracle no programa ONE, como parte da especialização Back-End.
