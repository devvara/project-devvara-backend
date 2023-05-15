package com.devvara.devvara.repository;

import com.devvara.devvara.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideoRepository {

    private final EntityManager em;

//    public List<Video> findAll(int page, int size) {
//        TypedQuery<Video> q =  em.createQuery("select i from Video i order by i.publishTime desc", Video.class);
//        q.setFirstResult(page * size);
//        q.setMaxResults(size);
//
//        return q.getResultList();
//    }
//
//    public List<Video> findByVideoTitleContaing(String title, int page, int size) {
//        TypedQuery<Video> q =  em.createQuery("select i from Video i where i.videoTitle like :title order by i.publishTime desc", Video.class);
//        q.setParameter("title", "%" + title + "%");
//        q.setFirstResult(page * size);
//        q.setMaxResults(size);
//
//        return q.getResultList();
//    }
//
//    public List<Video> findByChannelId(Long channelId, int page, int size) {
//        TypedQuery<Video> q =  em.createQuery("select i from Video i where i.channel.id = :channelId order by i.publishTime desc", Video.class);
//        q.setParameter("channelId", channelId);
//        q.setFirstResult(page * size);
//        q.setMaxResults(size);
//
//        return q.getResultList();
//    }

    public Page<Video> findAll(String[] language, int page, int size) {
        TypedQuery<Video> dataQ = em.createQuery("select v from Video v join fetch v.channel c where c.language in (:language) order by v.publishTime desc", Video.class);
        TypedQuery<Long> countQ = em.createQuery("select count(v) from Video v join v.channel c where c.language in (:language)", Long.class);

        dataQ.setParameter("language", Arrays.asList(language));
        dataQ.setFirstResult(page * size);
        dataQ.setMaxResults(size);

        countQ.setParameter("language", Arrays.asList(language));

        List<Video> videos = dataQ.getResultList();
        Long total = countQ.getSingleResult();

        return new PageImpl<>(videos, PageRequest.of(page, size), total);
    }

    public Page<Video> findByVideoTitleContaing(String[] language, String title, int page, int size) {
        TypedQuery<Video> dataQ = em.createQuery("select v from Video v join fetch v.channel c where c.language = :language and v.videoTitle like :title order by v.publishTime desc", Video.class);
        TypedQuery<Long> countQ = em.createQuery("select count(v) from Video v join v.channel c where c.language = :language and v.videoTitle like :title", Long.class);

        dataQ.setParameter("language", Arrays.asList(language));
        dataQ.setParameter("title", "%" + title + "%");
        dataQ.setFirstResult(page * size);
        dataQ.setMaxResults(size);

        countQ.setParameter("language", Arrays.asList(language));
        countQ.setParameter("title", "%" + title + "%");

        List<Video> videos = dataQ.getResultList();
        Long total = countQ.getSingleResult();

        return new PageImpl<>(videos, PageRequest.of(page, size), total);
    }

    public Page<Video> findByChannelId(Long channelId, int page, int size) {
        TypedQuery<Video> dataQ =  em.createQuery("select v from Video v where v.channel.id = :channelId order by v.publishTime desc", Video.class);
        TypedQuery<Long> countQ = em.createQuery("select count(v) from Video v where v.channel.id = :channelId", Long.class);

        dataQ.setParameter("channelId", channelId);
        dataQ.setFirstResult(page * size);
        dataQ.setMaxResults(size);

        countQ.setParameter("channelId", channelId);

        List<Video> videos = dataQ.getResultList();
        Long total = countQ.getSingleResult();

        return new PageImpl<>(videos, PageRequest.of(page, size), total);
    }
}
