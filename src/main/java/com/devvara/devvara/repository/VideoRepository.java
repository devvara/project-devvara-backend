package com.devvara.devvara.repository;

import com.devvara.devvara.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@RequiredArgsConstructor
public class VideoRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager em;

    /**
     * Find all videos
     *
     * @param language
     * @param page
     * @param size
     * @return Page object
     */
    public Page<Video> findAll(String[] language, int page, int size) {
        TypedQuery<Video> dataQ = em.createQuery("select v from Video v join fetch v.channel c where c.language in (:language) and v.status = 1 order by v.publishTime desc", Video.class);
        TypedQuery<Long> countQ = em.createQuery("select count(v) from Video v join v.channel c where c.language in (:language) and v.status = 1", Long.class);
        int startPosition = (page > 1) ? (page - 1) * size : 0;

        dataQ.setParameter("language", Arrays.asList(language));
        dataQ.setFirstResult(startPosition);
        dataQ.setMaxResults(size);

        countQ.setParameter("language", Arrays.asList(language));

        List<Video> videos = dataQ.getResultList();
        Long total = countQ.getSingleResult();

        return new PageImpl<Video>(videos, PageRequest.of(page, size), total);
    }

    /**
     * Find all videos by title
     *
     * @param language
     * @param title
     * @param page
     * @param size
     * @return Page object
     */
    public Page<Video> findByVideoTitleContaing(String[] language, String title, int page, int size) {
        TypedQuery<Video> dataQ = em.createQuery("select v from Video v join fetch v.channel c where c.language in (:language) and v.videoTitle like :title and v.status = 1 order by v.publishTime desc", Video.class);
        TypedQuery<Long> countQ = em.createQuery("select count(v) from Video v join v.channel c where c.language in (:language) and v.videoTitle like :title and v.status = 1 ", Long.class);
        int startPosition = (page > 1) ? (page - 1) * size : 0;

        dataQ.setParameter("language", Arrays.asList(language));
        dataQ.setParameter("title", "%" + title + "%");
        dataQ.setFirstResult(startPosition);
        dataQ.setMaxResults(size);

        countQ.setParameter("language", Arrays.asList(language));
        countQ.setParameter("title", "%" + title + "%");

        List<Video> videos = dataQ.getResultList();
        Long total = countQ.getSingleResult();

        return new PageImpl<Video>(videos, PageRequest.of(page, size), total);
    }

    /**
     * Find all videos by channel id
     *
     * @param channelId
     * @param page
     * @param size
     * @return Page object
     */
    public Page<Video> findByChannelId(Long channelId, int page, int size) {
        TypedQuery<Video> dataQ =  em.createQuery("select v from Video v where v.channel.id = :channelId and v.status= 1 order by v.publishTime desc", Video.class);
        TypedQuery<Long> countQ = em.createQuery("select count(v) from Video v where v.channel.id = :channelId and v.status= 1", Long.class);
        int startPosition = (page > 1) ? (page - 1) * size : 0;

        dataQ.setParameter("channelId", channelId);
        dataQ.setFirstResult(startPosition);
        dataQ.setMaxResults(size);

        countQ.setParameter("channelId", channelId);

        List<Video> videos = dataQ.getResultList();
        Long total = countQ.getSingleResult();

        return new PageImpl<Video>(videos, PageRequest.of(page, size), total);
    }
}
