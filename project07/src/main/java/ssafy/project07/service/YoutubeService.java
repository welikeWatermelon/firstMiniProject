package ssafy.project07.service;

import ssafy.project07.domain.YoutubeVideo;
import ssafy.project07.dto.youtube.YoutubeVideoDto;
import ssafy.project07.dto.youtube.api.YoutubeSearchResponse;
import ssafy.project07.dto.youtube.api.YoutubeVideoDetailResponse;
import ssafy.project07.repository.youtube.YoutubeVideoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YoutubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    @Autowired
    private YoutubeVideoRepository youtubeVideoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void fetchAndSaveNutritionVideos() {
        String query = "영양제";
        String searchUrl = String.format(
                "https://www.googleapis.com/youtube/v3/search?part=snippet&q=%s&type=video&maxResults=15&order=viewCount&regionCode=KR&relevanceLanguage=ko&key=%s",
                query, apiKey
        );

        try {
            ResponseEntity<YoutubeSearchResponse> searchResponse = restTemplate.getForEntity(searchUrl, YoutubeSearchResponse.class);

            if (searchResponse.getBody() == null || searchResponse.getBody().getItems() == null) return;

            youtubeVideoRepository.deleteByCategoryEquals("영양제");

            List<String> videoIds = new ArrayList<>();
            for (YoutubeSearchResponse.SearchItem item : searchResponse.getBody().getItems()) {
                if (item.getId() != null && "youtube#video".equals(item.getId().getKind()) && item.getId().getVideoId() != null) {
                    videoIds.add(item.getId().getVideoId());
                    if (videoIds.size() >= 10) break;
                }
            }

            if (videoIds.isEmpty()) return;

            String videoUrl = String.format(
                    "https://www.googleapis.com/youtube/v3/videos?part=snippet,statistics&id=%s&key=%s",
                    String.join(",", videoIds), apiKey
            );

            ResponseEntity<YoutubeVideoDetailResponse> videoResponse = restTemplate.getForEntity(videoUrl, YoutubeVideoDetailResponse.class);

            if (videoResponse.getBody() == null || videoResponse.getBody().getItems() == null) return;

            for (YoutubeVideoDetailResponse.VideoItem item : videoResponse.getBody().getItems()) {
                if (item.getId() == null || item.getSnippet() == null) continue;

                String thumbnailUrl = "";
                if (item.getSnippet().getThumbnails() != null) {
                    if (item.getSnippet().getThumbnails().getMedium() != null) {
                        thumbnailUrl = item.getSnippet().getThumbnails().getMedium().getUrl();
                    } else if (item.getSnippet().getThumbnails().getDefault_() != null) {
                        thumbnailUrl = item.getSnippet().getThumbnails().getDefault_().getUrl();
                    }
                }

                YoutubeVideo video = YoutubeVideo.builder()
                        .videoId(item.getId())
                        .title(item.getSnippet().getTitle())
                        .thumbnailUrl(thumbnailUrl)
                        .channelTitle(item.getSnippet().getChannelTitle())
                        .viewCount(item.getStatistics() != null ? item.getStatistics().getViewCount() : 0L)
                        .category("영양제")
                        .build();

                youtubeVideoRepository.save(video);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<YoutubeVideoDto> getNutritionVideos(int limit) {
        List<YoutubeVideo> videos = youtubeVideoRepository.findByCategoryOrderByViewCountDesc("영양제");

        if (videos.size() > limit) {
            videos = videos.subList(0, limit);
        }

        return videos.stream()
                .map(video -> YoutubeVideoDto.builder()
                        .id(video.getId())
                        .videoId(video.getVideoId())
                        .title(video.getTitle())
                        .thumbnailUrl(video.getThumbnailUrl())
                        .channelTitle(video.getChannelTitle())
                        .viewCount(video.getViewCount())
                        .category(video.getCategory())
                        .build())
                .collect(Collectors.toList());
    }
}