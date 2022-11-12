package com.it.academy.library.service.entity.author;

import com.it.academy.library.mapper.create.author.AuthorCreateEditMapper;
import com.it.academy.library.mapper.read.author.AuthorReadMapper;
import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.repository.entity.author.AuthorRepository;
import com.it.academy.library.service.ImageService;
import com.it.academy.library.service.dto.create.author.AuthorCreateEditDto;
import com.it.academy.library.service.dto.filter.author.AuthorFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.read.author.AuthorReadDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    public AuthorReadDto create(AuthorCreateEditDto authorDto) {
        return Optional.of(authorDto)
                .map(it -> {
                    uploadImage(it.getImagePath());

                    return authorCreateEditMapper.map(it);
                })
                .map(authorRepository::save)
                .map(authorReadMapper::map)
                .orElseThrow();
    }

    @Transactional(rollbackFor = {Exception.class})
    public Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto authorDto) {
        return authorRepository.findById(id)
                .map(entity -> {
                    uploadImage(authorDto.getImagePath());

                    return authorCreateEditMapper.map(authorDto, entity);
                })
                .map(authorRepository::saveAndFlush)
                .map(authorReadMapper::map);
    }

    @SneakyThrows
    private void uploadImage(@NotNull MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public boolean delete(Long id) {
        return authorRepository.findById(id)
                .map(entity -> {
                    authorRepository.delete(entity);

                    return true;
                }).orElse(false);
    }

    @SuppressWarnings("unused")
    public Optional<byte[]> findImage(Long id) {
        return authorRepository.findById(id)
                .map(Author::getImagePath)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }
}
