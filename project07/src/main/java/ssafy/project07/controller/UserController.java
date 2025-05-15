package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.User;
import ssafy.project07.dto.user.UserLoginRequest;
import ssafy.project07.dto.user.UserProfileRequest;
import ssafy.project07.dto.user.UserProfileResponse;
import ssafy.project07.dto.user.UserRegisterRequest;
import ssafy.project07.repository.user.UserRepository;
import ssafy.project07.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void login(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.userRegister(userRegisterRequest);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginRequest userLoginRequest) {
        userService.userLogin(userLoginRequest);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> profile(@RequestParam Long userId) {
        return  ResponseEntity.ok(userService.profileGet(userId));

    }

    @PutMapping("/profile")
    public void profilePut(@RequestParam Long userId, @RequestBody UserProfileRequest userProfileRequest) {
        userService.profilePut(userId, userProfileRequest);
    }
}
