CREATE TABLE test_plans_in_project(
    test_plan_id UUID REFERENCES test_plan(test_plan_id) ON DELETE CASCADE,
    project_id UUID REFERENCES project(project_id) ON DELETE CASCADE,
    PRIMARY KEY (test_plan_id,project_id)
);