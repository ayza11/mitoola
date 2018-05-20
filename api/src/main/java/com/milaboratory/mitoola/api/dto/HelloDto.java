package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alexei Zakharov (ayza)
 */
public class HelloDto {
    private final String name;
    private final int count;

    @JsonCreator
    public HelloDto(@JsonProperty("name") String name,
                    @JsonProperty("count") int count)
    {
        this.name = name;
        this.count = count;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("count")
    public int getCount() {
        return count;
    }
}
