package application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
                .antMatchers("/adminPage").hasAnyAuthority("ADMIN")
                .antMatchers("/newProjectForm").hasAnyAuthority("ADMIN")
                .antMatchers("/newUserForm").hasAnyAuthority("ADMIN")
                .antMatchers("/createNewUser").hasAnyAuthority("ADMIN")
                .antMatchers("/saveUser").hasAnyAuthority("ADMIN")
                .antMatchers("/getAllProjects").hasAnyAuthority("ADMIN")
                .antMatchers("/addNewProject").hasAnyAuthority("ADMIN")
                .antMatchers("/saveProject").hasAnyAuthority("ADMIN")
                .antMatchers("/addUsersToProject").hasAnyAuthority("ADMIN")
                .antMatchers("/addUserToProject").hasAnyAuthority("ADMIN")
                .antMatchers("/deleteUsersFromProject").hasAnyAuthority("ADMIN")
                .antMatchers("/deleteUserFromProject").hasAnyAuthority("ADMIN")
                .antMatchers("/readProject").hasAnyAuthority("USER")
                .antMatchers("/createNewRecord").hasAnyAuthority("USER")
                .antMatchers("/stopRecord").hasAnyAuthority("USER")
                .antMatchers("/userPage").hasAnyAuthority("USER", "ADMIN")
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/userPage", false)
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login");

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}
