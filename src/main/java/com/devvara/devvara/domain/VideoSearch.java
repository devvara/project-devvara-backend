package com.devvara.devvara.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VideoSearch {

    public String[] language;
    public String title;
    public Long channelId;
    public int page;
    public int size;

}
