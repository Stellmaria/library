package com.it.academy.library.service.entity.book;

import com.it.academy.library.mapper.read.book.BookFormatReadMapper;
import com.it.academy.library.model.repository.entity.book.BookFormatRepository;
import com.it.academy.library.service.dto.read.book.BookFormatReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookFormatService {
    private final BookFormatRepository bookFormatRepository;
    private final BookFormatReadMapper bookFormatReadMapper;

    public Optional<BookFormatReadDto> findById(Integer id) {
        return bookFormatRepository.findById(id)
                .map(bookFormatReadMapper::map);
    }

    public Collection<BookFormatReadDto> findAll() {
        return bookFormatRepository.findAll().stream()
                .map(bookFormatReadMapper::map)
                .collect(Collectors.toList());
    }
}
