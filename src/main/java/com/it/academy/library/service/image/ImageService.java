package com.it.academy.library.service.image;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.Optional;

public interface ImageService {
    /**
     * Loading an image and saving it.
     *
     * @param imagePath for search.
     * @param stream    for search.
     */
    void upload(String imagePath, @NotNull InputStream stream);

    /**
     * Getting a picture along its path.
     *
     * @param imagePath to get a picture.
     * @return picture.
     */
    Optional<byte[]> getImage(String imagePath);
}
