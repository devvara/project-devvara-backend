package com.devvara.devvara.repository;

import com.devvara.devvara.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoQueryRepository extends JpaRepository<Video, Long> {

}
