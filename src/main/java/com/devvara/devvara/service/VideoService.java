package com.devvara.devvara.service;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<Video> findVideos(String title, Long channelId, int page, int size) {
        System.out.println(channelId);

        if (StringUtils.hasText(title)) {
            return videoRepository.findByVideoTitleContaing(title, page, size);
        }

        if (channelId != null) {
            return videoRepository.findByChannelId(channelId, page, size);
        }

        return videoRepository.findAll(page, size);
    }

}
