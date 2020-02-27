package groentjes.onzeMoestuin.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class OnzeMoestuinSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService AuthenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/registerUser", "/", "/resources/css/**", "/resources/img/**", "/resources/javascript/**",
                        "/invitation/**", "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/loginsuccess", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(AuthenticationService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5242880);
        return multipartResolver;
    }
}
