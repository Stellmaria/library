package com.it.academy.library.service.entity.book;

import com.it.academy.library.mapper.create.book.BookCreateEditMapper;
import com.it.academy.library.mapper.read.book.BookReadMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import com.it.academy.library.service.ImageService;
import com.it.academy.library.service.dto.create.book.BookCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.read.book.BookReadDto;
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
public class BookService {
    private final BookRepository bookRepository;
    private final BookReadMapper bookReadMapper;
    private final BookCreateEditMapper bookCreateEditMapper;
    private final ImageService imageService;

    public Collection<BookReadDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookReadMapper::map)
                .collect(Collectors.toList());
    }

    public Page<BookReadDto> findAll(BookFilter bookFilter, Pageable pageable) {
        var predicate = BookFilter.queryPredicates(bookFilter);

        return bookRepository.findAll(predicate, pageable)
                .map(bookReadMapper::map);
    }

    public Optional<BookReadDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map);
    }

    @Transactional(rollbackFor = {Exception.class})
    public BookReadDto create(BookCreateEditDto bookDto) {
        return Optional.of(bookDto)
                .map(it -> {
                    uploadImage(it.getImage());

                    it.setBookStatusId(1);

                    return bookCreateEditMapper.map(it);
                })
                .map(bookRepository::save)
                .map(bookReadMapper::map)
                .orElseThrow();
    }

    @Transactional(rollbackFor = {Exception.class})
    public Optional<BookReadDto> update(Long id, BookCreateEditDto bookDto) {
        return bookRepository.findById(id)
                .map(entity -> {
                    uploadImage(bookDto.getImage());

                    return bookCreateEditMapper.map(bookDto, entity);
                })
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map);
    }
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long id) {
        return bookRepository.findById(id)
                .map(entity -> {
                    bookRepository.delete(entity);
                    bookRepository.flush();

                    return true;
                }).orElse(false);
    }

    @SneakyThrows
    private void uploadImage(@NotNull MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public Optional<byte[]> findImage(Long id) {
        return bookRepository.findById(id)
                .map(Book::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }
}