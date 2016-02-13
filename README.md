# saas-multitenancy-base

Saas DB design involves one of the following requirements depending on your application:

0. No tenant specific data isolation needs, individual client accounts need to be managed. This is typical B2C Saas.
1. Tenants can share a DB, a single DB scenario : using tenantID in database tables
2. Each tenant is provided a dedicated logical DB. 
3. One or more tenant can share a DB or have a dedicated DB, multiple DBs can be used similar to a sharding approach.


2 and 3 above, involve routing a request to correct instance of DB. This is done using tenantID or entityID which can be a part of request, both of which can encode the DB ID. And DB ID to DB instance mapping is available from a master data source. 



entityId = create_new_id_on_tenantDB(Long tenantId); 
and tenandID is created using something like following:

Instagram Sharding strategy:
http://instagram-engineering.tumblr.com/post/10853187575/sharding-ids-at-instagram
"Each of our IDs consists of:

41 bits for time in milliseconds (gives us 41 years of IDs with a custom epoch)
13 bits that represent the logical shard ID
10 bits that represent an auto-incrementing sequence, modulus 1024. This means we can generate 1024 IDs, per shard, per millisecond
"

The 13 bit logical shard ID is used as DB ID. The DB ID to JDBC URL is stored in master DB source.

This project, inserts the appropriate transactionManager based on this ID, such that the underlying code requires no changes and developers would implement assuming a single database scenario. 

Another project,  which uses Shard ID encoded in entity ID and provides implementation for @Sharding (similar to @Transactional but also looks up correct DB, uses correct transactionManager) project is here:
https://github.com/inncretech/modules

#Instructions for Initial Set up

create database name config ( or change in dev-db.properties file property : master.config.jdbcUrl)
run create_db.sql
add one entry in datasourceconfig table (this entry is required for routing datasource bean creation)
Look at insert_db.sql