package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.concurrent.Immutable;
import javax.ws.rs.QueryParam;

/**
 * @author Alexei Zakharov (ayza)
 */
@Immutable
public class PagerDto {
    private final Long pageNumber;
    private final Long pageSize;

    @JsonCreator
    public PagerDto(
            @JsonProperty("pageNo") @QueryParam("pageNo") Long pageNumber,
            @JsonProperty("pageSize") @QueryParam("pageNumber")Long pageSize)
    {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @JsonProperty("pageNo")
    public Long getPageNumber() {
        return pageNumber;
    }

    @JsonProperty("pageSize")
    public Long getPageSize() {
        return pageSize;
    }
}
