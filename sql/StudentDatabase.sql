CREATE SCHEMA if  not exists `cms` ;

use cms;

drop table if exists student;

CREATE TABLE `cms`.`student` (
  `s.no.` INT NOT NULL AUTO_INCREMENT,
  `roll_no.` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `sex` VARCHAR(1) NOT NULL,
  `age` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `batch_code` VARCHAR(45) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `security_ques` VARCHAR(100) NOT NULL,
  `answer` VARCHAR(45) NOT NULL,
  `subject1` VARCHAR(45) NULL,
  `subject2` VARCHAR(45) NULL,
  `subject3` VARCHAR(45) NULL,
  `subject4` VARCHAR(45) NULL,
  `subject5` VARCHAR(45) NULL,
  `subject6` VARCHAR(45) NULL,
  `subject7` VARCHAR(45) NULL,
  `subject8` VARCHAR(45) NULL,
  `subject9` VARCHAR(45) NULL,
  `subject10` VARCHAR(45) NULL,
  PRIMARY KEY (`s.no.`, `roll_no.`))AUTO_INCREMENT=1;
  
  ALTER TABLE `cms`.`student` 
CHANGE COLUMN `password` `password` VARCHAR(200) NOT NULL ;


INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (1,'1501CS56','Abhijit','Ember','Roy','M',20,'abhijit@pom.com','btech15','c+NcAMqzRuc3oI4HuGvd3Cn5l67cAlGwD0TeHPI0NNdXSDOaLqcepSEY1skemUQb','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (2,'1501CS101','Kisame','Shark','Hosigake','M',56,'kiss@pom.com','btech13','o3cNtjhUvktK1NqT/6XedpwYy1KbGCUdwzjt9HkrS6jX6PojN+7KDzCo2PPcfRuo','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (3,'1501EE09','Ashus','Suar','Singh','M',21,'suar@pom.com','btech13','bnRkc/+N2bBY0sO1kFNhY9vcWEKeQ2QQMbufmx1SZhCWexgN54NcdpB2f5E3JieA','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (4,'1501CH19','Reeves','Term','Kyle','M',21,'reeves@pom.com','btech12','+1mcpudCHH4ldKLK6WEGGKQPCsxPNfWd7rRW86HiYyBD8vh1p9/c7ws39zrEussC','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (5,'1501CS23','Jai','JY','Tatia','M',21,'jai@pom.com','btech12','Hm0qrpqAldENBkci8CGgvczIQqlYc6dTuBonHvEqigFv10lqHimVsd32l7o62yhx','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (6,'1501CS26','Kshitij','Jhatu','Jauhri','M',21,'kshitij@pom.com','btech12','bXs37JTaaC8Gx4ofa5qoevnIuyCMOX76MmVxfoaioGJb3+nY0gF44LPmO+c+3mJ6','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (7,'1501CS40','Sahil','Kasab','Sharma','M',21,'sahil@pom.com','btech12','zD6GA1nsV4RRBATZ9KhLZ6WOs9gSl+4agflQ801F0JCfTnu21HXMpecVaXzgusbv','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (8,'1501CS02','Angelina','Salt','Jolie','M',21,'angel@pom.com','btech12','nm5nSo0CiSUsBa18w06S34hNy+v4O6XY69xOE88b3rjmRJCHRVMuo5Oao9+FcBDD','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (9,'1501CS52','Shashwat','Sassy','Tiwari','M',19,'lauri@pom.com','btech12','fxcpiHbolVuY+5PLrOm3AMMk6blvzb/EhnGHBTIZ3HboDkPSqzJuWS/C4bAVh39x','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
INSERT INTO `student` (`s.no.`,`roll_no.`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`) VALUES (10,'1501CS43','Shivam','Dot','Tiwari','M',19,'divya@pom.com','btech12','DA9ktdeHU0h1JJkbW+OReMSEi1Q09gR64snDCEcbq4gODSw5LhIx0f90JR45MzkT','Where do you Live','Guwahati','Math','EVS','English','Algorithms','Social','History','Physics','Chemistry','Mech','SoftwareD');
