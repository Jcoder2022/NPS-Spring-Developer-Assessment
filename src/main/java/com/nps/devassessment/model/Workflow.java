package com.nps.devassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Workflow {
    private Long id;
    private Long yjbYp;
    private String taskStatus;
}
