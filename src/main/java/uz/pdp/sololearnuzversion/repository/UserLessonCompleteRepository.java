package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sololearnuzversion.entity.course.lesson.UserLessonCompleteEntity;

import java.util.List;

public interface UserLessonCompleteRepository extends JpaRepository<UserLessonCompleteEntity,Long> {
    List<UserLessonCompleteEntity> findByUserId(long userId);
}
