package com.test.proxybandtesttask.service.note;

import com.test.proxybandtesttask.domain.Note;
import com.test.proxybandtesttask.domain.User;
import com.test.proxybandtesttask.dto.NoteDto;
import com.test.proxybandtesttask.mapper.NoteMapper;
import com.test.proxybandtesttask.mapper.UserMapper;
import com.test.proxybandtesttask.service.repository.NoteRepository;
import com.test.proxybandtesttask.service.repository.UserRepository;
import com.test.proxybandtesttask.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
  private final NoteMapper noteMapper;
  private final UserRepository userRepository;
  private final NoteRepository noteRepository;
  private final UserService userService;
  private final String ENTITY_NOT_FOUND_BY_ID = "Not found entity with id:%s";
  private final String ENTITY_NOT_FOUND_BY_EMAIL = "Not found entity with email:%s";
  private final String AUTHENTICATION_BASIC = "Basic";
  private Charset credentialCharset = StandardCharsets.UTF_8;

  @Override
  public Collection<NoteDto> getAll() {
    return noteRepository.findAll().stream().map(noteMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Collection<NoteDto> getAllSortedByTime() {
    return noteRepository.findAll().stream()
          .sorted(Note::compareTo)
          .map(noteMapper::toDto)
          .collect(Collectors.toList());
  }

  @Override
  public NoteDto create(@NotNull NoteDto noteDto, HttpServletRequest request) { // TODO finish creating with user who create(try to using http request)

    noteDto.setCreator(userService.getByEmail(obtainEmailFromRequest(request)));
    noteDto.setCreatingDateTime(LocalDateTime.now());

    Note createdNote = noteRepository.save(noteMapper.toEntity(noteDto));

    return noteMapper.toDto(createdNote);
  }

  private String obtainEmailFromRequest(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null) {
      header = header.trim();
      if (header.startsWith(AUTHENTICATION_BASIC)) {
        byte[] base64Token = header.split(" ")[1].getBytes(credentialCharset);
        byte[] decoded;
        try {
          decoded = Base64.getDecoder().decode(base64Token);
          String token = new String(decoded, credentialCharset);
          int delim = token.indexOf(":");
          if (delim == -1) {
            throw new Exception("Wrong credential");
          }
          return token.substring(0, delim);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  @Override
  public void removeById(@NotNull final String id) {
    noteRepository.deleteById(id);
  }


  @Override
  public NoteDto addLike(@NotNull final String noteId,HttpServletRequest request) {
    Note note =noteRepository.findById(noteId).orElseThrow(()->new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID,noteId)));
    String email = obtainEmailFromRequest(request);
    User user = userRepository.findUserByEmail(email).orElseThrow(()->new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_EMAIL,email)));

    note.getLikes().add(user);
    noteRepository.save(note);

    return noteMapper.toDto(note);
  }

  @Override
  public void removeLike(@NotNull final String noteId,HttpServletRequest request) {
    Note note =noteRepository.findById(noteId).orElseThrow(()->new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID,noteId)));
    String email = obtainEmailFromRequest(request);
    User user = userRepository.findUserByEmail(email).orElseThrow(()->new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_EMAIL,email)));
    note.getLikes().remove(user);
    noteRepository.save(note);
  }

  @Override
  public Long getNoteLikes(@NotNull final String id) {
    Assert.notNull(id, "Id");
    Note noteFromDb = noteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, id)));
    return (long) noteFromDb.getLikes().size();
  }
}
