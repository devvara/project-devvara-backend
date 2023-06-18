package com.devvara.devvara.dto;

import com.devvara.devvara.domain.Channel;
import com.devvara.devvara.domain.Video;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VideoItemDto {

    private Long id;
    private String videoYoutubeId;
    private String videoTitle;
    private String videoDescription;
    private String videoThumbnail;
    private String publishTime;
    private Channel channel;

    public VideoItemDto(Video video) {
        id = video.getId();
        videoYoutubeId = video.getVideoYoutubeId();
        videoTitle = video.getVideoTitle();
        videoDescription = video.getVideoDescription();
        videoThumbnail = video.getVideoThumbnail();
        publishTime = video.getPublishTime();
        channel = video.getChannel();
    }

}
