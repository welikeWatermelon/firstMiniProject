package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.Comment;
import ssafy.project07.domain.User;
import ssafy.project07.dto.comment.CommentRequest;
import ssafy.project07.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/community/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // postId : 게시글 id
    // userId : 댓글 작성자 id

//    // 특정 게시글 댓글 조회하기
//    // 이건 게시글 조회할 때, 합쳐야함
//    @GetMapping("/{postId}/comments/{postId}")
//    public List<Comment> commentsList(@PathVariable Long postId) {
//        return commentService.getCommentsByPostId(postId);
//    }


//    // 특정 댓글 등록하기
//    @PostMapping("/{postId}/comments/{userId}")
//    public void saveComment(@PathVariable Long postId, @PathVariable Long userId, @RequestBody CommentRequest commentRequest) {
//
//        commentService.save(commentRequest);
//    }

    // ✅ 댓글 등록 (로그인 필요)
    @PostMapping("/{postId}/comments")
    public void saveComment(@PathVariable Long postId,
                            @AuthenticationPrincipal User user,
                            // 현재 로그인한 사용자 객체를 컨트롤러 메서드에 자동으로 주입
                            @RequestBody CommentRequest commentRequest) {
        commentService.save(user, postId, commentRequest);
    }

//    @DeleteMapping("/{postId}/comments/{userId}")
//    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long userId) {
//        commentService.delete(userId);
//        return ResponseEntity.ok().build();
//    }

    // ✅ 댓글 삭제 (로그인 필요)
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId,
                                              @PathVariable Long commentId,
                                              @AuthenticationPrincipal User user) {
        commentService.delete(user, postId, commentId);
        return ResponseEntity.ok().build();
    }
}

