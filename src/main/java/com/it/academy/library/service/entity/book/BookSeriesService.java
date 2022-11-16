package com.it.academy.library.service.entity.book;

import com.it.academy.library.mapper.create.book.BookSeriesCreateEditMapper;
import com.it.academy.library.mapper.read.book.BookSeriesReadMapper;
import com.it.academy.library.model.repository.entity.book.BookSeriesRepository;
import com.it.academy.library.service.dto.create.book.BookSeriesCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.dto.read.book.BookSeriesReadDto;
import lombok.RequiredArgsConstructor;
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
public class BookSeriesService {
    private final BookSeriesRepository bookSeriesRepository;
    private final BookSeriesReadMapper bookSeriesReadMapper;
    private final BookSeriesCreateEditMapper bookSeriesCreateEditMapper;

    @Transactional(rollbackFor = {Exception.class})
    public BookSeriesReadDto create(BookSeriesCreateEditDto dto) {
        return Optional.of(dto)
                .map(bookSeriesCreateEditMapper::map)
                .map(bookSeriesRepository::save)
                .map(bookSeriesReadMapper::map)
                .orElseThrow();
    }

    public Optional<BookSeriesReadDto> findById(Integer id) {
        return bookSeriesRepository.findById(id)
                .map(bookSeriesReadMapper::map);
    }

    public Collection<BookSeriesReadDto> findAll() {
        return bookSeriesRepository.findAll().stream()
                .map(bookSeriesReadMapper::map)
                .collect(Collectors.toList());
    }

    public Page<BookSeriesReadDto> findAll(BookSeriesFilter filter, Pageable pageable) {
        var predicate = BookSeriesFilter.queryPredicates(filter);

        return bookSeriesRepository.findAll(predicate, pageable)
                .map(bookSeriesReadMapper::map);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Optional<BookSeriesReadDto> update(Integer id, BookSeriesCreateEditDto bookDto) {
        return bookSeriesRepository.findById(id)
                .map(entity -> bookSeriesCreateEditMapper.map(bookDto, entity))
                .map(bookSeriesRepository::saveAndFlush)
                .map(bookSeriesReadMapper::map);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Integer id) {
        return bookSeriesRepository.findById(id)
                .map(entity -> {
                    bookSeriesRepository.delete(entity);
                    bookSeriesRepository.flush();

                    return true;
                })
                .orElse(false);
    }
}
