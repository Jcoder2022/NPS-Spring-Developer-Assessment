package com.nps.devassessment.service.impl;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.model.Placement;
import com.nps.devassessment.repo.WorkflowRepo;
import com.nps.devassessment.service.WorkflowRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class WorkflowRepoServiceImpl implements WorkflowRepoService {

    private WorkflowRepo workflowRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WorkflowRepoServiceImpl(WorkflowRepo workflowRepo) {
        this.workflowRepo = workflowRepo;
    }


    @Override
    public WorkflowEntity findWorkflowById(Long id) {
        return this.workflowRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public WorkflowEntity saveWorkFlowEntity(WorkflowEntity workflowEntity){
        return  workflowRepo.save(workflowEntity);
    }

    @Override
    public Set<WorkflowEntity> findWorkflowByYjbYp(Long YjbYp) {
        Set<WorkflowEntity> workflowentitiesByYjbYpId = new HashSet<WorkflowEntity>();
        this.workflowRepo.findAllByYjbYp(YjbYp).forEach(wfp->workflowentitiesByYjbYpId.add(wfp));
        return workflowentitiesByYjbYpId;
    }
    @Override
    public List<WorkflowEntity> findAll(){

        return workflowRepo.findAll();
    }

    @Value("${placement.endpoint.get}")
    private String placementPath;

    @Override
    public Placement getWorkflowWithPlacement(Long placementId, Long YjbYpId) {
        Placement placement = restTemplate.getForObject(placementPath,Placement.class);
        return placement;
    }


}
