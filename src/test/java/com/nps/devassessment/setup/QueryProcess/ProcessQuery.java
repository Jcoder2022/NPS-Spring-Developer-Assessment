package com.nps.devassessment.setup.QueryProcess;

import com.nps.devassessment.entity.WorkflowEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProcessQuery implements IProcessQuery {

    public List<WorkflowEntity> executeQuery(String query) throws SQLException {

        List<WorkflowEntity> workflowEntities =  new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            ResultSet resultSet = statement.executeQuery(query);

            workflowEntities = Transformer.transformResultSetToList(resultSet);

        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
        return  workflowEntities;
    }

}
