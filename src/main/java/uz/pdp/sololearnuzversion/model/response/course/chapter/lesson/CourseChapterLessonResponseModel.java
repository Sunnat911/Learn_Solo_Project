package uz.pdp.sololearnuzversion.model.response.course.chapter.lesson;

import lombok.Data;

import java.util.List;

@Data
public class CourseChapterLessonResponseModel {

    private String name;
    private List<CourseChapterLessonContent> courseChapterLessonContents;

}
