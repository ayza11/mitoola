CREATE TABLE tasks (
  task_id BIGSERIAL NOT NULL PRIMARY KEY,
  external_task_id TEXT,
  options TEXT DEFAULT '',
  owner TEXT,
  status INT DEFAULT 0,
  created_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  updated_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);
