package com.milaboratory.mitoola.repository;

import com.milaboratory.mitoola.api.dto.TaskStatus;
import com.milaboratory.mitoola.domain.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexei Zakharov (ayza)
 * TODO
 */
@Repository
public class TaskRepository {
    public List<Task> findRunningTasks() {
        //TODO
        return null;
    }

    // ---
    private static class TaskMapper implements RowMapper<Task> {
        public static String FIELDS = "task_id, external_task_id, options, owner, status, created_time, updated_time";

        @Override
        public Task mapRow(ResultSet rs, int i) throws SQLException {
            return new Task(
                    rs.getLong("task_id"),
                    rs.getString("external_task_id"),
                    rs.getString("owner"),
                    rs.getString("options"),
                    TaskStatus.fromValue(rs.getInt("status")),
                    rs.getTimestamp("created_time").toInstant(),
                    rs.getTimestamp("updated_time").toInstant());
        }
    }
}
