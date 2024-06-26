-- 查询是否开启函数创建，OFF 为关闭，ON 为开启
SHOW VARIABLES LIKE 'log_bin_trust_function_creators';
-- 开启函数创建
SET GLOBAL log_bin_trust_function_creators=1;

-- 获取随机字符串
CREATE FUNCTION RAND_STRING(n INT)
    RETURNS VARCHAR(255)
BEGIN
	DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(255) DEFAULT '';
	DECLARE i INT DEFAULT 0;
	WHILE i < n DO
		SET return_str =CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
		SET i = i + 1;
END WHILE;
RETURN return_str;
END

-- 获取随机整数
CREATE FUNCTION RAND_NUM (from_num INT ,to_num INT) RETURNS INT(11)
BEGIN
 DECLARE i INT DEFAULT 0;
 SET i = FLOOR(from_num +RAND()*(to_num -from_num+1));
RETURN i;
END

-- 获取随机名称
CREATE FUNCTION RAND_NAME() RETURNS VARCHAR(255)
BEGIN
    DECLARE first_names VARCHAR(255) DEFAULT 'John,Mary,Michael,Sophia,James,Emma,William,Olivia';
    DECLARE last_names VARCHAR(255) DEFAULT 'Smith,Johnson,Williams,Jones,Brown,Taylor,Miller,Wilson';
    DECLARE first_name VARCHAR(255);
    DECLARE last_name VARCHAR(255);
    SET first_name = SUBSTRING_INDEX(SUBSTRING_INDEX(first_names, ',', FLOOR(1 + RAND() * (LENGTH(first_names) - 1))), ',', -1);
    SET last_name = SUBSTRING_INDEX(SUBSTRING_INDEX(last_names, ',', FLOOR(1 + RAND() * (LENGTH(last_names) - 1))), ',', -1);
RETURN CONCAT(first_name, ' ', last_name, ' ', UNIX_TIMESTAMP());
END

-- 删除函数
DROP FUNCTION RAND_NAME;

-- 获取随机手机号
CREATE FUNCTION RAND_MOBILE() RETURNS VARCHAR(11)
BEGIN
    DECLARE prefix VARCHAR(1) DEFAULT '1';
    DECLARE suffix VARCHAR(10);
    SET suffix = LPAD(FLOOR(RAND() * 10000000000), 10, '0');
RETURN CONCAT(prefix, suffix);
END

-- 获取随机 EMAIL
CREATE FUNCTION RAND_EMAIL(prefix_length INT) RETURNS VARCHAR(255)
BEGIN
    DECLARE domain VARCHAR(255) DEFAULT 'example.com';
    DECLARE prefix VARCHAR(255);
    SET prefix = CONCAT(SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                        SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), prefix_length - 1));
RETURN CONCAT(prefix, '@', domain);
END

-- 获取随机 IP
CREATE FUNCTION RAND_IP_ADDRESS() RETURNS VARCHAR(20)
BEGIN
    DECLARE ip_address VARCHAR(20);
    SET ip_address = CONCAT(FLOOR(RAND() * 256), '.', FLOOR(RAND() * 256), '.', FLOOR(RAND() * 256), '.', FLOOR(RAND() * 256));
RETURN ip_address;
END

-- 创建存储过程用于大批量插入测试数据
CREATE DEFINER=`root`@`%` PROCEDURE `INSERT_USER`(max_num INT)
BEGIN
	DECLARE i INT DEFAULT 0;
	# 把autocommit设置成 0
	SET autocommit = 0;
	REPEAT
SET i = i + 1;
INSERT INTO user (`name`, sex, mobile) VALUES (RAND_NAME(), RAND_NUM(0,1), RAND_MOBILE());
UNTIL i = max_num
END REPEAT;
COMMIT;
END

-- 删除存储过程
DROP PROCEDURE INSERT_USER;

-- 调用存储过程，插入 10 万用户
CALL INSERT_USER(100000);

TRUNCATE `user`;
ALTER TABLE `user` AUTO_INCREMENT = 0;