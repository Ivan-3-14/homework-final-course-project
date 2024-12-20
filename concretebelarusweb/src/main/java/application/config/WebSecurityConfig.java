package application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/adminMainPage").hasAnyAuthority("ADMIN")
                .antMatchers("/userMainPage").hasAnyAuthority("USER")
                .antMatchers("/managerMainPage").hasAnyAuthority("MANAGER")
                .antMatchers("/mainPage").permitAll()
                .antMatchers("/authorizationPage").permitAll()
                .antMatchers("/authorization").permitAll()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/forgotPassword").permitAll()
                .antMatchers("/verifyPassword").permitAll()
                .antMatchers("/checkPassword").permitAll()
                .antMatchers("/changePassword").permitAll()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/signUp", false)
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/signUp");

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
