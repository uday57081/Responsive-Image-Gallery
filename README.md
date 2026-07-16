# Responsive Image Gallery

## Project Description

Lumina Gallery is a modern, premium **Responsive Image Gallery Java Full Stack Web Application**. The application leverages a Spring Boot backend managing a dynamic H2 database of gallery image metadata, while serving a polished, glassmorphism-themed frontend using semantic HTML5, custom CSS3, and Vanilla JavaScript. 

The frontend communicates with the backend asynchronously using the modern Fetch API to retrieve, filter, and show image cards, supporting lightboxes with fully looped category navigation and keyboard controls.

## Features

- **Responsive Image Gallery**: Grid-based dynamic image display adjusting perfectly from large desktops to small mobile devices.
- **Dynamic Image Loading**: Gallery cards populated dynamically on page load using data fetched from the Spring Boot REST API.
- **Category Filtering**: High-quality, active filter buttons for "All", "Nature", "Travel", "Animals", and "Architecture".
- **Full-Screen Lightbox**: Stunning visual overlay presenting high-resolution images, titles, category badges, and descriptions.
- **Previous and Next Navigation**: Multi-directional slider controls allowing seamless browsing of images.
- **Looping Image Navigation**: Smart index-looping logic wrapping around from the last image to the first, and vice-versa.
- **Filtered Navigation**: Prev/Next lightbox controls only traverse images within the *currently selected* category.
- **Keyboard Controls**: Intuitive keys when the lightbox is open: `Escape` (Close), `ArrowRight` (Next), and `ArrowLeft` (Previous).
- **Hover Effects**: Exquisite glassmorphic cards that lift, zoom the image, and reveal dark-gradient overlays on hover.
- **Smooth Transitions**: Refined CSS animation durations (0.35s - 0.4s) utilizing modern cubic-bezier curves for premium feel.
- **REST API**: Fully-featured endpoints for listing, searching, adding, and deleting gallery image data.
- **H2 Database**: Fast, zero-config in-memory relational database seeded automatically with 16 images on startup.
- **Graceful Error Handling**: SVG error fallback for images that fail to load, protecting against broken link icons and infinite error loops.
- **State Feedback**: Friendly UI states for loading, error, and empty-category results.

## Technologies Used

### Backend:
- **Java 17** (or later)
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Maven** (for builds and dependency management)

### Frontend:
- **HTML5** (semantic structure)
- **CSS3** (custom variables, Grid/Flexbox, transitions, media queries, Outfit typography)
- **Vanilla JavaScript** (ES6+, async/await, Fetch API)

## Architecture

The system utilizes a modern tiered architecture:

```
[ Frontend: HTML/CSS/JS ]  <--- (Fetch API / JSON) --->  [ Backend: REST API Controller ]
                                                                       │
                                                              [ Service Layer ]
                                                                       │
                                                             [ Repository Layer ]
                                                                       │
                                                             [ H2 Memory Database ]
```

- **Controller**: Exposes REST API endpoints on port `8080` mapped under `/api/images`.
- **Service**: Implements business and transactional logic, handling custom runtime exception propagation.
- **Repository**: Uses Spring Data JPA to perform database queries, including case-insensitive category searches.
- **H2 Database**: Relational in-memory storage, creating tables and initializing records at runtime.

## Project Structure

```
image-gallery/
│
├── pom.xml                                   # Maven Build Configuration
├── README.md                                 # Project Documentation
├── .gitignore                                # Git ignored file configurations
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── imagegallery/
    │   │           ├── ImageGalleryApplication.java      # Spring Boot Main Application
    │   │           │
    │   │           ├── model/
    │   │           │   └── GalleryImage.java             # JPA Entity Class
    │   │           │
    │   │           ├── repository/
    │   │           │   └── ImageRepository.java          # Spring Data JPA Repository
    │   │           │
    │   │           ├── service/
    │   │           │   └── ImageService.java             # Business Logic Layer
    │   │           │
    │   │           ├── controller/
    │   │           │   └── ImageController.java          # REST Endpoint Class
    │   │           │
    │   │           ├── exception/
    │   │           │   ├── ImageNotFoundException.java   # Custom 404 Exception
    │   │           │   └── GlobalExceptionHandler.java    # Global Controller Advice
    │   │           │
    │   │           └── config/
    │   │               └── DataInitializer.java          # Seeder for 16 images
    │   │
    │   └── resources/
    │       ├── application.properties                # Spring and Database properties
    │       │
    │       └── static/                               # Static Web Assets (Frontend)
    │           ├── index.html                        # Main single-page application
    │           ├── css/
    │           │   └── style.css                     # Stylesheet (Responsive Grid, UI)
    │           └── js/
    │               └── script.js                     # App Controller (DOM, API, Navigation)
    │
    └── test/
        └── java/
            └── com/
                └── imagegallery/
                    └── ImageGalleryApplicationTests.java # Context loading unit tests
```

## REST API Endpoints

The API is fully documented and structured as follows:

| Method | Endpoint | Description | Status Code |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/images` | Retrieves all gallery images | `200 OK` |
| **GET** | `/api/images/{id}` | Retrieves a single image by ID | `200 OK` / `404 Not Found` |
| **GET** | `/api/images/category/{category}` | Retrieves images in a specific category | `200 OK` |
| **POST** | `/api/images` | Creates new image metadata record | `201 Created` |
| **DELETE**| `/api/images/{id}` | Deletes an image record | `204 No Content` / `404 Not Found` |

### Sample POST JSON Body (`POST /api/images`):
```json
{
  "title": "Autumn forest",
  "description": "Orange and golden autumn leaves on trees in a misty woods.",
  "category": "Nature",
  "imageUrl": "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?auto=format&fit=crop&w=900&q=80"
}
```

## Prerequisites

- **Java 17** or later.
- **Maven** (included at `C:\Users\Uday\maven\apache-maven-3.9.9\bin\mvn.cmd`).

## How to Run

1. Open a terminal and navigate to the project directory:
   ```powershell
   cd "c:\Users\Uday\OneDrive\Desktop\image gallery\image-gallery"
   ```
2. Build and package the application:
   ```powershell
   & "C:\Users\Uday\maven\apache-maven-3.9.9\bin\mvn.cmd" clean install
   ```
3. Start the Spring Boot application:
   ```powershell
   & "C:\Users\Uday\maven\apache-maven-3.9.9\bin\mvn.cmd" spring-boot:run
   ```
4. Access the web app:
   - **Main Gallery**: [http://localhost:8080](http://localhost:8080)
   - **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - **JDBC URL**: `jdbc:h2:mem:imagegallerydb`
     - **Username**: `sa`
     - **Password**: `password`

## How to Demonstrate the Project

1. **Start the Spring Boot server** and navigate to `http://localhost:8080` in the browser.
2. **Show the responsive layout** by opening Developer Tools and switching viewport sizes (desktop columns auto-wrap into 3, 2, and eventually 1 column on mobile).
3. **Select different categories** (All, Nature, Travel, Animals, Architecture) using the filter buttons to show dynamic DOM reloading.
4. **Click an image card** to open it inside the custom glassmorphism lightbox. Note that the body scroll locks and the backdrop blurs.
5. **Navigate through the images** using the Left/Right arrow buttons in the lightbox. Note that it loops back around when reaching ends.
6. **Toggle different categories** and verify that the lightbox navigation respects filters (e.g., in "Animals", navigation only displays animal images).
7. **Test keyboard navigation** by pressing `ArrowRight` and `ArrowLeft` to move images, and `Escape` to close the lightbox modal.
8. **Demonstrate API integration** by querying `http://localhost:8080/api/images` in a new tab to show raw JSON payload returned by Spring Boot.
9. **Explain the Exception Handling** by opening a non-existent ID like `/api/images/999` and pointing to the custom structured JSON 404 response.
10. **Explain the database state** by logging into `/h2-console` and running `SELECT * FROM GALLERY_IMAGE;` to demonstrate persistence.
