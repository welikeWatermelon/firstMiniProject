package ssafy.project07.dto.supplement;

import lombok.Getter;

import java.util.List;

@Getter
public class SupplementRegisterRequest {
    private String name;
    private int doseMg;
    private int dailyLimit;
    private List<String> ingredients;
}
