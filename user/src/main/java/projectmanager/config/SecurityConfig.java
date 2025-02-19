package projectmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projectmanager.filter.JwtAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(restTemplate(), environment);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/users/register", "/users/login","/users/validate").permitAll() 
            .anyRequest().authenticated() // 나머지 요청은 인증 필요
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 사용 → 세션X
            .and()
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // JWT 필터 적용
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 추가적인 인증 설정 가능 (현재는 기본 설정)
    }
}
