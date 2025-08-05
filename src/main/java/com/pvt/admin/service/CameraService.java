package com.pvt.admin.service;

import com.pvt.admin.entity.Camera;
import com.pvt.admin.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    public Optional<Camera> getCameraById(Long id) {
        return cameraRepository.findById(id);
    }

    public Optional<Camera> getCameraByName(String name) {
        return cameraRepository.findByCameraName(name);
    }

    public List<Camera> searchCameras(String keyword) {
        return cameraRepository.findByCameraNameContainingIgnoreCase(keyword);
    }

    public Camera createCamera(Camera camera) {
        return cameraRepository.save(camera);
    }

    public Camera updateCamera(Long id, Camera cameraDetails) {
        Camera camera = cameraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camera not found with id: " + id));

        camera.setCameraName(cameraDetails.getCameraName());
        return cameraRepository.save(camera);
    }

    public void deleteCamera(Long id) {
        Camera camera = cameraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camera not found with id: " + id));
        cameraRepository.delete(camera);
    }

    public boolean existsById(Long id) {
        return cameraRepository.existsById(id);
    }
}