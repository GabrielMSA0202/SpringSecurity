
Spring Security – Autenticação e Autorização Básica

Este repositório demonstra como configurar Spring Security em um projeto Spring Boot utilizando:

* Usuários em memória
* Criptografia BCrypt
* Controle de acesso baseado em roles (USER / ADMIN)
* Autenticação HTTP Basic
* Regras de acesso por rotas

Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Security
* BCryptPasswordEncoder

Estrutura da Segurança

Toda a configuração está localizada em:

com.SecurityProject.Mercado.security.SecurityConfig

Essa classe contém:

* Configuração dos usuários
* Encoder de senha
* Filtro de segurança (SecurityFilterChain)
* Definição de rotas protegidas

Usuários em Memória

O projeto define dois usuários com InMemoryUserDetailsManager:

```java
UserDetails user = User.builder()
        .username("user")
        .password(encoder.encode("123"))
        .roles("USER")
        .build();

UserDetails admin = User.builder()
        .username("admin")
        .password(encoder.encode("admin123"))
        .roles("ADMIN")
        .build();
```

Usuário comum

* Login: `user`
* Senha: `123`
* Role: `USER`

Administrador

* Login: `admin`
* Senha: `admin123`
* Role: `ADMIN`


Criptografia

As senhas são criptografadas usando BCrypt:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

SecurityFilterChain – Configuração das Rotas

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/publico").permitAll()
                    .requestMatchers("/produtos/cadastrar").hasRole("ADMIN")
                    .requestMatchers("/produtos").hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic();

    return http.build();
}
```

---

Regras de Acesso

| Rota                  | Acesso permitido |
| --------------------- | ---------------- |
| `/`                   | Público          |
| `/publico`            | Público          |
| `/produtos`           | USER ou ADMIN    |
| `/produtos/cadastrar` | Somente ADMIN    |
| Outras rotas          | Autenticado      |

---

Tipo de Autenticação

O projeto utiliza autenticação **HTTP Basic**, onde o navegador exibe uma janela solicitando login e senha ao acessar qualquer rota protegida.

---

Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-repo.git
```

2. Entre no diretório:

```bash
cd seu-repo
```

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

4. Acesse as rotas pelo navegador ou ferramenta de API.

---

Conclusão

Este projeto demonstra de forma simples como implementar segurança com Spring Security, incluindo:

* Autenticação HTTP Basic
* Autorização baseada em roles
* Usuários definidos em memória
* Senhas criptografadas
