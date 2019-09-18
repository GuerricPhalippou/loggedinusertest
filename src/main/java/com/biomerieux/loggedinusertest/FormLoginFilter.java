package com.biomerieux.loggedinusertest;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sap.security.auth.login.LoginContextFactory;

/**
 * 
 * @author gphalippou
 *
 */
@Component
@Order(2)
public class FormLoginFilter implements Filter {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FormLoginFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String user = ((HttpServletRequest)request).getRemoteUser();
	    if (user != null) {
	      chain.doFilter(request, response);
	    } else {
	      LoginContext loginContext;
	      try {
	        loginContext = LoginContextFactory.createLoginContext("FORM");
	        loginContext.login();
	        chain.doFilter(request, response);

	      } catch (LoginException e) {
	        e.printStackTrace();
	      }
	    }
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
