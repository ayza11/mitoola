package com.milaboratory.mitoola.util;

/**
 * @author Alexei Zakharov (ayza)
 */
public class IntEnumResolver<T extends IntEnum> {
    private final Class<T> clazz;

    public IntEnumResolver(Class<T> clazz) {
        this.clazz = clazz;
    }

    // TODO speed up with hash map
    public T fromValue(int value) {
        for (T constant : clazz.getEnumConstants()) {
            if (constant.getValue() == value) {
                return constant;
            }
        }
        return null;
    }

}
