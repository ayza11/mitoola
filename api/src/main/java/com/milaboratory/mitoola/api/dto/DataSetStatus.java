package com.milaboratory.mitoola.api.dto;

import com.milaboratory.mitoola.util.IntEnum;
import com.milaboratory.mitoola.util.IntEnumResolver;

/**
 * @author Alexei Zakharov (ayza)
 */
public enum DataSetStatus implements IntEnum {
    UNKNOWN(0),
    CREATED(1),
    UPLOADING(2),
    UPLOADED(3),
    READY_FOR_PROCESSING(4),
    PROCESSING(5),
    PROCESSED(6),
    PROCESSING_FAILED(7)
    ;

    private final int value;

    DataSetStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final IntEnumResolver<DataSetStatus> R = new IntEnumResolver<>(DataSetStatus.class);

    public static DataSetStatus fromValue(int value) {
        return R.fromValue(value);
    }

}
