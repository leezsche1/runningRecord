package excercising.exercising.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/running", "/runningResult").permitAll()
                .anyRequest().authenticated());

        http.httpBasic((auth) -> auth.disable());
        http.csrf((auth) -> auth.disable());
        http.formLogin((auth) -> auth.disable());
        http.sessionManagement((session) -> session.disable());

        return http.build();

    }

}
