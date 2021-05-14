select * from products where (title = 'ACADEMY ACADEMY') or (title = 'ACADEMY BILL');
select * from products where price = 9.99 and category = 8 order by price, category;
select * from products where category IN (8, 15);
select * from products where price BETWEEN 10 AND 20;
select * from orders where orderdate BETWEEN to_date('2004-01-05','YYYY-MM-DD') AND to_date('2004-02-05','YYYY-MM-DD');
select customerid, COUNT(customerid) as груп_по_customerid from orders group by customerid;
select customerid, orderdate, SUM(totalamount) as sum_totalamount from orders group by customerid, orderdate having SUM(totalamount) > 100;
select customers.firstname, customers.lastname, products.title, orders.orderdate 
from customers 
join orders on customers.customerid = orders.customerid
join orderlines on orderlines.orderid = orders.orderid
join products on products.prod_id = orderlines .prod_id;