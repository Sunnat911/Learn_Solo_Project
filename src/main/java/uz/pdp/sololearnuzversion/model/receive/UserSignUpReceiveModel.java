package uz.pdp.sololearnuzversion.model.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.sololearnuzversion.entity.role.RoleEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignUpReceiveModel {

    @JsonProperty("full_name")
    @NotBlank(message = "ism familya bo'sh bo'lmasligi kerak")
    private String fullName;

    @JsonProperty("username")
    @NotEmpty(message = "username bo'sh bo'lmasligi kerak")
    private String username;

    @NotEmpty(message = "password bo'sh bo'lmasligi kerak")
    private String password;

    // TODO men bunga taskda qiynaldim, palonchi aka qiling
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("user_role")
    private RoleEnum roleEnum;

}
