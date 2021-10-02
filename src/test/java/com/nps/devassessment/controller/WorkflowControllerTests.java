package com.nps.devassessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.service.WorkflowRepoService;
import org.hibernate.cfg.NotYetImplementedException;
//import org.junit.Before;
//import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@SpringBootTest
public class WorkflowControllerTests {

    private static final Logger log = LoggerFactory.getLogger(WorkflowControllerTests.class);

    MockMvc mockMvc;


    @Autowired
    private WorkflowController workflowController;

    @MockBean
    private WorkflowRepoService workflowRepoService;


    ObjectMapper mapper = new ObjectMapper();


    // This object will be magically initialized by the initFields method below.
    private JacksonTester<WorkflowEntity> jsonWorkflowEntity;

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll- executes once before all test methods in this class");
    }
    @BeforeEach
    void init() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.
                standaloneSetup(workflowController).
                build();
    }

    @Test
    public void test1_shouldTestEndpointToRetrieveWorkflowById() throws Exception {

        WorkflowEntity workflowEntity = WorkflowEntity
                .builder()
                .id(Long.valueOf(1234))
                .workflowId(Long.valueOf(123123))
                .kpfConfirmed(true)
                .yjbYp(Long.valueOf(11232))
                .workflowState("IN PROGRESS")
                .created(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .modified(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .createdBy("katherine.simmons")
                .modifiedBy("katherine.simmons")
                .metadata(null)
                .process("placementProcess")
                .taskId("5f1b3c77-f5fb-4a78-9d16-d4ffd5b4facc")
                .previousState("STARTED")
                .taskStatus(null)
                .taskMetadata(null).build();

        // given
        given(workflowRepoService.findWorkflowById(Long.valueOf(1234)))
                .willReturn(workflowEntity);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/api/v1/tests-controller/workflow/1234")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        Assertions.assertEquals(HttpStatus.OK.value(),response.getStatus());
        Assertions.assertEquals(jsonWorkflowEntity.write(workflowEntity).getJson(),response.getContentAsString());




        // Create and test a controller GET endpoint to retrieve an entry from the workflow table
        // As a parameter, the Controller should receive Integer value (representing the 'id' of the workflow)
        // As a response, the Controller should return a JSON entity representing the workflow
        // This test should contain two calls to the Controller endpoint - one that returns a workflow, and one where no
        //    workflow matching the required id has been located
        // Each of these two calls should return an apppropriate HTTP Status in accordance with REST best practices
        // Assert that the appropriate responses have been received from the endpoint


    }




    @Test
    public void test2_shouldTestEndpointToRetrieveWorkflowsByYjbYId() throws Exception {


        WorkflowEntity workflowEntity = WorkflowEntity
                .builder()
                .id(Long.valueOf(1234))
                .workflowId(Long.valueOf(123123))
                .kpfConfirmed(true)
                .yjbYp(Long.valueOf(11232))
                .workflowState("IN PROGRESS")
                .created(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .modified(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .createdBy("katherine.simmons")
                .modifiedBy("katherine.simmons")
                .metadata(null)
                .process("placementProcess")
                .taskId("5f1b3c77-f5fb-4a78-9d16-d4ffd5b4facc")
                .previousState("STARTED")
                .taskStatus(null)
                .taskMetadata(null).build();

        Set<WorkflowEntity> s = new HashSet<WorkflowEntity>();
        s.add(workflowEntity);

        // given
        given(workflowRepoService.findWorkflowByYjbYp(Long.valueOf(11232)))
                .willReturn(s);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/api/v1/tests-controller/workflow/YjbYp/11232")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        Assertions.assertEquals(HttpStatus.OK.value(),response.getStatus());
        //Assertions.assertEquals(jsonWorkflowEntity.write(s.).getJson(),response.getContentAsString());



        // Create and test a controller GET endpoint to retrieve a set of entries from the workflow table
        // As a parameter, the Controller should receive a Long value (representing the 'yjb_yp_id' of the workflows)
        // As a response, the Controller should return a JSON entity containing a list of workflows matching the yjb_yp_id
        // This test should contain two calls to the Controller endpoint - one that returns a set of workflows, and one where no
        //    workflows matching the required yjb_yp_id have been located
        // Each of these two calls should return an apppropriate HTTP Status in accordance with REST best practices
        // Assert that the appropriate responses have been received from the endpoint


    }


    @Test
    public void test3_shouldTestEndpointToCreateANewWorkflow() throws Exception {
        // given
        WorkflowEntity workflowEntity = WorkflowEntity
                .builder()
                .id(1234L)
                .workflowId(Long.valueOf(123123))
                .kpfConfirmed(true)
                .yjbYp(Long.valueOf(11232))
                .workflowState("IN PROGRESS")
                .created(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .modified(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .createdBy("katherine.simmons")
                .modifiedBy("katherine.simmons")
                .metadata(null)
                .process("placementProcess")
                .taskId("5f1b3c77-f5fb-4a78-9d16-d4ffd5b4facc")
                .previousState("STARTED")
                .taskStatus(null)
                .taskMetadata(null).build();

        given(workflowRepoService.saveWorkFlowEntity(WorkflowEntity
                .builder()
                .id(null)
                .workflowId(Long.valueOf(123123))
                .kpfConfirmed(true)
                .yjbYp(Long.valueOf(11232))
                .workflowState("IN PROGRESS")
                .created(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .modified(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .createdBy("katherine.simmons")
                .modifiedBy("katherine.simmons")
                .metadata(null)
                .process("placementProcess")
                .taskId("5f1b3c77-f5fb-4a78-9d16-d4ffd5b4facc")
                .previousState("STARTED")
                .taskStatus(null)
                .taskMetadata(null).build()))
                .willReturn(workflowEntity);


        String json = mapper.writeValueAsString(WorkflowEntity
                .builder()
                .id(null)
                .workflowId(Long.valueOf(123123))
                .kpfConfirmed(true)
                .yjbYp(Long.valueOf(11232))
                .workflowState("IN PROGRESS")
                .created(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .modified(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .createdBy("katherine.simmons")
                .modifiedBy("katherine.simmons")
                .metadata(null)
                .process("placementProcess")
                .taskId("5f1b3c77-f5fb-4a78-9d16-d4ffd5b4facc")
                .previousState("STARTED")
                .taskStatus(null)
                .taskMetadata(null).build());

        //when
        MockHttpServletResponse response =  mockMvc
                .perform(
                        post("/api/v1/tests-controller/createWorkflow")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // then

        String content = response.getContentAsString();
        WorkflowEntity wfe = jsonWorkflowEntity.parseObject(content);
        Assertions.assertNotNull(wfe.getId());
        Assertions.assertEquals(HttpStatus.CREATED.value(),response.getStatus());




        // Create and test a controller POST endpoint to create a new entry within the workflow table
        // The Controller should receive a JSON payload that represents a workflow entity (the 'id' column
        //    will have to be null or not present as the workflow has not yet been written to the table)
        // The endpoint should create a new entry in the workflow table
        // The endpoint should return a JSON representation of the newly created workflow - complete with it's id
        // Assert that the appropriate response have been received from the endpoint
        // Assert that the response body contains a workflow object, and that the id column is populated


    }

    @Test
    public void test1_shouldTestEndpointToRetrieveWorkflowByIdWhenDoesNotExists() throws Exception {
        // given
        given(workflowRepoService.findWorkflowById(Long.valueOf(2)))
                .willThrow(new Exception("Does not exist"));

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/api/v1/tests-controller/workflow/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
        Assertions.assertEquals(true,response.getContentAsString().isEmpty());
    }



    @Test
    public void test4_shouldTestEndpointToUpdateExistingWorkflow() throws Exception {

        WorkflowEntity existedWorkflowEntity = WorkflowEntity
                .builder()
                .id(Long.valueOf(1234))
                .workflowId(Long.valueOf(123123))
                .kpfConfirmed(true)
                .yjbYp(Long.valueOf(11232))
                .workflowState("IN PROGRESS")
                .created(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .modified(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                .createdBy("katherine.simmons")
                .modifiedBy("katherine.simmons")
                .metadata(null)
                .process("placementProcess")
                .taskId("5f1b3c77-f5fb-4a78-9d16-d4ffd5b4facc")
                .previousState("STARTED")
                .taskStatus(null)
                .taskMetadata(null).build();

        // given
        given(workflowRepoService.findWorkflowById(Long.valueOf(1234)))
                .willReturn(existedWorkflowEntity);



        // when
        MockHttpServletResponse responseForExistedWF = mockMvc.perform(
                        get("/api/v1/tests-controller/workflow/1234")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        String content = responseForExistedWF.getContentAsString();

        existedWorkflowEntity = jsonWorkflowEntity.parseObject(content);

        // when
        MockHttpServletResponse response = mockMvc
                .perform(
                        put("/api/v1/tests-controller/updateWorkflow")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        jsonWorkflowEntity.write( WorkflowEntity
                                                .builder()
                                                .id(Long.valueOf(1234))
                                                .workflowId(Long.valueOf(123123))
                                                .kpfConfirmed(true)
                                                .yjbYp(Long.valueOf(11232))
                                                .workflowState("TASK COMPLETED")
                                                .created(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                                                .modified(Timestamp.valueOf("2021-01-07 09:53:55.261"))
                                                .createdBy("rabia.rabia")
                                                .modifiedBy("rabia.rabia")
                                                .metadata(null)
                                                .process("placementProcess")
                                                .taskId("5f1b3c77-f5fb-4a78-9d16-d4ffd5b4facc")
                                                .previousState("STARTED")
                                                .taskStatus(null)
                                                .taskMetadata(null).build()).getJson()
                                )).andReturn().getResponse();
        content = response.getContentAsString();

        WorkflowEntity updatedWorkflowEntity = jsonWorkflowEntity.parseObject(content);

        // then
        Assertions.assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        Assertions.assertEquals(true,updatedWorkflowEntity instanceof WorkflowEntity );
        Assertions.assertNotEquals(updatedWorkflowEntity.getWorkflowState(),existedWorkflowEntity.getWorkflowState());



        // Create and test a controller PUT endpoint to an existing entry within the workflow table
        // As a parameter, the Controller should receive a JSON payload that represents an existing workflow entity
        //    where one or more of the columns has changed
        // Before the unit test calls the controller, it should retrieve the existing workflow, so that it may compare
        //    the existing workflow entity against the modified version
        // The endpoint should update the appropriate entry in the workflow table
        // The endpoint should return a JSON representation of the newly updated workflow
        // Assert that the appropriate response have been received from the endpoint
        // Assert that the response body contains a workflow object
        // Assert that the workflow details in the response correctly demonstrate that the workflow has been updated


    }


    @Test
    public void test5_shouldTestPlacementFacade() throws Exception {
        // This tests requires you to MOCK/STUB a call to another REST endpoint in a different microservice
        // Create and test a controller GET endpoint in *this* microservice
        // The endoint in *this* microservice should receive an Integer parameter that allows the controller to retrieve
        //     an existing workflow by its id  (as per test 1)
        // Once the controller in *this* microservice has retrieved a workflow, you must demonstrate your understanding of
        //     the 'Facade Pattern' to make a call to a different microservice (the 'placements' microservice)
        // The placements microservice isn't reachable from this test project - so you must MOCK the call to the placement
        //     microservice, and provide a MOCK Response that contains a dummy placement object
        // The url for the 'placements' microservice is already in the application.properties file
        // The call to the placements microservice needs to supply the 'id' of the workflow, and the yjb_yp_id of the workflow
        // The placements microservice needs to return a com.nps.devassessment.model.placement object (already created)
        // Assert that an appropriate response has been received from the facade endpoint
        // Assert that the response body contains a 'placement' object
        // Assert that the 'placement' object id and yjb_yp_id match the id and yjb_yp_id of the workflow

        throw new NotYetImplementedException();
    }


    @Test
    public void test6_shouldDemonstrateRollbackOfModificationsDueToError() throws Exception {
        // This test requires you to demonstrate how a set of transations can be rolled back when an exception occurs
        // Create and test a controller GET endpoint.  The endpoint requires no parameters
        // The purpose of the endpoint is to locate all workflows with a 'process' of 'transferPlanned' - for all identified
        //    workflows, the endpoint should try to set the 'metadata' field to the text "PROCESSED"
        // Create a new class that extends Exception.  This class should be a new type of exception named "NpsDemoException"
        // Maintain a counter of the records being updated.  When this counter get to > 10 your code should throw a new
        //    NpsDemoException
        // Your code/service that is processing the updates should have been annotated correctly to roll back the modifications
        //    that your code had made thus far when an NpsDemoException is experienced
        // Find a way to Assert that no changes have been to the data

        throw new NotYetImplementedException();
    }

    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }

}
