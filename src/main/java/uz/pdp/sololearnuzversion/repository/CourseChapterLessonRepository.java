package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.sololearnuzversion.entity.course.lesson.CourseChapterLessonEntity;

import java.util.List;
import java.util.Optional;

public interface CourseChapterLessonRepository extends JpaRepository<CourseChapterLessonEntity,Long> {
    List<CourseChapterLessonEntity> findByCourseChapterEntityId(long courseChapterId);
    @Query(value = "select t.* from course_chapter_lesson_entity t where t.course_chapter_entity_id = ?1 and child_lesson_id = ?2 limit 1",nativeQuery = true)
    Optional<CourseChapterLessonEntity> findByCourseChapterEntityIdAndChildLessonId(long courseChapterId, Long lessonId);
}
