package uz.pdp.sololearnuzversion.controller.course.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.model.receive.course.chapter.CourseChapterReceiveModel;
import uz.pdp.sololearnuzversion.model.response.ApiResponse;
import uz.pdp.sololearnuzversion.service.course.chapter.CourseChapterService;


@RestController
@RequestMapping("/api/sololearn/course/chapter")
public class CourseChapterController {

    private final CourseChapterService courseChapterService;

    @Autowired
    public CourseChapterController(CourseChapterService courseChapterService) {
        this.courseChapterService = courseChapterService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addCourseChapter(
            @RequestBody CourseChapterReceiveModel courseChapterReceiveModel
    ){
        return ResponseEntity.ok(courseChapterService.addCourseChapter(courseChapterReceiveModel));
    }


    @GetMapping("/list")
    public ResponseEntity<?> getCourseChapterList(){
        return ResponseEntity.ok(courseChapterService.getCourseChapterList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseChapterByCourseId(
            @PathVariable("id") long courseId
    ){
        ApiResponse list = courseChapterService.getCourseChapterList(courseId);
        return ResponseEntity.ok(list);
    }
}
