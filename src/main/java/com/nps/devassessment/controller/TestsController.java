package com.nps.devassessment.controller;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.service.WorkflowRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tests-controller")
@Slf4j
public class TestsController {

    @Autowired
    private WorkflowRepoService workflowRepoService;

    @PostMapping("/createWorkflow")
    public WorkflowEntity createWorkFlow(@RequestBody WorkflowEntity workflowEntity) {
        log.info("Inside Controller createWorkflow method!");
        return  workflowRepoService.saveWorkFlowEntity(workflowEntity);
    }

    @GetMapping()

}
