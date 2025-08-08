package com.pvt.admin.controller;

import com.pvt.admin.entity.Camera;
import com.pvt.admin.service.CameraService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cameras")
@RequiredArgsConstructor
public class CameraController {

    @Autowired
    private CameraService cameraService;

    // GET /api/cameras
    @Operation(
            summary = "Get all cameras",
            description = "Retrieve a list of all cameras in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cameras retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        List<Camera> cameras = cameraService.getAllCameras();
        return ResponseEntity.ok(cameras);
    }

    // GET /api/cameras/{id}
    @Operation(
            summary = "Get camera by ID",
            description = "Retrieve a camera by its unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Camera not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Camera> getCameraById(@PathVariable Long id) {
        Optional<Camera> camera = cameraService.getCameraById(id);
        return camera.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get camera by name", description = "Search for a camera by its name")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid name supplied"),
            @ApiResponse(responseCode = "404", description = "Camera not found")
    })
    // GET /api/cameras/search?name={name}
    @GetMapping("/search")
    public ResponseEntity<Camera> getCameraByName(@Parameter(description = "Name of the came to search", example = "Camera 1") @RequestParam String name) {
        Optional<Camera> camera = cameraService.getCameraByName(name);
        return camera.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Search cameras by keyword", description = "Search for cameras using a keyword in their name or description")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid keyword supplied"),
            @ApiResponse(responseCode = "404", description = "No cameras found matching the keyword")
    })
    // GET /api/cameras/search?keyword={keyword}
    @GetMapping("/search-keyword")
    public ResponseEntity<List<Camera>> searchCameras(@RequestParam String keyword) {
        List<Camera> cameras = cameraService.searchCameras(keyword);
        return ResponseEntity.ok(cameras);
    }

    // POST /api/cameras
    @Operation(summary = "Create a new camera", description = "Add a new camera to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Camera created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid camera data supplied")
    })
    @PostMapping
    public ResponseEntity<Camera> createCamera(@Valid @RequestBody Camera camera) {
        Camera savedCamera = cameraService.createCamera(camera);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCamera);
    }

    // PUT /api/cameras/{id}
    @Operation(summary = "Update an existing camera", description = "Update the details of an existing camera")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Camera updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid camera data supplied"),
            @ApiResponse(responseCode = "404", description = "Camera not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Camera> updateCamera(@PathVariable Long id,
                                               @Valid @RequestBody Camera cameraDetails) {
        try {
            Camera updatedCamera = cameraService.updateCamera(id, cameraDetails);
            return ResponseEntity.ok(updatedCamera);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/cameras/{id}
    @Operation(summary = "Delete a camera", description = "Remove a camera from the system by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Camera deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Camera not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable Long id) {
        try {
            cameraService.deleteCamera(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}