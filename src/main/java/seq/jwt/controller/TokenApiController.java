package seq.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seq.jwt.dto.token.CreateAccessTokenReq;
import seq.jwt.dto.token.CreateAccessTokenRes;
import seq.jwt.service.token.TokenService;

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