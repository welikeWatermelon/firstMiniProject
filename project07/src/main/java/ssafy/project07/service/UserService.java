package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.User;
import ssafy.project07.dto.user.UserLoginRequest;
import ssafy.project07.dto.user.UserProfileRequest;
import ssafy.project07.dto.user.UserProfileResponse;
import ssafy.project07.dto.user.UserRegisterRequest;
import ssafy.project07.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
// 생성자 안만들어도 됨
public class UserService {
    private final UserRepository userRepository;

    public void userRegister(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setName(userRegisterRequest.getName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(userRegisterRequest.getPassword());
        user.setBirthDate(userRegisterRequest.getBirthDate());
        user.setGender(userRegisterRequest.getGender());
        user.setNickname(userRegisterRequest.getNickname());
        user.setProfileImage(userRegisterRequest.getProfileImage());

        userRepository.save(user);
    }

    public User userLogin(UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        // 1. 이메일로 유저 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 유저가 존재하지 않습니다."));


        // 2. 비밀번호 검증
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        // 3, 성공 시 User 반환
        return user;
    }

    public UserProfileResponse profileGet(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        UserProfileResponse response = new UserProfileResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setBirthDate(user.getBirthDate());
        response.setGender(user.getGender());
        response.setNickname(user.getNickname());
        response.setProfileImage(user.getProfileImage());

        return response;
    }

    public void profilePut(Long userId, UserProfileRequest userProfileRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("에러 !"));

        user.setName(userProfileRequest.getName());
        user.setBirthDate(userProfileRequest.getBirthDate());
        user.setGender(userProfileRequest.getGender());
        user.setNickname(userProfileRequest.getNickname());
        user.setProfileImage(userProfileRequest.getProfileImage());

        userRepository.save(user);
    }
}
