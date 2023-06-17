package com.devvara.devvara.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VideoSearchDto {

    public String[] language;
    public String title;
    public Long channelId;
    public int page;
    public int size;

}
