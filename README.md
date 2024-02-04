# beneficiarios
Aplicação de cadastro de beneficiários


Projeto criado utilizado:
- Spring Boot
- Spring Security
- Spring JPA
- Banco de dados H2
- Swagger
- Lombok
- JWT

Para a execução do projeto:

- Instale a versão 17 do Java Development Kit (JDK)
- Importe o projeto contido neste repositório no InteliJ Idea ou outra IDE de preferência utilizando o arquivo pom.xml como referência de projeto.
- Execute a instalação das dependências através do Maven (reload project ou função similar)
- Execute o Projeto

Observaçoes:
- Ele foi criado para permitir apenas duas roles ADMIN E USER, onde a role admin terá acesso a todas as funcionalidades da aplicação.
- Esta aplicação foi criada para atender os requisitos exigidos no teste em questão.


============================================================
API de criação e usuários:
http://localhost:8080/usuarios (post)
Json:
{
    "nome": "admin",
    "login": "admin",
    "senha": "123",
    "role": "ADMIN"
}

{
    "nome": "user",
    "login": "user",
    "senha": "123",
    "role": "USER"
}
============================================================

API para requisição do token:
http://localhost:8080/auth (post)

URL Swagger:
http://localhost:8080/swagger-ui/index.html

============================================================

**Exemplo de Json**

Criação de beneficiário com documento (conforme solicitado no teste):
{
  "dataNascimento": "1999-12-04",
  "documentos": [
    {
      "descricao": "DOCUMENTO 1",
      "tipoDocumento": "PAPEL"
    },    
    {
      "descricao": "DOCUMENTO 2",
      "tipoDocumento": "PDF"
    },
    {
      "descricao": "DOCUMENTO 3",
      "tipoDocumento": "XLSX"
    }
  ],
  "nome": "BENEFICIARIO 1",
  "telefone": "(11)3434-5566"
}


{
  "dataNascimento": "1986-05-20",
  "documentos": [
    {
      "descricao": "DOCUMENTO 11",
      "tipoDocumento": "PAPEL"
    },    
    {
      "descricao": "DOCUMENTO 12",
      "tipoDocumento": "PDF"
    },
    {
      "descricao": "DOCUMENTO 13",
      "tipoDocumento": "XLSX"
    }
  ],
  "nome": "BENEFICIARIO 2",
  "telefone": "(11)95534-0001"
}


Criação de documento para usuário:
{
  "beneficiarioId": 2,
  "descricao": "DOCUMENTO 123",
  "tipoDocumento": "DOCX"
}