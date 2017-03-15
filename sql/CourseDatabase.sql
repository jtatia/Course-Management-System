CREATE SCHEMA if  not exists `cms` ;

use cms;

drop table if exists course;

CREATE TABLE `cms`.`course` (
  `s.no` INT NOT NULL AUTO_INCREMENT,
  `course_id` VARCHAR(45) NOT NULL,
  `course_name` VARCHAR(45) NOT NULL,
  `course_info` LONGTEXT NULL,
  PRIMARY KEY (`s.no`, `course_id`));
  
INSERT INTO `course` (`s.no`,`course_id`,`course_name`,`course_info`) VALUES (1,'CS201','Algorithms','Dummy content for algo course');
INSERT INTO `course` (`s.no`,`course_id`,`course_name`,`course_info`) VALUES (2,'CS225','Switching Theory','Dummy Content for switching theory course');
INSERT INTO `course` (`s.no`,`course_id`,`course_name`,`course_info`) VALUES (3,'CS226','Switching THeory Lab','Dummy content for switching theory lab');