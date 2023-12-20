package seq.jwt.dto.login;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {

    Long id;
    String email;
    String accessToken;
    String refreshToken;
}
