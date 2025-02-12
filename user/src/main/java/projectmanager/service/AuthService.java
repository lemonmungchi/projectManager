package projectmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import projectmanager.domain.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Service
public class AuthService {
    private final RestTemplate restTemplate;
    private final String jwtServiceUrl;

    public AuthService() {
        this.restTemplate = new RestTemplate();
        this.jwtServiceUrl = "http://jwt-service:8082"; 
    }

    // ✅ 로그인 시 JWT 토큰 요청
    public String generateToken(User user) {

        String url = jwtServiceUrl + "/jwt/generate";
        System.out.println(url);
        
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userId", String.valueOf(user.getId())); 
        requestBody.put("role", user.getRole());                 
        requestBody.put("email", user.getEmail());   
        System.out.println(requestBody);            

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);
        System.out.println(response);
        return response.getBody();
    }

    // ✅ 토큰 검증 요청
    public Boolean validateToken(String token) {
        String url = jwtServiceUrl + "/jwt/validate";

        // ✅ 헤더에 JWT 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
            url, HttpMethod.POST, requestEntity, Boolean.class
        );

        return response.getBody() != null && response.getBody();
    }

    // ✅ 로그아웃 시 JWT 삭제 요청
    public String invalidateToken(String token) {
        String url = jwtServiceUrl + "/jwt/invalidate";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);
        return response.getBody();
    }
}
