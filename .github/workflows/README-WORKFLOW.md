### Na página de configuração do ambiente no GitHub, siga estes passos para adicionar suas secrets (como AWS_HOST):

### Vá até “Environment secrets”
### Clique em “Add secret” ou “Novo segredo”.

### Adicione cada secret necessária:

- **Nome:** AWS_HOST
    - **Valor:** IP ou domínio da sua instância EC2
- **Nome:** AWS_USER 
    - **Valor:** Usuário SSH (ex: ec2-user)
- **Nome:** AWS_SSH_KEY
    - **Valor:** Cole o conteúdo da sua chave privada SSH (começa com -----BEGIN OPENSSH PRIVATE KEY-----)

Salve cada secret
Repita para todas as variáveis sensíveis que o workflow precisa.

`Essas secrets ficarão disponíveis apenas para os workflows que rodarem nesse ambiente.`
Não é necessário configurar “Required reviewers”, “Wait timer” ou “custom rules” para o deploy funcionar, a menos que queira aprovações extras ou regras de proteção.

`Se quiser limitar os branches que podem fazer deploy, configure em “Deployment branches and tags”.`

**Resumo:**
Adicione as secrets em “Environment secrets” e salve. O workflow já poderá usá-las!

##

### Para definir o valor da secret AWS_HOST:

Clique no nome do ambiente (no caso, AWS_HOST) na lista de ambientes.
Dentro do ambiente, procure a seção “Environment secrets”.
Clique em “Add secret” ou “Novo segredo”.
No campo “Name”, coloque AWS_HOST.
No campo “Value”, insira o valor desejado (por exemplo, o IP ou domínio da sua instância EC2).
Clique em “Add secret” ou “Salvar”.
Se já existe a secret, você pode clicar nela para editar ou atualizar o valor.

`Repita esse processo para adicionar as outras secrets (AWS_USER, AWS_SSH_KEY).`