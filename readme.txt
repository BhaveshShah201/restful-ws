Launch MySQL as Docker Container
-----------------------------------

docker run 
--detach 
--env MYSQL_ROOT_PASSWORD=P@ssw0rd 
--env MYSQL_USER=social-media-user 
--env MYSQL_PASSWORD=P@ssw0rd 
--env MYSQL_DATABASE=social-media-database 
--name mysql 
--publish 3306:3306 mysql:8-oracle

-----------------------------------
mysqlsh commands
-----------------------------------
mysqlsh
\connect social-media-user@localhost:3306
\sql
use social-media-database
select * from user_details;
select * from post;
\quit


