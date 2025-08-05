package com.pvt.admin.repository;

import com.pvt.admin.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

    // Tìm camera theo tên
    Optional<Camera> findByCameraName(String cameraName);

    // Tìm camera có tên chứa keyword (không phân biệt hoa thường)
    List<Camera> findByCameraNameContainingIgnoreCase(String keyword);

    // Custom query với JPQL
    @Query("SELECT c FROM Camera c WHERE c.cameraName LIKE %:name%")
    List<Camera> findByNameContaining(@Param("name") String name);

    // Native query
    @Query(value = "SELECT * FROM camera.camera WHERE camera_name = :name", nativeQuery = true)
    Optional<Camera> findByNameNative(@Param("name") String name);
}