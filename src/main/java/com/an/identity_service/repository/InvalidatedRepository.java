package com.an.identity_service.repository;


import com.an.identity_service.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedRepository extends JpaRepository<InvalidatedToken, String> {

}
