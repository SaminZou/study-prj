-- test data
INSERT INTO job.job (action_code,create_time,cron,description,name,param_json,process_time,update_time,app_code) VALUES
('biz1Job','2024-03-27 12:27:19.218996','0 0/1 * * * ?','业务1定时任务','biz1Job','','2024-04-02 10:59:00',NULL,'job-worker-a'),
('biz2Job','2024-03-27 12:27:19.218996','0 0/1 * * * ?','业务2定时任务','biz2Job','{"param1":"biz-val","param2":1,"param3":true}','2024-04-02 10:59:00',NULL,'job-worker-b');