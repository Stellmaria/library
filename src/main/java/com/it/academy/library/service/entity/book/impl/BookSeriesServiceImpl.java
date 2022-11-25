package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.book.BookSeriesCreateEditMapper;
import com.it.academy.library.mapper.read.book.BookSeriesReadMapper;
import com.it.academy.library.model.repository.entity.book.BookSeriesRepository;
import com.it.academy.library.service.dto.create.book.BookSeriesCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.dto.read.book.BookSeriesReadDto;
import com.it.academy.library.service.entity.book.BookSeriesService;
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
public class BookSeriesServiceImpl implements BookSeriesService {
    private final BookSeriesRepository bookSeriesRepository;

    private final BookSeriesReadMapper bookSeriesReadMapper;
    private final BookSeriesCreateEditMapper bookSeriesCreateEditMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BookSeriesReadDto create(BookSeriesCreateEditDto dto) {
        return Optional.of(dto)
                .map(bookSeriesCreateEditMapper::map)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.CREATE));

                    return bookSeriesRepository.save(entity);
                })
                .map(bookSeriesReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<BookSeriesReadDto> findById(Integer id) {
        return bookSeriesRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookSeriesReadMapper.map(entity);
                });
    }

    @Override
    public Collection<BookSeriesReadDto> findAll() {
        return bookSeriesRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookSeriesReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookSeriesReadDto> findAll(BookSeriesFilter filter, Pageable pageable) {
        return bookSeriesRepository.findAll(BookSeriesFilter.queryPredicates(filter), pageable)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookSeriesReadMapper.map(entity);
                });
    }


    @Override
    public Optional<BookSeriesReadDto> findByName(String name) {
        var filter = BookSeriesFilter.builder()
                .name(name)
                .build();

        return bookSeriesRepository.findAllByBookSeriesFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookSeriesReadMapper.map(entity);
                })
                .findFirst();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<BookSeriesReadDto> update(Integer id, BookSeriesCreateEditDto dto) {
        return bookSeriesRepository.findById(id)
                .map(entity -> bookSeriesCreateEditMapper.map(dto, entity))
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.UPDATE));

                    return bookSeriesRepository.saveAndFlush(entity);
                })
                .map(bookSeriesReadMapper::map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Integer id) {
        return bookSeriesRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.DELETE));

                    bookSeriesRepository.delete(entity);
                    bookSeriesRepository.flush();

                    return true;
                })
                .orElse(false);
    }
}
