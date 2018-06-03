package com.milaboratory.mitoola.api.dto;

import com.milaboratory.mitoola.util.IntEnum;
import com.milaboratory.mitoola.util.IntEnumResolver;

/**
 * @author Alexei Zakharov (ayza)
 */
public enum TaskStatus implements IntEnum {
    UNKNOWN(0),
    CREATED(1),
    SCHEDULED(2),
    PENDING(3),
    RUNNING(4),
    COMPLETED(5),
    FAILED(6),
    CANCELED(7)
    ;

    private int value;

    TaskStatus(int val) {
        this.value = val;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final IntEnumResolver<TaskStatus> R = new IntEnumResolver<>(TaskStatus.class);

    public static TaskStatus fromValue(int value) {
        return R.fromValue(value);
    }
}
