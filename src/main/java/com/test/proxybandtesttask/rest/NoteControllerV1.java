package com.test.proxybandtesttask.rest;

import com.test.proxybandtesttask.dto.NoteDto;
import com.test.proxybandtesttask.service.note.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteControllerV1 {
  private final NoteService noteService;

  @GetMapping("/all")
  public ResponseEntity<Collection<NoteDto>> getAll(){
    return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
  }
  @GetMapping("/likes/{id}")
  public ResponseEntity<Long> getNoteLikes(@PathVariable String id){
    return new ResponseEntity<>(noteService.getNoteLikes(id),HttpStatus.OK);
  }
  @PostMapping
  public ResponseEntity<NoteDto> create(@RequestBody NoteDto noteDto, HttpServletRequest request){
    return new ResponseEntity<>(noteService.create(noteDto,request),HttpStatus.CREATED);
  }
  @GetMapping("/all/sorted-time")
  public ResponseEntity<Collection<NoteDto>> getAllSortedByCreatingTime(){
    return new ResponseEntity<>(noteService.getAllSortedByTime(),HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeById(@PathVariable String id){
    noteService.removeById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/like/{id}")
  public ResponseEntity<NoteDto> likeNote(@PathVariable String id,HttpServletRequest request){
    return new ResponseEntity<>(noteService.addLike(id,request),HttpStatus.OK);
  }
  @PutMapping("/like/remove/{id}")
  public ResponseEntity<Void> removeNoteLike(@PathVariable String id,HttpServletRequest request){
    noteService.removeLike(id,request);
    return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }



}
