package com.biomerieux.loggedinusertest;

import java.security.Principal;
import java.util.Set;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.stereotype.Component;

import com.sap.security.auth.login.LoginContextFactory;

@Component
public class FormAuthenticationProvider extends AbstractJaasAuthenticationProvider {
	
	private final static String FORM = "FORM";
	{
		setAuthorityGranters(new AuthorityGranter[] {new AuthorityGranter() {

			@Override
			public Set<String> grant(Principal principal) {
				// TODO Auto-generated method stub
				return null;
			}
		}});
	}

	@Override
	protected LoginContext createLoginContext(CallbackHandler handler) throws LoginException {
		// TODO Auto-generated method stub
		return LoginContextFactory.createLoginContext(FORM);
	}

}
