package ssafy.project07.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CommunityPost {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String imageUrl;

    @ElementCollection
    private List<String> supplementTags;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "communityPost")
    private List<Comment> comments;
}