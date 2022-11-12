package com.it.academy.library.service.entity.author;

import com.it.academy.library.mapper.read.author.AuthorRoleReadMapper;
import com.it.academy.library.model.repository.entity.author.AuthorRoleRepository;
import com.it.academy.library.service.dto.read.author.AuthorRoleReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorRoleService {

    private final AuthorRoleRepository authorRoleRepository;
    private final AuthorRoleReadMapper authorRoleReadMapper;

    public Optional<AuthorRoleReadDto> findById(Integer id) {
        return authorRoleRepository.findById(id)
                .map(authorRoleReadMapper::map);
    }

    public Collection<AuthorRoleReadDto> findAll() {
        return authorRoleRepository.findAll().stream()
                .map(authorRoleReadMapper::map)
                .collect(Collectors.toList());
    }
}
