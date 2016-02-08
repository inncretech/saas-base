---DDL-----
CREATE TABLE `datasourceconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `modi_date` datetime DEFAULT NULL,
  `db_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `dbType` varchar(255) DEFAULT NULL,
  `db_url` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

CREATE TABLE `tenant` (
  `tenantId` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `modi_date` datetime DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `master_tenant_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `data_source_config_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tenantId`),
  UNIQUE KEY `UK_10wd388mxsf0uyxs14iiu94gu` (`domain`) 
)  ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

 CREATE TABLE `id_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ;












