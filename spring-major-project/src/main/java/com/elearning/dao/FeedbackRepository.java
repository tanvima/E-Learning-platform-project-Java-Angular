package com.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
