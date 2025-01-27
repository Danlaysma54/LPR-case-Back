ALTER TABLE test_plan RENAME COLUMN test_plan_description to test_plan_name;
ALTER TABLE test_cases_in_test_plan RENAME COLUMN test_run_id to test_plan_id;