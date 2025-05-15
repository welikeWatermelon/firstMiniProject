package ssafy.project07.repository.community;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.CommunityPost;

public interface CommunityRepository extends JpaRepository<CommunityPost, Long> {

}
