package br.com.iamspe.beneficiario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests(auth -> auth
                    //PERMITINDO ACESSO AO H2 E SWAGGER (PARA TESTAR MAIS FÁCIL APENAS)
                    .antMatchers("/h2/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()

                    .antMatchers(HttpMethod.GET, "/usuarios/admin").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/usuarios/user").hasRole("USER")
                    .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                    .antMatchers("/auth/**").permitAll()

                    .antMatchers(HttpMethod.POST, "/beneficiarios").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/beneficiarios").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/beneficiarios").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/documentos").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/documentos").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/documentos").hasRole("ADMIN")

                    .anyRequest().permitAll()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}