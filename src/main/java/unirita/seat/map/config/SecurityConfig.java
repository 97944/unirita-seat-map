package unirita.seat.map.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import unirita.seat.map.filter.OpenIDConnectFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/img/**");
    }

    @Bean
    public OpenIDConnectFilter myFilter() {
        final OpenIDConnectFilter filter = new OpenIDConnectFilter("/google-login");
        filter.setRestTemplate(restTemplate);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .csrf().disable()
        .addFilterAfter(new OAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
        .addFilterAfter(myFilter(), OAuth2ClientContextFilter.class)
        .httpBasic().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/google-login"))
        .and()
        .authorizeRequests()
        .antMatchers("/").permitAll()
       // .antMatchers("/","/index*").permitAll()
        .anyRequest().authenticated()
        .and().logout().logoutSuccessUrl("/").permitAll()
        ;

     // @formatter:on
    }
}