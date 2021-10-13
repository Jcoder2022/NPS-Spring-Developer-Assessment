package com.nps.devassessment.service.impl;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.exception.NpsException;
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
import java.util.Optional;
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
    public WorkflowEntity findWorkflowById(Long id) throws NpsException {
        Optional<WorkflowEntity> wfe = this.workflowRepo.findById(id);
        if(wfe.isPresent())
        {
            return wfe.get();
        }
        else throw new NpsException(NpsException.NotFoundException(id));
    }

    @Override
    @Transactional
    public WorkflowEntity saveWorkFlowEntity(WorkflowEntity workflowEntity){
        return  workflowRepo.save(workflowEntity);
    }

    @Override
    public Set<WorkflowEntity> findWorkflowByYjbYp(Long YjbYp) throws NpsException {
        Set<WorkflowEntity> workflowentitiesByYjbYpId = new HashSet<WorkflowEntity>();
        Iterable<WorkflowEntity> iterator =  this.workflowRepo.findAllByYjbYp(YjbYp);
        if(iterator==null)
            throw new NpsException(NpsException.NotFoundException(YjbYp));
        else
            iterator.forEach(wfe -> workflowentitiesByYjbYpId.add( wfe));
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
        Placement placement = restTemplate.getForObject("/api/v1/placements/getplacement/"+placementId + "/ypid/" +YjbYpId,Placement.class);
        return placement;
    }

    public List<WorkflowEntity> findWorkflowEntityByWorkFlowState(String firstState,String secState, String thirdState){
        return workflowRepo.findWorkflowEntityByWorkFlowState(firstState,secState,thirdState);
    }

    public List<WorkflowEntity> findWorkflowEntitysByYjbJP(Long firstVal,Long secVal, Long thirdVal){
        return workflowRepo.findWorkflowEntitysByYjbJP(firstVal,secVal,thirdVal);
    }
}
