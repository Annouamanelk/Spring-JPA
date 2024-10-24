package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    @Autowired
    private SpeakerJpaRepository repository;

    @GetMapping
    public List<Speaker> list() {
        return repository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @GetMapping
    @RequestMapping("/and")
    public List<Speaker> findByFAndL() {
        return repository.findByFirstNameAndLastName("Annouamane","Becker");
    }

    @GetMapping
    @RequestMapping("/or")
    public List<Speaker> findByFOrL() {
        return repository.findByFirstNameOrLastName("Annouamane","Becker");
    }

    @GetMapping
    @RequestMapping("/count")
    public int count() {
        return repository.findBySpeakerIdNotNull().size();
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker){
        return repository.saveAndFlush(speaker);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Speaker existingSpeaker = repository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "session_id");
        return repository.saveAndFlush(speaker);
    }

}
