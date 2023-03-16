package com.test.proxybandtesttask.mapper;

import com.test.proxybandtesttask.domain.Note;
import com.test.proxybandtesttask.dto.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NoteMapper {
  NoteDto toDto(Note note);
  Note toEntity(NoteDto noteDto);
  void  updateParams(NoteDto noteDto, @MappingTarget Note note);
}
