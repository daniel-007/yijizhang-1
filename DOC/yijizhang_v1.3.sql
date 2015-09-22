-- MySQL Workbench Synchronization
-- Generated: 2015-09-22 14:31
-- Model: Database design for yijizhang
-- Version: 1.0
-- Project: yijizhang
-- Author: Sam,John,Joey,Jiarui

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `yijizhang`.`account_book` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`account_subject` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`dict_type` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '创建时间' ;

ALTER TABLE `yijizhang`.`dict_value` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`account_subject_template` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`voucher` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`period` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '' ;

ALTER TABLE `yijizhang`.`voucher_detail` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`company_common_value` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`voucher_template` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '修改时间' ;

ALTER TABLE `yijizhang`.`voucher_template_detail` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '' ;

ALTER TABLE `yijizhang`.`subject_length` 
CHANGE COLUMN `modify_time` `modify_time` TIMESTAMP NOT NULL DEFAULT "2015-01-01 00:00:00" COMMENT '' ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
