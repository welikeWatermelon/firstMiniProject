package ssafy.project07.dto.youtube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeVideoDto {

    private Long id;
    private String videoId;
    private String title;
    private String thumbnailUrl;
    private String channelTitle;
    private Long viewCount;
    private String category;

    // 유튜브 영상 URL 반환
    public String getVideoUrl() {
        return "https://www.youtube.com/watch?v=" + videoId;
    }
}