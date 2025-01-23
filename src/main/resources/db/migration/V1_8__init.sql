ALTER TABLE suite ADD created_at timestamp;
ALTER TABLE suite
   ALTER created_at SET DEFAULT NOW();
ALTER TABLE test_case ADD created_at timestamp;
ALTER TABLE test_case
   ALTER created_at SET DEFAULT NOW();