# Sistema de Gestão de Eventos - API REST

Projeto desenvolvido para a disciplina de **Tópicos Especiais em Programação**, com o objetivo de criar uma API REST para gerenciamento de eventos, participantes e inscrições.

## 📌 Objetivo

Desenvolver uma aplicação backend utilizando o framework **Micronaut** e banco de dados **MySQL**, com foco em:

- Cadastro de eventos
- Cadastro de participantes
- Realização de inscrições
- Validações de integridade dos dados
- Retorno de mensagens de erro adequadas via HTTP

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Micronaut 4.8.3**
- **MySQL**
- **Maven**
- **JUnit 5** (para testes)
- **Hibernate JPA**
- **Micronaut OpenAPI** (para documentação)

## 📖 Estrutura de Entidades

### Evento

| Campo     | Tipo          | Regras                      |
| --------- | ------------- | --------------------------- |
| id        | Long (PK)     | Gerado automaticamente      |
| nome      | String        | Obrigatório, não duplicado  |
| data      | LocalDateTime | Obrigatório, não duplicado  |
| local     | String        | Obrigatório, não duplicado  |
| descricao | String        | Opcional                    |

### Participante

| Campo    | Tipo   | Regras                     |
|--------- |------ |-------------------------- |
| id       | Long  | Gerado automaticamente     |
| nome     | String | Obrigatório               |
| email    | String | Obrigatório, único        |
| telefone | String | Opcional                  |

### Inscrição

| Campo           | Tipo          | Regras                               |
|---------------- |------------- |------------------------------------ |
| id              | Long          | Gerado automaticamente              |
| evento_id       | Long (FK)     | Referência ao Evento                |
| participante_id | Long (FK)     | Referência ao Participante          |
| status          | String        | CONFIRMADA ou CANCELADA             |
| data_inscricao  | LocalDateTime | Registrada automaticamente          |

**Regra especial:** Não permitir o mesmo participante se inscrever duas vezes no mesmo evento.

## ✅ Funcionalidades principais

- **POST /participantes:** Cadastrar novos participantes.
- **POST /eventos:** Cadastrar novos eventos.
- **POST /inscricoes:** Inscrever um participante em um evento.

### Exemplo de Fluxo de Requisições

```bash
# Cadastro de Participante
POST /participantes
{
  "nome": "Werbton",
  "email": "werbton@email.com"
}

# Cadastro de Evento
POST /eventos
{
  "nome": "Tech Summit 2025",
  "data": "2025-10-10T09:00:00",
  "local": "São Paulo Expo"
}
```
## ⚠️ Tratamento de Erros

| Cenário                            | Código HTTP | Mensagem                                  |
|------------------------------------|-------------|-------------------------------------------|
| Participante com e-mail duplicado  | 409         | `E-mail já cadastrado.`                   |
| Evento duplicado                   | 409         | `Evento já existe para essa data/local.` |
| Inscrição duplicada                | 409         | `Participante já inscrito neste evento.` |
| Campos obrigatórios ausentes       | 400         | `Campos obrigatórios não preenchidos.`   |
| Entidade não encontrada            | 404         | `Participante/Evento não encontrados.`   |

---

