package ssafy.project07.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "members")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String gender;
    private String nickname;
    private String profileImage;

    @OneToMany(mappedBy = "user")
    private List<SupplementIntake> supplementIntakes;

    @OneToMany(mappedBy = "user")
    private List<QuestHistory> questHistories;

    @OneToMany(mappedBy = "user")
    private List<CalendarLog> calendarLogs;

    @OneToMany(mappedBy = "user")
    private List<CommunityPost> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<Follow> follows;

    @OneToMany(mappedBy = "user")
    private List<UserBadge> userBadges;
}