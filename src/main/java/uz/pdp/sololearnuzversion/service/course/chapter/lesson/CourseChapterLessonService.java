package uz.pdp.sololearnuzversion.service.course.chapter.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.config.CurrentUser;
import uz.pdp.sololearnuzversion.entity.course.CourseChapterEntity;
import uz.pdp.sololearnuzversion.entity.course.lesson.CourseChapterLessonEntity;
import uz.pdp.sololearnuzversion.entity.course.lesson.LessonContentType;
import uz.pdp.sololearnuzversion.entity.course.lesson.UrlOrTextEntity;
import uz.pdp.sololearnuzversion.entity.course.lesson.UserLessonCompleteEntity;
import uz.pdp.sololearnuzversion.entity.user.UserEntity;
import uz.pdp.sololearnuzversion.model.receive.course.chapter.lesson.CourseChapterLessonReceiveModel;
import uz.pdp.sololearnuzversion.model.receive.course.chapter.lesson.LessonContent;
import uz.pdp.sololearnuzversion.model.response.ApiResponse;
import uz.pdp.sololearnuzversion.model.response.user.UserLessonListResponseModel;
import uz.pdp.sololearnuzversion.repository.CourseChapterLessonRepository;
import uz.pdp.sololearnuzversion.repository.CourseChapterRepository;
import uz.pdp.sololearnuzversion.repository.UrlOrTextRepository;
import uz.pdp.sololearnuzversion.repository.UserLessonCompleteRepository;
import uz.pdp.sololearnuzversion.service.base.BaseService;
import uz.pdp.sololearnuzversion.service.file.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseChapterLessonService implements BaseService {

    private final CourseChapterLessonRepository courseChapterLessonRepository;
    private final CourseChapterRepository courseChapterRepository;
    private final UrlOrTextRepository urlOrTextRepository;
    private final FileService fileService;
    private final UserLessonCompleteRepository userLessonCompleteRepository;
    private final CurrentUser currentUser;

    @Autowired
    public CourseChapterLessonService(CourseChapterLessonRepository courseChapterLessonRepository, CourseChapterRepository courseChapterRepository, UrlOrTextRepository urlOrTextRepository, FileService fileService, UserLessonCompleteRepository userLessonCompleteRepository, CurrentUser currentUser) {
        this.courseChapterLessonRepository = courseChapterLessonRepository;
        this.courseChapterRepository = courseChapterRepository;
        this.urlOrTextRepository = urlOrTextRepository;
        this.fileService = fileService;
        this.userLessonCompleteRepository = userLessonCompleteRepository;
        this.currentUser = currentUser;
    }

    public ApiResponse addCourseChapterLesson(
            CourseChapterLessonReceiveModel courseChapterLessonReceiveModel
    ) {
        Optional<CourseChapterEntity> optionalCourseChapterEntity
                = courseChapterRepository.findById(courseChapterLessonReceiveModel.getCourseChapterId());

        if (optionalCourseChapterEntity.isEmpty())
            return ERROR_COURSE_CHAPTER_NOT_FOUND;


        CourseChapterLessonEntity courseChapterLessonEntity
                = new CourseChapterLessonEntity();

        List<UrlOrTextEntity> urlOrTextEntityList
                = this.add(courseChapterLessonReceiveModel);

        courseChapterLessonEntity.setUrlOrTextEntityList(urlOrTextEntityList);
        courseChapterLessonEntity.setName(courseChapterLessonReceiveModel.getName());
        courseChapterLessonEntity.setCourseChapterEntity(optionalCourseChapterEntity.get());

        Optional<CourseChapterLessonEntity> optionalCourseChapterLessonEntityA
                = courseChapterLessonRepository.findByCourseChapterEntityIdAndChildLessonId(
                courseChapterLessonReceiveModel.getCourseChapterId(),
                0L
        );
        CourseChapterLessonEntity chapterLessonEntityB = courseChapterLessonRepository.save(courseChapterLessonEntity);

        if (optionalCourseChapterLessonEntityA.isPresent()) {
            CourseChapterLessonEntity parentChapterLessonEntity = optionalCourseChapterLessonEntityA.get();
            parentChapterLessonEntity.setChildLessonId(chapterLessonEntityB.getId());
            courseChapterLessonRepository.save(parentChapterLessonEntity);
        }
        return SUCCESS_V2;

    }

    private List<UrlOrTextEntity> add(
            CourseChapterLessonReceiveModel c
    ) {

        List<UrlOrTextEntity> list = new ArrayList<>();

        for (LessonContent lessonContent : c.getLessonContentList()) {
            if (lessonContent.getLessonContentType().equals(LessonContentType.IMAGE)) {
                String path = fileService.saveFile(lessonContent.getData(), ".png");

                UrlOrTextEntity urlOrTextEntity = new UrlOrTextEntity();
                urlOrTextEntity.setLessonContentType(LessonContentType.IMAGE);
                urlOrTextEntity.setData(path);
                urlOrTextRepository.save(urlOrTextEntity);
                list.add(urlOrTextEntity);
            } else {
                UrlOrTextEntity urlOrTextEntity = new UrlOrTextEntity();
                urlOrTextEntity.setLessonContentType(LessonContentType.TEXT);
                urlOrTextEntity.setData(lessonContent.getData());
                urlOrTextRepository.save(urlOrTextEntity);
                list.add(urlOrTextEntity);
            }
        }
        return list;
    }

    public ApiResponse getCourseChapterList(long courseChapterId) {
        List<CourseChapterLessonEntity> list
                = courseChapterLessonRepository.findByCourseChapterEntityId(courseChapterId);

        if (list.isEmpty())
            return ERROR_COURSE_CHAPTER_NOT_FOUND;

        SUCCESS.setData(list);
        return SUCCESS;
    }

    public ApiResponse completeLesson(
            long id // lessonId
    ){
        UserEntity currentUser = this.currentUser.getCurrentUser(); // current userni
        CourseChapterLessonEntity courseChapterLessonEntityOtasi
                = courseChapterLessonRepository.getById(id); // lesson

        UserLessonCompleteEntity userLessonCompleteEntity
                = new UserLessonCompleteEntity();

        userLessonCompleteEntity.setUserId(currentUser.getId());
        userLessonCompleteEntity.setLessonId(courseChapterLessonEntityOtasi.getChildLessonId());
        userLessonCompleteRepository.save(userLessonCompleteEntity);
        return SUCCESS_V2;
    }


    public ApiResponse  getLessonList(long chapterId){
        UserEntity currentUser = this.currentUser.getCurrentUser();

        List<CourseChapterLessonEntity> allLessonList
                = courseChapterLessonRepository.findByCourseChapterEntityId(chapterId);

        List<UserLessonCompleteEntity> completeLessonList
                = this.userLessonCompleteRepository.findByUserId(currentUser.getId());

        List<UserLessonListResponseModel> list = allLessonList.stream().map((lesson) -> {

            UserLessonListResponseModel userLessonListResponseModel =
                    new UserLessonListResponseModel();

            completeLessonList.forEach((completeLesson) -> {
                if (lesson.getId() == completeLesson.getLessonId())
                    userLessonListResponseModel.setOpen(true);
            });

            userLessonListResponseModel.setLessonId(lesson.getId());
            userLessonListResponseModel.setLessonName(lesson.getName());

            return userLessonListResponseModel;
        }).collect(Collectors.toList());

        list.get(0).setOpen(true);

        SUCCESS.setData(list);
        return SUCCESS;
    }
}
