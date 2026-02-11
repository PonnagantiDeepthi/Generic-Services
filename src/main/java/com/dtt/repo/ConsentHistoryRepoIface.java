package com.dtt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtt.model.ConsentHistory;



@Repository
public interface ConsentHistoryRepoIface extends JpaRepository<ConsentHistory, Integer> {

}
