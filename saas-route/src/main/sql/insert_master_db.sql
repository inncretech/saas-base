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

insert into subject_area(created_date, updated_date, version, name, description) 
values
(now(), now(), 0, 'Applications','Applications');

insert into subject_area(created_date, updated_date, version, name, description) 
values
(now(), now(), 0, 'Organization','Organization');

insert into subject_area(created_date, updated_date, version, name, description) 
values
(now(), now(), 0, 'Programs And Projects','Programs And Projects');

insert into subject_area(created_date, updated_date, version, name, description) 
values
(now(), now(), 0, 'Requirements','Requirements');

insert into subject_area(created_date, updated_date, version, name, description) 
values
(now(), now(), 0, 'Testing','Testing');

insert into subject_area(created_date, updated_date, version, name, description) 
values
(now(), now(), 0, 'Defects','Defects');
