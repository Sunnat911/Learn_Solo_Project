package uz.pdp.sololearnuzversion.entity.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AttachmentEntity {

    @Id
    private long id;

    private String fileName;

    private String contentType;

    private long size;

    @OneToOne(fetch = FetchType.LAZY)
    private AttachmentContentEntity attachmentContentEntity;

}
