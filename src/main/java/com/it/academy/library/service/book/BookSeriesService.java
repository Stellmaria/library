package com.it.academy.library.service.book;

import com.it.academy.library.dto.read.book.BookSeriesReadDto;
import com.it.academy.library.mapper.read.book.BookSeriesReadMapper;
import com.it.academy.library.model.repository.entity.book.BookSeriesRepository;
import lombok.RequiredArgsConstructor;
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

    public Optional<BookSeriesReadDto> findById(Integer id) {
        return bookSeriesRepository.findById(id)
                .map(bookSeriesReadMapper::map);
    }

    public Collection<BookSeriesReadDto> findAll() {
        return bookSeriesRepository.findAll().stream()
                .map(bookSeriesReadMapper::map)
                .collect(Collectors.toList());
    }
}
