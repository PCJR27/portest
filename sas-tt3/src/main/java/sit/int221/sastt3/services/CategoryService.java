package sit.int221.sastt3.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sastt3.DTO.AnnouncementInsert;
import sit.int221.sastt3.entities.Announcement;
import sit.int221.sastt3.entities.Category;
import sit.int221.sastt3.exception.InvalidRequestException;
import sit.int221.sastt3.exception.ItemNotFoundException;
import sit.int221.sastt3.repositories.AnnouncementRepo;
import sit.int221.sastt3.repositories.CategoryRepo;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    public CategoryRepo repo;
    @Autowired
    private ModelMapper modelMapper;
    public List<Category> getAllCategory() {
        return repo.findAll();
    }
//    public Category getCategoryById(Integer id) {
//        return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id "+ id+ "does not exist"));
//    }

    public Category getCategoryById(Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new InvalidRequestException("Invalid Category id: " + id);
            }
            return repo.findById(id).orElseThrow(() -> new ItemNotFoundException("Category id " + id + " does not exist"));
        } catch (ItemNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id not found",e );
        } catch (InvalidRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
    public Category createCategory(Category newCategory) {
        return  repo.saveAndFlush(newCategory);
    }
    public void deleteCategory(Integer id){
        Category category = repo.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id "+ id + " does not exist"));
        repo.delete(category);
    }
    public Category updateCategory(Integer id, Category category) {
        Category existingCategory = repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id" + id + "doesn't exist!!!"));
        existingCategory.setId(category.getId());
        existingCategory.setCategoryName(category.getCategoryName());
        return repo.saveAndFlush(existingCategory);
    }
    @Autowired
    private AnnouncementRepo AnnounceRepo;

//    public Announcement addAnnouncement(AnnouncementInsert newAnnouncement){
//Announcement announcement =new Announcement();
//        Category category = getCategoryById(newAnnouncement.getCategoryId());
//        Announcement announcement = modelMapper.map(newAnnouncement,Announcement.class);
//        newAnnouncement.setAnnouncementCategory();
//        return AnnounceRepo.saveAndFlush(newAnnouncement);
//    }

//    public Announcement addAnnouncement(AnnouncementInsert newAnnouncement){
//        Announcement announcement = new Announcement();
////        Announcement add = modelMapper.map(newAnnouncement,Announcement.class);
//        // Set the fields of the announcement with the values from the newAnnouncement object
//        announcement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
//        announcement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
//        announcement.setPublishDate(newAnnouncement.getPublishDate());
//        announcement.setCloseDate(newAnnouncement.getCloseDate());
//        announcement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
//        announcement.setCategoryId(newAnnouncement.getCategoryId());
////
////        // Set the announcement category
//        Category category = getCategoryById(newAnnouncement.getCategoryId());
//        announcement.setAnnouncementCategory(category);
////
//        // Save the new announcement
//        return AnnounceRepo.saveAndFlush(announcement);
//
////        Category category = getCategoryById(newAnnouncement.getCategoryId());
////        newAnnouncement.setAnnouncementCategory(category);
////        return AnnounceRepo.saveAndFlush(newAnnouncement);
//    }
public Announcement addAnnouncement(AnnouncementInsert newAnnouncement){
    try{
        Announcement announcement = new Announcement();
        // Set the fields of the announcement with the values from the newAnnouncement object
        announcement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
        announcement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
        announcement.setPublishDate(newAnnouncement.getPublishDate());
        announcement.setCloseDate(newAnnouncement.getCloseDate());
        announcement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
        announcement.setCategoryId(newAnnouncement.getCategoryId());
//        // Set the announcement category
        Category category = getCategoryById(newAnnouncement.getCategoryId());
        announcement.setAnnouncementCategory(category);
//
        // Save the new announcement
        return AnnounceRepo.saveAndFlush(announcement);
    }
    catch (InvalidRequestException e){throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);}

}



}
