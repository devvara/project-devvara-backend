package com.devvara.devvara.dto;

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

    private List<ChannelItemDto> channelItemDtoList = new ArrayList<>();

    public void addChannelItemDto(ChannelItemDto channelItemDto){
        channelItemDtoList.add(channelItemDto);
    }

}
