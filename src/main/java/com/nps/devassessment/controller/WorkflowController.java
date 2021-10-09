package com.nps.devassessment.controller;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.exception.NpsException;
import com.nps.devassessment.model.Placement;
import com.nps.devassessment.service.WorkflowRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/tests-controller")
@Slf4j
public class WorkflowController {

    @Autowired
    private WorkflowRepoService workflowRepoService;

    @PostMapping("/createWorkflow")
    public ResponseEntity<WorkflowEntity> createWorkFlow(@RequestBody WorkflowEntity workflowEntity) {
        log.info("Inside Controller's createWorkflow method!");
        workflowEntity = workflowRepoService.saveWorkFlowEntity(workflowEntity);
        log.info("Inside createWorkflow method! workflow id {} ",workflowEntity.getId());
        ResponseEntity<WorkflowEntity> responseEntity = new ResponseEntity<>(workflowEntity,HttpStatus.CREATED);
        return responseEntity;
    }




    @GetMapping("/workflow/{id}")
    public ResponseEntity<WorkflowEntity> findWorkflowById(@PathVariable("id") Long id) throws NpsException{
        log.info("inside Controller's findWorkflowById method");
        WorkflowEntity workflowEntity = null;
        try{
            workflowEntity = workflowRepoService.findWorkflowById(id);
            return new ResponseEntity<WorkflowEntity>(workflowEntity,HttpStatus.FOUND);
        }
        catch(NpsException npe) {
            return new ResponseEntity<WorkflowEntity>(workflowEntity,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/workflow/YjbYp/{YjbYpId}")
    public Optional<Set<WorkflowEntity>> findWorkflowByYjbYpId(@PathVariable("YjbYpId") Long YjbYpId){
        log.info("inside Controller's findWorkflowByYjbYpId method");
        return Optional.ofNullable(workflowRepoService.findWorkflowByYjbYp(YjbYpId));
    }

    @PutMapping("/updateWorkflow")
    @ResponseBody
    public ResponseEntity<WorkflowEntity> updateWorkflowEntity(@RequestBody WorkflowEntity workflowEntity) {
        log.info("inside Controller's updateWorkflowEntity method");

        if (workflowEntity == null || workflowEntity.getId() == null) {
            Re;

        }
        Optional<WorkflowEntity> optionalRecord = Optional.ofNullable(workflowRepoService.findWorkflowById(workflowEntity.getId()));
        if (optionalRecord.isEmpty()) {
            throw new NotFoundException("WorkflowEntity with ID " + optionalRecord.get().getId() + " does not exist.");
        }

        WorkflowEntity existedWFE = optionalRecord.get();

        existedWFE.setMetadata(workflowEntity.getMetadata());
        existedWFE.setCreated(workflowEntity.getCreated());
        existedWFE.setTaskStatus(workflowEntity.getTaskStatus());
        existedWFE.setCreatedBy(workflowEntity.getCreatedBy());
        existedWFE.setKpfConfirmed(workflowEntity.getKpfConfirmed());
        existedWFE.setWorkflowState(workflowEntity.getWorkflowState());
        existedWFE.setYjbYp(workflowEntity.getYjbYp());
        existedWFE.setModified(workflowEntity.getModified());
        existedWFE.setModifiedBy(workflowEntity.getModifiedBy());
        existedWFE.setProcess(workflowEntity.getProcess());
        existedWFE.setTaskId(workflowEntity.getTaskId());
        existedWFE.setPreviousState(workflowEntity.getPreviousState());
        existedWFE.setTaskMetadata(workflowEntity.getTaskMetadata());


        ResponseEntity<WorkflowEntity> responseEntity = new ResponseEntity<>(workflowRepoService.saveWorkFlowEntity(existedWFE),HttpStatus.CREATED);
        return responseEntity;
    }

    //"/api/v1/placements/getplacement/{id}/ypid/{yjb_yp_id}"
    @GetMapping("/api/v1/placements/getplacement/{id}/ypid/{yjb_yp_id}")
    public Placement getWorkflowWithPlacement(@PathVariable("id") Long placementId,@PathVariable("yjb_yp_id") Long YjbYpId){
        log.info("Inside getWorkflowWithPlacement of WorkflowController");
        Placement placement = workflowRepoService.getWorkflowWithPlacement(placementId,YjbYpId);

        return placement;
    }


}
