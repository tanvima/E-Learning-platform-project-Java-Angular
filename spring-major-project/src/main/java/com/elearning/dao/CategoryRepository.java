package com.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
}
