package ssafy.project07.controller;

import ssafy.project07.dto.youtube.YoutubeVideoDto;
import ssafy.project07.service.YoutubeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/youtube")
public class YoutubeController {

    @Autowired
    private YoutubeService youtubeService;

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshYoutubeVideos() {
        youtubeService.fetchAndSaveNutritionVideos();
        return ResponseEntity.ok("영양제 관련 유튜브 영상을 성공적으로 업데이트했습니다.");
    }

    @GetMapping("/nutrition")
    public ResponseEntity<List<YoutubeVideoDto>> getNutritionVideos(
            @RequestParam(defaultValue = "10") int limit) {
        List<YoutubeVideoDto> videos = youtubeService.getNutritionVideos(limit);
        return ResponseEntity.ok(videos);
    }
}