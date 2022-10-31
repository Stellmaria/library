package com.it.academy.library.model.repository.entity.author;

import com.it.academy.library.mapper.filter.author.AuthorRoleFilterMapper;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Author role repository test.")
class AuthorRoleRepositoryTest extends IntegrationTestBase {
    private static final Integer AUTHOR_ROLE_ID_11 = 11;

    private final AuthorRoleRepository authorRoleRepository;
    private final AuthorRoleFilterMapper authorRoleFilterMapper;

    @Test
    @DisplayName("Save author role.")
    void saveAuthorRole() {
        var expectedCount = authorRoleRepository.count() + 1;

        var actual = authorRoleRepository.save(
                AuthorRole.builder()
                        .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .build()
        );
        var actualCount = authorRoleRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Delete author role.")
    void deleteAuthorRole() {
        var expected = authorRoleRepository.count() - 1;

        authorRoleRepository.deleteById(AUTHOR_ROLE_ID_11);
        var actual = authorRoleRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update author role.")
    void updateAuthorRole() {
        var authorRole = authorRoleRepository.findById(ConstantUtil.AUTHOR_ROLE_ID_1);

        authorRole.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            authorRoleRepository.save(it);
        });
        var actual = authorRoleRepository.findById(ConstantUtil.AUTHOR_ROLE_ID_1);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
            assertEquals(ConstantUtil.AUTHOR_ROLE_ID_1, it.getId());
        });
    }

    @Test
    @DisplayName("Find all author by author role filter.")
    void findAllAuthorByAuthorRoleFilterRole() {
        var actual = authorRoleRepository.findAllByAuthorRoleFilter(authorRoleFilterMapper.map(
                AuthorRole.builder()
                        .name(ConstantUtil.AUTHOR_ROLE_NAME_AUTHOR)
                        .build())
        );

        assertThat(actual).hasSize(4);
    }
}