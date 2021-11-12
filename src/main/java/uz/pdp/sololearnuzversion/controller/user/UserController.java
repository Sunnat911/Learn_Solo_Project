package uz.pdp.sololearnuzversion.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.controller.base.BaseController;
import uz.pdp.sololearnuzversion.model.receive.UserSignInReceiveModel;
import uz.pdp.sololearnuzversion.model.receive.UserSignUpReceiveModel;
import uz.pdp.sololearnuzversion.model.response.ApiResponse;
import uz.pdp.sololearnuzversion.service.course.chapter.lesson.CourseChapterLessonService;
import uz.pdp.sololearnuzversion.service.user.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/sololearn/user")
public class UserController implements BaseController {

    private final UserService userService;
    private final CourseChapterLessonService courseChapterLessonService;

    @Autowired
    public UserController(UserService userService, CourseChapterLessonService courseChapterLessonService) {
        this.userService = userService;
        this.courseChapterLessonService = courseChapterLessonService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity<?> addUser(@Valid @RequestBody UserSignUpReceiveModel userSignUpReceiveModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userSignUpReceiveModel));
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody UserSignInReceiveModel userSignInReceiveModel){
        return ResponseEntity.ok(userService.login(userSignInReceiveModel));
    }

    @GetMapping("/lesson/list/{chapterId}")
    public HttpEntity<?> getLessonList(
            @PathVariable("chapterId") long chapterId){
        ApiResponse response = courseChapterLessonService.getLessonList(chapterId);
        return ResponseEntity.ok(response);
    }

}
