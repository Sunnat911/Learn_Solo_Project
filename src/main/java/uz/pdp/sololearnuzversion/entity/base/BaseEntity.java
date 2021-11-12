package uz.pdp.sololearnuzversion.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @JsonIgnore
    @CreatedBy
    private String username;


    @JsonProperty("created_date")
    public String createdDate() {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        if (this.createdDate != null)
            return simpleDateFormat.format(this.createdDate);

        return null;

    }


}
