package sit.int221.sastt3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @Column(name = "announcementId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "announcementTitle", nullable = false, length = 200)
    private String announcementTitle;
    @Column(name = "announcementDescription", nullable = false, length = 10000)
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
//    private String announcementCategory;
//    private String announcementCate ;
    @Column(insertable = false,updatable = false)
    private Integer categoryId;

@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category announcementCategory ;

    @JsonProperty("announcementCategory")
    public String getAnnouncementCategoryName() {
        if (announcementCategory != null) {
            return announcementCategory.getCategoryName();
        } else {
            return null;
        }
    }


//    @JsonProperty("announcementCategory")
//    public Integer getAnnouncementCategoryId() {
//        if (announcementCategory != null) {
//            return announcementCategory.getId();
//        } else {
//            return 1;
//        }
//    }
//    private String getAnnouncementCategory(){
//        return category.getCategoryName();
//    }


}


