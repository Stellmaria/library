package com.it.academy.library.service.entity;

import com.it.academy.library.mapper.create.AuthorCreateEditMapper;
import com.it.academy.library.mapper.read.AuthorReadMapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.repository.entity.AuthorRepository;
import com.it.academy.library.service.ImageService;
import com.it.academy.library.service.dto.create.AuthorCreateEditDto;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorReadMapper authorReadMapper;
    private final ImageService imageService;
    private final AuthorCreateEditMapper authorCreateEditMapper;

    @Transactional(rollbackFor = {Exception.class})
    public AuthorReadDto create(AuthorCreateEditDto dto) {
        return Optional.of(dto)
                .map(it -> {
                    Optional.ofNullable(it.getImage())
                            .ifPresent(multipartFile -> ImageService.uploadImage(multipartFile, imageService));

                    return authorCreateEditMapper.map(it);
                })
                .map(authorRepository::save)
                .map(authorReadMapper::map)
                .orElseThrow();
    }

    public Optional<AuthorReadDto> findById(Long id) {
        return authorRepository.findById(id)
                .map(authorReadMapper::map);
    }

    public Collection<AuthorReadDto> findAll() {
        return authorRepository.findAll().stream()
                .map(authorReadMapper::map)
                .collect(Collectors.toList());
    }

    public Page<AuthorReadDto> findAll(AuthorFilter authorFilter, Pageable pageable) {
        var predicate = AuthorFilter.queryPredicates(authorFilter);

        return authorRepository.findAll(predicate, pageable)
                .map(authorReadMapper::map);
    }

    public Collection<AuthorReadDto> findAllByBookId(Long id) {
        var bookFilter = BookFilter.builder().id(id).build();

        return authorRepository.findAllByBookFilter(bookFilter).stream()
                .map(authorReadMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = {Exception.class})
    public Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto dto) {
        return authorRepository.findById(id)
                .map(entity -> {
                    ImageService.uploadImage(dto.getImage(), imageService);

                    return authorCreateEditMapper.map(dto, entity);
                })
                .map(authorRepository::saveAndFlush)
                .map(authorReadMapper::map);
    }

    public boolean delete(Long id) {
        return authorRepository.findById(id)
                .map(entity -> {
                    authorRepository.delete(entity);

                    return true;
                })
                .orElse(false);
    }

    public Optional<byte[]> findImage(Long id) {
        return authorRepository.findById(id)
                .map(Author::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }
}
