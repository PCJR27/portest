package sit.int221.sastt3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.sastt3.entities.Announcement;
import sit.int221.sastt3.entities.Category;
import sit.int221.sastt3.services.AnnouncementService;
import sit.int221.sastt3.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public List<Category> getAllCustomer(){
        return service.getAllCategory();
    }

    @GetMapping("/category/{id}")
    public Category getAllCustomer(@PathVariable Integer id){
        return service.getCategoryById(id);
    }

}
