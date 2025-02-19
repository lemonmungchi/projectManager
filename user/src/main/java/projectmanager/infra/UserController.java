package projectmanager.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projectmanager.domain.LoginRequest;
import projectmanager.domain.User;
import projectmanager.domain.UserRequest;
import projectmanager.service.UserService;
import projectmanager.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // ✅ 회원가입 (UserService 사용)
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest request) {
        System.out.println("Received User Data: " + request);

        try {
            System.out.println("Received User Data: " + request.getName()+request.getEmail()+request.getPassword()+request.getRole());

            User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
            System.out.println("Converted User Object: " + user);
            
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    // ✅ 로그인 (JWT 발급)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        System.out.println("Request!!!!: " + request.getEmail()+request.getPassword());
        try {
            String token = userService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    // ✅ JWT 검증
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 제거
        }
        boolean isValid = authService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }

    // ✅ 로그아웃 (JWT 무효화)
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        authService.invalidateToken(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}
