package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sololearnuzversion.entity.course.lesson.UrlOrTextEntity;

public interface UrlOrTextRepository extends JpaRepository<UrlOrTextEntity,Long> {
}
