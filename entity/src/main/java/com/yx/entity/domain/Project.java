package com.yx.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Project {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    private List<Flow> flows;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
