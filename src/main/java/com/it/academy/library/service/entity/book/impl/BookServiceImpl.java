package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.book.BookCreateEditMapper;
import com.it.academy.library.mapper.read.book.BookReadMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import com.it.academy.library.service.dto.create.book.BookCreateEditDto;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import com.it.academy.library.service.entity.book.BookService;
import com.it.academy.library.service.image.ImageService;
import com.it.academy.library.service.image.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookReadMapper bookReadMapper;
    private final BookCreateEditMapper bookCreateEditMapper;

    private final ImageService imageService;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BookReadDto create(BookCreateEditDto dto) {
        return Optional.of(dto)
                .map(entity -> {
                    Optional.ofNullable(entity.getImage())
                            .ifPresent(multipartFile -> ImageServiceImpl.uploadImage(multipartFile, imageService));

                    entity.setBookStatusId(1);

                    return bookCreateEditMapper.map(entity);
                })
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.CREATE));

                    return bookRepository.save(entity);
                })
                .map(bookReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Collection<BookReadDto> findAll() {
        return bookRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookReadDto> findAll(BookFilter filter, Pageable pageable) {
        return bookRepository.findAll(BookFilter.queryPredicates(filter), pageable)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookReadMapper.map(entity);
                });
    }

    @Override
    public Collection<BookReadDto> findAllBySeriesId(Integer id) {
        var filter = new BookSeriesFilter();
        filter.setId(id);

        return bookRepository.findAllByBookSeriesFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Collection<BookReadDto> findAllByBookPublishingHouseId(Integer id) {
        var filter = new BookPublishingHouseFilter();
        filter.setId(id);

        return bookRepository.findAllByBookPublishingHouseFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Collection<BookReadDto> findAllByAuthorId(Long id) {
        var filter = new AuthorFilter();
        filter.setId(id);

        return bookRepository.findAllByAuthorFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookReadDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookReadMapper.map(entity);
                });
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<BookReadDto> update(Long id, BookCreateEditDto dto) {
        return bookRepository.findById(id)
                .map(entity -> {
                    ImageServiceImpl.uploadImage(dto.getImage(), imageService);

                    return bookCreateEditMapper.map(dto, entity);
                })
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.UPDATE));

                    return bookRepository.saveAndFlush(entity);
                })
                .map(bookReadMapper::map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long id) {
        return bookRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.DELETE));

                    bookRepository.delete(entity);
                    bookRepository.flush();

                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<byte[]> findImage(Long id) {
        return bookRepository.findById(id)
                .map(Book::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }
}
