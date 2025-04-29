create table step_status(
step_status_id UUID DEFAULT gen_random_uuid()
step_status_name VARCHAR(128) NOT NULL
)



create table test_case_status_in_test_run (
 test_case_status_in_test_run UUID DEFAULT gen_random_uuid() PRIMARY KEY,
 test_caseId UUID REFERENCES test_case(test_case_id)
 test_runId UUID REFERENCES test_run(test_run_id)
 step_statusId UUID REFERENCES step_status(step_status_id)
);

insert into step_status(step_status_name) values("PASSED")
insert into step_status(step_status_name) values("FAILED")
insert into step_status(step_status_name) values("SKIPPED")