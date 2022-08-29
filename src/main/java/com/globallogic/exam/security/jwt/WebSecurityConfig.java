package com.globallogic.exam.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.globallogic.exam.entity.RoleType;
import com.globallogic.exam.securityjwt.jwt.AuthEntryPointJwt;
import com.globallogic.exam.securityjwt.jwt.AuthTokenFilter;
import com.globallogic.exam.securityjwt.service.StaffDetailsServiceImpl;
import com.globallogic.exam.securityjwt.service.StudentDetailsServiceImpl;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	  @Autowired
	  StaffDetailsServiceImpl staffDetailsServiceImpl;

	  @Autowired
	  StudentDetailsServiceImpl studentDetailsServiceImpl;

	  @Autowired
	  private AuthEntryPointJwt unauthorizedHandler;

	  @Bean
	  public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	  }

	  @Override
	  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	    authenticationManagerBuilder.userDetailsService(staffDetailsServiceImpl).passwordEncoder(passwordEncoder());
	    authenticationManagerBuilder.inMemoryAuthentication()
	    .withUser("amdin").password("admin").authorities("ADMIN");
	  }

	  @Bean
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  private static final String[] AMDIN_WHITE_LIST= {
			  "/api/v1/auth/**",
			  "/swagger-ui/**",
			  "api/v1/exam",
			  "api/v1/course",
			  "api/v1/staff",
			  "api/v1/student",
			  "api/v1/stream",
			  "api/v1/subject"
	  };
	  
	  private static final String[] STAFF_WHITE_LIST= {
			  "/api/v1/auth/**",
			  "/swagger-ui/**",
			  "api/v1/exam",
			  "api/v1/subject",
			  "api/v1/student",
	  };

	  private static final String[] STUDENT_WHITELIST_LIST = {
			  "/api/v1/auth/**",
			  "/swagger-ui/**",
			  "api/v1/exam",
	  };
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      .authorizeRequests()
	      .antMatchers(AMDIN_WHITE_LIST).hasAnyAuthority("ADMIN")
	      .antMatchers(STAFF_WHITE_LIST).hasAnyAuthority("STAFF")
	      .antMatchers(STUDENT_WHITELIST_LIST).hasAnyAuthority("STUDENT")
	      .anyRequest().authenticated()
	      .and()
	      .formLogin().permitAll()
	      .and()
	      .logout().permitAll();

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	  }
	  
	  @Override
	  public void configure(WebSecurity web) throws Exception {
	      web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");

	  }

}
