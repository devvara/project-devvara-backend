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
            "and v.status = 1 " +
            "order by v.publishTime desc"
    )
    List<Video> findVideos(@Param("language") String[] language, Pageable pageable);

    @Query("select count(v) from Video v " +
            "where v.channel.language in (:language) " +
            "and v.status = 1"
    )
    Long countVideos(@Param("language") String[] language);
}
