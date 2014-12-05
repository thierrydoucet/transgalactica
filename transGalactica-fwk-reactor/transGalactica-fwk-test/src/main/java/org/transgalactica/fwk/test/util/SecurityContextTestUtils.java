package org.transgalactica.fwk.test.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class SecurityContextTestUtils {

	public static final UserDetails ADMINISTRATEUR = new User("administrateur", "mdp", true, true, true, true,
			Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMINISTRATEUR")));

	protected SecurityContextTestUtils() {
	}

	public static void setAnomymeInContext() {
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
		SecurityContextHolder.getContext().setAuthentication(
				new AnonymousAuthenticationToken("AnonymousAuthentication", "Anomymous User", Collections
						.singletonList(ga)));
	}

	public static void setAdministrateurInContext() {
		setUsernamePasswordAuthenticationTokenInSecurityContext(ADMINISTRATEUR);
	}

	public static void setUsernamePasswordAuthenticationTokenInSecurityContext(UserDetails userDetails) {
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails
						.getAuthorities()));
	}

	public static void setUsernamePasswordAuthenticationTokenInSecurityContext(String usename, String password,
			String... roles) {
		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
		for (String role : roles) {
			gas.add(new SimpleGrantedAuthority(role));
		}
		UserDetails user = new User(usename, password, true, true, true, true, gas);
		setUsernamePasswordAuthenticationTokenInSecurityContext(user);
	}
}
