package seq.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import seq.jwt.domain.User;
import seq.jwt.dto.login.LoginReq;
import seq.jwt.dto.login.LoginRes;
import seq.jwt.service.user.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        return ResponseEntity.ok().body(userService.createUser(email,password));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {
            return ResponseEntity.ok().body(userService.login(loginReq));

    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user) {
        // 로그아웃 로직을 수행합니다.
        userService.logout(user.getId());
        return ResponseEntity.ok().body("로그아웃 성공");
    }

}
