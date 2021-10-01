package com.nps.devassessment.service.impl;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.repo.WorkflowRepo;
import com.nps.devassessment.service.WorkflowRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WorkflowRepoServiceImpl implements WorkflowRepoService {

    private WorkflowRepo workflowRepo;

    @Autowired
    WorkflowRepoServiceImpl(WorkflowRepo workflowRepo) {
        this.workflowRepo = workflowRepo;
    }


    @Override
    public WorkflowEntity findWorkflowById(Long id) {
        return this.workflowRepo.findById(id).orElse(null);
    }

    @Override
    public WorkflowEntity saveWorkFlowEntity(WorkflowEntity workflowEntity){
        return  workflowRepo.save(workflowEntity);
    }
}
