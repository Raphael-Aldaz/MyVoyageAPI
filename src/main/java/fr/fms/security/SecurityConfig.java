package fr.fms.security;

import fr.fms.entities.AppUser;
import fr.fms.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser user = accountService.findUserByUsername(username);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                user.getRole().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                });
                return new User(user.getUsername(), user.getPassword(), authorities);
            }
        });
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/hotels/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/hotels/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/hotels/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/hotels/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/hotel/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/hotel/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/hotel/**").hasAuthority("SUPERVISEUR");

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/users/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/users/**").hasAuthority("SUPERVISEUR");

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/user/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/user/**").hasAuthority("SUPERVISEUR");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/user/**").hasAuthority("SUPERVISEUR");

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/cities/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/city/**").permitAll();



        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().disable();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
