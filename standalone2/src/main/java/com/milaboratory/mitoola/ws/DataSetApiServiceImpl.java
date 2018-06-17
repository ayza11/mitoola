package com.milaboratory.mitoola.ws;

import com.milaboratory.mitoola.api.dto.*;
import com.milaboratory.mitoola.api.resource.DataSetApiService;
import com.milaboratory.mitoola.util.ValidateUtils;

import javax.ws.rs.BeanParam;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * TODO implement
 * @author Alexei Zakharov (ayza)
 */
public class DataSetApiServiceImpl implements DataSetApiService {

    private static final DataSetDto EXAMPLE_DATA_SET_DTO = new DataSetDto(
            1L,
            "my dataset",
            Arrays.asList("tag1", "tag2", "tag3"),
            "chucknorris",
            "https://yandex.ru/file1",
            "https://yandex.ru/file2",
            DataSetStatus.CREATED,
            null
    );

    @Override
    public DataSetDto get(long id) {
        ValidateUtils.RequestParam.notNull(id, "id");
        ValidateUtils.checkExists(id == (EXAMPLE_DATA_SET_DTO.getId()),
                String.format("Data set with id %d not found", id));
        return EXAMPLE_DATA_SET_DTO.withId(id);
    }

    @Override
    public List<DataSetDto> list(@BeanParam DataSetFilterDto filter) {
        //TODO
        return Collections.singletonList(EXAMPLE_DATA_SET_DTO);
    }

    @Override
    public DataSetDto create(DataSetDto dataSet) {
        //TODO
        return dataSet.withId(1L);
    }

    @Override
    public DataSetDto update(long id, DataSetDto dataSet) {
        ValidateUtils.RequestParam.isTrue(dataSet.getId() == id, "Data set id mismatch");
        ValidateUtils.checkExists(id == EXAMPLE_DATA_SET_DTO.getId(),
                String.format("Data set with id %d not found", id));
        return dataSet;
    }

    @Override
    public DataSetDto delete(long id) {
        ValidateUtils.checkExists(id == EXAMPLE_DATA_SET_DTO.getId(),
                String.format("Data set with id %d not found", id));
        return EXAMPLE_DATA_SET_DTO;
    }
}
