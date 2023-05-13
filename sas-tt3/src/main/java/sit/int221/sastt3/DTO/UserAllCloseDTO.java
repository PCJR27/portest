package sit.int221.sastt3.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UserAllCloseDTO {
    private Integer id;
    private String announcementTitle;
    private ZonedDateTime closeDate;
    private String announcementDisplay;

    @JsonIgnore
    private CategoryDTO announcement;


    public String getAnnouncementCategory(){
        return announcement == null ? "-" : announcement.getCategoryName();
    }
}
