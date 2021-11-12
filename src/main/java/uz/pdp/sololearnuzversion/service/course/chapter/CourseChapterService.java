package uz.pdp.sololearnuzversion.service.course.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.course.CourseChapterEntity;
import uz.pdp.sololearnuzversion.entity.course.CourseEntity;
import uz.pdp.sololearnuzversion.model.receive.course.chapter.CourseChapterReceiveModel;
import uz.pdp.sololearnuzversion.model.response.ApiResponse;
import uz.pdp.sololearnuzversion.repository.CourseChapterRepository;
import uz.pdp.sololearnuzversion.repository.CourseRepository;
import uz.pdp.sololearnuzversion.service.base.BaseService;
import uz.pdp.sololearnuzversion.service.file.FileService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseChapterService implements BaseService {

    private final CourseRepository courseRepository;
    private final CourseChapterRepository courseChapterRepository;
    private final FileService fileService;

    @Autowired
    public CourseChapterService(CourseRepository courseRepository, CourseChapterRepository courseChapterRepository, FileService fileService) {
        this.courseRepository = courseRepository;
        this.courseChapterRepository = courseChapterRepository;
        this.fileService = fileService;
    }

    public ApiResponse addCourseChapter(
            CourseChapterReceiveModel courseChapterReceiveModel
    ){

        Optional<CourseEntity> optionalCourseEntity
                = courseRepository.findById(courseChapterReceiveModel.getCourseId());

        if (optionalCourseEntity.isEmpty())
            return ERROR_COURSE_NOT_FOUND;

        String path = fileService.saveFile(courseChapterReceiveModel.getBase64(), courseChapterReceiveModel.getContentType());

        if (path == null)
            return ERROR_FILE_CREATE;


        CourseChapterEntity courseChapterEntity
                = new CourseChapterEntity();

        courseChapterEntity.setCourseEntity(optionalCourseEntity.get());
        courseChapterEntity.setName(courseChapterReceiveModel.getName());
        courseChapterEntity.setImageUrl(path);

        courseChapterRepository.save(courseChapterEntity);
        return SUCCESS_V2;
    }

    public ApiResponse getCourseChapterList(){
        SUCCESS.setData(courseChapterRepository.findAll());
        return SUCCESS;
    }

    public ApiResponse getCourseChapterList(long courseId){
        Optional<CourseEntity> optionalCourseEntity
                = courseRepository.findById(courseId);

        if (optionalCourseEntity.isEmpty())
            return ERROR_COURSE_NOT_FOUND;

        List<CourseChapterEntity> courseChapterEntityList = courseChapterRepository.ketmonList(courseId);
        SUCCESS.setData(courseChapterEntityList);
        return SUCCESS;
    }
}
