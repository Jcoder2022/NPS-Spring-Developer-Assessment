package com.nps.devassessment.QueryProcess;

import com.nps.devassessment.entity.WorkflowEntity;

import java.sql.SQLException;
import java.util.List;

public interface IProcessQuery {
    public List<WorkflowEntity> executeQuery(String query) throws SQLException;
}
