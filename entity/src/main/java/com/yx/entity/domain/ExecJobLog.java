package com.yx.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "EXEC_JOB_LOG")
public class ExecJobLog {
    @Id
    @ManyToOne
    @JoinColumn(name = "EXEC_JOB_ID")
    private Job job;
    @Column(name = "LOG")
    private String log;
}
