package projectmanager.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import reactor.core.publisher.Mono;
import projectmanager.domain.LoginRequest;
import projectmanager.domain.User;
import projectmanager.domain.UserRepository;
import projectmanager.service.AuthService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.authService = authService;
    }

    // ✅ 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    // ✅ 로그인 & JWT 발급 요청 (수정됨)
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest request) {
        return Mono.justOrEmpty(userRepository.findByEmail(request.getEmail())) // ✅ Optional → Mono 변환 수정
            .flatMap(user -> {
                if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    return Mono.error(new RuntimeException("Invalid credentials"));
                }
                return authService.generateToken(String.valueOf(user.getId())) // ✅ Long → String 변환
                    .map(token -> ResponseEntity.ok(token));
            })
            .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    // ✅ 로그아웃
    @PostMapping("/logout")
    public Mono<ResponseEntity<String>> logout(@RequestHeader("Authorization") String token) {
        return authService.invalidateToken(token)
            .map(response -> ResponseEntity.ok("Logged out successfully"));
    }
}
