package com.nps.devassessment.general;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.service.WorkflowRepoService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.NotYetImplementedException;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class GeneralTests {

    @Autowired
    private WorkflowRepoService workflowRepoService;
    private JacksonTester<WorkflowEntity> jsonWorkflowEntity;


    @Test
    public void test1_shouldDemonstrateFilteringInLambdas() {
        List<WorkflowEntity> workflowEntities = this.workflowRepoService.findAll();

        // Select workflows by workflow_state = a given status  (e.g. “IN PROGRESS”, “CANCELLED”, “ADMITTED”)
        List<WorkflowEntity> workflowEntitiesByStatesAndStatus = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getWorkflowState()!=null
                                && workflowEntity.getWorkflowState().equalsIgnoreCase("IN PROGRESS")
                                && workflowEntity.getTaskStatus()==null))
                .collect(Collectors.toList());

        String str =  workflowEntitiesByStatesAndStatus.stream().map(w->w.getId().toString()).collect(Collectors.joining(","));

        log.info ("select all workflows with a workflow state of \"IN PROGRESS\" = {}" , str);

        // Use the query you created during the Setup tests to select all workflows with a workflow state of "IN PROGRESS"
        // Filter the results by task_status  NULL
        // With the resulting set of workflows, concatenate all of the id values into a comma-separated string and
        //    write that string to the log

    }


    @Test
    public void test2_shouldDemonstrateIdentificationOfMinAndMaxValues() {
        // Use the query you created during the Setup Tests to select workflows by status = “placementProcess” and task_status is not “admitted”
        // Given the results of the query, identify the highest and the lowest yjb_yp_id
        // Write those two values to the log

        List<WorkflowEntity> workflowEntities = this.workflowRepoService.findAll();


        List<WorkflowEntity> workflowEntitiesByProcessWithNotAdmitted = workflowEntities.stream()
                .filter(workflowEntity ->
                        (workflowEntity.getProcess()!=null
                                && workflowEntity.getProcess().equals("placementProcess")
                                && workflowEntity.getTaskStatus()!=null
                                && !workflowEntity.getTaskStatus().equals("ADMITTED")))
                .collect(Collectors.toList());

       Long maxYjbYp= workflowEntitiesByProcessWithNotAdmitted.stream()
               .max(Comparator.comparingLong(WorkflowEntity::getYjbYp)).get().getYjbYp();

        Long minYjbYp= workflowEntitiesByProcessWithNotAdmitted.stream()
                .min(Comparator.comparingLong(WorkflowEntity::getYjbYp)).get().getYjbYp();

        log.info("maximum YjbYp is {}, minimum YjbYp is {} ", maxYjbYp,minYjbYp);
    }


    @Test
    public void test3_shouldDemonstrateModifyingAValueFromWithinALambda() {

        List<WorkflowEntity> workflowEntities = this.workflowRepoService.findAll();


        Long largestYjbYp = workflowEntities.stream()
                    .max(Comparator.comparingLong(WorkflowEntity::getYjbYp)).get().getYjbYp();

        log.info("*************** Largest yfjsp is {}",largestYjbYp);

        var ref = new Object() {
            Long lowestYjbYp = workflowEntities.stream()
                    .min(Comparator.comparingLong(WorkflowEntity::getYjbYp)).get().getYjbYp();
        };

        List<Long> YjbYpList = workflowEntities.stream()
                .map(workflowEntity -> workflowEntity.getYjbYp()).collect(Collectors.toList());


        YjbYpList.stream().forEach(YjbYp-> {
                    if (YjbYp > ref.lowestYjbYp) {
                        ref.lowestYjbYp = YjbYp;
                    }
            });
                //filter(YjbYp->(YjbYp>lowestYjbYp))

        log.info("*************** lowest value YjbYp has reacheed to max value {} and largest value is {}", ref.lowestYjbYp.longValue(),  largestYjbYp );


        // Identify the lowest yjb_yp_id in the workflow table using whatever means you deem appropriate - store in a variable
        // Using a lambda, loop through all entries in the workflow table
        // For each workflow: If the yjb_yp_id is greater than the value you have stored in the variable, update the variable with the new value
        // After you have looped through all entries in the table, outside of the lambda write the highest yjb_yp_id to the log

    }



    @BeforeEach
    void init() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());


    }


    @Test
    public void test4_shouldDemonstrateUsingParallelProcesses() throws IOException {
        // Create an empty JSON array
        // Query the workflow table for all workflows by process = “placementProcess”
        // In parallel, query the workflow table for all workflows by task_status = “ADMITTED”
        // When both parallel processes are complete, identify workflows that exist in both sets of data
        // For each workflow that exists in both sets of data, convert the workflow to a JSON representation, and add the resulting JSON object to the JSON array
        // Assert that the JSON array is not empty or null
        // Write the resulting JSON to a file called “test4.json” so that it appears in the “resources” package of the project

        JSONArray jsonArray = new JSONArray();

        List<WorkflowEntity> workflowEntities = this.workflowRepoService.findAll();
        List<WorkflowEntity> workflowEntitiesByProcessWithplacementProcess = workflowEntities.parallelStream()
                .filter(workflowEntity ->
                        (workflowEntity.getProcess()!=null
                                && workflowEntity.getProcess().equalsIgnoreCase("placementProcess")))
                .collect(Collectors.toList());
        List<WorkflowEntity> workflowEntitiesByTaskStatusAdmitted =workflowEntities
                .parallelStream()
                .filter(workflowEntity ->
                        (workflowEntity.getTaskStatus()!=null
                                && workflowEntity.getTaskStatus().equalsIgnoreCase("ADMITTED")))
                .collect(Collectors.toList());



        List<WorkflowEntity> listOneList = workflowEntitiesByProcessWithplacementProcess.stream()
                // We select any elements such that in the stream of elements from the second list
                .filter(two -> workflowEntitiesByTaskStatusAdmitted.stream()
                        // there is an element that has the same id,
                        .anyMatch(one -> one.getId()==two.getId())
                                )
                // and collect all matching elements from the first list into a new list.
                .collect(Collectors.toList());



        // We build up a result by...
        List<WorkflowEntity> result = new ArrayList<WorkflowEntity>();
        // going through each element in the first list,
        for (WorkflowEntity one : workflowEntitiesByProcessWithplacementProcess)
        {
            // going through each element in the second list,
            for (WorkflowEntity two : workflowEntitiesByTaskStatusAdmitted)
            {
                // and collecting the first list's element if it matches the second list's element.
                if (one.getId()==two.getId())
                {
                    result.add(one);
                }
            }
        }
        // We return the collected list

        log.info("***********************************************   ");
        log.info("Comparing result size with listOneList. result.size = {} and listOneList size is = {}  ", result.size(),listOneList.size());



        for (WorkflowEntity wfe: listOneList) {
            jsonArray.put(jsonWorkflowEntity.write(listOneList.get(0)).getJson());
        }
//          listOneList.stream().map(wfe -> {
//            try {
//                return jsonArray.put(jsonWorkflowEntity.write(wfe).getJson());
//            } catch (IOException e) {
//                e.printStackTrace();
//                return jsonArray;
//            }
//
//        });

        log.info("jsonArray after populated = {}",jsonArray.length());
        Assertions.assertNotNull(jsonArray.length());

        String fileLocation = new File("src\\main\\resources\\").getAbsolutePath() + "\\" + "test4.json";


        //Write JSON file
        try (FileWriter file = new FileWriter(fileLocation)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
