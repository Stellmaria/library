package com.it.academy.library.service.entity.author.impl;

import com.it.academy.library.model.repository.entity.AuthorRepository;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.service.dto.create.AuthorCreateEditDto;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import com.it.academy.library.service.entity.author.AuthorService;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("Author service test.")
class AuthorServiceImplTest extends IntegrationTestBase {
    private static final String IMAGE_AVATAR_1 = "avatar_1.jpg";
    private static final long AUTHOR_ID_19 = 19L;
    private static final long AUTHOR_ID_99 = 99L;
    private static final long AUTHOR_ID_9 = 9L;
    private static final String AUTHOR_FIRST_NAME_STEPHEN = "Stephen";
    private static final String AUTHOR_LAST_NAME_KING = "King";
    private static final String AUTHOR_IMAGE_9 = "author_9.jpeg";
    private static final LocalDate AUTHOR_BIRTHDAY = LocalDate.of(1947, 9, 21);
    private static final String AUTHOR_LAST_NAME_PETERS = "Peters";

    private final AuthorService authorService;

    private final AuthorRepository authorRepository;

    @Test
    @DisplayName("Save author.")
    void create() {
        var author = new AuthorCreateEditDto(
                ConstantUtil.NEW + ConstantUtil.SAVE,
                ConstantUtil.NEW + ConstantUtil.SAVE,
                new MockMultipartFile(ConstantUtil.NAME_IMAGE_TEST, new byte[0]),
                ConstantUtil.AUTHOR_BIRTHDAY,
                ConstantUtil.AUTHOR_DATE_DEATH,
                ConstantUtil.NEW + ConstantUtil.SAVE
        );

        var actual = authorService.create(author);

        assertAll(
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getFirstName()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getLastName()),
                () -> assertEquals(IMAGE_AVATAR_1, actual.getImage()),
                () -> assertEquals(ConstantUtil.AUTHOR_BIRTHDAY, actual.getBirthday()),
                () -> assertEquals(ConstantUtil.AUTHOR_DATE_DEATH, actual.getDateDeath()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getDescription())
        );
    }

    @Test
    @DisplayName("Find author by id.")
    void findById() {
        var expected = new AuthorReadDto(
                AUTHOR_ID_9,
                AUTHOR_FIRST_NAME_STEPHEN,
                AUTHOR_LAST_NAME_KING,
                AUTHOR_IMAGE_9,
                AUTHOR_BIRTHDAY,
                null,
                null
        );

        var actual = authorService.findById(expected.getId());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertEquals(expected.getFirstName(), entity.getFirstName()),
                        () -> assertEquals(expected.getLastName(), entity.getLastName()),
                        () -> assertEquals(expected.getImage(), entity.getImage()),
                        () -> assertEquals(expected.getBirthday(), entity.getBirthday()),
                        () -> assertEquals(expected.getDateDeath(), entity.getDateDeath()),
                        () -> assertEquals(expected.getDescription(), entity.getDescription())
                )
        );
    }

    @Test
    @DisplayName("Find all author.")
    void findAll() {
        var expected = authorRepository.count();

        var actual = authorService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }

    @Test
    @DisplayName("Find all author with filter.")
    void findAllWithFilter() {
        var expected = 1;
        var filter = new AuthorFilter();
        filter.setLastName(AUTHOR_LAST_NAME_PETERS);

        var actual = authorService.findAll(filter, Pageable.ofSize(20));

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all author by book id.")
    void findAllByBookId() {
        var expected = 1;

        var actual = authorService.findAllByBookId(17L);

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Update author.")
    void update() {
        var id = 8L;
        var author = new AuthorCreateEditDto(
                ConstantUtil.NEW + ConstantUtil.SAVE,
                ConstantUtil.NEW + ConstantUtil.SAVE,
                new MockMultipartFile(ConstantUtil.NAME_IMAGE_TEST, new byte[0]),
                ConstantUtil.AUTHOR_BIRTHDAY,
                ConstantUtil.AUTHOR_DATE_DEATH,
                ConstantUtil.NEW + ConstantUtil.SAVE
        );

        var actual = authorService.update(id, author);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(id, entity.getId()),
                        () -> assertEquals(author.getFirstName(), entity.getFirstName()),
                        () -> assertEquals(author.getLastName(), entity.getLastName()),
                        () -> assertEquals(IMAGE_AVATAR_1, entity.getImage()),
                        () -> assertEquals(author.getBirthday(), entity.getBirthday()),
                        () -> assertEquals(author.getDateDeath(), entity.getDateDeath()),
                        () -> assertEquals(author.getDescription(), entity.getDescription())
                )
        );
    }

    @Test
    @DisplayName("Delete author.")
    void delete() {
        assertAll(
                () -> assertTrue(authorService.delete(AUTHOR_ID_19)),
                () -> assertFalse(authorService.delete(AUTHOR_ID_99))
        );
    }
}