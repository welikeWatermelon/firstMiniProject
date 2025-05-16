package ssafy.project07.service;


import ssafy.project07.domain.*;
import ssafy.project07.dto.supplement.SupplementIntakeRequest;
import ssafy.project07.dto.supplement.AlarmSettingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.repository.notification.NotificationRepository;
import ssafy.project07.repository.supplement.*;
import ssafy.project07.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplementService {

    private final SupplementRepository supplementRepository;
    private final SupplementIntakeRepository supplementIntakeRepository;
    private final CalendarLogRepository calendarLogRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository; // 로그인 기반 유저 참조용

    // 복용 기록 저장
    public void saveIntake(SupplementIntakeRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Supplement supplement = supplementRepository.findById(request.getSupplementId()).orElseThrow();

        SupplementIntake intake = new SupplementIntake();
        intake.setUser(user);
        intake.setSupplement(supplement);
        intake.setAmountTaken(request.getAmountTaken());
        intake.setIntakeTime(request.getIntakeTime());

        supplementIntakeRepository.save(intake);

        // 캘린더 로그도 함께 저장
        CalendarLog log = new CalendarLog();
        log.setUser(user);
        log.setDate(request.getIntakeTime().toLocalDate());
        log.setSupplementName(supplement.getName());
        log.setDoseMg(request.getAmountTaken());
        log.setTimeTaken(request.getIntakeTime().toLocalTime());

        calendarLogRepository.save(log);
    }

    // 캘린더 보기 (userID를 통해 캘린더 보기)
    public List<CalendarLog> getCalendarLog(Long userId, LocalDate date) {
        return calendarLogRepository.findByUserIdAndDate(userId, date);
    }

    // 알람 설정하기
    public void setAlarm(Long userId, AlarmSettingRequest request) {
        User user = userRepository.findById(userId).orElseThrow();

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(request.getMessage());
        notification.setIsRead(false);
        notification.setNotifiedAt(request.getAlarmTime().atDate(LocalDate.now()));

        // 알람 정보 저장하기
        notificationRepository.save(notification);
    }

    // 모든 영양제 리스트 보기
    public List<Supplement> getAllSupplements() {
        return supplementRepository.findAll();
    }
}
