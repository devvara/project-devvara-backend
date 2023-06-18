package com.devvara.devvara.service;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.dto.VideoItemDto;
import com.devvara.devvara.dto.VideoSearchDto;
import com.devvara.devvara.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    @Transactional(readOnly = true)
    public Page<Video> findVideos(String[] language, String title, Long channelId, int page, int size) {
        if (StringUtils.hasText(title)) {
            return videoRepository.findByVideoTitleContaing(language, title, page, size);
        }

        if (channelId != null) {
            return videoRepository.findByChannelId(channelId, page, size);
        }

        return videoRepository.findAll(language, page, size);
    }

    @Transactional(readOnly = true)
    public List<VideoItemDto> getVideoList(VideoSearchDto videoSearchDto){

        System.out.println(videoSearchDto.getTitle());

        return null;
    }

}
