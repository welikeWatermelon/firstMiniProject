package ssafy.project07.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CalendarLog {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String supplementName;
    private int doseMg;
    private LocalTime timeTaken;

    @ManyToOne
    private User user;
}