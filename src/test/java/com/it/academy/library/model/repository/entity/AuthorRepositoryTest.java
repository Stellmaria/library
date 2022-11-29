package com.it.academy.library.model.repository.entity;

import com.it.academy.library.mapper.filter.AuthorFilterMapper;
import com.it.academy.library.mapper.filter.book.BookFilterMapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Author repository test.")
class AuthorRepositoryTest extends IntegrationTestBase {
    private static final LocalDate AUTHOR_BIRTHDAY = LocalDate.of(2000, 1, 1);
    private static final LocalDate AUTHOR_DATE_DEATH = LocalDate.of(3000, 1, 1);
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
                .birthday(AUTHOR_BIRTHDAY)
                .dateDeath(AUTHOR_DATE_DEATH)
                .description(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = authorRepository.save(author);
        var actualCount = authorRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getFirstName()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getLastName()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getImage()),
                () -> assertEquals(AUTHOR_BIRTHDAY, actual.getBirthday()),
                () -> assertEquals(AUTHOR_DATE_DEATH, actual.getDateDeath()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getDescription())
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

        author.ifPresent(it -> {
            it.setFirstName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setLastName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setImage(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setBirthday(AUTHOR_BIRTHDAY);
            it.setDateDeath(AUTHOR_DATE_DEATH);
            it.setDescription(ConstantUtil.NEW + ConstantUtil.UPDATE);
            authorRepository.save(it);
        });
        var actual = authorRepository.findById(AUTHOR_ID_15);

        actual.ifPresent(it -> {
            assertEquals(AUTHOR_ID_15, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getFirstName());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getLastName());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getImage());
            assertEquals(AUTHOR_BIRTHDAY, it.getBirthday());
            assertEquals(AUTHOR_DATE_DEATH, it.getDateDeath());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getDescription());
        });
    }

    @Test
    @DisplayName("Find all author by author filter.")
    void findAllAuthorByAuthorFilter() {
        var author = new Author();
        author.setFirstName(AUTHOR_FIRST_NAME_JAMES);

        var actual = authorRepository.findAllByAuthorFilter(
                authorFilterMapper.map(author)
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all author by book filter.")
    void findAllAuthorByBookFilter() {
        var book = new Book();
        book.setTitle(ConstantUtil.BOOK_TITLE_FRAGMENT_PHP);

        var actual = authorRepository.findAllByBookFilter(
                bookFilterMapper.map(book)
        );

        assertThat(actual).hasSize(2);
    }
}
