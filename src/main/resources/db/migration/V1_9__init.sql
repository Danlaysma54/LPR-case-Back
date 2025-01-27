CREATE TABLE suites_in_project(
    suite_id UUID NOT NULL REFERENCES suite(suite_id),
    project_id UUID NOT NULL REFERENCES project(project_id)
);