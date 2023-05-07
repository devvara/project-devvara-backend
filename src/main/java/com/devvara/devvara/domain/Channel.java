package com.devvara.devvara.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "channel_title")
    private String channelTitle;

    @Column(name = "language")
    private String language;

    @Column(name = "thumbnail")
    private String thumbnail;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
