package com.nps.devassessment.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.service.WorkflowRepoService;
import com.nps.devassessment.setup.QueryProcess.IProcessQuery;
import com.nps.devassessment.setup.QueryProcess.ProcessQuery;
import org.hibernate.cfg.NotYetImplementedException;
//import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class SetupTests {

    private static final Logger log = LoggerFactory.getLogger(SetupTests.class);

    @Autowired
    private WorkflowRepoService workflowRepoService;
    private JacksonTester<WorkflowEntity> jsonWorkflowEntity;
    private StringBuilder builder;
    private IProcessQuery processQuery;

    @BeforeEach
    void init() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        builder = new StringBuilder();
        processQuery = new ProcessQuery();

    }


    // NOTE - This is a sample
    @Test
    public void test0_shouldProvideASampleOfAWorkingRepoCall() {

        // start test
        log.info("Starting test0 to demonstrate working repo call...");
        WorkflowEntity workflowEntity = this.workflowRepoService.findWorkflowById(66176L);

        // Assert
        Assertions.assertNotNull(workflowEntity);
        Assertions.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.  yjb_yp_id = {}, workflow_state = {}", workflowEntity.getId(), workflowEntity.getYjbYp(), workflowEntity.getWorkflowState());
        log.info("test0 complete");
    }



    @Test
    public void test1_shouldReturnWorkflowCriteriaOnWorkflowStateAsMentioned() throws SQLException {

        String query = builder.append("SELECT * FROM WORKFLOW WHERE WORKFLOW_STATE='IN PROGRESS' OR WORKFLOW_STATE='CANCELLED' OR WORKFLOW_STATE='ADMITTED'").toString();

        List<WorkflowEntity> workflowEntities = processQuery.executeQuery(query);


        log.info("Workflow - Criteria On WorkflowState - size of return list is {} ",workflowEntities.size() );
    }

    @Test
    public void test1_shouldReturnWorkflowCriteriaOnYJB_YP_IDAsMentioned() throws SQLException {

        String query =builder.append("SELECT * FROM WORKFLOW WHERE YJB_YP_ID IN (30848, 32524, 28117)").toString();

        List<WorkflowEntity> workflowEntities = processQuery.executeQuery(query);


        log.info("Workflow - Criteria On YJB_YP_ID - size of return list is {} ",workflowEntities.size() );


    }

    @Test
    public void test1_shouldReturnWorkflowCriteriaOnCreatedDateAsMentioned() throws SQLException {
        String query = builder.append("SELECT * FROM WORKFLOW WHERE CREATED > '2021-02-01'").toString();


        List<WorkflowEntity> workflowEntities = processQuery.executeQuery(query);


        log.info("Workflow - Criteria On Created Date - size of return list is {} ",workflowEntities.size() );


    }

    @Test
    public void test1_shouldReturnWorkflowCriteriaOnModifiedDateAsMentioned() throws SQLException {
        String query = builder.append("SELECT * FROM WORKFLOW WHERE MODIFIED > '2020-01-01' and MODIFIED < '2021-03-01'").toString();

        List<WorkflowEntity> workflowEntities = processQuery.executeQuery(query);

        log.info("Workflow - Criteria On Modified Date - size of return list is {} ",workflowEntities.size() );

    }


    @Test
    public void test1_shouldReturnWorkflowCriteriaOnProcessAsMentioned() throws SQLException {
        String query = builder.append("SELECT * FROM WORKFLOW WHERE PROCESS='placementProcess' and TASK_STATUS!='ADMITTED'").toString();

        List<WorkflowEntity> workflowEntities = processQuery.executeQuery(query);

        log.info("Workflow - Criteria On Process and TASK_STATUS  - size of return list is {} ",workflowEntities.size() );


    }

    @Test
    public void test1_shouldReturnWorkflowCriteriaOnCreatedByAsMentioned() throws SQLException {
        String query = builder.append("SELECT ID, YJB_YP_ID,TASK_STATUS FROM WORKFLOW WHERE CREATED_BY='lee.everitt'").toString();

        List<WorkflowEntity> workflowEntities = processQuery.executeQuery(query);

        log.info("Workflow - Criteria On CreatedBy  - size of return list is {} ",workflowEntities.size() );



    }

    @Test
    public void test1_shouldReturnWorkflowCriteriaOnProcessAsTransferPlannedAsMentioned() throws SQLException {
        String query =  builder.append("SELECT TOP 10 * FROM WORKFLOW WHERE PROCESS='transferPlanned' ORDER BY ID ASC").toString();

        List<WorkflowEntity> workflowEntities = processQuery.executeQuery(query);

        log.info("Workflow -  Top 10 Criteria On Process As TransferPlanned In ASC order  - size of return list is {} ",workflowEntities.size() );



    }




    @Test
    public void test1_shouldDemonstrateRequestedRepoQueries() {
        // implement queries as per the word document
        List<WorkflowEntity> workflowEntities = this.workflowRepoService.findAll();

        // assert that the results of each of the query calls is not null/empty
        Assertions.assertNotNull(workflowEntities,"Workflow from db is not null ");
        // write the count of each of the queries to the log
        log.info("number of Workflow entities from db are {}",workflowEntities.size());



        // Select workflows by workflow_state = a given status  (e.g. “IN PROGRESS”, “CANCELLED”, “ADMITTED”)
        List<WorkflowEntity> workflowEntitiesByStates = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getWorkflowState().equalsIgnoreCase("IN PROGRESS")
                                || workflowEntity.getWorkflowState().equalsIgnoreCase("CANCELLED")
                                || workflowEntity.getWorkflowState().equalsIgnoreCase("ADMITTED")))
                .collect(Collectors.toList());



        // Select workflows by a given list of yjb_yp_id values  (e.g. 30848, 32524, 28117)
        List<WorkflowEntity> workflowEntitiesByYJB_YP_Id = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getYjbYp()== Long.valueOf("30848")
                                || workflowEntity.getYjbYp()== Long.valueOf("30848")
                                || workflowEntity.getYjbYp()== Long.valueOf("30848")))
                .collect(Collectors.toList());




        // Select workflows by 'created' column is after a given date (e.g. 01/02/2021)


        LocalDateTime dateTime = LocalDateTime.parse("2021-02-01T12:30:30");


        List<WorkflowEntity> workflowEntitiesByYCreated = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getCreated().toLocalDateTime().isAfter(dateTime)))
                .collect(Collectors.toList());


        // Select workflows by 'modified' column is after a given date (e.g. 01/01/20) but before another given date (e.g. 01/03/2021)
        LocalDateTime beforeDateTime = LocalDateTime.parse("2021-03-01T12:30:30");
        LocalDateTime afterDateTime = LocalDateTime.parse("2020-01-01T12:30:30");

        List<WorkflowEntity> workflowEntitiesByYModified = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getModified().toLocalDateTime().isAfter(afterDateTime) &&
                                workflowEntity.getModified().toLocalDateTime().isBefore(beforeDateTime)))
                .collect(Collectors.toList());



        // Select workflows by process = a given value (e.g. “placementProcess”) AND task_status != a given value (e.g.  “ADMITTED”)

        List<WorkflowEntity> workflowEntitiesByProcess = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getProcess().equals("placementProcess") &&
                                !workflowEntity.getTaskStatus().equals("ADMITTED")))
                .collect(Collectors.toList());


        // Select id, yjb_yp_id and task_status columns for all workflows where created_by = a given value (e.g. “lee.everitt”)
        List<WorkflowEntity>  workflowEntitiesIdYjpYpIdTaskStatus = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getCreatedBy().equalsIgnoreCase("lee.everitt")))
                .collect(Collectors.toList());


        for (WorkflowEntity entitiesIdYjpYpIdTaskStatus : workflowEntitiesIdYjpYpIdTaskStatus) {
            log.info(" Id : {}, yjb_yp_id : {}, task_status: {}  \n" ,
                    entitiesIdYjpYpIdTaskStatus.getTaskId() ,
                    entitiesIdYjpYpIdTaskStatus.getYjbYp(),
                    entitiesIdYjpYpIdTaskStatus.getTaskStatus());

        }



        // Select the first 10 rows where process = a given value (e.g. “transferPlanned”).  Order the results by id in descending order
        List<WorkflowEntity> firstTenRows = workflowEntities.subList(0,10).stream()
                .filter(workflowEntity -> workflowEntity.getProcess().equalsIgnoreCase("transferPlanned"))
                .sorted(Comparator.comparingLong(WorkflowEntity::getId)).collect(Collectors.toList());

        throw new NotYetImplementedException();
    }


    @Test
    public void test2_shouldDemonstratePageableRepoCapability() {
        // Page through the entire workflow repo using a page size of 20
        // For each page: write the count of each distinct workflow_status to the log
        // Once you have paged through the entire table, write the amount of pages to the log

        throw new NotYetImplementedException();
    }
}
