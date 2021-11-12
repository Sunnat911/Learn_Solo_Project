package uz.pdp.sololearnuzversion.entity.course.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.sololearnuzversion.entity.base.BaseEntity;
import uz.pdp.sololearnuzversion.entity.course.CourseChapterEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CourseChapterLessonEntity extends BaseEntity {

    @OneToMany
    private List<UrlOrTextEntity> urlOrTextEntityList;

    @JsonIgnore
    @ManyToOne
    private CourseChapterEntity courseChapterEntity;

    private long childLessonId;

}
