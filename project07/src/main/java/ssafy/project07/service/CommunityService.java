package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.CommunityPost;
import ssafy.project07.dto.community.CommunityRequest;
import ssafy.project07.dto.community.CommunityResponse;
import ssafy.project07.repository.community.CommunityRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public List<CommunityResponse> getAllPosts(String sort) {
        List<CommunityPost> posts = communityRepository.findAll(); // sort 조건은 추후 정렬 로직 추가 가능

        return posts.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public CommunityResponse getPostById(Long id) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return convertToResponse(post);
    }

    public Long createPost(CommunityRequest request) {
        CommunityPost post = new CommunityPost();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        return communityRepository.save(post).getId();
    }

    public void updatePost(Long id, CommunityRequest request) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(LocalDateTime.now());
        communityRepository.save(post);
    }

    public void deletePost(Long id) {
        communityRepository.deleteById(id);
    }

    private CommunityResponse convertToResponse(CommunityPost post) {
        CommunityResponse res = new CommunityResponse();
        res.setId(post.getId());
        res.setTitle(post.getTitle());
        res.setContent(post.getContent());
        res.setCreatedAt(post.getCreatedAt());
        res.setUpdatedAt(post.getUpdatedAt());
        // res.setAuthorName(post.getUser().getName()); // 필요시
        return res;
    }
}
