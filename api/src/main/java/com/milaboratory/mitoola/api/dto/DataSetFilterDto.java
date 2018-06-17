package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.ws.rs.BeanParam;
import javax.ws.rs.QueryParam;

/**
 * Example:
 *
 * {
 *     "owner": "roger",
 *     "pageNo": 2,
 *     "pageSize": 10
 * }
 *
 * @author Alexei Zakharov (ayza)
 */
@Immutable
public class DataSetFilterDto {
    private final PagerDto pager;
    private final String owner;

    @JsonCreator
    public DataSetFilterDto(@JsonProperty("owner") @QueryParam("owner") String owner,
                            @JsonUnwrapped @BeanParam @Nullable PagerDto pager)
    {
        this.pager = pager;
        this.owner = owner;
    }

    @JsonUnwrapped
    public PagerDto getPager() {
        return pager;
    }

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }
}
