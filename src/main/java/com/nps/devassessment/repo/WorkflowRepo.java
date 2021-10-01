package com.nps.devassessment.repo;

import com.nps.devassessment.entity.WorkflowEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepo extends PagingAndSortingRepository<WorkflowEntity, Long> {


    Iterable<WorkflowEntity> findAllByYjbYpId(Long YjbYpId);
}
