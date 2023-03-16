package com.test.proxybandtesttask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class Note  implements Comparable<Note>{
  @Id
  private String id;
  @Field
  private String content;
  @Field
  private Set<User> likes;
  @Field
  private LocalDateTime creatingDateTime;
  @Field
  private User creator;

  @Override
  public int compareTo(Note o) {
    return this.getCreatingDateTime().compareTo(o.getCreatingDateTime());
  }
}
