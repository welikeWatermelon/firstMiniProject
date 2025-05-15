package ssafy.project07.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String gender;
    private String nickname;
    private String profileImage;

}
