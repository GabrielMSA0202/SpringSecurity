package com.SecurityProject.Mercado.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuração responsável por definir as regras de segurança do sistema.
 * Aqui são configurados os usuários em memória, o encoder de senha, permissões de acesso
 * aos endpoints e o tipo de autenticação utilizada (Basic Auth).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura um serviço de usuários em memória, criando dois usuários:
     * um com papel USER e outro com papel ADMIN.
     *
     * @param encoder o codificador de senhas utilizado para armazenar as senhas de forma segura
     * @return uma implementação de {@link UserDetailsService} contendo os usuários cadastrados
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

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

        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * Define o encoder utilizado para criptografar senhas.
     * O BCrypt é amplamente utilizado por ser seguro e resistente a ataques de força bruta.
     *
     * @return uma instância de {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura a cadeia de filtros de segurança, definindo:
     * <ul>
     *   <li>Desativação do CSRF para facilitar testes com Postman</li>
     *   <li>Permissão de acesso público para rotas específicas</li>
     *   <li>Regras de autorização baseadas em roles (USER e ADMIN)</li>
     *   <li>Autenticação via HTTP Basic</li>
     * </ul>
     *
     * @param http objeto de configuração de segurança HTTP
     * @return a cadeia de filtros configurada
     * @throws Exception caso ocorra erro na configuração
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para permitir requisições POST via Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/publico").permitAll()              // Endpoints abertos
                        .requestMatchers("/produtos/cadastrar").hasRole("ADMIN")  // Somente ADMIN
                        .requestMatchers("/produtos").hasAnyRole("USER", "ADMIN") // USER e ADMIN
                        .anyRequest().authenticated()                             // Restante precisa de login
                )
                .httpBasic(); // Autenticação Basic

        return http.build();
    }
}
