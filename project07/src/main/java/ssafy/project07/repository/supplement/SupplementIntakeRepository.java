package ssafy.project07.repository.supplement;

import ssafy.project07.domain.SupplementIntake;
import ssafy.project07.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SupplementIntakeRepository extends JpaRepository<SupplementIntake, Long> {
    // 현재는 저장 기능만 사용 중
}