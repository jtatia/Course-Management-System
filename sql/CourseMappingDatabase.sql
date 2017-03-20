/*
-- Query: SELECT * FROM cms.course_mapping
LIMIT 0, 200

-- Date: 2017-03-19 03:02
*/
CREATE SCHEMA if  not exists `cms` ;

use cms;

drop table if exists course_mapping;

CREATE TABLE `cms`.`course_mapping` (
  `s.no` INT NOT NULL AUTO_INCREMENT,
  `course_id` VARCHAR(45) NOT NULL,
  `professor` VARCHAR(45) NOT NULL,
  `batch_id1` VARCHAR(45) NULL,
  `batch_id2` VARCHAR(45) NULL,
  `batch_id3` VARCHAR(45) NULL,
  `batch_id4` VARCHAR(45) NULL,
  `batch_id5` VARCHAR(45) NULL,
  PRIMARY KEY (`s.no`));

INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (1,'MA201','pratibhamoy_prashant','ee15b','me15b',NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (2,'MA201','ashish_prashant','cse15b','che15b','ce15b',NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (3,'CS225','jimson','cse15b',NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (4,'CS226','jimson','cse15b',NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (5,'MA101','debashree','cse16b','ee16b',NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (6,'MA101','omprakash','me16b','che16b','ce16b',NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (7,'PH101','ajay','me16b','che16b','ce16b','ee16b','cse16b');
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (8,'EE101','shiva','ee16b','che16b','ce16b',NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (9,'EE101','sanjoy','me16b','cse16b',NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (10,'ME101','koushik_vaibhav','ee16b','cse16b',NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (11,'ME101','rishiraj','me16b','che16b','ce16b',NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (12,'CS201','arijit','cse15b',NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (13,'CS203','suman','cse15b',NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (14,'CS299','arijit_samrat_somnath_jimson_asif','cse15b',NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (15,'MA102','omprakash','ee16b','me16b',NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (16,'MA102','sudhan','cse16b','che16b','ce16b',NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (17,'EE200','shiva','ee15b',NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (18,'EE201','shouvan','ee15b',NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (19,'MA231','amit',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `course_mapping` (`s.no`,`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`) VALUES (20,'HS202','nalin',NULL,NULL,NULL,NULL,NULL);
