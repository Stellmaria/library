package com.it.academy.library.model.mapper.create.author;

import com.it.academy.library.dto.create.author.AuthorCreateEditDto;
import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.mapper.Mapper;
import com.it.academy.library.model.repository.entity.author.AuthorRoleRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorCreateEditMapper implements Mapper<AuthorCreateEditDto, Author> {
    private final AuthorRoleRepository authorRoleRepository;

    @Override
    public Author map(@NotNull AuthorCreateEditDto object) {
        var author = new Author();
        author.setFirstName(object.getFirstName());
        author.setLastName(object.getLastName());
        author.setImagePath(object.getImagePath());
        author.setAuthorRole(gerAuthorRole(object.getAuthorRoleId()));
        author.setBirthday(object.getBirthday());
        author.setDateDeath(object.getDateDeath());
        author.setDescription(object.getDescription());
        return author;
    }

    private AuthorRole gerAuthorRole(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(authorRoleRepository::findById)
                .orElse(null);
    }
}
