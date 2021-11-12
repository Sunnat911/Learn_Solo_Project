package uz.pdp.sololearnuzversion.model.receive.course.chapter.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.pdp.sololearnuzversion.entity.course.lesson.LessonContentType;

@Data
public class LessonContent {
    @JsonProperty("lesson_content_type")
    private LessonContentType lessonContentType;
    private String data;
}
