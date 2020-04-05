package com.bw.dentaldoor.dao;

import com.bw.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Repository
public
interface SettingRepository extends JpaRepository<Setting, Long> {

    Setting findByName(String name);

    List<Setting> findByNameIn(List<String> string);
}
