-- 有趣的电影

-- 个人题解
select *
from cinema
where id mod 2 = 1
  and description != 'boring'
order by rating desc;

-- 官方题解
select *
from cinema
where mod(id, 2) = 1
  and description != 'boring'
order by rating desc
;