package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.dto.community.CommunityRequest;
import ssafy.project07.dto.community.CommunityResponse;
import ssafy.project07.service.CommunityService;

import java.util.List;

@RestController
@RequestMapping("/api/community/posts")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<CommunityResponse>> getAllPosts(@RequestParam(required = false) String sort) {
        return ResponseEntity.ok(communityService.getAllPosts(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody CommunityRequest request) {
        return ResponseEntity.ok(communityService.createPost(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody CommunityRequest request) {
        communityService.updatePost(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        communityService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
