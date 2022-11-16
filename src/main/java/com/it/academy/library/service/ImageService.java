package com.it.academy.library.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
public class ImageService {
    private static final String BUCKET = "D:\\java\\home\\melnikova\\dev\\it-academy\\ee\\library\\images";

    @SneakyThrows
    public static void uploadImage(@NotNull MultipartFile image, ImageService imageService) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @SneakyThrows
    public void upload(String imagePath, @NotNull InputStream stream) {
        var path = Path.of(BUCKET, imagePath);

        try (stream) {
            Files.createDirectories(path.getParent());
            Files.write(path, stream.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> getImage(String imagePath) {
        var path = Path.of(BUCKET, imagePath);

        return Files.exists(path)
                ? Optional.of(Files.readAllBytes(path))
                : Optional.empty();
    }
}
