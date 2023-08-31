package com.example.application.controllers;

import com.example.application.domain.Technology;
import com.example.application.repositories.ParticipantRepository;
import com.example.application.services.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ConnectService connectService;

    public String selectTechnology(@PathVariable Long id, @RequestBody String technology)  {
        try {
            Technology selectedTechnology = Technology.valueOf(technology.toUpperCase());
            connectService.updateTechnology(id, selectedTechnology);
            return "Technology selected: " + selectedTechnology;
        } catch (IllegalArgumentException e) {
            return "Invalid technology selected";
        }
    }
}
