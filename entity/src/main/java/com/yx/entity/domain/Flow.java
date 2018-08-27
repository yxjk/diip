package com.yx.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Flow {
    @Id
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "TRIGGER_ID")
    private Trigger trigger;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flow")
    private List<Job> jobs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flow flow = (Flow) o;
        return id == flow.id &&
                Objects.equals(project, flow.project) &&
                Objects.equals(name, flow.name) &&
                Objects.equals(trigger, flow.trigger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, project, name, trigger);
    }
}
