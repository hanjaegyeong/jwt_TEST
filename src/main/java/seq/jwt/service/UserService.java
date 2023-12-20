package seq.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import seq.jwt.configuration.TokenProvider;
import seq.jwt.domain.User;
import seq.jwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import seq.jwt.token.RefreshTokenService;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;
   private final TokenProvider tokenProvider;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;
   private final RefreshTokenService refreshTokenService;

    @Value("${jwt.expiration}")
    private long ACCESS_TOKEN_EXPIRATION;
    @Value("${jwt.refresh-token.expiration}")
    private long REFRESH_TOKEN_EXPIRATION;

    public User createUser(String email, String password )  {
        User user = User.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .build();
        userRepository.save(user);
        return user;
    }
   public User findUserById(Long userId) {
       return userRepository.findById(userId)
               .orElseThrow(() -> new IllegalArgumentException("예외"));
   }

    public LoginRes login(LoginReq loginReq)  {
        User user = userRepository.findByEmail(loginReq.getEmail())
                .filter(it -> bCryptPasswordEncoder.matches(loginReq.getPassword(), it.getPassword()))	// 암호화된 비밀번호와 비교하도록 수정
                .orElseThrow(() -> new IllegalArgumentException("예외 1"));
        String accessToken = tokenProvider.generateToken(user, Duration.ofHours(ACCESS_TOKEN_EXPIRATION));
        String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(REFRESH_TOKEN_EXPIRATION));
        refreshTokenService.saveRefreshToken(user.getId(), refreshToken);
        return new LoginRes(user.getId(), user.getEmail(), accessToken, refreshToken);
    }

    public void logout(Long userId) {
        refreshTokenService.deleteRefreshTokenByUserId(userId);
    }
}
