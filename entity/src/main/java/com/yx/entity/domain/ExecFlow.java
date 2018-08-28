package com.yx.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "EXEC_FLOW")
public class ExecFlow implements Serializable {
    @Id
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "BATCH_ID")
    private Batch batch;
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
}
