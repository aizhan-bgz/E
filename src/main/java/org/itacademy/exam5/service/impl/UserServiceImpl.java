package org.itacademy.exam5.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.itacademy.exam5.dto.UserDto;
import org.itacademy.exam5.entity.User;
import org.itacademy.exam5.repo.UserRepo;
import org.itacademy.exam5.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    @Override
    public UserDto create(UserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
        repo.save(user);
        return dto;
    }
    @Override
    public UserDto getById(Long id) {
        User user = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("User is not found"));
        UserDto dto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password("*****")
                .build();
        return dto;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = repo.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User user: users){
            UserDto dto = UserDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .password("*****")
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User user = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Пользователь не найден"));
        if (dto.getUsername() != null)
            user.setUsername(dto.getUsername());
        if (dto.getPassword() != null)
            user.setPassword(dto.getPassword());
        repo.save(user);
        return dto;
    }

    @Override
    public void delete(Long id) {
        User user = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Пользователь не найден"));
        repo.delete(user);
    }
}
