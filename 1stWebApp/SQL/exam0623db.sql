/* 
	EXAM0623DB 
*/

SET FOREIGN_KEY_CHECKS=0;

-- ==================================================================
-- Database Schema & Controlled Set of Terms
-- ==================================================================

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `ID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL,
  `USERPASS` varchar(50) NOT NULL,
  `ROLE_ID` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `TITLE` varchar(256) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `STATUS_ID` smallint(5) unsigned NOT NULL,
  `DATE_UPDATED` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `STATUS_ID` (`STATUS_ID`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`STATUS_ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records - Part 1
-- ----------------------------

INSERT INTO `role` VALUES ('1', 'ADMINISTRATOR');
INSERT INTO `role` VALUES ('2', 'REGULAR USER');

INSERT INTO `status` VALUES ('1', 'ASSIGNED');
INSERT INTO `status` VALUES ('2', 'PENDING');
INSERT INTO `status` VALUES ('3', 'COMPLETED');

-- ==================================================================
-- Sample Data
-- ==================================================================

-- ----------------------------
-- Records - Part 2
-- ----------------------------

INSERT INTO `user` VALUES ('2', 'u1', 'p@ss1', '1');
INSERT INTO `user` VALUES ('3', 'u2', 'p2', '2');
INSERT INTO `user` VALUES ('4', 'u3', 'p@sswrd3!', '2');

INSERT INTO `task` VALUES ('1', '3', 'Task No. 1', 'This is a short description of a Task.', '1', '2023-06-25 14:18:00');
INSERT INTO `task` VALUES ('2', '3', 'Task No. 2', 'This is a long description of a Task. For this purpose we have repeated the same message several times… This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. This is a long description of a Task. Characters are more than 500 !', '2', '2023-06-25 14:20:44');
INSERT INTO `task` VALUES ('3', '4', 'Task No. 3', 'This is just another short description of a Task. ', '3', '2023-06-25 14:21:17');

-- ==================================================================
