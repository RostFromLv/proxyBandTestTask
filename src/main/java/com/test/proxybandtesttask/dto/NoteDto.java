package com.test.proxybandtesttask.dto;

import com.test.proxybandtesttask.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class NoteDto {
  @Nullable
  private String id;
  @NotNull
  private String content;
  @Nullable
  //groups
  private Set<User> likes = new HashSet<>();
  @Nullable
  private UserDto creator;
  @Nullable
  private LocalDateTime creatingDateTime;
}
