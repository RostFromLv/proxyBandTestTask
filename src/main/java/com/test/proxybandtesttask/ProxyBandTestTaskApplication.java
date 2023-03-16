package com.test.proxybandtesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.test.proxybandtesttask.service.repository")
public class ProxyBandTestTaskApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProxyBandTestTaskApplication.class, args);
  }
}
