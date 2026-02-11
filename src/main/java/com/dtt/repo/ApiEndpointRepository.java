//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dtt.repo;

import com.dtt.model.ApiEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiEndpointRepository extends JpaRepository<ApiEndpoint, Long> {
   // Optional<ApiEndpoint> findByKeyName(String keyName);
}
