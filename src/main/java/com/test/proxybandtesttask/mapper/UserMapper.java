package com.test.proxybandtesttask.mapper;

import com.test.proxybandtesttask.domain.User;
import com.test.proxybandtesttask.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
  User toEntity(UserDto userDto);
  void updateParams(UserDto userDto, @MappingTarget User user);
}
