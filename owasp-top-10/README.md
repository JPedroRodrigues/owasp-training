# OWASP Top 10 (2017): Um Breve Resumo

Sumário
---
- [A1 - Injection (SQL ou até em CLI)](#a1---injection-sql-ou-até-em-cli)
- [A2 - Broken Authentication](#a2---broken-authentication)

---

## A1 - Injection (SQL ou até em CLI)

### O que é

O SQL injection é uma prática em que um usuário, em um determinado ambiente que permita enviar uma entrada, escreve um script SQL de modo a burlar a consulta do sistema, a fim de obter informações valiosas sobre a base de dados, como credenciais de usuários.

### O que evitar

- Concatenar Strings;
- Confiar inteiramente ao interpretador a tarefa de execução de todas as instruções.

### Como evitar

- Validar entradas do usuário: é necessário tratar todo e qualquer input do usuário para que os dados recebidos sejam os esperados;
- Use APIs seguras ou ORMs, que já fazem a sanitização dos inputs para nós, como o PreparedStatement do Java ou Hibernate;
- Use *whitelists*, para ler somente parâmetros de interesse;
- Para qualquer query dinâmica, use o escape;
- Use sempre o LIMIT das consultas quando souber com exatidão a quantidade desejada de dados a serem entregues pelo sistema.

### Onde Testar

NÃO TESTE! Ou melhor: teste em programas ou ambientes em que você tenha direito de testar, menos em produção. Do contrário, será considerado um ataque e você responderá por isso.

## A2 - Broken Authentication

### O que é

Seu site permite senhas muito comuns? Atacantes podem se aproveitar dessa permissão para explorar o uso de senhas simplistas.

### O que evitar

- Permitir senhas fracas, muito comuns;
- Senhas padrão para recém-chegados usuários;
- Permite uma recuperação de credenciais, para casos de esquecimento de senha, de maneira fácil, simples;
- Armazenar senhas no banco de dados sem o uso de criptografia;
- Não possuir autenticação em mais de um fator ou uma ou mais que sejam simples;
- Expor IDs de sessão em URLs;
- Não invalidar tokens de autenticação quando há um tempo excedido de inatividade ou logout;

### Como evitar



### Onde Testar