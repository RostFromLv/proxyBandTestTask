package com.test.proxybandtesttask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User  {
  @Id
  private String id;
  @Field(name = "name")
  private String name;
  @Field(name = "second_name")
  private String secondName;
  @Field(name = "email")
  @Indexed(unique = true)
  private String email;
  @Field(name = "password")
  private String password;

}
