/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-11-10 17:05:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `id` int(8) NOT NULL,
  `class_code` varchar(32) DEFAULT NULL,
  `class_name` varchar(32) DEFAULT NULL,
  `class_logo` varchar(64) DEFAULT NULL,
  `charge_teacher` varchar(32) DEFAULT NULL,
  `c_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `u_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('1', '10001', '小一班', null, '邓老师', '2022-11-07 17:47:26', '2022-11-07 00:00:00');
INSERT INTO `class_info` VALUES ('2', '10002', '小二班', null, '张老师', '2022-11-04 11:31:39', '2022-11-04 11:31:42');
INSERT INTO `class_info` VALUES ('3', '10003', '小三班', '', '袁老师', '2022-11-04 11:32:11', '2022-11-04 11:32:13');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `class_code` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` int(3) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `father_name` varchar(20) DEFAULT NULL,
  `father_phone` varchar(11) DEFAULT NULL,
  `father_id_no` varchar(32) DEFAULT NULL,
  `father_job` varchar(64) DEFAULT NULL,
  `mother_name` varchar(20) DEFAULT NULL,
  `mother_phone` varchar(11) DEFAULT NULL,
  `mother_id_no` varchar(32) DEFAULT NULL,
  `mother_job` varchar(64) DEFAULT NULL,
  `home_address` varchar(64) DEFAULT NULL,
  `c_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `u_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `STUDENT_CODE_INDEX` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('30', '001', '周丽娟', '10003', '3', '2', null, '周小军', '15263251254', '420500194510181811', '国药葛洲坝中心医院', '曹琳', '15852652354', '420503198301215226', '第一人民医院', '珍珠路二号', '2022-11-10 17:05:04', '2022-11-10 17:05:04');
INSERT INTO `student` VALUES ('31', '003', '黄思琪', '10002', '4', '2', null, '黄文君', '15852635458', '420503198303125216', '宜昌市工商管理局', '聂开华', '15852652414', '420582199107040047', '家政服务', '东站二路1号', '2022-11-10 17:05:06', '2022-11-10 17:05:06');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(8) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `disable` int(1) DEFAULT NULL,
  `c_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '1', '2022-11-03 17:29:36');
