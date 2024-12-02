ALTER TABLE suite
    ALTER suite_id SET DEFAULT gen_random_uuid();

ALTER TABLE test_case
    ALTER test_case_id SET DEFAULT gen_random_uuid();

ALTER TABLE project
    ALTER project_id SET DEFAULT gen_random_uuid();

ALTER TABLE test_plan
    ALTER test_plan_id SET DEFAULT gen_random_uuid();

ALTER TABLE test_run
    ALTER test_run_id SET DEFAULT  gen_random_uuid();

ALTER TABLE user_case
    ALTER user_id SET DEFAULT  gen_random_uuid();

ALTER TABLE role
    ALTER role_id SET DEFAULT  gen_random_uuid();

ALTER TABLE test_step
   ALTER test_step_id SET DEFAULT gen_random_uuid();

CREATE TABLE layer(
    layer_id UUID default (gen_random_uuid()) PRIMARY KEY,
    layer_name VARCHAR(4096) NOT NULL
);
CREATE TABLE automation_status (
    automation_status_id UUID default (gen_random_uuid()) PRIMARY KEY,
    automation_name VARCHAR(4096) NOT NULL
);
