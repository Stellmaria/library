package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.read.book.BookFormatReadMapper;
import com.it.academy.library.model.repository.entity.book.BookFormatRepository;
import com.it.academy.library.service.dto.read.book.BookFormatReadDto;
import com.it.academy.library.service.entity.book.BookFormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookFormatServiceImpl implements BookFormatService {
    private final BookFormatRepository bookFormatRepository;

    private final BookFormatReadMapper bookFormatReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Collection<BookFormatReadDto> findAll() {
        return bookFormatRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookFormatReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }
}
