package com.nps.devassessment.repo;

import com.nps.devassessment.entity.WorkflowEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface WorkflowRepo extends JpaRepository<WorkflowEntity, Long> {


    List<WorkflowEntity> findByWorkflowState(String workflowState);
    List<WorkflowEntity> findByYjbYpIn(List<Long> ids);
    List<WorkflowEntity> findByCreatedAfter(Timestamp afterDate);
    List<WorkflowEntity> findByModifiedBetween(Timestamp beforeDate, Timestamp afterDate);
    List<WorkflowEntity> findByProcessAndTaskStatusNot(String process, String taskStatus);
    @Query(value="select new com.nps.devassessment.model.Workflow(w.id, w.yjbYp, w.taskStatus) from Workflow w where w.createdBy = ?1")
    List<WorkflowEntity> findIdAndYkbYpAndTaskStatusByCreatedBy(String createdBy);
    List<WorkflowEntity> findByProcess(String process, Pageable pageable);


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
    //"SELECT * FROM WORKFLOW WHERE CREATED > '2021-02-01'"
    @Query(value="SELECT * FROM WORKFLOW WHERE CREATED > ?1",nativeQuery = true)
    public List<WorkflowEntity> findWorkflowEntityByCreated(String created);


    // Select workflows by 'modified' column is after a given date (e.g. 01/01/20) but before another given date (e.g. 01/03/2021)
    //SELECT * FROM WORKFLOW WHERE MODIFIED > '2020-01-01' and MODIFIED < '2021-03-01'
    @Query(value="SELECT * FROM WORKFLOW WHERE MODIFIED > ?1 and MODIFIED < ?2",nativeQuery = true)
    public List<WorkflowEntity> findWorkflowEntityByModifiedTimeperiod(String gt,String lt);



    // Select workflows by process = a given value (e.g. “placementProcess”) AND task_status != a given value (e.g.  “ADMITTED”)
    //"SELECT * FROM WORKFLOW WHERE PROCESS='placementProcess' and TASK_STATUS!='ADMITTED'"
    @Query(value="SELECT * FROM WORKFLOW WHERE PROCESS=?1 and TASK_STATUS!=?2",nativeQuery = true)
    public List<WorkflowEntity> findWorkflowEntityByProcessAndTaskStatus(String process,String taskStatus);


    // Select id, yjb_yp_id and task_status columns for all workflows where created_by = a given value (e.g. “lee.everitt”)
    // "SELECT ID, YJB_YP_ID,TASK_STATUS FROM WORKFLOW WHERE CREATED_BY='lee.everitt'"
    @Query(value="SELECT  ID, YJB_YP_ID,TASK_STATUS  FROM WORKFLOW WHERE CREATED_BY=?1",nativeQuery = true)
    public List<Object> findIdYjbYpIdTaskStatusCriteriaOnCreatedBy(String createdBy);


    // Select the first 10 rows where process = a given value (e.g. “transferPlanned”).  Order the results by id in descending order
    // "SELECT TOP 10 * FROM WORKFLOW WHERE PROCESS='transferPlanned' ORDER BY ID ASC")
    @Query(value="SELECT TOP 10 * FROM WORKFLOW WHERE PROCESS==?1  ORDER BY ID ASC",nativeQuery = true)
    public List<WorkflowEntity> findWorkflowEntityByProcess(String process);


    // Page through the entire workflow repo using a page size of 20
    // For each page: write the count of each distinct workflow_status to the log
    // Once you have paged through the entire table, write the amount of pages to the log


    //@Query()

}
