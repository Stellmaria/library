package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.read.book.BookLanguageReadMapper;
import com.it.academy.library.model.repository.entity.book.BookLanguageRepository;
import com.it.academy.library.service.dto.read.book.BookLanguageReadDto;
import com.it.academy.library.service.entity.book.BookLanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookLanguageServiceImpl implements BookLanguageService {
    private final BookLanguageRepository bookLanguageRepository;

    private final BookLanguageReadMapper bookLanguageReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Collection<BookLanguageReadDto> findAll() {
        return bookLanguageRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookLanguageReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }
}
