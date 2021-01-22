package com.example.ui;

/**
 * Created by vijay
 * Date: 22/01/21
 * Time: 1:59 PM
 **/

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class NoteController {

    final RestTemplate restTemplate;

    public NoteController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping({"", "/home", "index"})
    public String getHome(Model model) {
        Note[] notes = restTemplate.getForEntity("http://localhost:8081/api/note", Note[].class).getBody();
        model.addAttribute("notes", notes);
        return "index";
    }

    @RequestMapping({"/addNote", "addNote.html"})
    public String addNote(Model model) {
        model.addAttribute("note", new Note());
        return "addNote";
    }

    @PostMapping(value = "/addNote", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNoteDetail(Note note, Model model) {
        if (note.getDetail() != null && !note.getDetail().trim().isEmpty()
                && note.getTitle() != null && !note.getTitle().trim().isEmpty()) {
            ResponseEntity<Note> noteResponseEntity = restTemplate.postForEntity("http://localhost:8081/api/note", note, Note.class);
            return "redirect:index";
        } else {
            model.addAttribute("note", note);
            return "addNote";
        }

    }
}
