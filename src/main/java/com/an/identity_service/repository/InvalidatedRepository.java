package com.an.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.an.identity_service.entity.InvalidatedToken;

@Repository
public interface InvalidatedRepository extends JpaRepository<InvalidatedToken, String> {}
