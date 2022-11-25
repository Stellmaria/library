package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.book.BookGenreCreateEditMapper;
import com.it.academy.library.mapper.read.book.BookGenreReadMapper;
import com.it.academy.library.model.repository.entity.book.BookGenreRepository;
import com.it.academy.library.service.dto.create.book.BookGenreCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.filter.book.BookGenreFilter;
import com.it.academy.library.service.dto.read.book.BookGenreReadDto;
import com.it.academy.library.service.entity.book.BookGenreService;
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
public class BookGenreServiceImpl implements BookGenreService {
    private final BookGenreRepository bookGenreRepository;

    private final BookGenreCreateEditMapper bookGenreCreateEditMapper;
    private final BookGenreReadMapper bookGenreReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BookGenreReadDto create(BookGenreCreateEditDto dto) {
        return Optional.of(dto)
                .map(bookGenreCreateEditMapper::map)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.CREATE));

                    return bookGenreRepository.save(entity);
                })
                .map(bookGenreReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<BookGenreReadDto> findById(Integer id) {
        return bookGenreRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookGenreReadMapper.map(entity);
                });
    }

    @Override
    public Collection<BookGenreReadDto> findAll() {
        return bookGenreRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookGenreReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookGenreReadDto> findAll(BookGenreFilter filter, Pageable pageable) {
        return bookGenreRepository.findAll(BookGenreFilter.queryPredicates(filter), pageable)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookGenreReadMapper.map(entity);
                });
    }

    @Override
    public Optional<BookGenreReadDto> findByName(String name) {
        var filter = new BookGenreFilter();
        filter.setName(name);

        return bookGenreRepository.findAllByBookGenreFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookGenreReadMapper.map(entity);
                })
                .findFirst();
    }

    @Override
    public Collection<BookGenreReadDto> findAllByBookId(Long id) {
        var filter = new BookFilter();
        filter.setId(id);

        return bookGenreRepository.findAllByBookFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookGenreReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<BookGenreReadDto> update(Integer id, BookGenreCreateEditDto dto) {
        return bookGenreRepository.findById(id)
                .map(entity -> bookGenreCreateEditMapper.map(dto, entity))
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.UPDATE));

                    return bookGenreRepository.saveAndFlush(entity);
                })
                .map(bookGenreReadMapper::map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Integer id) {
        return bookGenreRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.DELETE));

                    bookGenreRepository.delete(entity);
                    bookGenreRepository.flush();

                    return true;
                })
                .orElse(false);
    }
}
