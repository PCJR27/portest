package sit.int221.sastt3.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.sastt3.DTO.PageDTO;
import sit.int221.sastt3.entities.Announcement;

import java.util.List;

public interface AnnouncementRepo extends JpaRepository<Announcement,Integer> {
    List<Announcement> findAllByOrderByIdDesc();
    List<Announcement> findAllByAnnouncementDisplayEqualsOrderByIdDesc(String displayValue);
    List<Announcement> findAllByCategoryIdEqualsOrderByIdDesc(Integer id);
}
