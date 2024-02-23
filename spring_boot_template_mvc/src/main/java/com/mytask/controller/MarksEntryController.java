package com.mytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.service.MarksEntryService;

@RestController
@RequestMapping("/marks-entries")
public class MarksEntryController {
    
    @Autowired
    private MarksEntryService marksEntryService;

    // Define endpoints to interact with the MarksEntryService
}
