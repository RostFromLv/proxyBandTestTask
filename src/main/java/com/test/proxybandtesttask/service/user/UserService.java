package com.test.proxybandtesttask.service.user;

import com.test.proxybandtesttask.dto.UserDto;

import javax.swing.text.html.Option;
import java.util.Collection;

public interface UserService {
  UserDto getByEmail(String email);
  UserDto create(UserDto userDto);

  UserDto update(UserDto userDto);

  UserDto getById(String id);

  Collection<UserDto> getAll();

  void deleteById(String id);
}
