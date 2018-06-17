package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * @author Alexei Zakharov (ayza)
 */
@Deprecated
@Immutable
public class UpdateResultDto<T> {
    private final ResultDto updateResult;
    private final T object;

    @JsonCreator
    public UpdateResultDto(@JsonUnwrapped ResultDto updateResult,
                           @JsonProperty("object") @Nullable T object)
    {
        this.updateResult = updateResult;
        this.object = object;
    }

    @JsonUnwrapped
    public ResultDto getUpdateResult() {
        return updateResult;
    }

    @JsonProperty("object")
    public T getObject() {
        return object;
    }
}
