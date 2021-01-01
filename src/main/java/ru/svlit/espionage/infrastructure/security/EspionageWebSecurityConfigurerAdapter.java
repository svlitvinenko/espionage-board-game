package ru.svlit.espionage.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.svlit.espionage.domain.user.entity.Role;

import java.util.Set;
import java.util.stream.Collectors;

import static ru.svlit.espionage.domain.user.entity.Role.ADMIN;
import static ru.svlit.espionage.domain.user.entity.Role.USER;

/**
 * Настройки безопасности.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class EspionageWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(providePasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/sign-up").not().authenticated()

                .and()
                .authorizeRequests()
                .antMatchers("/user/id", "/user/username")
                .hasAnyAuthority(Set.of(USER, ADMIN).stream().map(Role::name).toArray(String[]::new))

                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

    @Bean
    PasswordEncoder providePasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
