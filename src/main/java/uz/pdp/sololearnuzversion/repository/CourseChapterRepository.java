package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.sololearnuzversion.entity.course.CourseChapterEntity;
import uz.pdp.sololearnuzversion.entity.course.CourseEntity;

import java.util.List;

public interface CourseChapterRepository extends JpaRepository<CourseChapterEntity,Long> {

    List<CourseChapterEntity> findByCourseEntity(CourseEntity courseEntity);

    @Query(value = "select t.* from course_chapter_entity t where t.course_entity_id = ?1 ",nativeQuery = true)

    List<CourseChapterEntity> ketmonList(long courseId);

}
