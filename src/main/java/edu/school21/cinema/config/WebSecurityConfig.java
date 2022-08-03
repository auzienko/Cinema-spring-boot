package edu.school21.cinema.config;

import edu.school21.cinema.models.Role;
import edu.school21.cinema.security.CinemaAuthenticationFailureHandler;
import edu.school21.cinema.services.CinemaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CinemaUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(CinemaUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/",
                        "/index",
                        "/signUp",
                        "/static/**",
                        "/images/**",
                        "/sessions/**",
                        "/confirm").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("email")
                    .loginProcessingUrl("/signIn")
                    .defaultSuccessUrl("/profile", true)
                    .failureHandler(authenticationFailureHandler())
                .permitAll();

        http
                .rememberMe()
                    .key("school21")
                    .rememberMeServices(rememberMeServices())
                    .tokenValiditySeconds(604800);

        http
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Bean
    public FilterRegistrationBean<CsrfFilter> csrfFilter() {
        FilterRegistrationBean<CsrfFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CsrfFilter(new HttpSessionCsrfTokenRepository()));
        registration.addUrlPatterns("/*");
        registration.setName("csrfFilter");
        return registration;
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CinemaAuthenticationFailureHandler();
    }

    @Bean
    protected RememberMeServices rememberMeServices(){
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices("school21", userService);
        tokenBasedRememberMeServices.setTokenValiditySeconds(604800);
        return tokenBasedRememberMeServices;
    }
}
