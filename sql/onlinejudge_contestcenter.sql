/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_contestcenter

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 09/06/2021 23:51:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `competition_id` bigint NOT NULL AUTO_INCREMENT COMMENT '竞赛id',
  `competition_create_user` bigint NOT NULL COMMENT '创建的用户id',
  `competition_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '竞赛标题',
  `competition_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '竞赛描述',
  `competition_rule_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ACM' COMMENT '竞赛赛制(OI ACM)',
  `competition_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Public' COMMENT '竞赛类型(Public , Password Protected)',
  `competition_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '竞赛类型为密码保护时，才需要此密码',
  `competition_real_time_rank` tinyint(1) NOT NULL DEFAULT '0' COMMENT '竞赛榜单权限(针对于OI模式)',
  `competition_create_time` datetime NOT NULL COMMENT '竞赛创建时间',
  `competition_start_time` datetime NOT NULL COMMENT '竞赛开始时间',
  `competition_end_time` datetime NOT NULL COMMENT '竞赛结束时间',
  `visible` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否可见',
  PRIMARY KEY (`competition_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of competition
-- ----------------------------
BEGIN;
INSERT INTO `competition` VALUES (1, 1, '计算机HBCPC训练赛', '<p>本比赛适用于参加HBCPC的同学，内部题目请勿泄漏！</p>', 'ACM', 'Password Protected', '$2a$10$Y4b4VXD.Cee7S3ebnecHce7HnfKSXUNCGNvKNwsJVCLkABBxm0Axa', 1, '2021-06-09 23:20:55', '2021-06-09 23:20:14', '2021-06-11 00:00:00', 1);
INSERT INTO `competition` VALUES (2, 1, '计算机HBCPC选拔赛', '<p>该比赛用于选拔HBCPC的赛员，选出具备合格算法能力的人员</p>', 'ACM', 'Password Protected', '$2a$10$OlH4.MKE/TrNY.BXQ5b2YezupWE0yjaRwEV3Lu7dHLG.HaqTgVbnG', 1, '2021-06-09 23:25:17', '2021-06-11 00:00:00', '2021-06-12 00:00:00', 1);
INSERT INTO `competition` VALUES (3, 2, 'B17532班级联赛', '<p>本比赛适用于软件二班的同学们，用于提高学生的算法能力</p>', 'ACM', 'Password Protected', '$2a$10$R5UhXrath8V4PII70GIqTueCDwwiGx01BIKmDcfOOzI2jDak2ILM2', 1, '2021-06-09 23:27:44', '2021-06-09 23:27:36', '2021-06-12 00:00:00', 1);
COMMIT;

-- ----------------------------
-- Table structure for competition_problem
-- ----------------------------
DROP TABLE IF EXISTS `competition_problem`;
CREATE TABLE `competition_problem` (
  `competition_problem_id` bigint NOT NULL AUTO_INCREMENT COMMENT '竞赛-题目 id',
  `competition_id` bigint NOT NULL COMMENT '竞赛id',
  `problem_id` bigint NOT NULL COMMENT '题目id',
  `problem_display_id` bigint NOT NULL COMMENT '题目展示id',
  `problem_score` bigint NOT NULL DEFAULT '0' COMMENT '题目所占分数',
  `submit_number` int NOT NULL DEFAULT '0' COMMENT '提交人数',
  `solved_number` int NOT NULL DEFAULT '0' COMMENT '通过人数',
  `problem_rule_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ACM' COMMENT '赛制',
  PRIMARY KEY (`competition_problem_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of competition_problem
-- ----------------------------
BEGIN;
INSERT INTO `competition_problem` VALUES (1, 0, 1, 1, 0, 1, 1, 'ACM');
INSERT INTO `competition_problem` VALUES (2, 0, 2, 2, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (3, 0, 3, 3, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (4, 0, 4, 4, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (5, 0, 5, 5, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (6, 0, 6, 6, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (7, 0, 7, 7, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (8, 0, 8, 8, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (9, 0, 9, 9, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (10, 0, 10, 10, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (11, 0, 11, 11, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (12, 0, 12, 12, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (13, 0, 13, 13, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (14, 1, 1, 1, 0, 1, 1, 'ACM');
INSERT INTO `competition_problem` VALUES (15, 1, 2, 2, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (16, 1, 3, 3, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (17, 2, 4, 1, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (18, 2, 5, 2, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (19, 2, 6, 3, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (20, 0, 14, 14, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (21, 0, 15, 15, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (22, 0, 16, 16, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (23, 3, 14, 1, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (24, 3, 15, 2, 0, 0, 0, 'ACM');
INSERT INTO `competition_problem` VALUES (25, 3, 16, 3, 0, 0, 0, 'ACM');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
