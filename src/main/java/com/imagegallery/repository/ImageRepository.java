package com.imagegallery.repository;

import com.imagegallery.model.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<GalleryImage, Long> {
    List<GalleryImage> findByCategoryIgnoreCase(String category);
}
