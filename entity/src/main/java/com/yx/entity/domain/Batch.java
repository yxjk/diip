package com.yx.entity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Batch {
    private String batchId;

    @Id
    @Column(name = "BATCH_ID")
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batch batch = (Batch) o;
        return Objects.equals(batchId, batch.batchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchId);
    }
}
