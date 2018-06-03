package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * @author Alexei Zakharov (ayza)
 */
public class TaskDto {
    private final long taskId;
    private final String externalTaskId;
    private final String owner;
    private final TaskStatus status;
    private final Instant createdTime;

    @JsonCreator
    public TaskDto(
            @JsonProperty("task-id") long taskId,
            @JsonProperty("external-task-id") String externalTaskId,
            @JsonProperty("owner") String owner,
            @JsonProperty("status") TaskStatus status,
            @JsonProperty("created-time") Instant createdTime)
    {
        this.taskId = taskId;
        this.externalTaskId = externalTaskId;
        this.owner = owner;
        this.status = status;
        this.createdTime = createdTime;
    }

    @JsonProperty("task-id")
    public long getTaskId() {
        return taskId;
    }

    @JsonProperty("external-task-id")
    public String getExternalTaskId() {
        return externalTaskId;
    }

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("status")
    public TaskStatus getStatus() {
        return status;
    }

    @JsonProperty("created-time")
    public Instant getCreatedTime() {
        return createdTime;
    }
}
