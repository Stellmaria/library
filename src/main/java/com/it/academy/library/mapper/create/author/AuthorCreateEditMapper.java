package com.it.academy.library.mapper.create.author;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.repository.entity.author.AuthorRoleRepository;
import com.it.academy.library.service.dto.create.author.AuthorCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class AuthorCreateEditMapper implements Mapper<AuthorCreateEditDto, Author> {
    private final AuthorRoleRepository authorRoleRepository;

    @Override
    public Author map(@NotNull AuthorCreateEditDto object) {
        var author = new Author();
        author.setFirstName(object.getFirstName());
        author.setLastName(object.getLastName());
        setImage(object, author);
        author.setAuthorRole(gerAuthorRole(object.getAuthorRoleId()));
        author.setBirthday(object.getBirthday());
        author.setDateDeath(object.getDateDeath());
        author.setDescription(object.getDescription());

        return author;
    }

    private void setImage(@NotNull AuthorCreateEditDto object, Author author) {
        Optional.ofNullable(object.getImagePath())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> author.setImagePath(image.getOriginalFilename()));
    }

    private AuthorRole gerAuthorRole(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(authorRoleRepository::findById)
                .orElse(null);
    }
}
