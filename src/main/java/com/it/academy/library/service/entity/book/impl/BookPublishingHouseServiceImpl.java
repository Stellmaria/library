package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.book.BookPublishingHouseCreateEditMapper;
import com.it.academy.library.mapper.read.book.BookPublishingHouseReadMapper;
import com.it.academy.library.model.repository.entity.book.BookPublishingHouseRepository;
import com.it.academy.library.service.dto.create.book.BookPublishingHouseCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;
import com.it.academy.library.service.entity.book.BookPublishingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookPublishingHouseServiceImpl implements BookPublishingHouseService {
    private final BookPublishingHouseRepository bookPublishingHouseRepository;

    private final BookPublishingHouseReadMapper bookPublishingHouseReadMapper;
    private final BookPublishingHouseCreateEditMapper bookPublishingHouseCreateEditMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BookPublishingHouseReadDto create(BookPublishingHouseCreateEditDto dto) {
        return Optional.of(dto)
                .map(bookPublishingHouseCreateEditMapper::map)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.CREATE));

                    return bookPublishingHouseRepository.save(entity);
                })
                .map(bookPublishingHouseReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<BookPublishingHouseReadDto> findById(Integer id) {
        return bookPublishingHouseRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookPublishingHouseReadMapper.map(entity);
                });
    }

    @Override
    public Optional<BookPublishingHouseReadDto> findByName(String name) {
        var filter = BookPublishingHouseFilter.builder()
                .name(name)
                .build();

        return bookPublishingHouseRepository.findAllByBookPublishingHouseFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookPublishingHouseReadMapper.map(entity);
                })
                .findFirst();
    }

    @Override
    public Collection<BookPublishingHouseReadDto> findAll() {
        return bookPublishingHouseRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookPublishingHouseReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookPublishingHouseReadDto> findAll(BookPublishingHouseFilter filter, Pageable pageable) {
        return bookPublishingHouseRepository.findAll(BookPublishingHouseFilter.queryPredicates(filter), pageable)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookPublishingHouseReadMapper.map(entity);
                });
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<BookPublishingHouseReadDto> update(Integer id, BookPublishingHouseCreateEditDto dto) {
        return bookPublishingHouseRepository.findById(id)
                .map(entity -> bookPublishingHouseCreateEditMapper.map(dto, entity))
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.UPDATE));

                    return bookPublishingHouseRepository.saveAndFlush(entity);
                })
                .map(bookPublishingHouseReadMapper::map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Integer id) {
        return bookPublishingHouseRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.DELETE));

                    bookPublishingHouseRepository.delete(entity);
                    bookPublishingHouseRepository.flush();

                    return true;
                })
                .orElse(false);
    }
}