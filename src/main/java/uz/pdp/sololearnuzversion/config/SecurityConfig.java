package uz.pdp.sololearnuzversion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import uz.pdp.sololearnuzversion.entity.user.UserEntity;
import uz.pdp.sololearnuzversion.filter.JwtTokenFilter;
import uz.pdp.sololearnuzversion.service.user.ApplicationUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
//        securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserDetailService applicationUserDetailService;
    private final JwtTokenFilter jwtTokenFilter;


    @Autowired
    public SecurityConfig(ApplicationUserDetailService applicationUserDetailService, JwtTokenFilter jwtTokenFilter) {
        this.applicationUserDetailService = applicationUserDetailService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailService).passwordEncoder(this.passwordEncoder());//TODO check user detail service
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(jwtTokenFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/sololearn/user/*").permitAll()
                .antMatchers("/api/sololearn/course/list").permitAll()
                .antMatchers(HttpMethod.GET,"/api/sololearn/course/chapter/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }





}
