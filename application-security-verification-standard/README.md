# Application Security Verification Standard (Padrão de Verificação de Segurança em Aplicações)

## V2: Authentication Verification Requirements

Autenticar é ter certeza que uma pessoa diz que é. Exigir usuário+senha é a forma mais comum de autenticação. Além disso, para tornar a verificação mais forte, pode ser preciso validar quem a pessoa é, o que ela sabe e o que ela tem. Existe um padrão contruído baseado em evidências, o **NIST 800-63**, que estabelece formas de construir autenticações mais seguras.

### V2.1: Password Security Requirements

- 2.1.1: Verificar que as senhas tenham, no mínimo, 12 caracteres;
- 2.1.2: Verifique que senhas de 64 caracteres ou maiores são permitidas;
- 2.1.3: Verifique que as senhas contenham espaços e sem truncagem. Além disso, espaços em branco subsequentes devem ser suportados. A senha é o que o usuário digitar;
- 2.1.4: Verifique que caracteres Unicode são permitidos, como emoji, kanji etc.;
- 2.1.5: Verifique que usuários consigam alterar a senha;
- 2.1.6: Verifique que alteração de senha requira a senha atual;
- 2.1.7: Verifique que a senha escolhida pelo usuário pode ser utilizada, isto é, não consta em listas de senhas já vazadas. Mas, cuidado! Caso utilize sistemas externos para fazer esta verificação, como APIs, não passe a senha como plain text;
- 2.1.8: Verifique que a senha é forte;
- 2.1.9: Verifique a não existam limites na composição de senhas;
- 2.1.10: Verifique que não exista rotação de senha ou limitações associadas a histórico de senhas;
- 2.1.11: Verifique que haja a possibilidade de colar senhas no campo apropriado, pois passwords helpers utilizam esta funcionalidade;
- 2.1.12: Verifique que haja a possibilidade do usuário observar o último caractere temporariamente ao digitar, ou visualizar a senha, em caso de digitação errônea.

### V2.2 General Authenticator Requirements

- 2.2.1: Verifique que haja um mecanismo de anti-automação, para que não haja ataques de força bruta em ambientes de autenticação;
- 2.2.2: Verifique que formas autenticações fracas, como SMS e email, sejam utilizadas de maneira secundária. Verifique a existência de outros mecanismos mais fortes como opções anteriores a estas;
- 2.2.3: Verifique que notificações de segurança sejam enviadas a usuários, mas sem enviar informações sensíveis.

### V2.3 Authenticator Lifecycle Requirements

- 2.3.1: Verifique que senhas iniciais em sistemas sejam geradas de maneira aleatória, com 6 caracteres no mínimo, e que expirem depois de um período curto de tempo. A mudança precisa ser obrigatória.

### V2.5 Credential RTecovery Requirements

- 2.5.1: Verifique que a senha digitada não seja enviada em texto puro, de maneira clara, em canais como email. Utilize códigos temporários que sejam gerados aleatoriamente e que sejam enviados por um canal mais seguro, como notificações push;
- 2.5.2: Verifique que dicas de senha ou autenticação baseada em conhecimento não sejam utilizadas;
- 2.5.3: Verifique que mecanismos de recuperação de senha não revelem a senha de forma alguma;
- 2.5.4: Verifique que contas compartilhadas ou default não estejam presentes, como "root" ou "admin";
- 2.5.5: Verifique que o fator de autenticação, caso seja alterado, o usuário seja notificado de acordo;
- 2.5.6: Verifique os caminhos de recuperação de senha usem rotas seguras, como mecanismos de recuperação por meio de tokens, push notifications ou qualquer mecanismo offline.

### V2.6 Look-up Secret Verifier Requirements

- 2.6.1: Verifique que itens de lista de segredo sejam utilizados apenas uma vez.

### V2.7 Out of Band Verifier Requirements

- 2.7.1: Verificar que mecanismos de autenticação, como SMS ou PSTN não são oferecidos por padrão, mas sim canais mais seguros como push notifications;
- 2.7.2: Verifique que os códigos de autenticação expirem depois de 10 minutos;
- 2.7.3: Verifique que todos os códigos, tokens etc. sejam utilizáveis uma vez e somente durante o período em que estiverem disponíveis;
- 2.7.4: Verifique que o autenticador externo use um canal seguro e independente.

### V2.8 Single or Multi Factor One Time Verifier Requirements

- 2.8.1: Verifique que haja um tempo de vida dos códigos.

## V3: Session Management Verification Requirements

O objetivo aqui é garantir o gerenciamento da sessão, que permita dizer se o usuário autenticado ainda é o mesmo dado um tempo de uso do sistema. Sessões de uso precisam se valer de códigos de sessão únicos, aleatórios e temporários, que expiram após um tempo ou durante um tempo de inatividade.

### V3.1 Fundamental Session Management Requirements

- 3.1.1: Verifique que a aplicação nunca revele os tokens de sessão em parâmetros da URL ou em mensagens de erro.

### V3.2 Session Binding Requirements

- 3.2.1: Verifique que a aplicação gere tokens novos de sessão a cada momento que o usuário é autenticado;
- 3.2.2: Verifique que os tokens possuem pelo menos 64 bits de entropia;
- 3.2.3: Verifique que a aplicação armazena tokens de sessão no browser usando métodos seguros, como cookies ou session storage do HTML 5.

### V3.3 Session Logout and Timeout Requirements

- 3.3.1: Verifique que o logout e a expiração invalidam o token (não apenas retirar o token do cookie do cliente). Com isso, o botão de retorno, após um logout, não permitiria o acesso ao sistema;
- 3.3.2: Verifique que, mesmo autenticado, o sistema exija novamente uma autenticação do usuário.

### V3.4 Cookie-based Session Management

- 3.4.1: Verifique que o token de uma sessão baseada em cookies possui o atributo "Secure" ativado (será utilizado somente em requisições HTTPS);
- 3.4.2: Verifique que os tokens de sessões baseadas em cookie possuem o atributo "HTTPOnly";
- 3.4.3: Verifique que cookies possuam atributos "SameSite" como "Strict";
- 3.4.4: Verifique que tokens de sessões baseadas em cookies usem o prefixo "__Host-";
- 3.4.5: Verifique que a aplicação, publicada sob um domínio, tenha uma diferenciação clara dos paths e que não haja compartilhamento de cookies entre eles.

### V3.7 Defenses Against Session Management Exploits

- 3.7.1: Verifique que a aplicação assegura a existência de uma sessão de login válida ou requira uma segunda forma de autenticação antes de permitir qualquer operação sensitiva, como transações bancárias, por exemplo.

## V4 Access Control Verification Requirements

Vamos garantir que os usuários acessem somente aquilo que lhes é de direito.

### V4.1 General Access Control Design

- 4.1.1: Verifique que o controile de acesso é aplicado na camada do servidor ao invés da camada do cliente;
- 4.1.2: Verifique que os usuários e todos os atributos relacionados a eles não sejam manuseados por usuários não autorizados;
- 4.1.3: Verifique o princípio do "mínimo de privilégio", em que cada usuário, por padrão, tenha acesso ao mínimo possível;
- 4.1.4: Verifique que o princípio de negação existe assim que um usuário é criado no sistema, em que haja permissões mínimas ou nenhuma desde o princípio;
- 4.1.5: Verifique que o controle de acesso falhe de uma forma segura, mesmo quando ocorra uma exceção.

### V4.2 Operation Level Access Control

- 4.2.1: Verifique que dados sensíveis, bem como a API, estão protegidos contra ataques diretos que miram o CRUD desses dados;
- 4.2.2: Verifique a utilização de mecanismos fortes de anti-CSRF, que diz respeito ao ato de clicar por engano em links maliciosos, como a utilização de tokens por página (sem limitar a navegação a uma página).

### V4.3 Other Access Control Considerations

- 4.3.1: Verifique que interfaces administrativas usam autenticações de múltiplos fatores.
- 4.3.2: Verifique que a listagem de diretórios está desabilitada por padrão, a não ser que seja desável explicitamente.

## V5: Validation, Sanitization and Encoding Verification Requirements

Aqui, vamos lidar com injections dos mais diversos tipos, como SQL, XSS, shell etc. De preferência, use APIs que já tenham em sua arquitetura mecanismos de prevenção de injections. Também, garanta que dados de input sejam fortemente tipados, validados, com ranges verificados, etc. Além disso, os dados de saída dessas entradas precisam ser encoded ou escapados.

### V5.1 Input Validation Requirements

- 5.1.1: Verifique que a aplicação possui defesas contra ataques de poluição de parâmetros HTTP;
- 5.1.2: Verifique que os frameworks se protegem de ataques de atribuição de parâmetros em massa ou valores inseguros;
- 5.1.3: Verifique que todos os inputs vindos de uma requisição HTTP são validados com uma lista positiva;
- 5.1.4: Verifique que a estrutura do dado é fortemente tipada e validada com um esquema (schema) ou que campos relacionado sejam validados, como CEP e o nome da cidade, de uma rua;
- 5.1.5: Verifique que cada URL redireciona a sites presentes em uma lista positiva.

### V5.2 Sanitization and Sandoboxing Requirements

- 5.2.1: Verifique que todo input HTML não confiávels de editores What You See Is What You Get (WYSIWYG) são sanitizados apropriadamente, principalmente com uma bibliotecas sanitizadoras de HTML;
- 5.2.2: Verifique que dados não estruturados sejam sanitizados;
- 5.2.3: Verifique que a aplicação sanitiza o input do usuário antes de utilizar sistemas de email, como SMTP ou IMAP;
- 5.2.4: Verifique que a aplicação NÃO USA eval(). Se utiliza, sanitize, embora não seja o ideal;
- 5.2.5: Verifique que a aplicação protege contra o uso de templates;
- 5.2.6: Verifique que a aplicação protege contra ataques Server Side Request Forgery (SSRF), validando ou sanitizando dados HTML ou metadados HTTP, por meio da utilização de uma lista positiva;
- 5.2.7: Verifique que a aplicação sanitize, desative ou sandbox um SVG fornecido por um usuário, pois é algo que pode abrir brecha para ataques XSS;
- 5.2.8: Verifique que a aplicação sanitiza, desativa ou sandbox linguagens fornecidas pelo usuário como Markdown, CSS ou XSL stylesheets, BBCode ou similares.

### V5.3 Output encoding and Injection Prevention Requirements

- 5.3.1: Verifique que o encoding da saída é relevante para o interpretador ou contexto utilizado. Utilize encodings específicos para valores ou atributos HTML, JavaScript, parâmetros URL, SMT, entre outros;
- 5.3.2: Verifique que o encoding da saída tem relação com o conjunto de caracteres escolhidos pelo usuário, bem como o locale, para assegurar que qualquer caractere Unicode seja trabalhado de maneira segura;
- 5.3.3: Verifique que haja escaping do input do usuário para evitar ataques XSS baseados em DOM;
- 5.3.4: Verifique que as queries de banco de dados (SQL, HQL, ORM, NoSQL) usem queries parametrizadas, SEM NENHUMA concatenação.
- 5.3.5: Verifique que, caso não haja mecanismos seguros para o contexto trabalhado, crie formas de sanitizar inputs (boa sorte);
- 5.3.6: Verifique que a aplicação se protege de ataques de injeção em JSON, JS, DOM XSS e JS evaluation;
- 5.3.7: Verifique que a aplicação está protegida contra LDAP Injection vulnerabilities;
- 5.3.8: Verifique que a aplicação está protegida de injeções no OS;
- 5.3.9: Verifique que a aplicação está protegida contra Local File Inclusion (LFI) ou Remote File Inclusion (RFI) attacks;
- 5.3.10: Verifique que a aplicação está protegida de XPath Injection ou XML injection.

### V5.5 Deserialization Prevention Requirements

- 5.5.1: Verifique que existam checks de integridade, chaves públicas ou privadas, criptografia, dentre outros recursos, que previnam a criação e passagem de objetos hostis no sistema;
- 5.5.2: Verifique que, caso use XML, use-o em uma configuração mais restrita, de modo a prevenir XXE;
- 5.5.3: Verifique que a desserialização de dados de terceiros, não confiáveis, é protegida por código customizado, realizado por bibliotecas já prontas. É claro que, de preferência, não faça isso;
- 5.5.4: Verifique que, ao realizar o parse de JSON, use JSON.parse ao invés de eval().

## V6 Stored Cryptography Verification Requirements

### V6.2 Algorithms

- 6.2.1: Verifique que em módulos de criptografia haja falhas seguras, em que os erros sejam manuseados de forma que não permitam ataques Padding Oracle, em que há a exploração de dicas em mensagens de erro.

## V7 Error Handling and Loggin Verification Requirements

Logs de alta qualidade sempre terão dados sensíveis incluídos. O que precisa ser feito é protegê-los por meio de regras locais ou diretivas, o que inclui:
- Não coletar informações sensíveis para log a não ser que seja expressamente requisitado;
- Assegurar que toda informação que aparecer em logs é manuseada com segurança pela sua classificação;
- Assegurar que todos os logs não são armazenados para sempre.

### V7.1: Log Content Requirements

- 7.1.1: Verifique que a aplicação não apresente em seus logs credenciais e detalhes de pagamento. Tokens de sessão devem ser armazenados em log somente em modo hash e de maneira irreversível;
- 7.1.2: Verifique que a aplicação não apresente em seus logs qualquer tipo de dado sensível;

### V7.2 Log Processing Requirements

- 7.2.1: Verifique que todas as decisões que envolvem autenticação são logadas, sem armazenar dados sensíveis (ao invés de armazenar senhas e tokens de sessão no logo, armazenar o id de usuário);
- 7.2.2: Verifique que todos os logs de falha relacionados ao controle de acesso são armazenados, mas há também a possibilidade de armazenar os logs também que envolvem sucesso nesse âmbito.

### V7.3 Log Protection Requirements

- 7.3.1: Encode os inputs do usuário para evitar injections em log.

### V7.4 Error Handling

- 7.4.1: Verifique que uma mensagem genérica é evidenciada quando algo inesperado ou um erro de segurança ocorre, para que dados sensíveis não sejam vazados.

## V8 Data Protection Verification Requirements

Quando tratamos de segurança dos dados, pensamos nos seguintes pilares:
- Confidencialidade: os dados devem ser protegidos de acessos não autorizados, quando são armazenados ou em se encontram em trânsito;
- Integridade: os dados não devem ser alterados, deletados ou substituídos por atacantes não autorizados;
- Disponibilidade: quem tem direito ao acesso precisa acessar.

### V8.1 General Data Protection

- 8.1.1: Verifique que a aplicação protege dados sensíveis de serem armazenados em cache;
- 8.1.5: Verifique que backups funcionem corretamente.

### V8.2

- 8.2.1: Verifique que a aplicação ututiliza headers anti-caching o suficiente de modo que dados sensíveis não sejam armazenados em cache por navegadores modernos;
- 8.2.2: Verifique que dados armazenados no lado do cliente (como o HTML5 local storage, session storage etc.) não contenham dados sensíveis.
- 8.2.3: Verificar que dados autenticados sejam limpos no lado do cliente após a sessão ser terminada.

### V8.3 Sensitive Private Data

- 8.3.1: Verifique que dados sensíveis estejam presenter no corpo (body) da requisição e jamais nos headers;
- 8.3.2: Verifique que os usuários possuem alguma modo de remover ou exportar seus dados sob demanda;
- 8.3.3: Verifique que os usuários consentem expressamente que os dados sejam tratados pela aplicação;
- 8.3.4: Verifique que todos os dados sensíveis manuseados pela aplicação possuam regras claras de como devem ser tratados;

Obs: regras dessa natureza podem variar de acordo com as leis locais e devem segui-las.

## V9: Communications Verification Requirements

### V9.1 Communications Security Requirements

- 9.1.1: Verifique que Verifique que o protocolo TLS é utilizado em todas as conexões do cliente, evitando, como fallback, conexões inseguras;
- 9.1.2: Verifique que são utilizadas ferramentas atualizadas de teste de TLS, que avaliam cifras, se as criptografias utilizadas são as mais fortes possíveis, se os protocolos estão ativados;
- 9.1.3: Verifique que versões desatualizadas dos protocolos SSL e TLS não são utilizadas. Garanta que sempre a versão mais atualizada é utilizada.

## V10: Malicious Code Verification Requirements

Vamos nos proteger de devs demitidos ou mal intencionados.

### V10.3 Code Integrity Control

- 10.3.1: Verifique que, se a aplicação possui um mecanismo de auto-update, as atualizações sejam feitas por meio de um canal seguro e com pacotes digitalmente assinados;
- 10.3.2: Verifique que a aplicação usa proteções de integridade, como uma assinatura para o código. A aplicação não deve executar código de fontes inseguras;
- 10.3.3: Verifique que a aplicação está protegida de tomadas de posse de domínio. Crie proteções como mecanismos de checagem de validade de domínio ou mudança em nomes do DNS.

## V11: Business Logic Verification Requirements

### V11.1 Business Logic Security Requirements

- 11.1.1: Verifique que a aplicação vai, para um dado fluxo de aplicação, seguir uma lógica sequancial, sem que haja a possibilidade de burlar etapas. Isso não anula o uso de paralelismo, mas a pretenção é dizer que para cada etapa, se existir uma condição anterior para que funcione, essa condição precisa ser executada;
- 11.1.2: Verifique que a aplicação terá suas funcionalidades executadas em um tempo humanamente possível, seguindo casos razoáveis;
- 11.1.3: Verifique que a aplicação possui limites de ações, principalmente por usuário;
- 11.1.4: Verifique que a aplicação possui controles anti-automação, a fim de evitar extrações e ataques em um volume gigantesco, como observado em ataques DDoS;
- 11.1.5: Verifique que a aplicação possui limites a fim de evitar riscos atrelados às regras de negócio.

## V12: File and Resources Verification Requirements

### V12.1 File Upload Requirements

- 12.1.1: Verifique que a aplicação não aceitará arquivos grandes de mais de tal modo que trave os serviços.

### V12.3 File execution Requirements

- 12.3.1: Verifique que os metadados de arquivos não sejam utilizados diretamente no sistema, a fim de evitar injeções;
- 12.3.2: Verifique que os metadados enviados por meio de arquivos são validados ou ignorados para prevenir alterações no arquivo ou no espaço em que é armazenado;
- 12.3.3: Verifique que os metadados enviados por meio de arquivos são validados ou ignorados de modo a evitar SSRF;
- 12.3.4: Verifique que a aplicação se protege contra reflective file download (RFD), validando ou ignorando nomes de arquivo em um parâmetro JSON, JSONP ou de URL. A resposta do header Content-Type tem que ser text/plain e a header Content-Disposition deve ter um nome fixo;
- 12.3.5: Verifique que metadados de arquivos vindos do cliente não sejam utilizados diretamente pela API, também com o objetivo de prevenir ataques.

### V12.4 File Storage Requirements

- 12.4.1: verifique que arquivos obtidos de fontes não confiáveis sejam armazenados com limites de permissões, sem serem executáveis e após uma forte validação;
- 12.4.2: Verifique que arquivos de fontes não confiáveis passam por uma triagem de um antivirus.

### V12.5 File Download Requirements

- 12.5.1: Verifique que a camada da web está configurada para entregar arquivos com determinadas extensões, já definidas pela aplicação;
- 12.5.2: Verifique que arquivos entregues não serão interpretados como HTML/JS.

### V12.6 SSRF Protection Requirements

- 12.6.1: Verifique que o servidor da aplicação acesse uma lista positiva de recursos (Ex: o uso de uma store para acessar caminhos de APIs em JSON).

## V13: API and Web Service Verification Requirements

### V13.1 Generic Web Service Security Verification Requirements

- 13.1.1: Verifique que os componentes da aplicação usa os mesmo encodings e parsers para evitar ataques que explorem parsing de arquivos, que poderia ser usado para ataques SSRF e RFI;
- 13.1.2: Verifique que o acesso de funcionalidades de gerenciamento está limitado aos administradores;
- 13.1.3: Verifique que URLs de API não expõe dados sensíveis, como API keys, session tokens etc.

É claro que os cuidados precisam ser os mesmos para qualquer tipo de acesso.

### V13.2 RESTful Web Service Verification Requirements

- 13.2.1: Verifique que os métodos HTTP desejados representam uma escolha válida para um dado endpoint;
- 13.2.2: Verifique que há um JSON schema que valide a entrada do usuário;
- 13.2.3: Verifique que os serviços web que utilizam cookies estejam protegidos de ataques contra cookies.

Vale lembrar de que, na comunicação entre serviços, todo input precisa ser validado e sanitizado.

## V14: Configuration Verification Requirements

### V14.2 Dependency

- 14.2.1: Verifique que todos os componentes estão atualizados, preferencialmente usando um dependency checker durante o build;
- 14.2.2: Verifique que todas as ferramentas, documentações, demonstrações etc. desnecessárias **não** são utilizadas;
- 14.2.3: Verifique que assets gerais, armazenados externamente, são validados por meio de Subresource Integrity (SRI), que emprega um hash, por exemplo, como forma de validação.

### 14.3 Unintended Security Disclosure Requirements

- 14.3.1: Verifique que mensagens de erro da aplicação são configuraddas para entregar ao usuário respostas customizadas e acionáveis a fim de eliminar qualquer brecha de segurança;
- 14.3.2: Verifique que o modo de depuração está desativado em produção, de modo a evitar recursos adicionais, como acesso a logs em níveis indesejados para ocasião;
- 14.3.3: Verifique que os headers HTTP ou qualquer trecho de uma resposta HTTP não entreguem dados relacionados às versões dos componentes do sistema.

### 14.4 HTTP Security Headers Requirements

- 14.4.1: Verifique que a resposta HTTP possui um header content type especificado e o encoding apropriado/desejado;
- 14.4.2: Verifique que todas as respostas de API possuam `Content-Disposition: attachment; filename="api.json"`, por exemplo;
- 14.4.3: Verifique que exista uma content security policy de modo a ajudar a mitigar ataques;
- 14.4.4: Verifique que todas as respostas contenhan `X-Content-Type-Options: nosniff`, de modo a evitar adivinhações mesmo que o content type tenha sido explicitamente declarado;
- 14.4.5: Verifique que haja o uso de headers HTTP Strict Transport Security;
- 14.4.6: Verifique que o referrer-policy é usado;
- 14.4.7: Verifique que X-Frame-Options ou Content-Securty-Policy são usados, a fim de controlar embeddings da página web por terceiros.

### 14.5 Validate HTTP Request Header Requirements

- 14.5.1: Verifique que o servidor aceita somente métodos HTTP que se encontram em uso pela aplicação ou API;
- 14.5.2: Verifique que o cabeçalho Origin não é utilizado para autenticação ou controle de acesso. Mas, por que o cookie é utilizado? No cookie, é possível validar a sua integridade por meio de um hash, o que aumenta a segurança da comunicação;
- 14.5.3: Verifique que, para evitar o ataque de cross-domain resource sharing (CORS), o cabeçalho Access-Control-Allow-Origin usa uma lista positiva de domínios confiáveis para que não seja suportado o domínio "null".
