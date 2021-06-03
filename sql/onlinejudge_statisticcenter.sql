/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_statisticcenter

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 03/06/2021 09:55:26
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_problem
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
