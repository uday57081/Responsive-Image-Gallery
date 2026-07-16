const galleryElement = document.getElementById('gallery');
const loadingElement = document.getElementById('loading');
const errorElement = document.getElementById('error-message');
const emptyElement = document.getElementById('empty-message');
const categoryFiltersElement = document.getElementById('category-filters');

const lightboxElement = document.getElementById('lightbox');
const lightboxImage = document.getElementById('lightbox-image');
const lightboxTitle = document.getElementById('lightbox-title');
const lightboxCategory = document.getElementById('lightbox-category');
const lightboxDescription = document.getElementById('lightbox-description');
const closeLightboxButton = document.getElementById('close-lightbox');
const prevButton = document.getElementById('prev-image');
const nextButton = document.getElementById('next-image');
const backdrop = document.getElementById('lightbox-backdrop');

const categories = ['All', 'Nature', 'Travel', 'Animals', 'Architecture'];
let allImages = [];
let filteredImages = [];
let currentCategory = 'All';
let currentIndex = 0;

// SVG Placeholder to load when an image fails to load
const PLACEHOLDER_SVG = 'data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="800" height="600" viewBox="0 0 800 600"><rect width="800" height="600" fill="%23f1f5f9"/><text x="50%" y="50%" dominant-baseline="middle" text-anchor="middle" font-family="sans-serif" font-size="18" fill="%2394a3b8">Image Unavailable</text></svg>';

async function fetchImages() {
    try {
        loadingElement.classList.remove('hidden');
        errorElement.classList.add('hidden');
        emptyElement.classList.add('hidden');
        galleryElement.classList.add('hidden');

        const response = await fetch('/api/images');

        if (!response.ok) {
            throw new Error('Unable to load the gallery. Please try again.');
        }

        allImages = await response.json();
        createFilterButtons();
        filterImages('All');
    } catch (error) {
        loadingElement.classList.add('hidden');
        errorElement.textContent = error.message;
        errorElement.classList.remove('hidden');
        console.error('Developer error:', error);
    }
}

function createFilterButtons() {
    categoryFiltersElement.innerHTML = '';

    categories.forEach((category) => {
        const button = document.createElement('button');
        button.className = 'filter-button';
        button.textContent = category;
        button.setAttribute('aria-label', `Filter by ${category}`);

        if (category === currentCategory) {
            button.classList.add('active');
        }

        button.addEventListener('click', () => {
            filterImages(category);
        });

        categoryFiltersElement.appendChild(button);
    });
}

function filterImages(category) {
    currentCategory = category;
    filteredImages = category === 'All'
        ? [...allImages]
        : allImages.filter((image) => image.category.toLowerCase() === category.toLowerCase());

    currentIndex = 0; // Reset index for navigation safety
    renderGallery();
    updateFilterButtonState();
}

function updateFilterButtonState() {
    const buttons = categoryFiltersElement.querySelectorAll('.filter-button');
    buttons.forEach((button) => {
        button.classList.toggle('active', button.textContent.toLowerCase() === currentCategory.toLowerCase());
    });
}

function renderGallery() {
    loadingElement.classList.add('hidden');
    galleryElement.innerHTML = '';

    if (filteredImages.length === 0) {
        emptyElement.classList.remove('hidden');
        galleryElement.classList.add('hidden');
        return;
    }

    emptyElement.classList.add('hidden');
    galleryElement.classList.remove('hidden');

    filteredImages.forEach((image, index) => {
        const card = document.createElement('article');
        card.className = 'gallery-card';
        card.setAttribute('tabindex', '0');
        card.setAttribute('role', 'button');
        card.setAttribute('aria-label', `View ${image.title || 'image'}`);

        card.innerHTML = `
            <img class="gallery-card__image" src="${image.imageUrl || ''}" alt="${image.title || 'Image'}" />
            <div class="gallery-card__overlay">
                <h3>${image.title || 'Untitled'}</h3>
                <p>${image.category || 'General'}</p>
            </div>
        `;

        // Handle image loading error gracefully
        const img = card.querySelector('.gallery-card__image');
        img.addEventListener('error', () => {
            if (img.getAttribute('src') !== PLACEHOLDER_SVG) {
                img.src = PLACEHOLDER_SVG;
            }
        });

        const handleOpen = () => {
            currentIndex = index;
            openLightbox(image);
        };

        card.addEventListener('click', handleOpen);
        card.addEventListener('keydown', (event) => {
            if (event.key === 'Enter' || event.key === ' ') {
                event.preventDefault();
                handleOpen();
            }
        });

        galleryElement.appendChild(card);
    });
}

function openLightbox(image) {
    if (!image) return;

    lightboxImage.src = image.imageUrl || PLACEHOLDER_SVG;
    lightboxImage.alt = image.title || 'Image';
    lightboxTitle.textContent = image.title || 'Untitled';
    lightboxCategory.textContent = image.category || 'General';
    lightboxDescription.textContent = image.description || 'No description available.';

    lightboxElement.classList.remove('hidden');
    lightboxElement.setAttribute('aria-hidden', 'false');
    document.body.classList.add('lightbox-open');
}

function closeLightbox() {
    lightboxElement.classList.add('hidden');
    lightboxElement.setAttribute('aria-hidden', 'true');
    document.body.classList.remove('lightbox-open');
}

function showPreviousImage() {
    if (!filteredImages.length) return;

    currentIndex = (currentIndex - 1 + filteredImages.length) % filteredImages.length;
    updateLightbox();
}

function showNextImage() {
    if (!filteredImages.length) return;

    currentIndex = (currentIndex + 1) % filteredImages.length;
    updateLightbox();
}

function updateLightbox() {
    const currentImage = filteredImages[currentIndex];
    if (!currentImage) return;

    lightboxImage.src = currentImage.imageUrl || PLACEHOLDER_SVG;
    lightboxImage.alt = currentImage.title || 'Image';
    lightboxTitle.textContent = currentImage.title || 'Untitled';
    lightboxCategory.textContent = currentImage.category || 'General';
    lightboxDescription.textContent = currentImage.description || 'No description available.';
}

// Lightbox image loading error fallback
lightboxImage.addEventListener('error', () => {
    if (lightboxImage.getAttribute('src') !== PLACEHOLDER_SVG) {
        lightboxImage.src = PLACEHOLDER_SVG;
    }
});

closeLightboxButton.addEventListener('click', closeLightbox);
backdrop.addEventListener('click', closeLightbox);
prevButton.addEventListener('click', showPreviousImage);
nextButton.addEventListener('click', showNextImage);

document.addEventListener('keydown', (event) => {
    if (lightboxElement.classList.contains('hidden')) {
        return;
    }

    if (event.key === 'Escape') {
        closeLightbox();
    }

    if (event.key === 'ArrowRight') {
        showNextImage();
    }

    if (event.key === 'ArrowLeft') {
        showPreviousImage();
    }
});

// Run fetch on load
fetchImages();
