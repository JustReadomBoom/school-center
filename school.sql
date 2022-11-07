/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-11-07 15:47:36
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
INSERT INTO `class_info` VALUES ('1', '10001', '小一班', null, '邓老师', '2022-11-04 11:31:11', '2022-11-04 11:31:13');
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
  `height` varchar(8) DEFAULT NULL,
  `father_name` varchar(20) DEFAULT NULL,
  `father_phone` varchar(11) DEFAULT NULL,
  `father_job` varchar(64) DEFAULT NULL,
  `mother_name` varchar(20) DEFAULT NULL,
  `mother_phone` varchar(11) DEFAULT NULL,
  `mother_job` varchar(64) DEFAULT NULL,
  `home_address` varchar(64) DEFAULT NULL,
  `other_family` varchar(255) DEFAULT NULL,
  `c_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `u_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '001', '胡明', '10002', '8', '0', null, '130', '胡军', '13872829349', '自由职业', '杨红', '18827362910', '家政111', '东湖一路', null, '2022-11-04 17:13:47', '2022-11-04 17:13:47');
INSERT INTO `student` VALUES ('2', '002', '周洲', '10001', '5', '1', null, '110', '2222', '2222', '333', '333', '4444', '3444', '5555', null, '2022-11-04 17:03:25', '2022-11-04 17:03:25');
INSERT INTO `student` VALUES ('3', '003', '周洲', '10002', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:39', '2022-11-04 15:43:39');
INSERT INTO `student` VALUES ('4', '004', '周洲', '10002', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:41', '2022-11-04 15:43:41');
INSERT INTO `student` VALUES ('5', '005', '周洲', '10001', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:30', '2022-11-04 15:43:30');
INSERT INTO `student` VALUES ('8', '008', '周洲', '10003', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:50', '2022-11-04 15:43:50');
INSERT INTO `student` VALUES ('9', '009', '周洲', '10001', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:33', '2022-11-04 15:43:33');
INSERT INTO `student` VALUES ('10', '010', '周洲', '10003', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:53', '2022-11-04 15:43:53');
INSERT INTO `student` VALUES ('11', '011', '周洲', '10001', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:37', '2022-11-04 15:43:37');
INSERT INTO `student` VALUES ('12', '012', '周洲', '10002', '3', '1', null, '110', '周远山', '15822542241', '企业经理', '孙俪', '12541224411', '大学教师', '珍珠路一号', null, '2022-11-04 15:43:46', '2022-11-04 15:43:46');
INSERT INTO `student` VALUES ('24', '013', '黄晓雯', '10002', '4', '2', null, null, '黄天', '12356952658', '还是说说', '聂小红', '15862565894', '但是窘境覅殴打事件', '反倒是哦第四哦啊多久啊是', null, '2022-11-04 15:59:50', '2022-11-04 15:59:50');

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
