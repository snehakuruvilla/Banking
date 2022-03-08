INSERT INTO CUSTOMER (cust_id, customer_name, customer_surname) VALUES (111, 'TestName1','TestSurname1');    
INSERT INTO CUSTOMER (cust_id, customer_name, customer_surname) VALUES (122, 'TestName2','TestSurname2');        
INSERT INTO CUSTOMER (cust_id, customer_name, customer_surname) VALUES (133, 'TestName3','TestSurname3');
INSERT INTO CUSTOMER (cust_id, customer_name, customer_surname) VALUES (555, 'TestName5','TestSurname5');
INSERT INTO CUSTOMER (cust_id, customer_name, customer_surname) VALUES (666, 'TestName6','TestSurname6');

INSERT INTO ACCOUNT (account_id, cust_id, account_type, balance) VALUES (1,111, 'S',1000);    
INSERT INTO ACCOUNT (account_id, cust_id, account_type, balance) VALUES (2,122,'S',1050);    
INSERT INTO ACCOUNT (account_id, cust_id, account_type, balance) VALUES (3,133, 'S',2000);
INSERT INTO ACCOUNT (account_id, cust_id, account_type, balance) VALUES (4,111, 'C',100);

INSERT INTO TRANSACTION (transaction_id, cust_id, account_id, from_account, description, amount) VALUES (1000,111, 1, 'Bank','Joining Bonus',600);
INSERT INTO TRANSACTION (transaction_id, cust_id, account_id, from_account, description, amount) VALUES (1001,111, 1, 'Amazon','Refund',400);
INSERT INTO TRANSACTION (transaction_id, cust_id, account_id, from_account, description, amount) VALUES (1002,122, 2, 'Bank','Joining Bonus',1050);
INSERT INTO TRANSACTION (transaction_id, cust_id, account_id, from_account, description, amount) VALUES (1003,133, 3, 'Bank','Bonus',500);
INSERT INTO TRANSACTION (transaction_id, cust_id, account_id, from_account, description, amount) VALUES (1004,133, 3, 'Father','Gift',1500);
INSERT INTO TRANSACTION (transaction_id, cust_id, account_id, from_account, description, amount) VALUES (1005,111, 4, 'Bank','Joning Current Account Bonus',100);


