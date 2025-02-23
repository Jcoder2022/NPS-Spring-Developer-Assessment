package com.nps.devassessment.service;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.exception.NpsException;
import com.nps.devassessment.model.Placement;

import java.util.List;
import java.util.Set;

public interface WorkflowRepoService {

    // fetch an individual workflow by its 'id'
    WorkflowEntity findWorkflowById(Long id) throws NpsException;

    public WorkflowEntity saveWorkFlowEntity(WorkflowEntity workflowEntity);

    public Set<WorkflowEntity> findWorkflowByYjbYp(Long YjbYpId) throws NpsException;

    public List<WorkflowEntity> findAll();

   public Placement getWorkflowWithPlacement(Long placementId, Long YjbYpId);

    public List<WorkflowEntity> findWorkflowEntityByWorkFlowState(String firstState,String secState, String thirdState);

    public List<WorkflowEntity> findWorkflowEntitysByYjbJP(Long firstVal,Long secVal, Long thirdVal);
    public List<WorkflowEntity> findWorkflowEntityByCreated(String created);

    public List<WorkflowEntity> findWorkflowEntityByModifiedTimeperiod(String gt,String lt);
    public List<WorkflowEntity> findWorkflowEntityByProcessAndTaskStatus(String process,String taskStatus);
    public List<Object> findIdYjbYpIdTaskStatusCriteriaOnCreatedBy(String createdBy);

    public List<WorkflowEntity> findWorkflowEntityByProcess(String process);
}
