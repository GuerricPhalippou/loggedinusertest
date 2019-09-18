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
	    if (user == null) {
	      LoginContext loginContext;
	      try {
	        loginContext = LoginContextFactory.createLoginContext("FORM");
	        loginContext.login();
	        LOGGER.debug("Login success: " + ((HttpServletRequest)request).getRemoteUser());
	        chain.doFilter(request, response);
	      } catch (LoginException e) {
	        LOGGER.debug("Login error", e);
	        request.setAttribute("errorMessage", e);
			request.getRequestDispatcher("/WEB-INF/views/jsp/error.jsp")
                               .forward(request, response);
	      }
	    } else {
	    	chain.doFilter(request, response);
	    }
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
