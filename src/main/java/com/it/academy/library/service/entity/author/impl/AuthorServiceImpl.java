package com.it.academy.library.service.entity.author.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.AuthorCreateEditMapper;
import com.it.academy.library.mapper.read.author.AuthorReadMapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.repository.entity.author.AuthorRepository;
import com.it.academy.library.service.dto.create.AuthorCreateEditDto;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import com.it.academy.library.service.entity.author.AuthorService;
import com.it.academy.library.service.image.ImageService;
import com.it.academy.library.service.image.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final AuthorReadMapper authorReadMapper;
    private final AuthorCreateEditMapper authorCreateEditMapper;

    private final ImageService imageService;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AuthorReadDto create(AuthorCreateEditDto dto) {
        return Optional.of(dto)
                .map(entity -> {
                    Optional.ofNullable(entity.getImage())
                            .ifPresent(multipartFile -> ImageServiceImpl.uploadImage(multipartFile, imageService));

                    return authorCreateEditMapper.map(entity);
                })
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.CREATE));

                    if (entity.getImage() == null) {
                        entity.setImage("avatar_1.jpg");
                    } else {
                        entity.setImage(entity.getImage());
                    }

                    return authorRepository.save(entity);
                })
                .map(authorReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<AuthorReadDto> findById(Long id) {
        return authorRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return authorReadMapper.map(entity);
                });
    }

    @Override
    public Collection<AuthorReadDto> findAll() {
        return authorRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return authorReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuthorReadDto> findAll(AuthorFilter filter, Pageable pageable) {
        return authorRepository.findAll(AuthorFilter.queryPredicates(filter), pageable)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return authorReadMapper.map(entity);
                });
    }

    @Override
    public Collection<AuthorReadDto> findAllByBookId(Long id) {
        var filter = new BookFilter();
        filter.setId(id);

        return authorRepository.findAllByBookFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return authorReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto dto) {
        return authorRepository.findById(id)
                .map(entity -> {
                    ImageServiceImpl.uploadImage(dto.getImage(), imageService);

                    return authorCreateEditMapper.map(dto, entity);
                })
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.UPDATE));

                    return authorRepository.saveAndFlush(entity);
                })
                .map(authorReadMapper::map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long id) {
        return authorRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.DELETE));

                    authorRepository.delete(entity);
                    authorRepository.flush();

                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<byte[]> findImage(Long id) {
        return authorRepository.findById(id)
                .map(Author::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }
}
