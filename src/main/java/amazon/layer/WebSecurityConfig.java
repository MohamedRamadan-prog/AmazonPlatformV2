package amazon.layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import amazon.layer.service.UserDetailServiceImpl;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	
	@Autowired
    private UserDetailServiceImpl userDetailsService;
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests().antMatchers("/css/**", "/signup/**").permitAll() // Enable css when logged out
    			.and()
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
            	.and()
            .logout()
            	.permitAll()
            	.and();
    }
     
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").and().ignoring().antMatchers("/h2/**/**");
																																																	// Production!;
	}
}