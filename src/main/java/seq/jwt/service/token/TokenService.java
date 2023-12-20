package seq.jwt.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import seq.jwt.config.TokenProvider;
import seq.jwt.domain.User;
import seq.jwt.service.user.UserService;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @Value("${jwt.expiration}")
    private long ACCESS_TOKEN_EXPIRATION;
    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("ex");
        }
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findUserById(userId);
        return tokenProvider.generateToken(user, Duration.ofHours(ACCESS_TOKEN_EXPIRATION));
    }
}