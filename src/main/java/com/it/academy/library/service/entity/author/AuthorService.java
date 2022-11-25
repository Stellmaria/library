package com.it.academy.library.service.entity.author;

import com.it.academy.library.service.dto.create.AuthorCreateEditDto;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface AuthorService {
    /**
     * Creation of a new author.
     *
     * @param dto for creating.
     * @return new created author.
     */
    AuthorReadDto create(AuthorCreateEditDto dto);

    /**
     * Search for an author by author id.
     *
     * @param id for search.
     * @return author.
     */
    Optional<AuthorReadDto> findById(Long id);

    /**
     * Search for an author by author first name and last name.
     *
     * @param firstName for search.
     * @param lastName  for search.
     * @return author.
     */
    Optional<AuthorReadDto> findByFullName(String firstName, String lastName);

    /**
     * Search for all authors.
     *
     * @return collection of found authors.
     */
    Collection<AuthorReadDto> findAll();

    /**
     * Search for all authors with filtering.
     *
     * @param filter   for search.
     * @param pageable for search.
     * @return collection of found authors.
     */
    Page<AuthorReadDto> findAll(AuthorFilter filter, Pageable pageable);

    /**
     * Search for all authors by book id.
     *
     * @param id for search.
     * @return collection of found authors.
     */
    Collection<AuthorReadDto> findAllByBookId(Long id);

    /**
     * Author update by author id.
     *
     * @param id  for an update.
     * @param dto for an update.
     * @return updated author.
     */
    Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto dto);

    /**
     * Deleting an author by author id.
     *
     * @param id for removing.
     * @return true - succeeded; false - failed.
     */
    boolean delete(Long id);

    /**
     * Search for an author's image by author's id.
     *
     * @param id for search.
     * @return author's image.
     */
    Optional<byte[]> findImage(Long id);
}
