package com.test.proxybandtesttask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
  @Nullable
  private String id;
  @NotNull
  private String name;
  @NotNull
  private String secondName;
  @Email
  @NotNull
  private String email;
  @NotNull
  private String password;
}
