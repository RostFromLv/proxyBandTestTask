package com.test.proxybandtesttask.rest;

import com.test.proxybandtesttask.dto.UserDto;
import com.test.proxybandtesttask.service.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

  private final UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable String id){
    return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
  }
  @GetMapping("/all")
  public ResponseEntity<Collection<UserDto>> getAll(){
    return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
    return new ResponseEntity<>(userService.create(userDto),HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<UserDto> update(@RequestBody UserDto userDto){
    return new ResponseEntity<>(userService.update(userDto),HttpStatus.OK);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable String id){
    userService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
