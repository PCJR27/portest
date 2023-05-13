package sit.int221.sastt3.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementInsert {
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private String announcementCategory ;
    private Integer categoryId;
    @JsonIgnore
    private CategoryDTO announcement;

    public String getAnnouncementCategory() {
        return announcement == null ? "-" : announcement.getCategoryName();
    }

}
