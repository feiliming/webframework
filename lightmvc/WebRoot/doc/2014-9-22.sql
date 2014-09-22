/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.27 : Database - lightmvc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lightmvc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lightmvc`;

/*Table structure for table `mb_mbgroup` */

DROP TABLE IF EXISTS `mb_mbgroup`;

CREATE TABLE `mb_mbgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `credit` int(10) NOT NULL,
  `discount` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='会员用户组';

/*Table structure for table `mb_member` */

DROP TABLE IF EXISTS `mb_member`;

CREATE TABLE `mb_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `membercardid` varchar(16) NOT NULL,
  `realname` varchar(32) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `mobile` varchar(16) NOT NULL,
  `email` varchar(32) NOT NULL,
  `prov_id` int(10) NOT NULL,
  `prov_name` varchar(32) NOT NULL,
  `city_id` int(10) NOT NULL,
  `city_name` varchar(32) NOT NULL,
  `address` varchar(200) NOT NULL,
  `zipcode` int(7) NOT NULL,
  `cardid` varchar(18) NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `grade` tinyint(1) NOT NULL DEFAULT '1',
  `credit` int(10) NOT NULL DEFAULT '0',
  `regdateymd` date NOT NULL,
  `lastdateline` date NOT NULL,
  `mbgroup_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `membercardid` (`membercardid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='会员信息表';

/*Table structure for table `sale_category` */

DROP TABLE IF EXISTS `sale_category`;

CREATE TABLE `sale_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `pid` int(10) NOT NULL DEFAULT '0',
  `seq` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='商品分类';

/*Table structure for table `sale_goods` */

DROP TABLE IF EXISTS `sale_goods`;

CREATE TABLE `sale_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(32) NOT NULL,
  `cat_id` int(11) NOT NULL,
  `stock` float NOT NULL DEFAULT '0',
  `warn_stock` tinyint(3) NOT NULL DEFAULT '1',
  `weight` varchar(32) NOT NULL,
  `unit` varchar(32) NOT NULL,
  `out_price` decimal(8,2) NOT NULL,
  `in_price` decimal(8,2) NOT NULL,
  `market_price` decimal(8,2) NOT NULL,
  `promote_price` decimal(8,2) NOT NULL,
  `ispromote` tinyint(1) NOT NULL DEFAULT '0',
  `promote_start_date` date NOT NULL,
  `promote_end_date` date NOT NULL,
  `ismemberprice` tinyint(1) NOT NULL DEFAULT '1',
  `creatymd` date NOT NULL,
  `lastinymd` date NOT NULL,
  `goods_desc` varchar(200) NOT NULL,
  `countamount` float(10,2) unsigned NOT NULL,
  `salesamount` float(10,2) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='商品表';

/*Table structure for table `sale_log` */

DROP TABLE IF EXISTS `sale_log`;

CREATE TABLE `sale_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `user_id` int(10) NOT NULL,
  `username` varchar(32) NOT NULL,
  `dateymd` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='商品管理日志表';

/*Table structure for table `sale_purchase` */

DROP TABLE IF EXISTS `sale_purchase`;

CREATE TABLE `sale_purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) NOT NULL,
  `goods_sn` varchar(32) NOT NULL,
  `goods_name` varchar(100) NOT NULL,
  `cat_id` int(10) NOT NULL,
  `in_num` float NOT NULL,
  `out_num` float NOT NULL DEFAULT '0',
  `in_price` decimal(8,2) NOT NULL,
  `dateymd` date NOT NULL,
  `dateline` int(10) NOT NULL,
  `isdel` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='进货表';

/*Table structure for table `sale_sales` */

DROP TABLE IF EXISTS `sale_sales`;

CREATE TABLE `sale_sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) NOT NULL,
  `goods_sn` varchar(16) NOT NULL,
  `goods_name` varchar(100) NOT NULL,
  `cat_id` int(10) NOT NULL,
  `order_id` varchar(14) NOT NULL,
  `mid` int(10) NOT NULL,
  `membercardid` varchar(16) NOT NULL,
  `realname` varchar(32) NOT NULL,
  `num` float NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `out_price` decimal(8,2) NOT NULL,
  `in_price` decimal(8,2) NOT NULL,
  `dateymd` date NOT NULL,
  `dateline` int(10) NOT NULL,
  `m_discount` float(10,2) unsigned NOT NULL DEFAULT '0.00',
  `p_discount` float(10,2) unsigned NOT NULL DEFAULT '0.00',
  `refund_type` tinyint(1) NOT NULL DEFAULT '0',
  `refund_num` float NOT NULL DEFAULT '0',
  `refund_amount` decimal(8,2) NOT NULL DEFAULT '0.00',
  `sales_type` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='销售记录表';

/*Table structure for table `sys_dictionary` */

DROP TABLE IF EXISTS `sys_dictionary`;

CREATE TABLE `sys_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) NOT NULL,
  `text` varchar(64) NOT NULL,
  `dictionarytype_id` int(11) NOT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `isdefault` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='数据字典';

/*Table structure for table `sys_dictionarytype` */

DROP TABLE IF EXISTS `sys_dictionarytype`;

CREATE TABLE `sys_dictionarytype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='字典分类';

/*Table structure for table `sys_organization` */

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `code` varchar(64) NOT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `createdatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='组织机构';

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` smallint(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `resourcetype` tinyint(1) NOT NULL DEFAULT '0',
  `createdatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` smallint(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `isdefault` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `role_id` smallint(5) NOT NULL,
  `resource_id` smallint(5) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色资源';

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` smallint(5) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `sex` tinyint(1) NOT NULL DEFAULT '0',
  `age` tinyint(1) NOT NULL DEFAULT '0',
  `usertype` tinyint(1) NOT NULL DEFAULT '0',
  `isdefault` tinyint(1) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `organization_id` int(11) NOT NULL DEFAULT '0',
  `createdatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginname` (`loginname`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` smallint(5) NOT NULL,
  `role_id` smallint(5) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
