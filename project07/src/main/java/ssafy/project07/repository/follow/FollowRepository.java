package ssafy.project07.repository.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Follow;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUserId(Long userId);
}
