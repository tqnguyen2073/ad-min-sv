package com.pvt.admin.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "location", schema = "camera")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @NotBlank(message = "Location name is required")
    @Size(max = 255, message = "Location name must not exceed 255 characters")
    @Column(name = "location_name", nullable = false)
    @Schema(description = "Name of the location", example = "Main Entrance")
    private String locationName;

    @NotBlank(message = "IP address is required")
    @Size(max = 50, message = "IP address must not exceed 50 characters")
    @Column(name = "ipaddress", nullable = false)
    @Schema(description = "IP address of the location", example = "192.168.1.13")
    private String ipaddress;

    public Location() {}

    public Location(String locationName, String ipaddress) {
        this.locationName = locationName;
        this.ipaddress = ipaddress;
    }
}

