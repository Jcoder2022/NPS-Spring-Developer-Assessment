package com.nps.devassessment.repo;

import com.nps.devassessment.entity.WorkflowEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkflowRepo extends PagingAndSortingRepository<WorkflowEntity, Long> {


    Iterable<WorkflowEntity> findAllByYjbYpId(Long YjbYpId);


    List<WorkflowEntity> findAll();

    //Select workflows by workflow_state = a given status  (e.g. “IN PROGRESS”, “CANCELLED”, “ADMITTED”)
    //List<WorkflowEntity> findAllByWorkflowState



}
