package com.devvara.devvara.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Column(name = "video_youtube_id")
    private String videoYoutubeId;

    @Column(name = "video_title")
    private String videoTitle;

    @Column(name = "video_description")
    private String videoDescription;

    @Column(name = "video_thumbnail")
    private String videoThumbnail;

    @Column(name = "publish_time")
    private String publishTime;

    private Short status;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
