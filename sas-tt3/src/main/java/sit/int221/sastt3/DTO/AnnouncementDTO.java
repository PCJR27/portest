package sit.int221.sastt3.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import sit.int221.sastt3.entities.Category;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AnnouncementDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String announcementTitle;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private String announcementCategory ;
    private Integer categoryId;
@JsonIgnore
    private CategoryDTO announcement;


    public String getAnnouncementCategory(){
        return announcement == null ? "-" : announcement.getCategoryName();
    }
    public Integer getCategoryId(){
        return announcement == null ? 1 : announcement.getCategoryId();
    }
//        private String getCategory(){
//        return category.getCategoryName();
//    }

}
