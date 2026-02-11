package com.dtt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtt.model.AppconfigModel;

@Repository
public interface AppConfigRepo extends JpaRepository<AppconfigModel, Integer>{

	@Query("SELECT a FROM AppconfigModel a WHERE a.osVersion = :osVersion")
	AppconfigModel getAppConfig(@Param("osVersion") String osVersion);

}