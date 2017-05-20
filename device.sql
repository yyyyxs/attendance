/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : device

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-20 10:16:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(50) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `deviceName` varchar(50) DEFAULT NULL,
  `deviceType` varchar(50) DEFAULT '0',
  `brand` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `buyTime` date DEFAULT NULL,
  `buyYear` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT '0',
  `deviceUser` varchar(20) DEFAULT NULL,
  `status` varchar(15) DEFAULT '0',
  `CPU` varchar(100) DEFAULT NULL,
  `internalMemory` varchar(100) DEFAULT NULL,
  `graphicsCard` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `otherInfo` varchar(100) DEFAULT NULL,
  `serialNumber` varchar(50) DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT '0',
  `codeUrl` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_info
-- ----------------------------
INSERT INTO `device_info` VALUES ('138', '1fa260effe8c4c06848ee305a2f2692e', '0', '超级巨无霸', '0', '超级巨无霸', '10000.00', '2017-05-18', '2017', '0', '项滔', '0', '100', '100', '100', '111', '520bb', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\1fa260effe8c4c06848ee305a2f2692e.png');
INSERT INTO `device_info` VALUES ('139', 'f3d5483802564f2480e2b954b454b3c8', '65', '猪皮鱿鱼', '0', '猪皮鱿鱼', '1123.00', '2017-05-01', '2017', '0', '张祖文', '0', '113213', '213123', '123123', '12312', 'sda', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\f3d5483802564f2480e2b954b454b3c8.png');
INSERT INTO `device_info` VALUES ('140', '73daaf2a6aa046048640268556a08e02', '0', '123123', '1', '123123', '1231.00', '2017-05-03', '2017', '0', '项滔', '0', '12312', '1231', '123', '1231', '123123', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\73daaf2a6aa046048640268556a08e02.png');
INSERT INTO `device_info` VALUES ('141', '7290e69496e548ffbcd39062c7af2d3d', '0', '超级旋风', '1', '超级旋风', '213.00', '2017-05-18', '2017', '0', '项滔', '0', '124', '12412', '124124', '124142', '124124', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\7290e69496e548ffbcd39062c7af2d3d.png');
INSERT INTO `device_info` VALUES ('142', '90c7cf40cb194853bb489c48f4d8d3f7', '0', '231231', '1', '12312', '124124.00', '2017-05-03', '2017', '0', 'xt', '0', '12412', '12412', '142', '12414', '12312', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\90c7cf40cb194853bb489c48f4d8d3f7.png');
INSERT INTO `device_info` VALUES ('143', 'c9c71d492a604a96a9ecca6f4fe038b5', '66', '1112213123', '0', '124113', '12312312.00', '2017-05-18', '2017', '0', 'xt', '0', '124124', '123123', '124214', '12414', null, '0', null);
INSERT INTO `device_info` VALUES ('144', '719db795d0bd45aaa6f2844660ed081b', '0', '无敌鸡排', '1', '无敌鸡排', '2344.00', '2017-05-18', '2017', '0', 'xt', '0', '124', '1241', '124124', '124', '123123', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\719db795d0bd45aaa6f2844660ed081b.png');
INSERT INTO `device_info` VALUES ('145', '08a3a049c3ac4e5d8b3ee3ce54c0b8c6', '0', '大鸡排', '0', '22', '123123.00', '2017-05-19', '2017', '0', '', '0', '123', '123123', '123', '414', '2qwe', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\08a3a049c3ac4e5d8b3ee3ce54c0b8c6.png');
INSERT INTO `device_info` VALUES ('146', '323a62bd48dc428e96647579356e9d73', '0', '123', '0', '123', '123.00', '2017-05-16', '2017', '0', '', '0', '13', '132', '123', '123', '123', '0', 'E:\\jmhz-device\\target\\device\\assets\\erweima\\323a62bd48dc428e96647579356e9d73.png');

-- ----------------------------
-- Table structure for faultapply_info
-- ----------------------------
DROP TABLE IF EXISTS `faultapply_info`;
CREATE TABLE `faultapply_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT '0',
  `deviceName` varchar(50) DEFAULT NULL,
  `deviceType` varchar(50) DEFAULT NULL,
  `deviceUser` varchar(50) DEFAULT NULL,
  `applytime` date DEFAULT NULL,
  `faultDescribe` varchar(100) DEFAULT NULL,
  `status` varchar(15) DEFAULT '0',
  `approve` varchar(15) DEFAULT '0',
  `approveRemark` varchar(100) DEFAULT NULL,
  `logmark` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fapplytoodev` (`deviceId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of faultapply_info
-- ----------------------------
INSERT INTO `faultapply_info` VALUES ('1', '131', '61', '林炜的电脑', '1', '项滔', '2017-05-13', '太垃圾了\n', '0', '1', '', '0');
INSERT INTO `faultapply_info` VALUES ('2', '143', '66', '1112213123', '1', 'xt', '2017-05-19', 'sfdsd fsdf', '0', '1', '', '1');

-- ----------------------------
-- Table structure for faultrepair_info
-- ----------------------------
DROP TABLE IF EXISTS `faultrepair_info`;
CREATE TABLE `faultrepair_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applyId` int(11) DEFAULT NULL,
  `deviceName` varchar(50) DEFAULT NULL,
  `deviceUser` varchar(15) DEFAULT NULL,
  `deviceType` varchar(50) DEFAULT NULL,
  `dealStatus` varchar(20) DEFAULT '0',
  `cost` decimal(10,2) DEFAULT NULL,
  `repairpart` varchar(100) DEFAULT NULL,
  `repairResult` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reptoapp` (`applyId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of faultrepair_info
-- ----------------------------
INSERT INTO `faultrepair_info` VALUES ('1', '2', '1112213123', 'xt', '1', '2', '12313.00', '123123', '12312');

-- ----------------------------
-- Table structure for manager_info
-- ----------------------------
DROP TABLE IF EXISTS `manager_info`;
CREATE TABLE `manager_info` (
  `managerId` int(15) NOT NULL,
  `managerName` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager_info
-- ----------------------------

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` varchar(20) DEFAULT NULL,
  `studentName` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `Grade` int(11) DEFAULT NULL,
  `teacherName` varchar(20) DEFAULT NULL,
  `deviceId` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stutodev` (`deviceId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_export
-- ----------------------------
DROP TABLE IF EXISTS `sys_export`;
CREATE TABLE `sys_export` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(64) DEFAULT NULL,
  `town` varchar(64) DEFAULT NULL,
  `exporttime` datetime NOT NULL,
  `spendtime` int(11) DEFAULT NULL,
  `exportcontent` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `filepath` varchar(500) DEFAULT NULL,
  `filesize` varchar(10) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '导出的excel所属的类型：诉求、农户等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_export
-- ----------------------------

-- ----------------------------
-- Table structure for sys_loginlog
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginlog`;
CREATE TABLE `sys_loginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `ipinfo` varchar(50) DEFAULT NULL,
  `useragent` varchar(255) DEFAULT NULL,
  `browser` varchar(50) DEFAULT NULL,
  `osinfo` varchar(255) DEFAULT NULL,
  `logintime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4293 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_loginlog
-- ----------------------------
INSERT INTO `sys_loginlog` VALUES ('4178', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月03日 19时57分30秒');
INSERT INTO `sys_loginlog` VALUES ('4179', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月03日 19时57分43秒');
INSERT INTO `sys_loginlog` VALUES ('4180', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月03日 19时57分43秒');
INSERT INTO `sys_loginlog` VALUES ('4181', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月03日 19时57分44秒');
INSERT INTO `sys_loginlog` VALUES ('4182', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月03日 19时58分13秒');
INSERT INTO `sys_loginlog` VALUES ('4194', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时31分56秒');
INSERT INTO `sys_loginlog` VALUES ('4195', 'fjmjuhqc', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时32分20秒');
INSERT INTO `sys_loginlog` VALUES ('4196', 'fjmjuhqc', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时32分25秒');
INSERT INTO `sys_loginlog` VALUES ('4197', 'fjmjuhqc', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时32分26秒');
INSERT INTO `sys_loginlog` VALUES ('4198', 'fjmjuhqc', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时32分50秒');
INSERT INTO `sys_loginlog` VALUES ('4199', 'fjmjuhqc', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时32分56秒');
INSERT INTO `sys_loginlog` VALUES ('4200', 'fjmjuhqc', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时33分27秒');
INSERT INTO `sys_loginlog` VALUES ('4201', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年04月11日 21时33分09秒');
INSERT INTO `sys_loginlog` VALUES ('4202', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月11日 19时19分07秒');
INSERT INTO `sys_loginlog` VALUES ('4203', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时03分31秒');
INSERT INTO `sys_loginlog` VALUES ('4204', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时04分02秒');
INSERT INTO `sys_loginlog` VALUES ('4205', 'test1', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时09分38秒');
INSERT INTO `sys_loginlog` VALUES ('4206', 'test1', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时09分40秒');
INSERT INTO `sys_loginlog` VALUES ('4207', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时17分55秒');
INSERT INTO `sys_loginlog` VALUES ('4208', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时20分00秒');
INSERT INTO `sys_loginlog` VALUES ('4209', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时30分08秒');
INSERT INTO `sys_loginlog` VALUES ('4210', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月13日 09时38分34秒');
INSERT INTO `sys_loginlog` VALUES ('4211', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 20时21分14秒');
INSERT INTO `sys_loginlog` VALUES ('4212', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 20时40分21秒');
INSERT INTO `sys_loginlog` VALUES ('4213', 'jmhz', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 20时45分23秒');
INSERT INTO `sys_loginlog` VALUES ('4214', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 20时50分41秒');
INSERT INTO `sys_loginlog` VALUES ('4215', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 20时56分27秒');
INSERT INTO `sys_loginlog` VALUES ('4216', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时17分48秒');
INSERT INTO `sys_loginlog` VALUES ('4217', 'zzw', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时18分36秒');
INSERT INTO `sys_loginlog` VALUES ('4218', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时26分12秒');
INSERT INTO `sys_loginlog` VALUES ('4219', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时29分41秒');
INSERT INTO `sys_loginlog` VALUES ('4220', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时30分57秒');
INSERT INTO `sys_loginlog` VALUES ('4221', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时32分24秒');
INSERT INTO `sys_loginlog` VALUES ('4222', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时33分04秒');
INSERT INTO `sys_loginlog` VALUES ('4223', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时33分27秒');
INSERT INTO `sys_loginlog` VALUES ('4224', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时33分52秒');
INSERT INTO `sys_loginlog` VALUES ('4225', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时33分57秒');
INSERT INTO `sys_loginlog` VALUES ('4226', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时34分20秒');
INSERT INTO `sys_loginlog` VALUES ('4227', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时34分23秒');
INSERT INTO `sys_loginlog` VALUES ('4228', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时35分10秒');
INSERT INTO `sys_loginlog` VALUES ('4229', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时35分20秒');
INSERT INTO `sys_loginlog` VALUES ('4230', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时35分40秒');
INSERT INTO `sys_loginlog` VALUES ('4231', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时36分40秒');
INSERT INTO `sys_loginlog` VALUES ('4232', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时41分44秒');
INSERT INTO `sys_loginlog` VALUES ('4233', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 21时44分58秒');
INSERT INTO `sys_loginlog` VALUES ('4234', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 22时11分40秒');
INSERT INTO `sys_loginlog` VALUES ('4235', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月18日 22时12分45秒');
INSERT INTO `sys_loginlog` VALUES ('4236', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 08时36分07秒');
INSERT INTO `sys_loginlog` VALUES ('4237', 'zzw', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 08时36分27秒');
INSERT INTO `sys_loginlog` VALUES ('4238', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 08时36分41秒');
INSERT INTO `sys_loginlog` VALUES ('4239', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 08时44分06秒');
INSERT INTO `sys_loginlog` VALUES ('4240', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 08时46分33秒');
INSERT INTO `sys_loginlog` VALUES ('4241', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 09时03分38秒');
INSERT INTO `sys_loginlog` VALUES ('4242', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 10时10分19秒');
INSERT INTO `sys_loginlog` VALUES ('4243', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时50分14秒');
INSERT INTO `sys_loginlog` VALUES ('4244', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时56分46秒');
INSERT INTO `sys_loginlog` VALUES ('4245', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时56分51秒');
INSERT INTO `sys_loginlog` VALUES ('4246', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时56分55秒');
INSERT INTO `sys_loginlog` VALUES ('4247', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时57分08秒');
INSERT INTO `sys_loginlog` VALUES ('4248', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时57分17秒');
INSERT INTO `sys_loginlog` VALUES ('4249', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时58分26秒');
INSERT INTO `sys_loginlog` VALUES ('4250', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时58分31秒');
INSERT INTO `sys_loginlog` VALUES ('4251', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 12时59分41秒');
INSERT INTO `sys_loginlog` VALUES ('4252', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 13时12分41秒');
INSERT INTO `sys_loginlog` VALUES ('4253', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 13时19分37秒');
INSERT INTO `sys_loginlog` VALUES ('4254', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 13时31分59秒');
INSERT INTO `sys_loginlog` VALUES ('4255', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 13时32分19秒');
INSERT INTO `sys_loginlog` VALUES ('4256', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 13时32分30秒');
INSERT INTO `sys_loginlog` VALUES ('4257', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 16时05分38秒');
INSERT INTO `sys_loginlog` VALUES ('4258', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 16时06分33秒');
INSERT INTO `sys_loginlog` VALUES ('4259', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 16时08分15秒');
INSERT INTO `sys_loginlog` VALUES ('4260', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 16时08分45秒');
INSERT INTO `sys_loginlog` VALUES ('4261', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 16时54分14秒');
INSERT INTO `sys_loginlog` VALUES ('4262', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时21分31秒');
INSERT INTO `sys_loginlog` VALUES ('4263', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时27分57秒');
INSERT INTO `sys_loginlog` VALUES ('4264', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时38分50秒');
INSERT INTO `sys_loginlog` VALUES ('4265', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时43分20秒');
INSERT INTO `sys_loginlog` VALUES ('4266', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时44分48秒');
INSERT INTO `sys_loginlog` VALUES ('4267', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时45分49秒');
INSERT INTO `sys_loginlog` VALUES ('4268', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时46分12秒');
INSERT INTO `sys_loginlog` VALUES ('4269', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时54分21秒');
INSERT INTO `sys_loginlog` VALUES ('4270', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月19日 17时54分24秒');
INSERT INTO `sys_loginlog` VALUES ('4271', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 08时50分49秒');
INSERT INTO `sys_loginlog` VALUES ('4272', 'zzw', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 08时51分19秒');
INSERT INTO `sys_loginlog` VALUES ('4273', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 08时51分30秒');
INSERT INTO `sys_loginlog` VALUES ('4274', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 08时53分18秒');
INSERT INTO `sys_loginlog` VALUES ('4275', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 08时56分39秒');
INSERT INTO `sys_loginlog` VALUES ('4276', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 08时58分08秒');
INSERT INTO `sys_loginlog` VALUES ('4277', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时07分18秒');
INSERT INTO `sys_loginlog` VALUES ('4278', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时09分28秒');
INSERT INTO `sys_loginlog` VALUES ('4279', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时09分52秒');
INSERT INTO `sys_loginlog` VALUES ('4280', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时22分52秒');
INSERT INTO `sys_loginlog` VALUES ('4281', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时25分20秒');
INSERT INTO `sys_loginlog` VALUES ('4282', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时29分37秒');
INSERT INTO `sys_loginlog` VALUES ('4283', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时30分45秒');
INSERT INTO `sys_loginlog` VALUES ('4284', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时34分13秒');
INSERT INTO `sys_loginlog` VALUES ('4285', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时37分22秒');
INSERT INTO `sys_loginlog` VALUES ('4286', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时46分24秒');
INSERT INTO `sys_loginlog` VALUES ('4287', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时48分22秒');
INSERT INTO `sys_loginlog` VALUES ('4288', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时50分52秒');
INSERT INTO `sys_loginlog` VALUES ('4289', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 09时51分51秒');
INSERT INTO `sys_loginlog` VALUES ('4290', 'admin', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 10时11分09秒');
INSERT INTO `sys_loginlog` VALUES ('4291', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 10时12分06秒');
INSERT INTO `sys_loginlog` VALUES ('4292', 'xt', '本地', '', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36', '谷歌浏览器', ' (X64)', '2017年05月20日 10时14分58秒');

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` tinyint(1) DEFAULT '1',
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1', '1', '市级部门', '0', '0/');
INSERT INTO `sys_organization` VALUES ('2', '0', '镇组织部', '1', '0/1/');
INSERT INTO `sys_organization` VALUES ('3', '0', '镇宣传部', '1', '0/1/');
INSERT INTO `sys_organization` VALUES ('4', '0', '镇组织处', '2', '0/1/2/');
INSERT INTO `sys_organization` VALUES ('5', '0', '镇宣传处', '3', '0/1/3/');
INSERT INTO `sys_organization` VALUES ('7', '0', '镇办公室', '1', '0/1/');
INSERT INTO `sys_organization` VALUES ('8', '0', '镇办公处', '7', '0/1/7/');
INSERT INTO `sys_organization` VALUES ('9', '0', '镇图书馆', '1', '0/1/');
INSERT INTO `sys_organization` VALUES ('10', '0', '镇图书角', '9', '0/1/9/');
INSERT INTO `sys_organization` VALUES ('11', '1', '镇组织组', '4', '0/1/2/4/');
INSERT INTO `sys_organization` VALUES ('12', '1', '镇宣传组', '5', '0/1/3/5/');
INSERT INTO `sys_organization` VALUES ('13', '1', '镇办公组', '8', '0/1/7/8/');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` tinyint(1) DEFAULT '1',
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `type` tinyint(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('10', '1', '菜单', '0', '0/', '', '0', '');
INSERT INTO `sys_resource` VALUES ('20', '1', '个人信息', '10', '0/10/', 'student:*', '1', '/user/studentInfo');
INSERT INTO `sys_resource` VALUES ('30', '1', '个人信息', '10', '0/10/', 'teacher:*', '1', '/user/teacherInfo');
INSERT INTO `sys_resource` VALUES ('40', '1', '设备管理', '10', '0/10/', 'userdevice:*', '0', '');
INSERT INTO `sys_resource` VALUES ('41', '1', '查看设备', '40', '0/10/40/', 'userdevice:view', '1', '/devicemg/show');
INSERT INTO `sys_resource` VALUES ('42', '1', '添加设备', '40', '0/10/40/', 'userdevice:create', '1', '/devicemg/add');
INSERT INTO `sys_resource` VALUES ('50', '1', '设备管理', '10', '0/10/', 'alldevice:*', '0', '');
INSERT INTO `sys_resource` VALUES ('51', '1', '设备添加', '50', '0/10/50/', 'alldevice:create', '1', '/equipment/add');
INSERT INTO `sys_resource` VALUES ('52', '1', '设备信息', '50', '0/10/50/', 'alldevice:view', '1', '/equipment/show');
INSERT INTO `sys_resource` VALUES ('60', '1', '故障申报', '10', '0/10/', 'fault:*', '0', '');
INSERT INTO `sys_resource` VALUES ('61', '1', '查看审核进度', '60', '0/10/60/', 'fault:view', '1', '/fault/showapply');
INSERT INTO `sys_resource` VALUES ('70', '1', '故障处理', '10', '0/10/', 'FaultHandling:*', '0', '');
INSERT INTO `sys_resource` VALUES ('71', '1', '故障处理', '70', '0/10/70/', 'FaultHandling:view', '1', '/fault/approve');
INSERT INTO `sys_resource` VALUES ('80', '1', '升级申报', '10', '0/10/', 'upgrade:*', '0', '');
INSERT INTO `sys_resource` VALUES ('81', '1', '查看审核进度', '80', '0/10/80/', 'upgrade:view', '1', '/Upgrade/showapply');
INSERT INTO `sys_resource` VALUES ('90', '1', '管理员管理', '10', '0/10/', 'user:*', '0', '');
INSERT INTO `sys_resource` VALUES ('91', '1', '添加管理员', '90', '0/10/90/', 'user:create', '1', '/user/create');
INSERT INTO `sys_resource` VALUES ('92', '1', '管理员信息', '90', '0/10/90/', 'user:view,user:update,user:delete', '1', '/user');
INSERT INTO `sys_resource` VALUES ('100', '1', '系统管理', '10', '0/10/', '', '0', '');
INSERT INTO `sys_resource` VALUES ('101', '1', '角色管理', '100', '0/10/100/', 'role:*', '1', '/role');
INSERT INTO `sys_resource` VALUES ('102', '1', '菜单管理', '100', '0/10/100/', '	resource:*', '1', '/resource');
INSERT INTO `sys_resource` VALUES ('103', '1', '部门管理', '100', '0/10/100/', '	organization:*', '1', '/organization');
INSERT INTO `sys_resource` VALUES ('104', '1', '登陆日志', '100', '0/10/100/', 'audit:*', '1', '/audit');
INSERT INTO `sys_resource` VALUES ('105', '1', '升级处理', '10', '0/10/', 'upgradehandling:*', '0', '');
INSERT INTO `sys_resource` VALUES ('106', '1', '升级处理', '105', '0/10/105/', 'upgradehandling:view', '1', 'Upgrade/approve');
INSERT INTO `sys_resource` VALUES ('107', '1', '信息统计', '10', '0/10/', 'Statistics:*', '0', '');
INSERT INTO `sys_resource` VALUES ('108', '1', '设备统计', '107', '0/10/107/', '', '1', '/statistics/devicestatistics');
INSERT INTO `sys_resource` VALUES ('109', '1', '升级概况', '107', '0/10/107/', '', '1', '/statistics/updatestatistics');
INSERT INTO `sys_resource` VALUES ('110', '1', '维修状态', '107', '0/10/107/', '', '1', '/statistics/repairstatistics');
INSERT INTO `sys_resource` VALUES ('111', '1', '年份地点', '107', '0/10/107/', '', '1', '/statistics/yearandposition');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` tinyint(1) DEFAULT '1',
  `description` varchar(36) NOT NULL,
  `resource_ids` varchar(255) NOT NULL,
  `role` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '超级管理员', '10,50,51,52,70,90,100,101,102,103,104,105,107', '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '1', '普通管理员，无系统级权限', '10,50,70', '普通管理员');
INSERT INTO `sys_role` VALUES ('3', '1', '学生', '10,20,40,41,42,51,52,60', '学生');
INSERT INTO `sys_role` VALUES ('4', '1', '老师', '10,30,40,41,42,51,52,60', '老师');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `locked` tinyint(1) DEFAULT '0',
  `organization_id` bigint(20) DEFAULT NULL,
  `role_ids` varchar(36) NOT NULL,
  `salt` varchar(36) NOT NULL,
  `city` varchar(64) DEFAULT '沙县',
  `town` varchar(64) DEFAULT NULL,
  `auth_level` tinyint(1) DEFAULT NULL,
  `tel` varchar(36) DEFAULT NULL,
  `username` varchar(36) NOT NULL,
  `password` varchar(36) NOT NULL,
  `studentName` varchar(30) DEFAULT NULL,
  `teacherName` varchar(30) DEFAULT NULL,
  `grade` varchar(30) DEFAULT '0',
  `teacherId` bigint(20) DEFAULT '0',
  `studentId` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('62', '0', null, '1', '8500e65d6b47afd509e3448aede4dfe0', '沙县', null, '0', '18250152008', 'admin', 'b284bcd8ae29a9b7aac913dec0351ed4', '', '', '', '0', '0');
INSERT INTO `sys_user` VALUES ('64', '0', null, '3', '8953fe80d52eb0353107fe9237feb7c9', '沙县', null, '0', '12345678910', 'zx', '7dd11cdde9a5567a26aed5b7754bbbf2', '张棽', '', '研十', '0', '0');
INSERT INTO `sys_user` VALUES ('65', '0', null, '4', '823c9b3f04f0c3108584d25e82632caa', '沙县', null, '0', '12345678999', 'zzw', '30453156245c3fa1c8cf293407d314da', '', '张祖文', '', '0', '0');
INSERT INTO `sys_user` VALUES ('66', '0', null, '3', '300fead0684d9bf8897d6f6b6249fa46', '沙县', null, '0', '18250152008', 'xt', 'cff19d83a1ffa2c31da6c0ef018d39c3', '项滔', '', '研一', '0', '0');
INSERT INTO `sys_user` VALUES ('68', '0', null, '3', 'c9bfcb34c0b105a0c798adc0032bdd47', '沙县', null, '0', '111111111111', 'yxs', '1d753efc2e73859f7b4b830fd8c21d38', '叶心舒', '', '研一', '0', '0');

-- ----------------------------
-- Table structure for tapp
-- ----------------------------
DROP TABLE IF EXISTS `tapp`;
CREATE TABLE `tapp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versioncode` varchar(64) DEFAULT NULL COMMENT '版本号：1,2,3,4,5，。。。',
  `versionname` varchar(64) DEFAULT NULL COMMENT '版本名：1.2.0；2.1.1',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型：1 android/  2 ios',
  `start` tinyint(1) DEFAULT NULL COMMENT '是否启用 1 启用 0 关闭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tapp
-- ----------------------------
INSERT INTO `tapp` VALUES ('2', '3', '1.0.2', '1', '0');
INSERT INTO `tapp` VALUES ('3', '2', '1.0.3', '1', '1');

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` varchar(15) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `teacherName` varchar(20) DEFAULT NULL,
  `studentId` varchar(15) DEFAULT NULL,
  `deviceId` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teatodev` (`deviceId`) USING BTREE,
  KEY `teatostu` (`studentId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------

-- ----------------------------
-- Table structure for upgradeapply_info
-- ----------------------------
DROP TABLE IF EXISTS `upgradeapply_info`;
CREATE TABLE `upgradeapply_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT '0',
  `deviceName` varchar(15) DEFAULT NULL,
  `deviceType` varchar(20) DEFAULT NULL,
  `deviceUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `applytime` date DEFAULT NULL,
  `upgradeDescribe` varchar(100) DEFAULT NULL,
  `Status` varchar(15) DEFAULT '0',
  `approve` varchar(15) DEFAULT '0',
  `approveRemark` varchar(255) DEFAULT NULL,
  `logmark` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `updtodev` (`deviceId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upgradeapply_info
-- ----------------------------
INSERT INTO `upgradeapply_info` VALUES ('1', '143', '66', '1112213123', '1', 'xt', '2017-05-20', '12313123', '0', '0', null, '0');

-- ----------------------------
-- Table structure for upgraderepair_info
-- ----------------------------
DROP TABLE IF EXISTS `upgraderepair_info`;
CREATE TABLE `upgraderepair_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applyId` int(11) DEFAULT NULL,
  `deviceName` varchar(255) DEFAULT NULL,
  `deviceUser` varchar(255) DEFAULT NULL,
  `deviceType` varchar(255) DEFAULT NULL,
  `dealStatus` varchar(255) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `upgradepart` varchar(50) DEFAULT NULL,
  `upgradeResult` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upgraderepair_info
-- ----------------------------
