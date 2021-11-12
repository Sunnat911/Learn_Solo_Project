package uz.pdp.sololearnuzversion.entity.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AttachmentContentEntity {

    @Id
    private long id;

    private byte[] content;

}
