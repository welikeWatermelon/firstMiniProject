package ssafy.project07.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Follow {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Pharmacist pharmacist;
}