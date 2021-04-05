package com.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.Certificate;
import com.elearning.model.User;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer>{


}
