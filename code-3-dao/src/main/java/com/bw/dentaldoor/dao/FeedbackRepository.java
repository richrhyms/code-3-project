package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
