package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.ImageTag;
import com.bw.dentaldoor.entity.PreloadedImage;
import com.bw.dentaldoor.entity.PreloadedImageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface PreloadedImageTagRepository extends JpaRepository<PreloadedImageTag, Long> {
    @Query("select pit from PreloadedImageTag pit join fetch pit.imageTag " +
            "where pit.preloadedImage = ?1 and pit.imageTag in ?2 and pit.status='ACTIVE'")
    List<PreloadedImageTag> findActiveByPreloadedImageAndTags(PreloadedImage preloadedImage, List<ImageTag> tags);

    Long countAllByPreloadedImage(PreloadedImage preloadedImage);
}