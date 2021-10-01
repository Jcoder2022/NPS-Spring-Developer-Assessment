package com.nps.devassessment.service.impl;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.repo.WorkflowRepo;
import com.nps.devassessment.service.WorkflowRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @Override
    public Set<WorkflowEntity> findWorkflowByYjbYpId(Long YjbYp) {
        Set<WorkflowEntity> workflowentitiesByYjbYpId = new HashSet<WorkflowEntity>();
        this.workflowRepo.findAllByYjbYpId(YjbYp).forEach(wfp->workflowentitiesByYjbYpId.add(wfp));
        return workflowentitiesByYjbYpId;
    }
    @Override
    public List<WorkflowEntity> findAll(){

        return workflowRepo.findAll();
    }


}
