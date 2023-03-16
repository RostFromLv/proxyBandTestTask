package com.test.proxybandtesttask.service.note;

import com.test.proxybandtesttask.dto.NoteDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;

public interface NoteService {
  Collection<NoteDto> getAll();

  Collection<NoteDto> getAllSortedByTime();

  NoteDto create(NoteDto noteDto, HttpServletRequest request);

  void removeById(String id);

  NoteDto addLike(String id,HttpServletRequest request);

  void removeLike(String id,HttpServletRequest request);

  Long getNoteLikes(String id);
}
