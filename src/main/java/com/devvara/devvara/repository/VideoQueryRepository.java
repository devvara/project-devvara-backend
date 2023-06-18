package com.devvara.devvara.repository;

import com.devvara.devvara.domain.Video;
import com.devvara.devvara.dto.VideoItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoQueryRepository extends JpaRepository<Video, Long> {

    @Query("select v from Video v " +
            "where v.channel.language in (:language) " +
            "and v.videoTitle like :title " +
            "and v.status = 1 " +
            "order by v.publishTime desc"
    )
    List<VideoItemDto> findVideos(@Param("language") String[] language, @Param("title") String title, Pageable pageable);

    @Query("select v from Video v " +
            "where v.channel.language in (:language) " +
            "and v.videoTitle like :title " +
            "and v.status = 1"
    )
    Long countVideo(@Param("language") String[] language, @Param("title") String title);

}
