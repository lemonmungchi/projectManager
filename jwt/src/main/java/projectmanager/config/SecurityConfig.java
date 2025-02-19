package projectmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/jwt/generate","/jwt/invalidate","/jwt/validate").permitAll() // JWT 생성 API는 인증 없이 허용
            .anyRequest().authenticated() // 나머지는 인증 필요
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // JWT 사용 → 세션X
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 필요 시 사용자 인증 관련 설정 추가 가능
    }
}
