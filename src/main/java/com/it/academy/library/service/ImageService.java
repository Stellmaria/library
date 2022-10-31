package com.it.academy.library.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
public class ImageService {
    private static final String BUCKET = "D:\\java\\home\\melnikova\\dev\\it-academy\\ee\\library\\images";

    @SneakyThrows
    public void upload(String imagePath, InputStream stream) {
        var path = Path.of(BUCKET, imagePath);

        try (stream) {
            Files.createDirectories(path.getParent());
            Files.write(path, stream.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }
}
