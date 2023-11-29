package az.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((authorize) -> authorize.anyRequest()
//                        .authenticated()).httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/allemployees")
//                .hasRole("ADMIN")
//               .requestMatchers("/api/search").hasRole("USER");
//        return http.build();



    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("Haji")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("Huser")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);


    }
}

