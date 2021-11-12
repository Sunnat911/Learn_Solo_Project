package uz.pdp.sololearnuzversion.entity.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.sololearnuzversion.entity.base.BaseEntity;
import uz.pdp.sololearnuzversion.entity.course.lesson.CourseChapterLessonEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CourseChapterEntity extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private CourseEntity courseEntity;

    private String imageUrl;

    @OneToMany(mappedBy = "courseChapterEntity")
    private List<CourseChapterLessonEntity> courseChapterLessonEntityList;

}
