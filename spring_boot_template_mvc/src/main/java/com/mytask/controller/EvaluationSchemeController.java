package com.mytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.service.EvaluationSchemeService;

@RestController
@RequestMapping("/evaluation-schemes")
public class EvaluationSchemeController {
    
    @Autowired
    private EvaluationSchemeService evaluationSchemeService;

    // Define endpoints to interact with the EvaluationSchemeService
}