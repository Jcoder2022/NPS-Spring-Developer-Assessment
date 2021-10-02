package com.nps.devassessment.controller;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.model.Placement;
import com.nps.devassessment.service.WorkflowRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/tests-controller")
@Slf4j
public class WorkflowController {

    @Autowired
    private WorkflowRepoService workflowRepoService;

    @PostMapping("/createWorkflow")
    public WorkflowEntity createWorkFlow(@RequestBody WorkflowEntity workflowEntity) {
        log.info("Inside Controller's createWorkflow method!");
        return  workflowRepoService.saveWorkFlowEntity(workflowEntity);
    }

    @GetMapping("/workflow/{id}")
    public Optional<WorkflowEntity> findWorkflowById(@PathVariable("id") Long id){
        log.info("inside Controller's findWorkflowById method");
        return Optional.ofNullable(workflowRepoService.findWorkflowById(id));
    }

    @GetMapping("/workflow/YjbYp/{YjbYpId}")
    public Optional<Set<WorkflowEntity>> findWorkflowByYjbYpId(@PathVariable("YjbYpId") Long YjbYpId){
        log.info("inside Controller's findWorkflowByYjbYpId method");
        return Optional.ofNullable(workflowRepoService.findWorkflowByYjbYp(YjbYpId));
    }

    @PutMapping("/updateWorkflow")
    @ResponseBody
    public WorkflowEntity updateWorkflowEntity(@RequestBody WorkflowEntity workflowEntity) {
        log.info("inside Controller's updateWorkflowEntity method");
        return workflowRepoService.saveWorkFlowEntity(workflowEntity);
    }

    //"/api/v1/placements/getplacement/{id}/ypid/{yjb_yp_id}"
    @GetMapping("${placement.endpoint.get}")
    public Placement getWorkflowWithPlacement(@PathVariable("placementId") Long placementId,@PathVariable("YjbYpId") Long YjbYpId){
        log.info("Inside getWorkflowWithPlacement of WorkflowController");
        Placement placement = workflowRepoService.getWorkflowWithPlacement(placementId,YjbYpId);

        return placement;
    }


}
