package com.example.rest;
/*
 *Created by vijay
 *Date: 22/01/21
 *Time: 1:36 PM
 */

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/note")
@RestController
public class NoteController {

    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public void addNote(@RequestBody Note note){
        repository.save(note);
    }

    @DeleteMapping("/{id}")
    public void  removeNote(@PathVariable long id){
        repository.deleteById(id);
    }

    @GetMapping("")
    public List<Note> getNotes(){
        List<Note> notes = new ArrayList<>();
        for (Note note:repository.findAll()){
            notes.add(note);
        }
        return notes;
    }

}
