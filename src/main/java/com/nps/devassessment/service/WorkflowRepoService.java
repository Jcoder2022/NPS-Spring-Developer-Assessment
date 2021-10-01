package com.nps.devassessment.service;

import com.nps.devassessment.entity.WorkflowEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WorkflowRepoService {

    // fetch an individual workflow by its 'id'
    WorkflowEntity findWorkflowById(Long id);

    public WorkflowEntity saveWorkFlowEntity(WorkflowEntity workflowEntity);

    public Set<WorkflowEntity> findWorkflowByYjbYp(Long YjbYpId);

    public List<WorkflowEntity> findAll();
}
