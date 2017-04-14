CREATE SCHEMA if  not exists `cms` ;

use cms;

drop table if exists professor;


CREATE TABLE `cms`.`professor` (
  `s.no` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(1) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `security_ques` VARCHAR(45) NOT NULL,
  `answer` VARCHAR(45) NOT NULL,
  `course_id1` VARCHAR(45) NOT NULL,
  `course_id2` VARCHAR(45) NOT NULL,
  `course_id3` VARCHAR(45) NOT NULL,
  `course_id4` VARCHAR(45) NOT NULL,
  `course_id5` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`s.no`, `user_name`));

INSERT INTO `professor` (`s.no`,`user_name`,`first_name`,`middle_name`,`last_name`,`email`,`sex`,`password`,`security_ques`,`answer`,`course_id1`,`course_id2`,`course_id3`,`course_id4`,`course_id5`) VALUES (1,'samrat','Samrat','','Mondal','samrat@iitp.ac.in','M','O6ER7YxGXiLBkABwPcu+U5VFsE5rHBxe71O8WLYkbRDjh7gX2xvnIcjcnp5XChEB','Where do you Live','Guwahati','CS225','CS221','CS201','CS203','CS229');
INSERT INTO `professor` (`s.no`,`user_name`,`first_name`,`middle_name`,`last_name`,`email`,`sex`,`password`,`security_ques`,`answer`,`course_id1`,`course_id2`,`course_id3`,`course_id4`,`course_id5`) VALUES (2,'arijit','Arijit','','Mondal','arijit@iitp.ac.in','M','4YJVDEJkTYZdeG0kD37pviV+/U0gcQQOWkdZTumqHW0Fz/K1f5kjiaFfUQeIfrAX','Where do you Live','Kolkata','CS229','CS2301','CS525','CS233','CS299');
INSERT INTO `professor` (`s.no`,`user_name`,`first_name`,`middle_name`,`last_name`,`email`,`sex`,`password`,`security_ques`,`answer`,`course_id1`,`course_id2`,`course_id3`,`course_id4`,`course_id5`) VALUES (3,'jimson','Jimson','','Mathew','jimson@iitp.ac.in','M','1VQMs+GVlewgDIpXU2Wsw10e0PaowqjCe3wSeAOQfJzkMHFdKG2fK5eYZswSBUGL','Where do you Live','Bristol','CS401','CS101','CS525','CS243','CS201');
INSERT INTO `professor` (`s.no`,`user_name`,`first_name`,`middle_name`,`last_name`,`email`,`sex`,`password`,`security_ques`,`answer`,`course_id1`,`course_id2`,`course_id3`,`course_id4`,`course_id5`) VALUES (4,'sriparna','Sriparna','','Saha','sriparna@iitp.ac.in','F','jzd/iMQCKfXFOr9scmn5K4QCPJe1SwT0E6BTxvSfEE3HMv+6rcxQqSLvq19BeYqk','Where do you Live','Bristol','CS401','CS101','CS501','CS343','CS251');
ALTER TABLE `cms`.`professor` 
DROP COLUMN `course_id5`,
DROP COLUMN `course_id4`,
DROP COLUMN `course_id3`,
DROP COLUMN `course_id2`,
CHANGE COLUMN `course_id1` `course_ids` LONGTEXT NOT NULL ;
UPDATE `cms`.`professor` SET `course_ids`='CS229_CS552_CS110_CS444' WHERE `s.no`='1' and`user_name`='samrat';
UPDATE `cms`.`professor` SET `course_ids`='CS202_CS333' WHERE `s.no`='2' and`user_name`='arijit';
UPDATE `cms`.`professor` SET `course_ids`='CS225_CS226' WHERE `s.no`='3' and`user_name`='jimson';
UPDATE `cms`.`professor` SET `course_ids`='CS400' WHERE `s.no`='4' and`user_name`='sriparna';
ALTER TABLE `cms`.`professor` 
CHANGE COLUMN `course_ids` `course_ids` LONGTEXT NULL ;
