package com.yx.entity.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "JOB_BACKUP")
public class JobBackup {
    private int bid;
    private int id;
    private int projectId;
    private int flowId;
    private int versionId;
    private String name;
    private String jobType;
    private String command;
    private int newVersionId;

    @Id
    @Column(name = "BID")
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_ID")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "FLOW_ID")
    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    @Basic
    @Column(name = "VERSION_ID")
    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "JOB_TYPE")
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Basic
    @Column(name = "COMMAND")
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Basic
    @Column(name = "NEW_VERSION_ID")
    public int getNewVersionId() {
        return newVersionId;
    }

    public void setNewVersionId(int newVersionId) {
        this.newVersionId = newVersionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobBackup jobBackup = (JobBackup) o;
        return bid == jobBackup.bid &&
                id == jobBackup.id &&
                projectId == jobBackup.projectId &&
                flowId == jobBackup.flowId &&
                versionId == jobBackup.versionId &&
                newVersionId == jobBackup.newVersionId &&
                Objects.equals(name, jobBackup.name) &&
                Objects.equals(jobType, jobBackup.jobType) &&
                Objects.equals(command, jobBackup.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, id, projectId, flowId, versionId, name, jobType, command, newVersionId);
    }
}
