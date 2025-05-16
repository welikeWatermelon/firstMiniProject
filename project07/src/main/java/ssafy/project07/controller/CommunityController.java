package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.User;
import ssafy.project07.dto.community.CommunityRequest;
import ssafy.project07.dto.community.CommunityResponse;
import ssafy.project07.service.CommunityService;

import java.util.List;

@RestController
@RequestMapping("/api/community/posts")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    // 게시글 조회
    @GetMapping
    public ResponseEntity<List<CommunityResponse>> getAllPosts(@RequestParam(required = false) String sort) {
        return ResponseEntity.ok(communityService.getAllPosts(sort));
    }

    // 특정 회원의 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getPostById(id));
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody CommunityRequest request,
                                           @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(communityService.createPost(request, user));
    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id,
                                           @RequestBody CommunityRequest request,
                                           @AuthenticationPrincipal User user) {
        communityService.updatePost(id, request, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @AuthenticationPrincipal User user) {
        // 지금 로그인한게 누군지 아는 것 : @AuthenticationPrincipal
        communityService.deletePost(id,user);
        return ResponseEntity.ok().build();
    }
}
