package com.nps.devassessment.QueryProcess;

import com.nps.devassessment.entity.WorkflowEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Transformer {
    public static List<WorkflowEntity> transformResultSetToList(ResultSet resultSet) throws SQLException {

        List<WorkflowEntity> workflowEntities = new ArrayList<WorkflowEntity>();

        while (resultSet.next()) {
            WorkflowEntity workflowEntity = WorkflowEntity
                    .builder()
                    .id(resultSet.getLong("ID"))
                    .workflowId(resultSet.getLong("WORKFLOW_ID"))
                    .kpfConfirmed(resultSet.getBoolean("KPF_CONFIRMED"))
                    .yjbYp(resultSet.getLong("YJB_YP_ID"))
                    .workflowState(resultSet.getString("WORKFLOW_STATE"))
                    .created(resultSet.getTimestamp("CREATED"))
                    .modified(resultSet.getTimestamp("MODIFIED"))
                    .createdBy(resultSet.getString("CREATED_BY"))
                    .modifiedBy(resultSet.getString("MODIFIED_BY"))
                    .metadata(resultSet.getString("METADATA"))
                    .process(resultSet.getString("PROCESS"))
                    .taskId(resultSet.getString("TASK_ID"))
                    .previousState(resultSet.getString("PREVIOUS_STATE"))
                    .taskStatus(resultSet.getString("TASK_STATUS"))
                    .taskMetadata(resultSet.getString("TASK_METADATA"))
                    .build();


            workflowEntities.add(workflowEntity);


        }
        return workflowEntities;
    }
}
