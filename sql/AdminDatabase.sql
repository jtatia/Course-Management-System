CREATE SCHEMA if  not exists `sql12168820` ;

use sql12168820;

drop table if exists admin;

CREATE TABLE `sql12168820`.`admin` (
  `s.no` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `age` INT NOT NULL,
  `sex` VARCHAR(1) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `security_ques` VARCHAR(100) NOT NULL,
  `answer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`s.no`, `username`))AUTO_INCREMENT=1;

  INSERT INTO `admin` (`s.no`,`username`,`password`,`first_name`,`middle_name`,`last_name`,`age`,`sex`,`email`,`security_ques`,`answer`) VALUES (1,'warlord','VH0SfHHQ6I9tqOlHz056ao2rp4I+BMCUT32CLNPmOip7CN0VHn70OXFH6bymB1Yj','Abhijit','Ember','Roy',19,'M','emberroy@pom.com','Where do you Live','Guwahati');
INSERT INTO `admin` (`s.no`,`username`,`password`,`first_name`,`middle_name`,`last_name`,`age`,`sex`,`email`,`security_ques`,`answer`) VALUES (2,'brisingr','/KSzksMk8J5+abgQDx/Aw3jqouHKSzURozZNVjYPNyJMWChwfholQL+O9TGRUOc+','Kshitij','EkNumber','Jauhri',19,'M','brisingr@pom.com','Where do you Live','Lucknow');
INSERT INTO `admin` (`s.no`,`username`,`password`,`first_name`,`middle_name`,`last_name`,`age`,`sex`,`email`,`security_ques`,`answer`) VALUES (3,'jthacker','/rG3Heck62f0GnYgB+BWG0HhLeF0nDOmorshBZppBQq7aKKEJwResAHaq47AcWvN','Jai','Coder','Tatia',19,'M','jtatia@pom.com','Where do you Live','Goa');
INSERT INTO `admin` (`s.no`,`username`,`password`,`first_name`,`middle_name`,`last_name`,`age`,`sex`,`email`,`security_ques`,`answer`) VALUES (4,'sasyboy','RaHyf59CuPa52j/9bCF6ERluRV6ZjSwGc4HyiPedaurGziWeCKnK/pKD+1BK/udV','Shashwat','Laudiwala','Tiwari',19,'M','sasy@pom.com','Where do you Live','Kanpur');

UPDATE `sql12168820`.`admin` SET `middle_name`='Susheel', `email`='brisingr@gmail.com' WHERE `s.no`='2' and`username`='brisingr';
UPDATE `sql12168820`.`admin` SET `middle_name`='', `email`='jtatia@gmail.com' WHERE `s.no`='3' and`username`='jthacker';
UPDATE `sql12168820`.`admin` SET `middle_name`='', `email`='sasy@gmail.com' WHERE `s.no`='4' and`username`='sasyboy';
UPDATE `sql12168820`.`admin` SET `email`='emberroy@gmail.com' WHERE `s.no`='1' and`username`='warlord';
