package com.it.academy.library.model.repository.entity;

import com.it.academy.library.mapper.filter.AuthorFilterMapper;
import com.it.academy.library.mapper.filter.book.BookFilterMapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Author repository test.")
class AuthorRepositoryTest extends IntegrationTestBase {
    private static final String AUTHOR_FIRST_NAME_JAMES = "James";
    private static final Long AUTHOR_ID_2 = 2L;
    private static final Long AUTHOR_ID_15 = 15L;

    private final AuthorRepository authorRepository;

    private final AuthorFilterMapper authorFilterMapper;
    private final BookFilterMapper bookFilterMapper;

    @Test
    @DisplayName("Save author.")
    void saveAuthor() {
        var expectedCount = authorRepository.count() + 1;
        var author = Author.builder()
                .firstName(ConstantUtil.NEW + ConstantUtil.SAVE)
                .lastName(ConstantUtil.NEW + ConstantUtil.SAVE)
                .image(ConstantUtil.NEW + ConstantUtil.SAVE)
                .birthday(ConstantUtil.AUTHOR_BIRTHDAY)
                .dateDeath(ConstantUtil.AUTHOR_DATE_DEATH)
                .description(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = authorRepository.save(author);
        var actualCount = authorRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(author.getFirstName(), actual.getFirstName()),
                () -> assertEquals(author.getLastName(), actual.getLastName()),
                () -> assertEquals(author.getImage(), actual.getImage()),
                () -> assertEquals(author.getBirthday(), actual.getBirthday()),
                () -> assertEquals(author.getDateDeath(), actual.getDateDeath()),
                () -> assertEquals(author.getDescription(), actual.getDescription())
        );
    }

    @Test
    @DisplayName("Delete author.")
    void deleteAuthor() {
        var expected = authorRepository.count() - 1;

        authorRepository.deleteById(AUTHOR_ID_2);
        var actual = authorRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update author.")
    void updateAuthor() {
        var author = authorRepository.findById(AUTHOR_ID_15);

        author.ifPresent(entity -> {
            entity.setFirstName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            entity.setLastName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            entity.setImage(ConstantUtil.NEW + ConstantUtil.UPDATE);
            entity.setBirthday(ConstantUtil.AUTHOR_BIRTHDAY);
            entity.setDateDeath(ConstantUtil.AUTHOR_DATE_DEATH);
            entity.setDescription(ConstantUtil.NEW + ConstantUtil.UPDATE);

            authorRepository.save(entity);
        });
        var actual = authorRepository.findById(AUTHOR_ID_15);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(AUTHOR_ID_15, entity.getId()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getFirstName()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getLastName()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getImage()),
                        () -> assertEquals(ConstantUtil.AUTHOR_BIRTHDAY, entity.getBirthday()),
                        () -> assertEquals(ConstantUtil.AUTHOR_DATE_DEATH, entity.getDateDeath()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getDescription())
                )
        );
    }

    @Test
    @DisplayName("Find all author by author filter.")
    void findAllAuthorByAuthorFilter() {
        var expected = 1;
        var author = new Author();
        author.setFirstName(AUTHOR_FIRST_NAME_JAMES);

        var actual = authorRepository.findAllByAuthorFilter(
                authorFilterMapper.map(author)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all author by book filter.")
    void findAllAuthorByBookFilter() {
        var expected = 2;
        var book = new Book();
        book.setTitle(ConstantUtil.BOOK_TITLE_FRAGMENT_PHP);

        var actual = authorRepository.findAllByBookFilter(
                bookFilterMapper.map(book)
        );

        assertThat(actual).hasSize(expected);
    }
}
