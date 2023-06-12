package com.devvara.devvara.api;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.domain.VideoSearch;
import com.devvara.devvara.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping("/api/v1/videos")
    public Result videosV1(@RequestParam(required = false) String[] language,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) Long channelId,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "12") int size) {

        Page<Video> pageData = videoService.findVideos(language, title, channelId, page, size);
        List<Video> content = pageData.getContent();
        int pageNumber = pageData.getNumber();
        int totalPages = pageData.getTotalPages();

        return new Result(content, pageNumber, totalPages);
    }

    @GetMapping("api/v2/videos")
    public String videosV2(@ModelAttribute("videoSearch") VideoSearch videoSearch, Model model){

        return "";
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T items;
        private int current_page;
        private int total_count;
    }
}
