package uz.pdp.sololearnuzversion.entity.course.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.sololearnuzversion.entity.base.BaseEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserLessonCompleteEntity extends BaseEntity {
    private long userId;
    private long lessonId;
    private int stateId;
}
