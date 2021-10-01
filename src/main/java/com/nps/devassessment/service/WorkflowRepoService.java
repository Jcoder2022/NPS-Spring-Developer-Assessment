package com.nps.devassessment.service;

import com.nps.devassessment.entity.WorkflowEntity;

public interface WorkflowRepoService {

    // fetch an individual workflow by its 'id'
    WorkflowEntity findWorkflowById(Long id);

    public WorkflowEntity saveWorkFlowEntity(WorkflowEntity workflowEntity);

}
