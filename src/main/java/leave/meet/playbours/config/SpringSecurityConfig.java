/*
package leave.meet.playbours.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(
                                "/manage/**",
                                "/front/**",
                                "/pages/manage/**",
                                "/athnLogin",
                                "/",
                                "/browse",
                                "/profile",
                                "/details",
                                "/streams").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/athnLogin")
                        .loginProcessingUrl("/maLoginProcess")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/ma", true)
                        .permitAll()
                )
                .logout(loguout -> loguout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/athnLogin")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
*/
