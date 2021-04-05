package com.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer>{

}
