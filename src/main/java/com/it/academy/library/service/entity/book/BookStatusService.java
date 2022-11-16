package com.it.academy.library.service.entity.book;

import com.it.academy.library.mapper.read.book.BookStatusReadMapper;
import com.it.academy.library.model.repository.entity.book.BookStatusRepository;
import com.it.academy.library.service.dto.read.book.BookStatusReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookStatusService {
    private final BookStatusRepository bookStatusRepository;
    private final BookStatusReadMapper bookStatusReadMapper;

    public Collection<BookStatusReadDto> findAll() {
        return bookStatusRepository.findAll().stream()
                .map(bookStatusReadMapper::map)
                .collect(Collectors.toList());
    }
}
