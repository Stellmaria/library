package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.read.book.BookStatusReadMapper;
import com.it.academy.library.model.repository.entity.book.BookStatusRepository;
import com.it.academy.library.service.dto.read.book.BookStatusReadDto;
import com.it.academy.library.service.entity.book.BookStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookStatusServiceImpl implements BookStatusService {
    private final BookStatusRepository bookStatusRepository;

    private final BookStatusReadMapper bookStatusReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Collection<BookStatusReadDto> findAll() {
        return bookStatusRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookStatusReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }
}
