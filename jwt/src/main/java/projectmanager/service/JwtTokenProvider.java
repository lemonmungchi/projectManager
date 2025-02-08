package projectmanager.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}") // ✅ 환경 변수에서 불러오기
    private String secretKey;

    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1시간
    private Key key;

    @Autowired
    private StringRedisTemplate redisTemplate; // ✅ Redis 추가

    // ✅ @PostConstruct를 이용한 안전한 키 초기화
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // ✅ JWT 생성 (유저 ID, 역할, 이메일 포함)
    public String generateToken(String userId, String role, String email) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ 최신 방식 적용
                .compact();
    }

    // ✅ JWT 검증
    public boolean validateToken(String token) {
        // ✅ Redis에서 토큰이 무효화되었는지 확인
        if (redisTemplate.hasKey(token)) {
            return false; // 블랙리스트에 있는 경우
        }
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ JWT에서 사용자 정보 추출
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ 로그아웃 처리 (블랙리스트 추가 → Redis 사용)
    public void invalidateToken(String token) {
        redisTemplate.opsForValue().set(token, "invalid", EXPIRATION_TIME, TimeUnit.MILLISECONDS);
    }
}
