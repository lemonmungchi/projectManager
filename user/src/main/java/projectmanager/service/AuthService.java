package projectmanager.service;

import org.springframework.stereotype.Service;
import projectmanager.client.JwtClient;
import projectmanager.domain.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final JwtClient jwtClient;

    public AuthService(JwtClient jwtClient) {
        this.jwtClient = jwtClient;
    }

    // ✅ 로그인 시 JWT 토큰 요청
    public String generateToken(User user) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userId", String.valueOf(user.getId()));
        requestBody.put("role", user.getRole());
        requestBody.put("email", user.getEmail());

        return jwtClient.generateToken(requestBody);
    }

    // ✅ 토큰 검증 요청
    public Boolean validateToken(String token) {
        return jwtClient.validateToken("Bearer " + token);
    }

    // ✅ 로그아웃 시 JWT 삭제 요청
    public String invalidateToken(String token) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        return jwtClient.invalidateToken(requestBody);
    }
}
