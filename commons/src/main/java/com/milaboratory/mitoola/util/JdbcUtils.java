package com.milaboratory.mitoola.util;

import java.time.Instant;

/**
 * @author Alexei Zakharov (ayza)
 */
public class JdbcUtils {
    public static java.sql.Timestamp toSqlTimestamp(Instant instant) {
        return new java.sql.Timestamp(instant.toEpochMilli());
    }
}
