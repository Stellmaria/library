package com.it.academy.library.mapper.convert;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorReadDto, Author> {
    @Override
    public Author map(@NotNull AuthorReadDto object) {
        return Author.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .birthday(object.getBirthday())
                .dateDeath(object.getDateDeath())
                .description(object.getDescription())
                .image(object.getImage())
                .build();
    }
}
