-- 变更性别

-- 个人题解
update salary
set sex=case
            when sex = 'm'
                then 'f'
            when sex = 'f'
                then 'm'
            else ''
    end;

-- 官方题解
update salary
set sex = case sex
              when 'm' then 'f'
              else 'm'
    end;