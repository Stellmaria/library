package com.it.academy.library.service.entity.book;

import com.it.academy.library.mapper.read.book.BookLanguageReadMapper;
import com.it.academy.library.model.repository.entity.book.BookLanguageRepository;
import com.it.academy.library.service.dto.read.book.BookLanguageReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookLanguageService {
    private final BookLanguageRepository bookLanguageRepository;
    private final BookLanguageReadMapper bookLanguageReadMapper;

    public Collection<BookLanguageReadDto> findAll() {
        return bookLanguageRepository.findAll().stream()
                .map(bookLanguageReadMapper::map)
                .collect(Collectors.toList());
    }
}
