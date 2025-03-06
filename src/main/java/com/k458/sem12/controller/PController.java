package com.k458.sem12.controller;

import com.k458.sem12.model.Person;
import com.k458.sem12.model.PersonDecorator;
import com.k458.sem12.service.IFileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api")
public class PController {

    private final IFileGateway fileGateway;

    public PController(IFileGateway fileGateway) {
        this.fileGateway = fileGateway;
    }

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person p)
    {
        /*
        localhost:8080/api/person post
        {
            "name":"bastard"
        }
        */
        p.setId(1L);
        p.setCreatedTime(LocalDateTime.now().toString());
        fileGateway.writeToFile(p.getName()+".txt", new PersonDecorator(p).toString());
        return ResponseEntity.ok(p);
    }

}
