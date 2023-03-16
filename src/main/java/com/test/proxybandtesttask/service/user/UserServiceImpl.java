package com.test.proxybandtesttask.service.user;

import com.test.proxybandtesttask.domain.User;
import com.test.proxybandtesttask.dto.UserDto;
import com.test.proxybandtesttask.mapper.UserMapper;
import com.test.proxybandtesttask.service.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final String ENTITY_NOT_FOUND_BY_ID = "Not found entity with id: %s";
  private final String ENTITY_NOT_FOUND_BY_EMAIL = "Not found entity with email: %s";

  @Override
  public UserDto create(@NotNull final UserDto userDto) {
    User user = userRepository.save(userMapper.toEntity(userDto));
    return userMapper.toDto(user);
  }

  @Override
  public UserDto update(@NotNull final UserDto userDto) {
    String id = userDto.getId();
    Assert.notNull(id,"Updating id cannot be null");
    User dbUser = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID,id)));
    userMapper.updateParams(userDto,dbUser);
    User updatedUser = userRepository.save(dbUser);
    return userMapper.toDto(updatedUser);
  }

  @Override
  public UserDto getById(@NotNull final String id) {
    User user = userRepository.findById(id).orElseThrow(
          ()-> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID,id)));
    return userMapper.toDto(user);
  }

  @Override
  public Collection<UserDto> getAll() {
    return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public void deleteById(@NotNull final String id) {
      userRepository.deleteById(id);
  }

  @Override
  public UserDto getByEmail(@NotNull final String email){
    User user = userRepository.findUserByEmail(email).orElseThrow(()-> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_EMAIL,email)));
    return userMapper.toDto(user);
  }
}
