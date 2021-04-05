package com.elearning.dao;

import javax.transaction.Transactional;

import com.elearning.model.PrimeUser;


@Transactional
public interface PrimeUserRepository extends UserRepository<PrimeUser> {

}
