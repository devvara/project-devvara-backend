package com.devvara.devvara.api;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.dto.VideoItemDto;
import com.devvara.devvara.dto.VideoSearchDto;
import com.devvara.devvara.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/api/v2/videos")
    public Result videosV2(VideoSearchDto videoSearchDto) {

        Pageable pageable = PageRequest.of(Optional.of(videoSearchDto.getPage()).isPresent() ? videoSearchDto.getPage() : 0, 12);

        List<Video> videos = videoService.getVideoList(videoSearchDto, pageable);
        Long totalCount = videoService.getVideoListTotalCount(videoSearchDto);
        int totalCountNum = totalCount.intValue();

        List<VideoItemDto> videoItemDtoList = videos.stream()
                .map(o -> new VideoItemDto(o))
                .collect(Collectors.toList());

        return new Result(videoItemDtoList, pageable.getPageNumber(), totalCountNum);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T items;
        private int current_page;
        private int total_count;
    }
}
