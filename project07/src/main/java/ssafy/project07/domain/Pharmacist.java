package ssafy.project07.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Pharmacist {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String title;
    private String profileImage;

    @OneToMany(mappedBy = "pharmacist")
    private List<Column> columns;

    @OneToMany(mappedBy = "pharmacist")
    private List<Follow> followers;
}
