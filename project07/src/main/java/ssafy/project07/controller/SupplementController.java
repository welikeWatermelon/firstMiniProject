package ssafy.project07.controller;


import ssafy.project07.domain.CalendarLog;
import ssafy.project07.domain.Supplement;
import ssafy.project07.dto.supplement.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.service.SupplementService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/supplements")
@RequiredArgsConstructor
public class SupplementController {

    private final SupplementService supplementService;

    // POST /api/supplements/intake
    @PostMapping("/intake")
    public void recordIntake(@RequestBody SupplementIntakeRequest request,
                             @RequestParam Long userId) {
        supplementService.saveIntake(request, userId);
    }

    // GET /api/supplements/calendar?date=2025-05-13&userId=1
    @GetMapping("/calendar")
    public List<CalendarLog> getCalendar(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                         @RequestParam Long userId) {
        return supplementService.getCalendarLog(userId, date);
    }

    // POST /api/supplements/alarm
    @PostMapping("/alarm")
    public void setAlarm(@RequestBody AlarmSettingRequest request,
                         @RequestParam Long userId) {
        supplementService.setAlarm(userId, request);
    }

    // GET /api/supplements/list
    @GetMapping("/list")
    public List<Supplement> getAllSupplements() {
        return supplementService.getAllSupplements();
    }
}

