/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_statisticcenter

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 09/06/2021 23:52:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_problem
-- ----------------------------
DROP TABLE IF EXISTS `user_problem`;
CREATE TABLE `user_problem` (
  `user_problem_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联关系id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `problem_display_id` bigint NOT NULL COMMENT '题目id',
  `competition_id` bigint NOT NULL COMMENT '竞赛id',
  `status` int NOT NULL COMMENT '状态',
  PRIMARY KEY (`user_problem_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_problem
-- ----------------------------
BEGIN;
INSERT INTO `user_problem` VALUES (1, 3, 1, 0, 0);
INSERT INTO `user_problem` VALUES (2, 3, 1, 1, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
