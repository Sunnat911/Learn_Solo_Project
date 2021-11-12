package uz.pdp.sololearnuzversion.model.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLessonListResponseModel {

    @JsonProperty("id")
    private long lessonId;

    @JsonProperty("lesson_name")
    private String lessonName;

    @JsonProperty("is_open")
    private boolean open = false;

}
