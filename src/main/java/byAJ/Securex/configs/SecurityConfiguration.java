package byAJ.Securex.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



import byAJ.Securex.repositories.BookRepository;
import byAJ.Securex.repositories.SSUserDetailsService;
import byAJ.Securex.repositories.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired private UserRepository userRepository;
    @Override public UserDetailsService userDetailsServiceBean() throws Exception{
    	return new SSUserDetailsService(userRepository);
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
    
    
    auth.userDetailsService(userDetailsServiceBean());
    }

 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
        .authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()   
        .and()
        .formLogin()
        .loginPage("/login") .permitAll()
        .and()
        .formLogin().failureUrl("/login?error")
        .defaultSuccessUrl("/")
        
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
        .permitAll();
        
        
    }


	
	/*

    @Autowired private BookRepository userRepository;
    @Override public UserDetailsService userDetailsServiceBean() throws Exception{
    	return new SSUserDetailsService(userRepository);
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
    
    
    auth.userDetailsService(userDetailsServiceBean());
    }

    
  
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
      
        
        http
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("saurav").password("password").roles("USER","ADMIN");
        auth.inMemoryAuthentication().withUser("sunil").password("password").roles("ADMIN");
    }
    
    */
}
