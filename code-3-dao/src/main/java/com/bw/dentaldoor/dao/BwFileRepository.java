package com.bw.dentaldoor.dao;

import com.bw.entity.BwFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BwFileRepository extends JpaRepository<BwFile, Long>, QuerydslPredicateExecutor<BwFile> {
}
