package seq.jwt.service;

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
