package com.it.academy.library.mapper.read.author;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorReadMapper implements Mapper<Author, AuthorReadDto> {
    @Override
    public AuthorReadDto map(@NotNull Author object) {
        return new AuthorReadDto(
                object.getId(),
                object.getFirstName(),
                object.getLastName(),
                object.getImage(),
                object.getBirthday(),
                object.getDateDeath(),
                object.getDescription()
        );
    }
}
