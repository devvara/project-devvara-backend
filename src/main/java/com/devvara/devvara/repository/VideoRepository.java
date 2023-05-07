package com.devvara.devvara.repository;

import com.devvara.devvara.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideoRepository {

    private final EntityManager em;

    public List<Video> findAll(int page, int size) {
        TypedQuery<Video> q =  em.createQuery("select i from Video i order by i.publishTime desc", Video.class);
        q.setFirstResult(page * size);
        q.setMaxResults(size);

        return q.getResultList();
    }

    public List<Video> findByVideoTitleContaing(String title, int page, int size) {
        TypedQuery<Video> q =  em.createQuery("select i from Video i where i.videoTitle like :title order by i.publishTime desc", Video.class);
        q.setParameter("title", "%" + title + "%");
        q.setFirstResult(page * size);
        q.setMaxResults(size);

        return q.getResultList();
    }

    public List<Video> findByChannelId(Long channelId, int page, int size) {
        TypedQuery<Video> q =  em.createQuery("select i from Video i where i.channel.id = :channelId order by i.publishTime desc", Video.class);
        q.setParameter("channelId", channelId);
        q.setFirstResult(page * size);
        q.setMaxResults(size);

        return q.getResultList();
    }
}
