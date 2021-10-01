package com.nps.devassessment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "Workflow")
@NamedQuery(name = "Workflow.findAll", query = "SELECT w FROM WorkflowEntity w")
@Builder
@AllArgsConstructor
@Data
public class WorkflowEntity implements Serializable {

    @Id
    private Long id;

    @Column(name = "workflow_id")
    private Long workflowId;

    @Column(name = "kpf_confirmed", nullable = false)
    private Boolean kpfConfirmed = Boolean.FALSE;

    @Column(name = "yjb_yp_id", nullable = false)
    private Long yjbYp;

    @Column(name = "workflow_state")
    private String workflowState;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "process")
    private String process;

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "previous_state")
    private String previousState;

    @Column(name = "task_status")
    private String taskStatus;

    @Column(name = "task_metadata")
    private String taskMetadata;



}
