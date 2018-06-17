package com.milaboratory.mitoola.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.concurrent.Immutable;
import java.time.Instant;

/**
 * DTO with error info for all non-200 HTTP codes
 * @author Alexei Zakharov (ayza)
 */
@Immutable
public class ErrorResponseDto {
    private final boolean success;
    private final int status;
    private final String error;
    private final String message;
    private final Instant timestamp;

    @JsonCreator
    public ErrorResponseDto(
            @JsonProperty("success") boolean success,
            @JsonProperty("status") int status,
            @JsonProperty("error") String error,
            @JsonProperty("message") String message,
            @JsonProperty("timestamp") Instant timestamp) {
        this.success = success;
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @JsonProperty("error")
    public String getError() {
        return error;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("timestamp")
    public Instant getTimestamp() {
        return timestamp;
    }
}
