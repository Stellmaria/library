package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.read.book.BookPublishingHouseReadMapper;
import com.it.academy.library.model.repository.entity.book.BookPublishingHouseRepository;
import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;
import com.it.academy.library.service.entity.book.BookPublishingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookPublishingHouseServiceImpl implements BookPublishingHouseService {
    private final BookPublishingHouseRepository bookPublishingHouseRepository;

    private final BookPublishingHouseReadMapper bookPublishingHouseReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Collection<BookPublishingHouseReadDto> findAll() {
        return bookPublishingHouseRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return bookPublishingHouseReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }
}