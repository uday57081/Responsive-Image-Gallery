package com.imagegallery.controller;

import com.imagegallery.model.GalleryImage;
import com.imagegallery.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<GalleryImage> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GalleryImage> getImageById(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImageById(id));
    }

    @GetMapping("/category/{category}")
    public List<GalleryImage> getImagesByCategory(@PathVariable String category) {
        return imageService.getImagesByCategory(category);
    }

    @PostMapping
    public ResponseEntity<GalleryImage> createImage(@RequestBody GalleryImage galleryImage) {
        GalleryImage savedImage = imageService.createImage(galleryImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}

