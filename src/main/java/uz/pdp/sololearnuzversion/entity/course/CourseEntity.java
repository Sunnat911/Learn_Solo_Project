package uz.pdp.sololearnuzversion.entity.course;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.sololearnuzversion.entity.attachment.AttachmentEntity;
import uz.pdp.sololearnuzversion.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import uz.pdp.sololearnuzversion.entity.course.CourseChapterEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CourseEntity extends BaseEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "courseEntity")
    private List<CourseChapterEntity> courseChapterEntity;

    @JsonProperty("image_url")
    private String imageUrl;

}
