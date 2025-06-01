CREATE TABLE suite (
    suite_id UUID PRIMARY KEY,
    suite_name VARCHAR(128) NOT NULL,
    suite_root_id UUID NOT NULL
);


CREATE TABLE test_case (
    test_case_id UUID  PRIMARY KEY,
    test_case_name VARCHAR(128) NOT NULL,
    suite_id UUID NOT NULL REFERENCES suite(suite_id) ON DELETE CASCADE,
    automation_status VARCHAR(128) NOT NULL,
    layer VARCHAR(128) NOT NULL
);


CREATE TABLE project (
    project_id UUID  PRIMARY KEY,
    project_name VARCHAR(128) NOT NULL,
    project_description VARCHAR(1024)
);


CREATE TABLE test_plan (
    test_plan_id UUID PRIMARY KEY,
    test_plan_name VARCHAR(1024)
);


CREATE TABLE test_run (
    test_run_id UUID  PRIMARY KEY,
    test_name VARCHAR(128),
    test_plan_id UUID REFERENCES test_plan(test_plan_id) ON DELETE SET NULL
);

CREATE TABLE test_cases_in_test_plan (
    test_plan_id UUID REFERENCES test_plan(test_plan_id) ON DELETE CASCADE,
    test_case_id UUID REFERENCES test_case(test_case_id) ON DELETE CASCADE,
    PRIMARY KEY (test_plan_id, test_case_id)
);

CREATE TABLE role (
    role_id UUID PRIMARY KEY,
    role_name VARCHAR(128) NOT NULL
);
CREATE TABLE users (
  userID UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE users_in_projects (
    project_id UUID REFERENCES project(project_id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(userID) ON DELETE CASCADE,
    PRIMARY KEY (project_id, user_id)
);


CREATE TABLE test_step (
    test_step_id UUID PRIMARY KEY,
    step_description VARCHAR(128) NOT NULL,
    test_case_id UUID REFERENCES test_case(test_case_id) ON DELETE CASCADE,
    step_data VARCHAR(4096) NOT NULL,
    step_result VARCHAR(4096) NOT NULL
);

