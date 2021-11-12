package uz.pdp.sololearnuzversion.entity.course.lesson.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.sololearnuzversion.entity.base.BaseEntity;
import uz.pdp.sololearnuzversion.entity.course.lesson.answer.TaskAnswer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CourseChapterLessonTaskEntity extends BaseEntity {


    private String question;

    @OneToMany
    private List<TaskAnswer> taskAnswers;

    private TaskResponseTypeEnum taskResponseTypeEnum;

}
