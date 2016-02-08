# saas-base

Saas DB design involves:

0. No tenant specific data isolation needs, individual client accounts need to be managed. 
1. using tenantID in tables, single DB scenario
2. Each tenant is provided a dedicated DB having same set of tables. 
3. One or more tenant can share a DB, multiple DBs can be used similar to sharding.

Essentially, 2 and 3 involve routing a request to correct DB. This is done based on tenantID which can come in each request. 
Alternatively, the entities Id can also be used. 

Something like following ensures, the DB ID used in all entities for a tenant is same as DB ID in the tenantID.
entityId = create_new_id_on_tenantDB(Long tenantId); 


From Instagram Sharding strategy:
http://instagram-engineering.tumblr.com/post/10853187575/sharding-ids-at-instagram
"Each of our IDs consists of:

41 bits for time in milliseconds (gives us 41 years of IDs with a custom epoch)
13 bits that represent the logical shard ID
10 bits that represent an auto-incrementing sequence, modulus 1024. This means we can generate 1024 IDs, per shard, per millisecond
"

The 13 bit logical shard ID is used as DB ID. 

Shard ID encoded in entity ID and @Sharding (@Transactional but also looks up correct DB, transcationManager) Annotation project is here:
https://github.com/inncretech/modules

#Instructions for Initial Set up

first create database name config ( or change in dev-db.properties file property : master.config.jdbcUrl)
run db.sql
add one entry in datasourceconfig table (this entry is required for routing datasource bean creation)
Look at test.sql

run server using command : mvn clean package -DskipTests jetty:run -Djetty.port=8080 -Dspring.profiles.active=dev -U
