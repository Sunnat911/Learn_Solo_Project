package uz.pdp.sololearnuzversion.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.model.receive.course.CourseReceiveModel;
import uz.pdp.sololearnuzversion.service.course.CourseService;

@RestController
@RequestMapping("/api/sololearn/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public HttpEntity<?> getCourseList(){
        return ResponseEntity.ok(courseService.getCourseList());
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> something(
            @RequestBody CourseReceiveModel courseReceiveModel
            ){
        return ResponseEntity.ok(courseService.addCourse(courseReceiveModel));
    }
}
