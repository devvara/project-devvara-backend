package com.devvara.devvara.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelItemDto {

    private Long id;
    private String channelId;
    private String channelTitle;
    private String language;
    private String thumbnail;

}
