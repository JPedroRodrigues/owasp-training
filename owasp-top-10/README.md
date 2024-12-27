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

- Permitir senhas fracas, muito comuns, ou forçar o uso de senhas muito complexas, o que desestimula a prática de variar senhas pelo usuário;
- Senhas padrão para recém-chegados usuários;
- Permite uma recuperação de credenciais, para casos de esquecimento de senha, de maneira fácil, simples;
- Armazenar senhas no banco de dados sem o uso de criptografia;
- Não possuir autenticação em mais de um fator ou uma ou mais que sejam simples (favoreça o "o que eu tenho, o que eu sou e o que eu sei");
- Expor IDs de sessão em URLs ou em logs;
- Não invalidar tokens de autenticação quando há um tempo excedido de inatividade ou logout;
- Pedir ao usuário redefinir a senha de tempos em tempos, reciclando a senha, o que favorece o uso de senhas fracas ou já utilizadas
- Não utilizar timeouts de sessão

### Como evitar
- Implementar multi-factor authentication. Isso evita força bruta, roubo de credenciais etc.;
- Não utilize credenciais padrão para adms;
- Implemente serviços de checagem de senhas comuns;
- Exigir tamanho mínimo de senha de acordo com o grau de criticidade das informações que podem ser acessadas pela credencial;
- Use as mesmas mensagens independentemente se uma conta está cadastrada ou não. Isso evita ataques em que é possível descobrir se um usuário existe por meio de mensagens de tentativa de login (ataques de enumeração);
- Limite ou estabeleça um delay para logins falhos. Log as tentativas falhas de login, sobretudo as que tentaram utilizar credenciais de admin;
- Use frameworks para gerenciar as session IDs, pois eles vão assegurar aleatoriedade dos IDs, ajudam a diminuir a entropia etc.

## A3 - Sensitive Data Exposure

### O que é

Nós não podemos armazenar senhas descriptografadas, não é? Alías, nós não podemos deixar de criptografar as informações durante as requisições e respostas do servidor. Isso evita ataques como *man in the middle*. **Sensitive Data Exposure** diz respeito à exposição desnecessária dos dados durante uma comunicação.

### O que evitar

- Não criptografar informações de ponta a ponta;
- Armazenar informações com texto claro e limpo;
- Não se atentar a backups que contenham dados não criptografados (precisam ser!!);
- Não possuir certificados válidos e protocolos de segurança, como o TLS. 

### Como evitar

- Não armazene dados sensíveis desnecessariamente. Se preciso da informação em somente agora (em algum momento específico), não preciso armazena-la por um longo período;
- Ademais, todos os dados sensíveis armazenados **precisam** ser criptografados;
- Use os protocolos de segurança mais avançados;
- Desabilite o caching para respostas que contenham dados sensíveis;
- Armazene senhas utilizando-se de algoritmos decentes, como Argon2, scrypt, bcrypt ou PBKDF2;
- Verifique de maneira independente a eficiência da sua proteção

## A4 - XML External Entities (XXE)

### O que é

Fragilidades atraladas a esse tipo de dado. Se a sua aplicação aceita XML, é bom dar uma olhada! Será que não existe injeção nesse tipo de arquivo? É preciso validar. É preciso sanitizar.

```XML
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE contato [
        <!ENTITY meunome SYSTEM "file:///etc/password">
    ]
>

<contato>
    <nome>&meunome;</nome>
    <nome-oficial>&meunome;</nome-oficial>
    <telefone tipo="residencial">3218791329</telefone>
    <telefone tipo="comercial">3218791329</telefone>
    <telefone tipo="residencial">3218791329</telefone>
    <telefone tipo="celular">3218791329</telefone>
    <telefone tipo="residencial">3218791329</telefone>
</contato>
```

Ao ler uma entidade externa, há a permissão que o parser do XML leia as infos de um local de fora. Arquivos são carregados, abrindo a porta para que informações sejam trazidas. As portas dos arquivos do seu sistema ficam abertas!

### O que Evitar



### Como Evitar

