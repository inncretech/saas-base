# appanalytix-backend

1. first create database name config  ( or change  in dev-db.properties file property : master.config.jdbcUrl)
2. run db.sql 
3. add one entry in datasourceconfig table (this entry is required for routing datasource bean creation)

Look at test.sql

4. run server using command : mvn clean package -DskipTests jetty:run -Djetty.port=8080 -Dspring.profiles.active=dev -U





