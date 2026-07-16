package com.imagegallery.config;

import com.imagegallery.model.GalleryImage;
import com.imagegallery.repository.ImageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ImageRepository imageRepository;

    public DataInitializer(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void run(String... args) {
        if (imageRepository.count() == 0) {
            List<GalleryImage> images = List.of(
                    // Nature
                    new GalleryImage("Mountain Sunrise", "A majestic golden sunrise lighting up the snowy peaks.", "Nature", "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Forest Path", "A sun-drenched pathway winding through tall pine trees.", "Nature", "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Ocean Waves", "Powerful turquoise waves crashing onto a sandy beach under a soft sky.", "Nature", "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Waterfall", "A pristine waterfall hidden deep inside a lush green rainforest.", "Nature", "https://images.unsplash.com/photo-1432406186267-e727e27c2f16?auto=format&fit=crop&w=900&q=80"),

                    // Travel
                    new GalleryImage("Paris Streets", "A scenic view of a classic Parisian street leading to the Eiffel Tower.", "Travel", "https://images.unsplash.com/photo-1499856871958-5b9627545d1a?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Santorini View", "Iconic white buildings and blue domes overlooking the Aegean Sea.", "Travel", "https://images.unsplash.com/photo-1533105079780-92b9be482077?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Tokyo Night", "Vibrant neon lights and bustling streets of Tokyo's night districts.", "Travel", "https://images.unsplash.com/photo-1503899036084-c55cdd92da26?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Desert Journey", "An endless empty road stretching through red desert sand dunes.", "Travel", "https://picsum.photos/seed/desert-journey/900/600"),

                    // Animals
                    new GalleryImage("Wild Lion", "A majestic male lion resting on the grassy plains of the Serengeti.", "Animals", "https://images.unsplash.com/photo-1546182990-dffeafbe841d?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Colorful Bird", "A stunning colorful macaw flying through the tropical forest canopy.", "Animals", "https://images.unsplash.com/photo-1552728089-57bdde30ebd3?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Gentle Deer", "A curious white-tailed deer standing peacefully in a misty woodland.", "Animals", "https://images.unsplash.com/photo-1507666405821-4223cac1efc6?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Curious Fox", "An active red fox foraging through fresh snow in a winter forest.", "Animals", "https://images.unsplash.com/photo-1474511320723-9a56873867b5?auto=format&fit=crop&w=900&q=80"),

                    // Architecture
                    new GalleryImage("Modern Tower", "A breathtaking shot of a towering glass skyscraper reflecting the sky.", "Architecture", "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Historic Castle", "A romantic medieval stone castle sitting atop a rocky cliff.", "Architecture", "https://images.unsplash.com/photo-1508849789987-4e5333c12b78?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("City Bridge", "An iconic steel suspension bridge spanning the river at sunset.", "Architecture", "https://images.unsplash.com/photo-1449034446853-66c86144b0ad?auto=format&fit=crop&w=900&q=80"),
                    new GalleryImage("Glass Building", "Intricate modern geometric patterns of a large glass building facade.", "Architecture", "https://images.unsplash.com/photo-1511818966892-d7d671e672a2?auto=format&fit=crop&w=900&q=80")
            );
            imageRepository.saveAll(images);
        }
    }
}

