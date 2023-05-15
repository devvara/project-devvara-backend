package com.devvara.devvara.api;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.service.VideoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping("/api/v1/videos")
    public List<VideoListDto> videosV2(@RequestParam(required = false) String[] language,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) Long channelId,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "12") int size) {

        Page<Video> pageData = videoService.findVideos(language, title, channelId, page, size);
        List<Video> content = pageData.getContent();
        int pageNumber = pageData.getNumber();
        int totalPages = pageData.getTotalPages();
        List<VideoListDto> result = new ArrayList<>();
        result.add(new VideoListDto(content, pageNumber, totalPages));

        return result;
    }

    @Data
    static class VideoListDto {
        private List<Video> content;
        private int pageNumber;
        private int totalPage;

        public VideoListDto(List<Video> content, int pageNumber, int totalPages) {
            this.content = content;
            this.pageNumber = pageNumber;
            this.totalPage = totalPages;
        }
    }

}
