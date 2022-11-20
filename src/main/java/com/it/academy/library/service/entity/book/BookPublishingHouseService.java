package com.it.academy.library.service.entity.book;

import com.it.academy.library.mapper.read.book.BookPublishingHouseReadMapper;
import com.it.academy.library.model.repository.entity.book.BookPublishingHouseRepository;
import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookPublishingHouseService {
    private final BookPublishingHouseRepository bookPublishingHouseRepository;
    private final BookPublishingHouseReadMapper bookPublishingHouseReadMapper;

    public Collection<BookPublishingHouseReadDto> findAll() {
        return bookPublishingHouseRepository.findAll().stream()
                .map(bookPublishingHouseReadMapper::map)
                .collect(Collectors.toList());
    }
}