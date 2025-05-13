package ssafy.project07.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Notification {
    @Id @GeneratedValue
    private Long id;
    private String message;
    private boolean isRead;
    private LocalDateTime notifiedAt;

    @ManyToOne
    private User user;
}