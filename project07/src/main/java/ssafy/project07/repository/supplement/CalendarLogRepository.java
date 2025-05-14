package ssafy.project07.repository.supplement;

import ssafy.project07.domain.CalendarLog;
import ssafy.project07.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CalendarLogRepository extends JpaRepository<CalendarLog, Long> {
    List<CalendarLog> findByUserIdAndDate(Long userId, LocalDate date);
}