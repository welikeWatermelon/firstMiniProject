//package ssafy.project07.dto.youtube;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class YoutubeSearchResponseDto {
//    private String kind;
//    private String etag;
//    private String nextPageToken;
//    private PageInfoDto pageInfo;
//    private ItemDto[] items;
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static class PageInfoDto {
//        private int totalResults;
//        private int resultsPerPage;
//    }
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static class ItemDto {
//        private String kind;
//        private String etag;
//        private String id;
//        private SnippetDto snippet;
//        private StatisticsDto statistics;
//
//        @Data
//        @NoArgsConstructor
//        @AllArgsConstructor
//        static class SnippetDto {
//            private String publishedAt;
//            private String channelId;
//            private String title;
//            private String description;
//            private ThumbnailsDto thumbnails;
//            private String channelTitle;
//
//            @Data
//            @NoArgsConstructor
//            @AllArgsConstructor
//            static class ThumbnailsDto {
//                private ThumbnailDto default_;
//                private ThumbnailDto medium;
//                private ThumbnailDto high;
//
//                @Data
//                @NoArgsConstructor
//                @AllArgsConstructor
//                static class ThumbnailDto {
//                    private String url;
//                    private int width;
//                    private int height;
//                }
//            }
//        }
//
//        @Data
//        @NoArgsConstructor
//        @AllArgsConstructor
//        static class StatisticsDto {
//            private Long viewCount;
//            private Long likeCount;
//            private Long favoriteCount;
//            private Long commentCount;
//        }
//    }
//}