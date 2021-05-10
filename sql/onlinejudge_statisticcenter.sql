/*
 Navicat Premium Data Transfer

 Source Server         : Connection
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_statisticcenter

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/05/2021 13:12:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_problem
-- ----------------------------
DROP TABLE IF EXISTS `user_problem`;
CREATE TABLE `user_problem`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `problem_id` bigint(20) NOT NULL COMMENT '题目id',
  `status` int(20) NOT NULL COMMENT '状态'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_problem
-- ----------------------------
INSERT INTO `user_problem` VALUES (1, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
