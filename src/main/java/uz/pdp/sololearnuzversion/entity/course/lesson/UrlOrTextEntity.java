package uz.pdp.sololearnuzversion.entity.course.lesson;


import lombok.*;
import uz.pdp.sololearnuzversion.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class UrlOrTextEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private LessonContentType lessonContentType;
    private String data;

}

