package uz.pdp.sololearnuzversion.controller.course.chapter.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.entity.user.UserEntity;
import uz.pdp.sololearnuzversion.model.receive.course.chapter.lesson.CourseChapterLessonReceiveModel;
import uz.pdp.sololearnuzversion.model.response.ApiResponse;
import uz.pdp.sololearnuzversion.service.course.chapter.lesson.CourseChapterLessonService;

@RestController
@RequestMapping("/api/sololearn/course/chapter/lesson")
public class CourseChapterLessonController {

    private final CourseChapterLessonService courseChapterLessonService;

    @Autowired
    public CourseChapterLessonController(CourseChapterLessonService courseChapterLessonService) {
        this.courseChapterLessonService = courseChapterLessonService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addCourseChapterLesson(
            @RequestBody CourseChapterLessonReceiveModel courseChapterLessonReceiveModel
    ) {

        ApiResponse apiResponse = courseChapterLessonService.addCourseChapterLesson(courseChapterLessonReceiveModel);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getCourseChapterLessonList(
            @PathVariable("id") long courseChapterId
    ) {
        return ResponseEntity.ok(courseChapterLessonService.getCourseChapterList(courseChapterId));
    }

    @GetMapping("/complete/{id}")
    public HttpEntity<?> completeLesson(
            @PathVariable("id") long lessonId
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ApiResponse apiResponse = courseChapterLessonService.completeLesson(lessonId);
        return ResponseEntity.ok(apiResponse);
    }


}
