package com.it.academy.library.service.dto;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.util.Collection;

@Value
public class PageResponse<T> {
    Collection<T> content;

    Metadata metadata;

    public static <T> @NotNull PageResponse<T> of(@NotNull Page<T> page) {
        var metadata = new Metadata(page.getNumber(), page.getSize(), page.getTotalElements());

        return new PageResponse<>(page.getContent(), metadata);
    }

    @Value
    public static class Metadata {
        int page;
        int size;
        long totalElement;
    }
}
