package com.example.idemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//                auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("user")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN");

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/").permitAll()
                .antMatchers("/app/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/doLogin").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .failureForwardUrl("/login_failure_handler")
                .defaultSuccessUrl("/user")
                .and()
                .logout()
                .logoutUrl("/doLogout")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

}
