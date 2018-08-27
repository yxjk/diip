package com.yx.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Job {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "JOB_TYPE")
    private String jobType;
    @Column(name = "COMMAND")
    private String command;
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "FLOW_ID")
    private Flow flow;
    @ManyToOne
    @JoinColumn(name = "VERSION_ID")
    private Version version;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "DEPENDENCE", joinColumns = {@JoinColumn(name = "JOB_ID")}, inverseJoinColumns = {@JoinColumn(name = "DEPEND_JOB_ID")})
    private List<Job> depends;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RELATION", joinColumns = {@JoinColumn(name = "JOB_ID")}, inverseJoinColumns = {@JoinColumn(name = "NEXT_JOB_ID")})
    private List<Job> nexts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id &&
                Objects.equals(project, job.project) &&
                Objects.equals(flow, job.flow) &&
                Objects.equals(version, job.version) &&
                Objects.equals(name, job.name) &&
                Objects.equals(jobType, job.jobType) &&
                Objects.equals(command, job.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, project, flow, version, name, jobType, command);
    }
}
