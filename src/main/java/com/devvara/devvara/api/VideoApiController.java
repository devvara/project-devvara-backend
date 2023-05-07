package com.devvara.devvara.api;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping("/api/v1/videos")
    public List<Video> videosV1(@RequestParam(required = false) String title,
                                @RequestParam(required = false) Long channelId,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "12") int size) {
        return videoService.findVideos(title, channelId, page, size);
    }
}
