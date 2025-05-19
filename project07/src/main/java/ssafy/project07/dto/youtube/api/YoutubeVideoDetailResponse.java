package ssafy.project07.dto.youtube.api;

import lombok.Data;

@Data
public class YoutubeVideoDetailResponse {
    private VideoItem[] items;

    @Data
    public static class VideoItem {
        private String id;
        private Snippet snippet;
        private Statistics statistics;

        @Data
        public static class Snippet {
            private String title;
            private String channelTitle;
            private Thumbnails thumbnails;

            @Data
            public static class Thumbnails {
                private Thumbnail medium;
                private Thumbnail default_;

                @Data
                public static class Thumbnail {
                    private String url;
                }
            }
        }

        @Data
        public static class Statistics {
            private Long viewCount;
        }
    }
}