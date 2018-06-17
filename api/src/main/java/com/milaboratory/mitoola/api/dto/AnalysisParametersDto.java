package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author Alexei Zakharov (ayza)
 */
public class AnalysisParametersDto {
    private final String parametersJson;

    @JsonCreator
    public AnalysisParametersDto(@JsonProperty("parameters") String parametersJson) {
        this.parametersJson = parametersJson;
    }

    @JsonProperty("parameters")
    public String getParametersJson() {
        return parametersJson;
    }

}
