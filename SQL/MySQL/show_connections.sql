SELECT @@max_connections AS '最大连接数', (SELECT VARIABLE_VALUE
                                           FROM performance_schema.global_status
                                           WHERE VARIABLE_NAME = 'Max_used_connections') AS '历史最大连接数', (SELECT VARIABLE_VALUE
                                                                                                               FROM performance_schema.global_status
                                                                                                               WHERE VARIABLE_NAME = 'Threads_connected') AS '当前连接数';