package com.biomerieux.loggedinusertest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 
 * @author gphalippou
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Configuration
    @Order(1)
    public static class BasicWebSecurityConfig extends WebSecurityConfigurerAdapter{
    	@Autowired
    	private BasicAuthenticationProvider basicAuthProvider;
    	
    	@Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(basicAuthProvider);
        }
    	
    	@Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/basic")
                    .authorizeRequests()
                        .anyRequest().authenticated()
                        .and()
                    .httpBasic();
        }
    }

    @Configuration
    @Order(2)
    public static class FormWebSecurityConfig extends WebSecurityConfigurerAdapter{
    	
    	@Autowired
    	private FormLoginFilter formLoginFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().antMatcher("/form")
            		.addFilterAfter(formLoginFilter, BasicAuthenticationFilter.class);
        }
    }
}

