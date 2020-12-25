-- 查找重复的电子邮箱

-- 个人题解
select email
from person
group by email
having count(email) > 1;

-- 官方题解
select Email, count(Email) as num
from Person
group by Email;