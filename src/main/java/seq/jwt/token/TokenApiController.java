package seq.jwt.token;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<CreateAccessTokenRes> createNewAccessToken(@RequestBody CreateAccessTokenReq request)  {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return  ResponseEntity.ok().body(new CreateAccessTokenRes(newAccessToken));
    }
}