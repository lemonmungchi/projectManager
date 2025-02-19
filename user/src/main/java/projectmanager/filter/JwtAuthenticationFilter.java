package projectmanager.filter;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders; 
import org.springframework.http.HttpMethod; 
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final RestTemplate restTemplate;
    private final String jwtServiceUrl;

    public JwtAuthenticationFilter(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.jwtServiceUrl = env.getProperty("jwt.service.url");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (StringUtils.hasText(token)) {
            boolean isValid = validateTokenWithJwtService(token);
            if (!isValid) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateTokenWithJwtService(String token) {
        try {
            String validationUrl = jwtServiceUrl + "/jwt/validate";

            // ✅ 헤더에 JWT 토큰 추가
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

            // ✅ POST 요청으로 변경
            ResponseEntity<Boolean> response = restTemplate.exchange(
                validationUrl, HttpMethod.POST, requestEntity, Boolean.class
            );

            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            System.err.println("❌ JWT 검증 요청 실패: " + e.getMessage());
            return false;
        }
    }
}
