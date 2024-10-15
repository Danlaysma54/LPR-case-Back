-- Create table "suite"
CREATE TABLE suite (
    suite_id UUID PRIMARY KEY,
    suite_name VARCHAR(128) NOT NULL,
    suite_root_id UUID NOT NULL,
    suite_description VARCHAR(1024)
);

-- Create table "test_case"
CREATE TABLE test_case (
    test_case_id UUID PRIMARY KEY,
    case_name VARCHAR(128) NOT NULL,
    suite_id UUID NOT NULL REFERENCES suite(suite_id) ON DELETE CASCADE,
    automation_status VARCHAR(128) NOT NULL,
    layer VARCHAR(128) NOT NULL
);

-- Create table "project"
CREATE TABLE project (
    project_id UUID PRIMARY KEY,
    project_name VARCHAR(128) NOT NULL,
    project_description VARCHAR(1024)
);

-- Create table "test_plan"
CREATE TABLE test_plan (
    test_plan_id UUID PRIMARY KEY,
    test_plan_description VARCHAR(1024)
);

-- Create table "test_run"
CREATE TABLE test_run (
    test_run_id UUID PRIMARY KEY,
    test_name VARCHAR(128),
    test_plan UUID REFERENCES test_plan(test_plan_id) ON DELETE SET NULL
);

-- Create table "test_cases_in_test_plan"
CREATE TABLE test_cases_in_test_plan (
    test_run_id UUID REFERENCES test_run(test_run_id) ON DELETE CASCADE,
    test_case_id UUID REFERENCES test_case(test_case_id) ON DELETE CASCADE,
    PRIMARY KEY (test_run_id, test_case_id)
);

-- Create table "user"
CREATE TABLE "user" (
    user_id UUID PRIMARY KEY,
    surname VARCHAR(128) NOT NULL,
    name VARCHAR(128) NOT NULL,
    user_password VARCHAR(128) NOT NULL
);

-- Create table "role"
CREATE TABLE role (
    role_id UUID PRIMARY KEY,
    role_name VARCHAR(128) NOT NULL
);

-- Create table "users_in_projects"
CREATE TABLE users_in_projects (
    project_id UUID REFERENCES project(project_id) ON DELETE CASCADE,
    user_id UUID REFERENCES "user"(user_id) ON DELETE CASCADE,
    role_id UUID REFERENCES role(role_id) ON DELETE SET NULL,
    PRIMARY KEY (project_id, user_id)
);

-- Create table "test_step"
CREATE TABLE test_step (
    test_step_id UUID PRIMARY KEY,
    step_description VARCHAR(128) NOT NULL,
    test_case_id UUID REFERENCES test_case(test_case_id) ON DELETE CASCADE,
    step_data VARCHAR(4096) NOT NULL,
    step_result VARCHAR(4096) NOT NULL
);

