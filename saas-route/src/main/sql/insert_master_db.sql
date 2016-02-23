use config;

insert into config.data_source_config(created_date, updated_date, db_name, db_lease_type, db_url, user_name, password, version) 
values
(now(), now(),'db1', 'SHARED','localhost:3306', 'root', 'root', 0);

insert into config.data_source_config(created_date, updated_date, db_name, db_lease_type, db_url, user_name, password, version) 
values
(now(), now(),'db2', 'DEDICATED','localhost:3306', 'root', 'root', 0);

insert into config.data_source_config(created_date, updated_date, db_name, db_lease_type, db_url, user_name, password, version) 
values
(now(), now(),'db3', 'DEDICATED','localhost:3306', 'root', 'root', 0);

create database db1;

create database db2;

create database db3;