-- -----------------------------------------------------
-- Schema ssafy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
use `ssafy`;


-- -----------------------------------------------------
-- Table `ssafy`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`user` ;

CREATE TABLE `user` (
  `uid` INTEGER NOT NULL AUTO_INCREMENT,
  `nickname` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user_idx_unique_nick_email` (`nickname`, `email`)) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`survey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`survey` ;

CREATE TABLE `survey` (
  `surveyid` INTEGER NOT NULL AUTO_INCREMENT,
  `survey` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`surveyid`))
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`answer` ;

CREATE TABLE `answer` (
  `answerid` INTEGER NOT NULL AUTO_INCREMENT,
  `answer` varchar(128) DEFAULT NULL,
  `uid` INTEGER DEFAULT NULL,
  `surveyid` INTEGER DEFAULT NULL,
  PRIMARY KEY (`answerid`),
  CONSTRAINT `ans_uid`
    FOREIGN KEY (`uid`)
    REFERENCES `ssafy`.`user` (`uid`),
  CONSTRAINT `ans_surveyid`
    FOREIGN KEY (`surveyid`)
    REFERENCES `ssafy`.`survey` (`surveyid`))
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`randomnick`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`randomnick` ;

CREATE TABLE `randomnick` (
  `nickid` INTEGER NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`nickid`))
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`question` ;

CREATE TABLE `question` (
  `questionid` INTEGER NOT NULL AUTO_INCREMENT,
  `question` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`questionid`))
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`balancegame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`balancegame` ;

CREATE TABLE `balancegame` (
  `bgid` INTEGER NOT NULL AUTO_INCREMENT,
  `bgquestion1` varchar(255) DEFAULT NULL,
  `bgquestion2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bgid`))
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`liargame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`liargame` ;

CREATE TABLE `liargame` (
  `lgid` INTEGER NOT NULL AUTO_INCREMENT,
  `lgtype` varchar(128) DEFAULT NULL,
  `lgword` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`lgid`))
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`fileinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy`.`fileinfo` ;

CREATE TABLE `fileinfo` (
  `fildid` INTEGER NOT NULL AUTO_INCREMENT,
  `uid` INTEGER DEFAULT NULL,
  `save_folder` varchar(128) DEFAULT NULL,
  `original_file` varchar(128) DEFAULT NULL,
  `save_file` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`fildid`),
  CONSTRAINT `file_uid`
    FOREIGN KEY (`uid`)
    REFERENCES `ssafy`.`user` (`uid`))
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;