package uz.pdp.sololearnuzversion.model.receive.course.chapter.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.sololearnuzversion.entity.course.lesson.LessonContentType;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CourseChapterLessonReceiveModel {

    private String name;
    @JsonProperty("content_list")
    private List<LessonContent> lessonContentList;
    @JsonProperty("course_chapter_id")
    private long courseChapterId;

}

