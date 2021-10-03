package com.nps.devassessment.setup.QueryProcess;

import com.nps.devassessment.entity.WorkflowEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IProcessQuery {
    public List<WorkflowEntity> executeQuery(String query) throws SQLException;
}
