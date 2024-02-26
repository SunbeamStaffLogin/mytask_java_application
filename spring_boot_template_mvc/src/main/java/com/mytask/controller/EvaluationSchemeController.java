package com.mytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.entities.EvaluationSchedule;
import com.mytask.service.EvaluationSchemeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/evaluation")
public class EvaluationSchemeController {
    
    @Autowired
    private EvaluationSchemeService evaluationSchemeService;

    @PostMapping("/evaluation-schedule")
    public EvaluationSchedule insertEvaluationSchedule(@RequestBody EvaluationSchedule evaluationSchedule) {
        return evaluationSchemeService.saveEvaluationSchedule(evaluationSchedule);
    }
}