package com.biomerieux.loggedinusertest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BasicAuthenticationProvider basicAuthProvider;
	
	@Autowired
	private FormAuthenticationProvider formAuthenticationProvider;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(basicAuthProvider);
        auth.authenticationProvider(formAuthenticationProvider);
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        	.antMatchers("/basic")
        	.authenticated()
        	.and()
        	.httpBasic()
        .and()
        	.authorizeRequests()
        	.antMatchers("/form")
        	.authenticated()
        	.and()
        	.formLogin();
        
        
		/*
		 * http.addFilterAfter(new Filter() {
		 * 
		 * @Override public void init(FilterConfig filterConfig) throws ServletException
		 * { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void doFilter(ServletRequest request, ServletResponse
		 * response, FilterChain chain) throws IOException, ServletException { // TODO
		 * Auto-generated method stub LoginContext loginContext; try { loginContext =
		 * LoginContextFactory.createLoginContext(BASIC); loginContext.login();
		 * LOGGER.debug("Certificate found" +
		 * ((HttpServletRequest)request).getRemoteUser()); loginContext.logout();
		 * chain.doFilter(request, response); } catch (LoginException e) {
		 * LOGGER.debug("Certificate not found : " + e.getMessage());
		 * ((HttpServletResponse)response).sendError(401); } }
		 * 
		 * @Override public void destroy() { // TODO Auto-generated method stub
		 * 
		 * } }, BasicAuthenticationFilter.class);
		 */
        
        
    }
}
