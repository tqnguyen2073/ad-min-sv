package com.pvt.admin.controller;

import com.pvt.admin.entity.Camera;
import com.pvt.admin.service.CameraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cameras")
@RequiredArgsConstructor
public class CameraController {

    @Autowired
    private CameraService cameraService;

    // GET /api/cameras
    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        List<Camera> cameras = cameraService.getAllCameras();
        return ResponseEntity.ok(cameras);
    }

    // GET /api/cameras/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Camera> getCameraById(@PathVariable Long id) {
        Optional<Camera> camera = cameraService.getCameraById(id);
        return camera.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/cameras/search?name={name}
    @GetMapping("/search")
    public ResponseEntity<Camera> getCameraByName(@RequestParam String name) {
        Optional<Camera> camera = cameraService.getCameraByName(name);
        return camera.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/cameras/search?keyword={keyword}
    @GetMapping("/search-keyword")
    public ResponseEntity<List<Camera>> searchCameras(@RequestParam String keyword) {
        List<Camera> cameras = cameraService.searchCameras(keyword);
        return ResponseEntity.ok(cameras);
    }

    // POST /api/cameras
    @PostMapping
    public ResponseEntity<Camera> createCamera(@Valid @RequestBody Camera camera) {
        Camera savedCamera = cameraService.createCamera(camera);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCamera);
    }

    // PUT /api/cameras/{id}
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