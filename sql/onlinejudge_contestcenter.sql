/*
 Navicat Premium Data Transfer

 Source Server         : Connection
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_contestcenter

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/05/2021 13:12:06
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
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`competition_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (1, 1, 'Competition Test', 'The Test Description', 'ACM', 'Password Protected', '$2a$10$j17XGvkhFpxAQS/f66uome0NuFVcVv3LqAvly1nV7inJtVH.V46NW', 1, '2021-05-08 09:32:56', '2021-05-08 12:00:00', '2021-05-11 12:00:00', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of competition_problem
-- ----------------------------
INSERT INTO `competition_problem` VALUES (1, 1, 1, 2, NULL, 0, 0);
INSERT INTO `competition_problem` VALUES (2, 1, 2, 1, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
