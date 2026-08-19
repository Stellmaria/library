package com.it.academy.library.service.image.impl;

import com.it.academy.library.service.image.ImageService;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
public class ImageServiceImpl implements ImageService {
    private final Path bucket;

    public ImageServiceImpl(@Value("${app.images.directory:./images}") String imagesDirectory) {
        this.bucket = Path.of(imagesDirectory).toAbsolutePath().normalize();
    }

    @SneakyThrows
    public static void uploadImage(@NotNull MultipartFile image, ImageService imageService) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Override
    @SneakyThrows
    public void upload(String imagePath, @NotNull InputStream stream) {
        var path = resolveImagePath(imagePath);

        try (stream) {
            Files.createDirectories(bucket);
            Files.write(path, stream.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @Override
    @SneakyThrows
    public Optional<byte[]> getImage(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) {
            return Optional.empty();
        }

        var path = resolveImagePath(imagePath);

        return Files.isRegularFile(path)
                ? Optional.of(Files.readAllBytes(path))
                : Optional.empty();
    }

    private Path resolveImagePath(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) {
            throw new IllegalArgumentException("Image path must not be blank");
        }

        var fileName = Path.of(imagePath).getFileName();
        if (fileName == null) {
            throw new IllegalArgumentException("Invalid image path");
        }

        var resolved = bucket.resolve(fileName).normalize();
        if (!resolved.startsWith(bucket)) {
            throw new IllegalArgumentException("Image path escapes configured storage directory");
        }

        return resolved;
    }
}
