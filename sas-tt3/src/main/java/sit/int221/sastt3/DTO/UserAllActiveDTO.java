package sit.int221.sastt3.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAllActiveDTO {
    private Integer id;
    private String announcementTitle;
    @JsonIgnore
    private CategoryDTO announcement;


    public String getAnnouncementCategory(){
        return announcement == null ? "-" : announcement.getCategoryName();
    }
}
