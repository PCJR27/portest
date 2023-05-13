package sit.int221.sastt3.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T>{
    public PageDTO(List subList, PageRequest of, int totalElements) {
    }
    private List<T> content;
    private Boolean last;
    private Boolean first;
    private Integer totalPages;
    private Long totalElements;
    private Integer size;
    private Integer page;
    @JsonIgnore
    private Integer number;



    public Integer getPage() {
        return number;
    }

//    public void setContent(List<?> contentList) {
//    }
}
