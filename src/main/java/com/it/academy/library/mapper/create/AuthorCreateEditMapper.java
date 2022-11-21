package com.it.academy.library.mapper.create;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.service.dto.create.AuthorCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
public class AuthorCreateEditMapper implements Mapper<AuthorCreateEditDto, Author> {
    @Override
    public Author map(@NotNull AuthorCreateEditDto fromObject, @NotNull Author toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public Author map(@NotNull AuthorCreateEditDto object) {
        var author = new Author();

        copy(object, author);

        return author;
    }

    private void copy(@NotNull AuthorCreateEditDto object, @NotNull Author author) {
        author.setFirstName(object.getFirstName());
        author.setLastName(object.getLastName());
        setImage(object, author);
        author.setBirthday(object.getBirthday());
        author.setDateDeath(object.getDateDeath());
        author.setDescription(object.getDescription());
    }

    private void setImage(@NotNull AuthorCreateEditDto object, Author author) {
        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> author.setImage(image.getOriginalFilename()));
    }
}
