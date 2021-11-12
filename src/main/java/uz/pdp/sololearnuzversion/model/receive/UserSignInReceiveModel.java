package uz.pdp.sololearnuzversion.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignInReceiveModel {

    @NotBlank(message = "username bosh bolishi kk emas")
    private String username;

    @NotBlank(message = "password bosh bolishi kk emas")
    private String password;
}
