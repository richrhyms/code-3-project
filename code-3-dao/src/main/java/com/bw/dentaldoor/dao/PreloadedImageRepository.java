package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.PreloadedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface PreloadedImageRepository extends JpaRepository<PreloadedImage,Long> {
}