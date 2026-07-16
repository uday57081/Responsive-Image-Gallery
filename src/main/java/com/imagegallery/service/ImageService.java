package com.imagegallery.service;

import com.imagegallery.model.GalleryImage;
import com.imagegallery.repository.ImageRepository;
import com.imagegallery.exception.ImageNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<GalleryImage> getAllImages() {
        return imageRepository.findAll();
    }

    public GalleryImage getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Image not found with id: " + id));
    }

    public List<GalleryImage> getImagesByCategory(String category) {
        return imageRepository.findByCategoryIgnoreCase(category);
    }

    public GalleryImage createImage(GalleryImage galleryImage) {
        return imageRepository.save(galleryImage);
    }

    public void deleteImage(Long id) {
        if (!imageRepository.existsById(id)) {
            throw new ImageNotFoundException("Image not found with id: " + id);
        }
        imageRepository.deleteById(id);
    }
}

