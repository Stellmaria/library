package com.it.academy.library.service.book;

import com.it.academy.library.dto.read.book.BookLanguageReadDto;
import com.it.academy.library.mapper.read.book.BookLanguageReadMapper;
import com.it.academy.library.model.repository.entity.book.BookLanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookLanguageService {
    private final BookLanguageRepository bookLanguageRepository;
    private final BookLanguageReadMapper bookLanguageReadMapper;

    public Optional<BookLanguageReadDto> findById(Integer id) {
        return bookLanguageRepository.findById(id)
                .map(bookLanguageReadMapper::map);
    }

    public Collection<BookLanguageReadDto> findAll() {
        return bookLanguageRepository.findAll().stream()
                .map(bookLanguageReadMapper::map)
                .collect(Collectors.toList());
    }
}
