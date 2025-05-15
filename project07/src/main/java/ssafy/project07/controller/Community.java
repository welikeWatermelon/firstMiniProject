package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class Community {

    private final CommunityService communityService;

    @GetMapping("/posts")
    public List<Community> viewPosts() {

    }

    @GetMapping("/posts/{id}")
    public Community detailPost(@PathVariable Long id) {

    }

    @PostMapping("/posts")
    public void createPost(@RequestBody) {

    }


    @PutMapping("/posts/{id}")
    public void updatePost(@RequestBody) {

    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@RequestBody) {

    }
}
