
#   **Projeto: Atividade Avaliativa sobre REST**

###  - **Roteiro de Implantação:**
**1. Pré-requisitos:**
* docker;
* maven;
* git (ou fazer download do *.zip* a partir desta página).  

**2. Como executar a aplicação:**  
* Execute no diretório deste projeto o arquivo de implantação com o comando:    
    `sh run.sh`  
* Para terminar a execução da aplicação, utilize o comando:  
    `sh kill.sh`
  
**3. Link de acesso à API:**  http://localhost:8081/pos-atividade-rest/resource
- Para criar o usuário e acessar a API:  
    `POST /usuario, com form urlencoded email, senha`
- Para acessar as demais páginas: 
    `Basic Token ` recebido ao criar o usuário no sistema.

**3. Link de acesso à API de consultas:**  http://localhost:8081/pos-atividade-rest/resource/consulta
- **Clientes:** /cliente
    - **por nome:** /nome/{nome}
    - **por e-mail:** /email/{email}
    - **por CPF:** /cpd/{cpf}
- **Produtos:** /produto
    - **por ID:** /id/{id}
    - **por palavras contidas na descrição:** /descricao/{key}
    - **por nome:** /nome/{nome}
    - **por preço:** /preco?inicio={inicio}&fim={fim}

