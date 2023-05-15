package com.devvara.devvara.api;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping("/api/v1/videos")
    public Page<Video> videosV2(@RequestParam(required = false) String[] language,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) Long channelId,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "12") int size) {
        return videoService.findVideos(language, title, channelId, page, size);
    }

}
