# Guia de Replicação da App - Circuitos Oceânicos

Este ficheiro descreve os passos necessários para replicar o sistema a partir do zero.

## Pré-requisitos

- Podman Desktop ou Docker Desktop instalado
- Acesso aos ficheiros da App

## Passos para replicar a aplicação

1. **Instalar o Podman Desktop ou o Docker Desktop**

   Certifique-se de que o Podman/Docker está corretamente instalado e funcional.

2. **Criar os contentores Podman/Docker**

   Abrir a linha de comandos na diretoria raíz da App e executar os comandos:
   ```bash
   cd CircPeticionario-WebApp
   call go.bat
   cd ..

   podman compose -f CircPeticionario-Compose.yaml -p circ_peticionario create
   ```
   Em alernativa ao comando `podman` pode utilizar o `docker`
   
1. **Iniciar os contentores previamente criados no Podman/Docker**

   No Podman Desktop/Docker Desktop, iniciar todos os contentores que foram criados:
   
   - `circ_peticionario_cnt_db`
   - `circ_peticionario_cnt_payara`
   - `circ_peticionario_cnt_app`

   Certifique-se de que todos estão com o estado "Running".
   
2. **Aceder à página de administração do Payara**

   No browser, aceder à página de administração do Payara: [http://localhost:4848](http://localhost:4848)

   Para fazer login, utilizar as seguintes credenciais:
   - **User Name**: `admin`
   - **Password**: `admin`
   - Pressionar o botão de Login.

3. **Confirmar que a App do Payara está instalad**

   Depois de autenticado, no menu vertical:
   
   - Selecionar **Applications**
   - A App `CircPeticionario.war` deverá estar disponível
   
4. **Criar um JDBC Connection Pool**

   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem. Caso se pretenda replicar num sistema totalmente novo:
   - Ainda na página de administração do Payara, no menu vertical, selecionar **JDBC** > **JDBC Connection Pools**
   - Clicar em **New**
   - Preencher os seguintes campos:
     - **Pool Name:** `CircuitoPeticionario`
     - **Resource Type:** `javax.sql.XADataSource`
     - **Database Driver Vendor:** `Postgresql`
   - Clicar em **Next**
   
  Na página seguinte:
   - Fazer scroll até à secção **Additional Properties**
   - Adicionar as seguintes propriedades:

     | Name           | Value                   |
     |----------------|-------------------------|
     | `portNumber`   | `5432`                  |
     | `serverName`   | `circ.peticionario.db`  |
     | `password`     | `postgres`              |
     | `databaseName` | `proj_fapesp`           |
     | `user`         | `postgres`              |

   - Clicar em **Finish**
   
7.  **Criar um JDBC Resource**

   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem. Caso se pretenda replicar num sistema totalmente novo:
   - Ainda na página de administração do Payara, no menu vertical, selecionar **JDBC** > **JDBC Resources**

   - Clicar em **New**
   - Preencher os seguintes campos:
     - **JNDI Name:** `jdbc/app_cpet`
     - **Pool Name:** `CircuitoPeticionario`
   - Clicar em **OK** para concluir o processo
   
8.  **Instalar o driver do Postgresql no Payara**
   
   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem.

9.  **Criar um Custom Resource no Payara (JNDI)**

   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem. Caso se pretenda replicar num sistema totalmente novo:

   - Voltar à página de administração do Payara: [http://localhost:4848](http://localhost:4848)
   - No menu vertical, selecionar **JNDI** > **Custom Resources**
   - Clicar em **New** e preencher os seguintes campos:
     - **JNDI Name**: `TOKEN_SERVICE`
     - **Resource Type**: `java.lang.String`
   - Ainda na mesma página, um pouco a baixo, no campo **Additional Properties** clicar em **Add Property** para adicionar uma nova propriedade:
     - **Name**: `value`
     - **Value**: (copiar o valor da variável `TOKEN_SERVICE` do ficheiro `.env` localizado na raiz do projeto)
   - Clicar em **OK** para concluir o processo
