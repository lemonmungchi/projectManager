package projectmanager.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ 이메일로 사용자 검색
    Optional<User> findByEmail(String email);
}
