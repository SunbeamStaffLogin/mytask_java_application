package com.mytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    
    @Autowired
    private SubjectService subjectService;

    // Define endpoints to interact with the SubjectService
}