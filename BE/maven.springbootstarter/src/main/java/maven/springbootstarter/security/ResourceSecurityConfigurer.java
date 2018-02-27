package maven.springbootstarter.security;

import maven.springbootstarter.modal.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceSecurityConfigurer extends WebSecurityConfigurerAdapter{


	@Autowired
	private PostLoginHandler loginHandler;

	@Autowired
	private CustomUserDetailService mUserService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(mUserService).passwordEncoder(UserAccount.PASSWORD_ENCODER);
	}






	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()

				.exceptionHandling()
				.authenticationEntryPoint(loginHandler).and()
				.authorizeRequests()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/register/**").permitAll()
				.antMatchers("/**").hasRole("ADMIN")
				.and().formLogin().
				loginProcessingUrl("/signin") //the URL on which the clients should post the login information
				.usernameParameter("username") //the username parameter in the queryString, default is 'username'
				.passwordParameter("password").successHandler(loginHandler).failureHandler(new SimpleUrlAuthenticationFailureHandler());

	}

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//    	authenticationMgr.inMemoryAuthentication()
//    			.withUser("java").password("{noop}java").authorities("ROLE_ADMIN");
//    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring();

	}
}
