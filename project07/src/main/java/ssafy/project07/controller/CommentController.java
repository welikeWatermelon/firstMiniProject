package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.Comment;
import ssafy.project07.dto.comment.CommentRequest;
import ssafy.project07.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글 댓글 조회하기
    // 이건 게시글 조회할 때, 합쳐야함
    @GetMapping("/comments/{postId}")
    public List<Comment> commentsList(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    // 특정 댓글 등록하기
    @PostMapping("/comments")
    public void saveComment(@RequestBody CommentRequest commentRequest) {
        commentService.save(commentRequest);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }
}

