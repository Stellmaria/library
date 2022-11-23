package com.it.academy.library.mapper.convert.author;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorReadDto, Author> {
    @Override
    public Author map(@NotNull AuthorReadDto object) {
        var author = new Author();

        author.setId(object.getId());
        author.setFirstName(object.getFirstName());
        author.setLastName(object.getLastName());
        author.setBirthday(object.getBirthday());
        author.setDateDeath(object.getDateDeath());
        author.setDescription(object.getDescription());
        author.setImage(object.getImage());

        return author;
    }
}
