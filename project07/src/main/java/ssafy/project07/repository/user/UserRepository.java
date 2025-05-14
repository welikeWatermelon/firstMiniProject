package ssafy.project07.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 인증 및 참조용
}