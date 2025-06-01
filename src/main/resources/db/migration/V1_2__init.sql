ALTER TABLE test_case
    ADD automation_status_id UUID  REFERENCES automation_status(automation_status_id),
    ADD layer_id UUID  REFERENCES layer(layer_id),
    DROP COLUMN automation_status,
    DROP COLUMN layer;