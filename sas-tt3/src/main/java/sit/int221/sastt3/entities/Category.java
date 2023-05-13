package sit.int221.sastt3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "categoryId")
    private Integer id;
    private String categoryName;
    @JsonIgnore
    @OneToMany(mappedBy = "announcementCategory")
    private Set<Announcement> announcements = new LinkedHashSet<>();
}
