package com.yx.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "EXEC_JOB")
public class ExecJob implements Serializable {
    @Id
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "BATCH_ID")
    private Batch batch;
    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "FLOW_ID")
    private Flow flow;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "START_TIME")
    private Timestamp startTime;
    @Column(name = "END_TIME")
    private Timestamp endTime;
    @Column(name = "INPUT_PARAM")
    private String inputParam;
    @Column(name = "OUTPUT_PARAM")
    private String outputParam;
    @Column(name = "CREATE_TIME")
    private Timestamp createTime;
    @Column(name = "EXECUTOR")
    private String executor;
}
