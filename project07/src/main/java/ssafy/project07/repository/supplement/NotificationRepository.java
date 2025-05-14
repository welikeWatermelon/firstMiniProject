package ssafy.project07.repository.supplement;

import ssafy.project07.domain.Notification;
import ssafy.project07.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 알림 저장 및 조회 기본 제공
}