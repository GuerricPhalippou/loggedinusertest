package com.biomerieux.loggedinusertest;

import java.security.Principal;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(path = "/", produces = "application/json")
	public @ResponseBody String getUser(HttpServletRequest request) throws Exception {
		try {
			Principal principal = request.getUserPrincipal();
            LOGGER.debug("User found" + principal.toString());
            return principal.toString();
          } 
		catch (Exception e) {
        	LOGGER.debug("User not found : " + e.getMessage());
        	throw e;
		}
	}
}
