package com.pvt.admin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@ToString
@Table(name = "camera", schema = "camera")
public class Camera {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_id")
    private Long cameraId;

    @NotBlank(message = "Camera name is required")
    @Size(max = 255, message = "Camera name must not exceed 255 characters")
    @Column(name = "camera_name", nullable = false)
    private String cameraName;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    // Constructors
    public Camera() {}

    public Camera(String cameraName, String locationName, LocalDateTime createdDate) {
        this.cameraName = cameraName;
        this.locationName = locationName;
        this.createdDate = createdDate;
    }
}