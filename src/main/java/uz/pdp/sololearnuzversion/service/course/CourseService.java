package uz.pdp.sololearnuzversion.service.course;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.course.CourseEntity;
import uz.pdp.sololearnuzversion.model.receive.course.CourseReceiveModel;
import uz.pdp.sololearnuzversion.model.response.ApiResponse;
import uz.pdp.sololearnuzversion.repository.CourseRepository;
import uz.pdp.sololearnuzversion.service.base.BaseService;
import uz.pdp.sololearnuzversion.service.file.FileService;

@Service
@RequiredArgsConstructor
public class CourseService implements BaseService {

    private final CourseRepository courseRepository;
    private final FileService fileService;

    public ApiResponse addCourse(
            CourseReceiveModel courseReceiveModel
    ){
        String imageUrl = fileService.saveFile(courseReceiveModel.getBase64(), courseReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_FILE_CREATE;

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseReceiveModel.getName());
        courseEntity.setImageUrl(imageUrl);
        courseRepository.save(courseEntity);

        return SUCCESS_V2;

    }

    public ApiResponse getCourseList(){
        SUCCESS.setData(courseRepository.findAll());
        return SUCCESS;
    }

}
