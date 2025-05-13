package ssafy.project07.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Badge {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String imageUrl;
    private String description;

    @OneToMany(mappedBy = "badge")
    private List<UserBadge> userBadges;
}