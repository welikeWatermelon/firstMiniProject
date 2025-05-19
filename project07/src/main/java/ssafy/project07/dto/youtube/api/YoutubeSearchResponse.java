package ssafy.project07.dto.youtube.api;

import lombok.Data;

@Data
public class YoutubeSearchResponse {
    private SearchItem[] items;

    @Data
    public static class SearchItem {
        private Id id;
        private Snippet snippet;

        @Data
        public static class Id {
            private String kind;
            private String videoId;
        }

        @Data
        public static class Snippet {
            private String title;
            private String channelTitle;
            private Thumbnails thumbnails;

            @Data
            public static class Thumbnails {
                private Thumbnail medium;

                @Data
                public static class Thumbnail {
                    private String url;
                }
            }
        }
    }
}