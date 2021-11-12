package uz.pdp.sololearnuzversion.model.receive.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CourseReceiveModel {
    private String name;
    @JsonProperty("content_type")
    private String contentType;
    private String base64; // rasmni base64 o'girib beradi client
}
