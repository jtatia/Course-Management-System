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
  
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`) VALUES ('1', '1501CS12', 'Ashutosh', 'Drolia', 'M', '18', 'daksh@gmail.com', 'cse15b', 'java', 'What is your favourite book?', 'Harry PotterCS110', 'EE101', 'CS234');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `middle_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`, `subject4`) VALUES ('2', '1501CS26', 'Kshitij', 'Susheel', 'Jauhri', 'M', '19', 'ksj@gmail.com', 'cse15b', 'java', 'What is your favourite book?', 'Harry Potter', 'CS204', 'CS232', 'CS113', 'EE201');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `middle_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`, `subject4`) VALUES ('3', '1501EE03', 'Abhishek', 'Kumar', 'Singh', 'M', '20', 'aks@gmail.com', 'ee15b', 'java', 'What is your favourite book?', 'Harry Potter', 'EE232', 'MA201', 'CS234', 'EE101');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`, `subject4`, `subject5`) VALUES ('4', '1401CS22', 'Arpita', 'Jain', 'F', '20', 'arpita@gmail.com', 'cse14b', 'java', 'What is your favourite book?', 'Harry Potter', 'EE101', 'MA201', 'CS113', 'CS234', 'EE232');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`, `subject4`, `subject5`) VALUES ('5', '1321CS34', 'Sakshi', 'Tiwari', 'F', '24', 'stiwari@gmail.com', 'cse13m', 'java', 'What is your favourite book?', 'Harry Potter', 'EE432', 'EE223', 'CS342', 'MA231', 'MA234');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`) VALUES ('6', '1501CS23', 'Jai', 'Tatia', 'M', '18', 'jai@gmail.com', 'cse15b', 'java', 'What is your favourite book?', 'Harry Potter', 'CS204', 'CS234', 'MA201');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`) VALUES ('7', '1501CH03', 'Saurabh', 'Gulati', 'M', '20', 'gulati@gmail.com', 'ch15b', 'java', 'What is your favourite book?', 'Harry Potter', 'CH223', 'MA201', '');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`) VALUES ('8', '1301EE26', 'Priyanka', 'F', '22', 'priyanka@gmail.com', 'ee13b', 'java', 'What is your favourite book?', 'Harry Potter', 'EE401', 'EE402', 'CS223');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`, `subject4`) VALUES ('9', '1501CS52', 'Shashwat', 'Tiwar', 'M', '20', 'shashwat@gmail.com', 'cse15b', 'java', 'What is your favourite book?', 'Harry Potter', 'CS204', 'CS232', 'CS113', 'EE201');
INSERT INTO `cms`.`student` (`s.no.`, `roll_no.`, `first_name`, `last_name`, `sex`, `age`, `email`, `batch_code`, `password`, `security_ques`, `answer`, `subject1`, `subject2`, `subject3`, `subject4`) VALUES ('10', '1501CS56', 'Abhijit', 'Roy', 'M', '19', 'abhijit@gmail.com', 'cse15b', 'java', 'What is your favourite book?', 'Harry Potter', 'CS204', 'CS232', 'CS113', 'EE201');

UPDATE `cms`.`student` SET `subject3`=NULL WHERE `s.no.`='7' and`roll_no.`='1501CH03';
UPDATE `cms`.`student` SET `last_name`='Tiwari' WHERE `s.no.`='9' and`roll_no.`='1501CS52';
UPDATE `cms`.`student` SET `answer`='Harry Potter', `subject3`='CS110' WHERE `s.no.`='1' and`roll_no.`='1501CS12';
