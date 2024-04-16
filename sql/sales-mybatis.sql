use sales; 
show tables;

desc customers; 
select * from customers;

desc orders;
select count(*) from orders; 
select * from orders LIMIT 5; 
ALTER TABLE orders RENAME COLUMN orderNumber TO ordernumber; 
ALTER TABLE orders RENAME COLUMN orderDate TO orderdate; 
ALTER TABLE orders RENAME COLUMN requiredDate TO requireddate; 
ALTER TABLE orders RENAME COLUMN shippeddate TO shippeddate; 
ALTER TABLE orders RENAME COLUMN customerNumber TO customernumber; 
--  
SELECT 
  TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME
FROM
  INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
  REFERENCED_TABLE_SCHEMA = 'sales' AND
  REFERENCED_TABLE_NAME = 'orders' AND
  REFERENCED_COLUMN_NAME = 'ordernumber'; 
-----------------
CREATE TABLE bkorder AS SELECT * FROM orders;
ALTER TABLE bkorder ADD PRIMARY KEY(orderNumber); 

ALTER TABLE bkorder RENAME COLUMN  orderNumber TO ordernumber; 
ALTER TABLE bkorder RENAME COLUMN  orderDate TO orderdate; 
ALTER TABLE bkorder RENAME COLUMN  requiredDate TO requireddate; 
ALTER TABLE bkorder RENAME COLUMN  shippedDate TO shippeddate; 
ALTER TABLE bkorder RENAME COLUMN  customerNumber TO customernumber;  
---
desc bkorder;
drop TABLE bkorder;
CREATE TABLE bkorder(
      id bigint NOT NULL AUTO_INCREMENT,
      ordernumber int NOT NULL, 
      orderdate DATE NOT NULL,
      requireddate DATE NOT NULL,
      shippeddate DATE NOT NULL,
      status VARCHAR(50) NOT NULL, 
      comments VARCHAR(200),
      customernumber int NOT NULL,  
      PRIMARY KEY (id) 
	  ); 
ALTER TABLE bkorder AUTO_INCREMENT=10001;
-- ALTER TABLE bkorder ADD PRIMARY KEY(orderNumber); 
CREATE INDEX onindex ON bkorder (ordernumber);
CREATE INDEX cnindex ON bkorder (customernumber); 
insert into bkorder (ordernumber, orderdate, requireddate, shippeddate, status, comments, customernumber)  
SELECT ordernumber, orderdate, requireddate, shippeddate, status, comments, customernumber FROM orders LIMIT 30; 
-- 
select * from bkorder LIMIT 5; 

SELECT 
		id, 
		ordernumber,
		DATE_FORMAT(orderdate, "%Y-%m-%d") as orderdate,
		DATE_FORMAT(requireddate, "%Y-%m-%d") as requireddate, 
		DATE_FORMAT(shippeddate, "%Y-%m-%d") as shippeddate, 
		status,
		comments,
		customernumber 
FROM bkorder 
WHERE orderdate BETWEEN '2003-01-01' AND '2003-03-31'; 
-------------------- 
-- STR_TO_DATE("2017,8,19 10,30,10", "%Y,%m,%d %h,%i,%s");
insert into orders values (20100, STR_TO_DATE("2009-10-19", "%Y-%m-%d"), STR_TO_DATE("2009-10-31", "%Y-%m-%d"), STR_TO_DATE("2009-10-21", "%Y-%m-%d"), "shipped", "out for delivery", 103) ;
select count(*) from orders; 
--------------------- 
desc payments; 

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` int(7)  NOT NULL AUTO_INCREMENT, 
  `customernumber` int(11) NOT NULL,
  `checknumber` varchar(50) NOT NULL,
  `paymentdate` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`, `customernumber`,`checknumber`) 
  -- CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`customerNumber`) REFERENCES `customers` (`customerNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

desc payment; 
select * from payment; 
select count(*) from payment; 
-- SELECT TOP 30 * FROM  orders; 

delete from payment where customernumber = 103; 
insert into payment ( customernumber, checknumber, paymentdate, amount ) 
WITH p AS ( 
        SELECT * FROM  payments where ( customerNumber > 200 and customerNumber < 300 ) 
        -- UNION ALL 
        -- SELECT 2, 'Right Handed Screwdriver', 22.75 FROM dual UNION ALL 
  )
SELECT * FROM p;

select * from book; 
CREATE TABLE book(
      id int NOT NULL AUTO_INCREMENT,
      title VARCHAR(50) NOT NULL,
	  author VARCHAR(50) NOT NULL,
      publishdate DATE,
      PRIMARY KEY (id) 
	  ); 

insert into book values (1,'Peace and War', 'Joe Haldeman', CURRENT_DATE) ;
insert into book values (2,'J2EE Step by Step', 'Alan Z', CURRENT_DATE) ;
insert into book values (3,'Cat Ellie', 'Diana', CURRENT_DATE) ;

desc book; 
select * from book; 
      
ALTER TABLE orders DROP COLUMN customer_number;
ALTER TABLE orders DROP COLUMN check_number;

-- table employee for demo 
create table employee
(
    id int not null primary key auto_increment,
    fullname varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50) not null,
    hobbies varchar(50) not null,
    country varchar(50) not null,
    address varchar(50) not null
);

-- springmybatis 
create table employee
(
    id int not null primary key auto_increment,
    fullname varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50) not null,
    hobbies varchar(50) not null,
    country varchar(50) not null,
    address varchar(50) not null
);

ALTER TABLE employee AUTO_INCREMENT=101;
desc  employee; 
select * from employee; 
insert into employee values (101, 'AlanZ', 'alanzhu@gmail.com', 'Male', 'Sports,Cooking,Reading', 'China', '665 Kennedy Rd');
delete from employee where id=101; 





