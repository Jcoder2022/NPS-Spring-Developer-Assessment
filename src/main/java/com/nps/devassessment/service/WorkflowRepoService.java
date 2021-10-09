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

    public Set<WorkflowEntity> findWorkflowByYjbYp(Long YjbYpId);

    public List<WorkflowEntity> findAll();

   public Placement getWorkflowWithPlacement(Long placementId, Long YjbYpId);
}
