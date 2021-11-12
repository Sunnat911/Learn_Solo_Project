package uz.pdp.sololearnuzversion.entity.course.lesson.answer;

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
public class TaskAnswer extends BaseEntity {

    private String questionAnswer;
    private byte correctOrder;

}
