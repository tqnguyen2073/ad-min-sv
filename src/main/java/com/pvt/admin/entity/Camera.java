package com.pvt.admin.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema (description = "Unique identifier for the camera", example = "1")
    private Long cameraId;

    @NotBlank(message = "Camera name is required")
    @Size(max = 255, message = "Camera name must not exceed 255 characters")
    @Column(name = "camera_name", nullable = false)
    @Schema(description = "Name of the camera", example = "Camera 1")
    private String cameraName;

    @Column(name = "location_name")
    @NotBlank(message = "Location name is required")
    @Size(max = 255, message = "Location name must not exceed 255 characters")
    @Schema(description = "Name of the location where the camera is installed", example = "Main Entrance")
    private String locationName;

    @Column(name = "created_date")
    @CreationTimestamp
    @Schema(description = "Date and time when the camera was created", example = "2023-10-01T12:00:00")
    private LocalDateTime createdDate;

    // Constructors
    public Camera() {}

    public Camera(String cameraName, String locationName, LocalDateTime createdDate) {
        this.cameraName = cameraName;
        this.locationName = locationName;
        this.createdDate = createdDate;
    }
}