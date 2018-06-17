package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * @author Alexei Zakharov (ayza)
 */
@Immutable
public class ResultDto {
    private final boolean success;
    private final String message;

    @JsonCreator
    public ResultDto(@JsonProperty("success") boolean success,
                     @JsonProperty("message") @Nullable String message)
    {
        this.success = success;
        this.message = message;
    }

    @JsonProperty("success")
    public boolean getSuccess() {
        return success;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
}
