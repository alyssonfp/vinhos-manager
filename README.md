# Vinhos Sales API - Documentação
Este projeto consiste em dois serviços principais: Serviço 1 (Produto) e Serviço 2 (Pagamento), que compõem um sistema de vendas de vinhos. A arquitetura foi desenvolvida utilizando Java, Spring Boot WebFlux, MongoDB como banco de dados reativo, e RabbitMQ para comunicação assíncrona entre os serviços.

## Arquitetura
A arquitetura escolhida é baseada em microsserviços, onde cada serviço é independente e responsável por uma parte específica do sistema. Utilizamos WebFlux para criar APIs reativas e não bloqueantes, garantindo alta performance e escalabilidade.

- MongoDB foi utilizado como banco de dados para armazenar as informações de produtos, categorias, carrinhos e pedidos de forma reativa.
- RabbitMQ foi utilizado para gerenciar a comunicação assíncrona entre os serviços de Produto e Pagamento, permitindo o envio e recebimento de mensagens de forma eficiente.

## Massa de Dados
Uma massa inicial de dados foi criada no arquivo vinhos.json, localizado na raiz do projeto. Este arquivo contém 40 vinhos organizados em diferentes categorias. O sistema carrega esses dados automaticamente ao iniciar.

## Regras Implementadas
- Listar Produtos Disponíveis: Apenas produtos com quantidade disponível são listados ao buscar por categoria.
- Decrementar Quantidade do Produto: Ao concluir um pedido com pagamento bem-sucedido, a quantidade do produto no estoque é decrementada.
- Incrementar Quantidade em Caso de Erro no Pagamento: Se o pagamento de um pedido falhar, a quantidade do produto é incrementada de volta ao estoque.
- Verificar Disponibilidade de Produto ao Criar Pedido: O sistema verifica se há disponibilidade suficiente do produto antes de criar um pedido.

## Comunicação entre Serviços
- Ao criar um pedido, uma mensagem é enviada para uma fila no RabbitMQ indicando que o pedido está aguardando pagamento.
- O serviço de Pagamento consome esta mensagem, processa o pagamento, e envia uma nova mensagem com o status do pagamento.
- O serviço de Produto recebe o status do pagamento e atualiza a quantidade dos produtos no estoque conforme o resultado do pagamento.

## Pré-requisitos

Antes de executar a aplicação, certifique-se de ter os seguintes softwares instalados:

- Java 17
- Maven
- MongoDB
- RabbitMQ

## Instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/vinhos-sales-api.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd vinhos-sales-api
    ```
3. Construa o projeto utilizando Maven:
    ```bash
    mvn clean install
    ```
4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints da API

### Endpoints do Carrinho

- **Criar Carrinho**
    - **POST** `/carrinhos`
    - **Body**:
      ```json
      {
        "produtos": [],
        "quantidade": 0
      }
      ```
    - **Resposta**:
      ```json
      {
        "id": "carrinho-id",
        "produtos": [],
        "dataCriacao": "data-criacao",
        "dataAlteracao": "data-alteracao"
      }
      ```

- **Buscar Carrinho por ID**
    - **GET** `/carrinhos/{id}`
    - **Resposta**:
      ```json
      {
        "id": "carrinho-id",
        "produtos": [],
        "dataCriacao": "data-criacao",
        "dataAlteracao": "data-alteracao"
      }
      ```

- **Excluir Carrinho**
    - **DELETE** `/carrinhos/{id}`
    - **Resposta**:
      ```json
      {
        "message": "Carrinho excluído com sucesso"
      }
      ```

- **Adicionar Produto ao Carrinho**
    - **PUT** `/carrinhos/{id}/produto/{produtoId}?quantidade={quantidade}`
    - **Resposta**:
      ```json
      {
        "id": "carrinho-id",
        "produtos": [
          {
            "id": "produto-id",
            "nome": "nome-produto",
            "descricao": "descricao-produto",
            "quantidade": 2,
            "valor": 50.0,
            "categoriaId": "categoria-id"
          }
        ],
        "dataCriacao": "data-criacao",
        "dataAlteracao": "data-alteracao"
      }
      ```

- **Remover Produto do Carrinho**
    - **DELETE** `/carrinhos/{id}/produto/{produtoId}`
    - **Resposta**:
      ```json
      {
        "id": "carrinho-id",
        "produtos": [],
        "dataCriacao": "data-criacao",
        "dataAlteracao": "data-alteracao"
      }
      ```

### Endpoints da Categoria

- **Buscar Todas as Categorias**
    - **GET** `/categorias`
    - **Resposta**:
      ```json
      [
        {
          "id": "categoria-id",
          "nome": "nome-categoria",
          "descricao": "descricao-categoria"
        }
      ]
      ```

- **Buscar Categoria por ID**
    - **GET** `/categorias/{id}`
    - **Resposta**:
      ```json
      {
        "id": "categoria-id",
        "nome": "nome-categoria",
        "descricao": "descricao-categoria"
      }
      ```

- **Criar Categoria**
    - **POST** `/categorias`
    - **Body**:
      ```json
      {
        "nome": "nome-categoria",
        "descricao": "descricao-categoria"
      }
      ```
    - **Resposta**:
      ```json
      {
        "id": "categoria-id",
        "nome": "nome-categoria",
        "descricao": "descricao-categoria"
      }
      ```

- **Excluir Categoria**
    - **DELETE** `/categorias/{id}`
    - **Resposta**:
      ```json
      {
        "message": "Categoria excluída com sucesso"
      }
      ```

### Endpoints do Pedido

- **Criar Pedido**
    - **POST** `/pedidos`
    - **Body**:
      ```json
      {
        "carrinho": {
          "id": "carrinho-id",
          "produtos": [
            {
              "id": "produto-id",
              "quantidade": 2
            }
          ]
        },
        "status": "WAIT_PAYMENT"
      }
      ```
    - **Resposta**:
      ```json
      {
        "id": "pedido-id",
        "carrinho": {
          "id": "carrinho-id",
          "produtos": [
            {
              "id": "produto-id",
              "nome": "nome-produto",
              "descricao": "descricao-produto",
              "quantidade": 2,
              "valor": 50.0,
              "categoriaId": "categoria-id"
            }
          ],
          "dataCriacao": "data-criacao",
          "dataAlteracao": "data-alteracao"
        },
        "dataCriacao": "data-criacao",
        "status": "WAIT_PAYMENT"
      }
      ```

- **Buscar Pedido por ID do Carrinho**
    - **GET** `/pedidos/carrinho/{carrinhoId}`
    - **Resposta**:
      ```json
      {
        "id": "pedido-id",
        "carrinho": {
          "id": "carrinho-id",
          "produtos": [],
          "dataCriacao": "data-criacao",
          "dataAlteracao": "data-alteracao"
        },
        "dataCriacao": "data-criacao",
        "status": "ORDER_STATUS"
      }
      ```

### Endpoints do Produto

- **Buscar Produtos por Categoria**
    - **GET** `/produtos/categoria/{categoriaId}`
    - **Resposta**:
      ```json
      [
        {
          "id": "produto-id",
          "nome": "nome-produto",
          "descricao": "descricao-produto",
          "quantidade": 10,
          "valor": 50.0,
          "categoriaId": "categoria-id"
        }
      ]
      ```

- **Criar Produto**
    - **POST** `/produtos`
    - **Body**:
      ```json
      {
        "nome": "nome-produto",
        "descricao": "descricao-produto",
        "quantidade": 10,
        "valor": 50.0,
        "categoriaId": "categoria-id"
      }
      ```
    - **Resposta**:
      ```json
      {
        "id": "produto-id",
        "nome": "nome-produto",
        "descricao": "descricao-produto",
        "quantidade": 10,
        "valor": 50.0,
        "categoriaId": "categoria-id"
      }
      ```

- **Atualizar Produto**
    - **PUT** `/produtos/{id}`
    - **Body**:
      ```json
      {
        "nome": "nome-produto",
        "descricao": "descricao-produto",
        "quantidade": 8,
        "valor": 45.0,
        "categoriaId": "categoria-id"
      }
      ```
    - **Resposta**:
      ```json
      {
        "id": "produto-id",
        "nome": "nome-produto",
        "descricao": "descricao-produto",
        "quantidade": 8,
        "valor": 45.0,
        "categoriaId": "categoria-id"
      }
      ```

- **Excluir Produto**
    - **DELETE** `/produtos/{id}`
    - **Resposta**:
      ```json
      {
        "message": "Produto excluído com sucesso"
      }
      ```
