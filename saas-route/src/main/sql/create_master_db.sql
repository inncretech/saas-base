create database config;

use config;

CREATE TABLE config.`data_source_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `db_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `db_lease_type` varchar(255) DEFAULT NULL,
  `db_url` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

CREATE TABLE config.`tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `master_tenant_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `data_source_config_id` bigint(20) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_10wd388mxsf0uyxs14iiu94gu` (`domain`) 
)  ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

CREATE TABLE config.`id_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

CREATE TABLE config.`subject_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
