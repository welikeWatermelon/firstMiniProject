package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.dto.follow.FollowRequest;
import ssafy.project07.dto.follow.FollowResponse;
import ssafy.project07.service.FollowService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<Void> follow(@RequestBody FollowRequest request) {
        followService.follow(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<FollowResponse>> getMyFollows(@RequestParam Long userId) {
        return ResponseEntity.ok(followService.getMyFollows(userId));
    }
}
