package sit.int221.sastt3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.sastt3.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
