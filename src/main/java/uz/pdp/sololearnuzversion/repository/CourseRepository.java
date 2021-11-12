package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.sololearnuzversion.entity.course.CourseEntity;


public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
}
