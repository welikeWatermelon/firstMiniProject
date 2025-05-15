package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Follow;
import ssafy.project07.domain.Pharmacist;
import ssafy.project07.domain.User;
import ssafy.project07.dto.follow.FollowRequest;
import ssafy.project07.dto.follow.FollowResponse;
import ssafy.project07.repository.follow.FollowRepository;
import ssafy.project07.repository.pharmacist.PharmacistRepository;
import ssafy.project07.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final PharmacistRepository pharmacistRepository;

    public void follow(FollowRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Pharmacist pharmacist = pharmacistRepository.findById(request.getPharmacistId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 약사입니다."));

        Follow follow = new Follow();
        follow.setUser(user);
        follow.setPharmacist(pharmacist);

        followRepository.save(follow);
    }

    public List<FollowResponse> getMyFollows(Long userId) {
        return followRepository.findByUserId(userId).stream()
                .map(follow -> {
                    Pharmacist p = follow.getPharmacist();
                    FollowResponse res = new FollowResponse();
                    res.setPharmacistId(p.getId());
                    res.setName(p.getName());
                    res.setTitle(p.getTitle());
                    res.setProfileImage(p.getProfileImage());
                    return res;
                }).collect(Collectors.toList());
    }
}
