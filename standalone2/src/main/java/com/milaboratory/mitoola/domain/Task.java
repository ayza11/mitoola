package com.milaboratory.mitoola.domain;

import com.milaboratory.mitoola.api.dto.TaskStatus;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.concurrent.Immutable;
import java.time.Instant;

/**
 * @author Alexei Zakharov (ayza)
 */
@Immutable
public class Task {
    private final Long taskId;
    private final String externalTaskId;
    private final String owner;
    private final String options;
    private final TaskStatus status;
    private final Instant createdTime;
    private final Instant updatedTime;

    public Task(Long taskId, String externalTaskId, String owner, String options, TaskStatus status,
                Instant createdTime, Instant updatedTime)
    {
        this.taskId = taskId;
        this.externalTaskId = externalTaskId;
        this.owner = owner;
        this.options = options;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getExternalTaskId() {
        return externalTaskId;
    }

    public String getOwner() {
        return owner;
    }

    public String getOptions() {
        return options;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    // ---


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return new EqualsBuilder()
                .append(taskId, task.taskId)
                .append(externalTaskId, task.externalTaskId)
                .append(owner, task.owner)
                .append(options, task.options)
                .append(status, task.status)
                .append(createdTime, task.createdTime)
                .append(updatedTime, task.updatedTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(taskId)
                .append(externalTaskId)
                .append(owner)
                .append(options)
                .append(status)
                .append(createdTime)
                .append(updatedTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", externalTaskId='" + externalTaskId + '\'' +
                ", owner='" + owner + '\'' +
                ", options='" + options + '\'' +
                ", status=" + status +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
