package seq.jwt.service;

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
