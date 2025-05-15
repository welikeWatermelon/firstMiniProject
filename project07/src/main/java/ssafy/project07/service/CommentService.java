package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Comment;
import ssafy.project07.domain.CommunityPost;
import ssafy.project07.domain.User;
import ssafy.project07.dto.comment.CommentRequest;
import ssafy.project07.repository.comment.CommentRepository;
import ssafy.project07.repository.community.CommunityRepository;
import ssafy.project07.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByCommunityPostId(postId);
    }

    public Long save(CommentRequest request) {

        // 1. 유저 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 2. 게시글 조회
        CommunityPost post = communityRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 3. 댓글 생성
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now()); // 또는 JPA Auditing으로 자동 처리
        comment.setUser(user);
        comment.setCommunityPost(post);

        // 4. 저장 및 ID 반환
        return commentRepository.save(comment).getId();
    }

    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        commentRepository.delete(comment);
    }
}
