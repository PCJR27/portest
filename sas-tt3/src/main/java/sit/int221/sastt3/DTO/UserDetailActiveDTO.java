package sit.int221.sastt3.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailActiveDTO {
    private Integer id;
    private String announcementTitle;
    private String announcementDescription;
    @JsonIgnore
    private CategoryDTO announcement;


    public String getAnnouncementCategory(){
        return announcement == null ? "-" : announcement.getCategoryName();
    }

}
