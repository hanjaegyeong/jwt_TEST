package seq.jwt.dto.login;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {

    private String email;
    private String password;
}
