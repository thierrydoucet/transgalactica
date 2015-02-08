package org.transgalactica.management.rest.context;

import static org.springframework.http.HttpMethod.OPTIONS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> manager = auth.inMemoryAuthentication();

		manager.withUser("HAL").password("2001_odyssee").roles("SUPER_GESTIONNAIRE", "ADMINISTRATEUR");
		manager.withUser("6PO").password("StarWars").roles("GESTIONNAIRE");
		manager.withUser("Sonny").password("1Robot").roles("GESTIONNAIRE").disabled(true);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring() //
				.antMatchers("/resources/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/").permitAll() //
				.antMatchers(OPTIONS, "/**").permitAll() //
				.anyRequest().authenticated() //
				.and() //
				.httpBasic() //
				.and() //
				.logout();
	}
}
