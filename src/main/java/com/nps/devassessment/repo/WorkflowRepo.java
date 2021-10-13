package com.nps.devassessment.repo;

import com.nps.devassessment.entity.WorkflowEntity;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface WorkflowRepo extends JpaRepository<WorkflowEntity, Long> {


    Iterable<WorkflowEntity> findAllByYjbYp(Long YjbYpId);


    List<WorkflowEntity> findAll();

    // Select workflows by workflow_state = a given status  (e.g. “IN PROGRESS”, “CANCELLED”, “ADMITTED”)
    //"SELECT * FROM WORKFLOW WHERE WORKFLOW_STATE='IN PROGRESS' OR WORKFLOW_STATE='CANCELLED' OR WORKFLOW_STATE='ADMITTED'"
    @Query(value="Select * from Workflow where workflow_state = ?1 or workflow_state = ?2 or workflow_state = ?3",nativeQuery = true)
    public List<WorkflowEntity> findWorkflowEntityByWorkFlowState(String firstState,String secState, String thirdState);


    // Select workflows by a given list of yjb_yp_id values  (e.g. 30848, 32524, 28117)
    //SELECT * FROM WORKFLOW WHERE YJB_YP_ID IN (30848, 32524, 28117)
    @Query(value="Select * from Workflow where YJB_YP_ID IN (?1, ?2, ?3)",nativeQuery = true)
    public List<WorkflowEntity> findWorkflowEntitysByYjbJP(Long firstVal,Long secVal, Long thirdVal);


    // Select workflows by 'created' column is after a given date (e.g. 01/02/2021)

    // Select workflows by 'modified' column is after a given date (e.g. 01/01/20) but before another given date (e.g. 01/03/2021)

    // Select workflows by process = a given value (e.g. “placementProcess”) AND task_status != a given value (e.g.  “ADMITTED”)

    // Select id, yjb_yp_id and task_status columns for all workflows where created_by = a given value (e.g. “lee.everitt”)

    // Select the first 10 rows where process = a given value (e.g. “transferPlanned”).  Order the results by id in descending order

    // Page through the entire workflow repo using a page size of 20
    // For each page: write the count of each distinct workflow_status to the log
    // Once you have paged through the entire table, write the amount of pages to the log


    //Select workflows by workflow_state = a given status  (e.g. “IN PROGRESS”, “CANCELLED”, “ADMITTED”)
    //List<WorkflowEntity> findAllByWorkflowState




}
