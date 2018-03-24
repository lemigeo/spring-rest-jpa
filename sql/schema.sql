CREATE DATABASE `rest` /*!40100 DEFAULT CHARACTER SET utf8 */

CREATE DATABASE `rest_log` /*!40100 DEFAULT CHARACTER SET utf8 */

USE rest;
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `acctno` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `is_block` bit(1) NOT NULL DEFAULT b'0',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`acctno`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8

USE rest_log;
DROP TABLE IF EXISTS `account_log`;
CREATE TABLE `account_log` (
  `logid` bigint(20) NOT NULL AUTO_INCREMENT,
  `acctno` bigint(20) NOT NULL,
  `is_block` bit(1) NOT NULL,
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8`