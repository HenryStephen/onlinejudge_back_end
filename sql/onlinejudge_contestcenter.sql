/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_contestcenter

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 03/06/2021 09:54:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `competition_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '竞赛id',
  `competition_create_user` bigint(20) NOT NULL COMMENT '创建的用户id',
  `competition_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '竞赛标题',
  `competition_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '竞赛描述',
  `competition_rule_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ACM' COMMENT '竞赛赛制(OI ACM)',
  `competition_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Public' COMMENT '竞赛类型(Public , Password Protected)',
  `competition_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '竞赛类型为密码保护时，才需要此密码',
  `competition_real_time_rank` tinyint(1) NOT NULL DEFAULT 0 COMMENT '竞赛榜单权限(针对于OI模式)',
  `competition_create_time` datetime(0) NOT NULL COMMENT '竞赛创建时间',
  `competition_start_time` datetime(0) NOT NULL COMMENT '竞赛开始时间',
  `competition_end_time` datetime(0) NOT NULL COMMENT '竞赛结束时间',
  `visible` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可见',
  PRIMARY KEY (`competition_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (1, 1, 'Competition Test', '<p>The Test Description update</p>', 'ACM', 'Password Protected', '$2a$10$j17XGvkhFpxAQS/f66uome0NuFVcVv3LqAvly1nV7inJtVH.V46NW', 1, '2021-05-08 09:32:56', '2021-05-08 12:00:00', '2021-05-11 12:00:00', 1);
INSERT INTO `competition` VALUES (4, 1, 'Contest Test 1', '<p>Contest Test 1 Description<br></p>', 'ACM', 'Public', '', 1, '2021-05-17 10:31:24', '2021-05-17 10:31:18', '2021-05-19 00:00:00', 1);

-- ----------------------------
-- Table structure for competition_problem
-- ----------------------------
DROP TABLE IF EXISTS `competition_problem`;
CREATE TABLE `competition_problem`  (
  `competition_problem_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '竞赛-题目 id',
  `competition_id` bigint(20) NOT NULL COMMENT '竞赛id',
  `problem_id` bigint(20) NOT NULL COMMENT '题目id',
  `problem_display_id` bigint(20) NOT NULL COMMENT '题目展示id',
  `problem_score` bigint(20) NULL DEFAULT NULL COMMENT '题目所占分数',
  `submit_number` int(10) NOT NULL DEFAULT 0 COMMENT '提交人数',
  `solved_number` int(10) NOT NULL DEFAULT 0 COMMENT '通过人数',
  PRIMARY KEY (`competition_problem_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of competition_problem
-- ----------------------------
INSERT INTO `competition_problem` VALUES (1, 0, 1, 1, NULL, 0, 0);
INSERT INTO `competition_problem` VALUES (4, 1, 1, 1, NULL, 0, 0);
INSERT INTO `competition_problem` VALUES (10, 0, 8, 3, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
