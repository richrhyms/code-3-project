package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.ImageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface ImageTagRepository extends JpaRepository<ImageTag, Long> {

    @Query("select it from ImageTag it where it.status='ACTIVE' and lower(it.name) in ?1")
    List<ImageTag> findActiveByNames(List<String> names);
}