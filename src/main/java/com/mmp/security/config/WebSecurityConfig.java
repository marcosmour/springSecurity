package com.mmp.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic().and().authorizeHttpRequests().anyRequest().authenticated().and().csrf().disable();
	}
}

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final String uri = "/custom/*";
//
//    @Override
//    public void configure(final HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.headers().httpStrictTransportSecurity().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // Authorize sub-folders permissions
//        http.antMatcher(uri).authorizeRequests().anyRequest().permitAll();
//    }
//}
