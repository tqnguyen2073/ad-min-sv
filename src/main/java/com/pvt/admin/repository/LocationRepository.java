package com.pvt.admin.repository;

import com.pvt.admin.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // You can add custom queries here if needed
}

