package com.milaboratory.mitoola.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.concurrent.Immutable;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexei Zakharov (ayza)
 */
@Immutable
public class DataSetDto {
    private final Long id;
    private final String name;
    private final List<String> tags;
    private final String owner;
    private final String file1Link;
    private final String file2Link;
    private final AnalysisParametersDto analysisParameters;
    private final DataSetStatus status;

    @JsonCreator
    public DataSetDto(@JsonProperty("id") Long id,
                      @JsonProperty("name") String name,
                      @JsonProperty("tags") List<String> tags,
                      @JsonProperty("owner") String owner,
                      @JsonProperty("file1Link") String file1Link,
                      @JsonProperty("file2Link") String file2Link,
                      @JsonProperty("status") DataSetStatus status,
                      @JsonProperty("analysisParameters") AnalysisParametersDto analysisParameters)
    {
        this.id = id;
        this.name = name;
        this.tags = Collections.unmodifiableList(tags);
        this.owner = owner;
        this.file1Link = file1Link;
        this.file2Link = file2Link;
        this.status = status;
        this.analysisParameters = analysisParameters;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("file1Link")
    public String getFile1Link() {
        return file1Link;
    }

    @JsonProperty("file2Link")
    public String getFile2Link() {
        return file2Link;
    }

    @JsonProperty("status")
    public DataSetStatus getStatus() {
        return status;
    }

    @JsonProperty("analysisParameters")
    public AnalysisParametersDto getAnalysisParameters() {
        return analysisParameters;
    }

    // ---

    public DataSetDto withId(Long newId) {
        return new DataSetDto(newId, name, tags, owner, file1Link, file2Link, status, analysisParameters);
    }
}
