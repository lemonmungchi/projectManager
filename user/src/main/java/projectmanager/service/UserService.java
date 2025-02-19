package projectmanager.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectmanager.domain.User;
import projectmanager.domain.UserRepository;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // ✅ 회원가입 (비밀번호 null 체크 후 저장)
    public void registerUser(User user) {
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 값입니다.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ 비밀번호 암호화 저장

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("회원가입 중 오류 발생: " + e.getMessage());
        }
    }

    // ✅ 로그인 (JWT 발급)
    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isPresent()){
            System.out.println("User found!!!!: " + userOpt.get().getEmail() + " " + userOpt.get().getName());
        }else{
            System.out.println("User not found for email: " + email);
        }


        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return authService.generateToken(user); // ✅ JWT 발급
    }
}
