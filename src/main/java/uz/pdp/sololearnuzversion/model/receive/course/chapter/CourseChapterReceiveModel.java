package uz.pdp.sololearnuzversion.model.receive.course.chapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CourseChapterReceiveModel {

    private String name;
    private String base64;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("course_id")
    private long courseId;
}
