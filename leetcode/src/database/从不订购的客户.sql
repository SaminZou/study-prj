-- 从不订购的客户

-- 个人题解
select a.Name Customers
from Customers a
         left join Orders b
                   on a.Id = b.CustomerId
where b.CustomerId is null

-- 官方题解

select customers.name as 'Customers'
from customers
where customers.id not in
      (select customerid
       from orders);