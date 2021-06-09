/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_usercenter

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 09/06/2021 23:52:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限英文名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权路径',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, 0, '系统管理', 'System', '/api/admin', NULL, '2021-04-16 11:54:22', '2021-04-16 11:54:26');
INSERT INTO `permission` VALUES (2, 0, '内容管理', 'Content', '/api/content', NULL, '2021-04-16 11:55:12', '2021-04-16 11:55:15');
INSERT INTO `permission` VALUES (3, 0, '用户管理', 'User', '/api/user', NULL, '2021-04-16 21:51:07', '2021-04-16 21:51:10');
INSERT INTO `permission` VALUES (4, 2, '公告查看', 'Announcement', '/api/content/announcement', NULL, '2021-04-17 15:40:12', '2021-04-17 15:40:14');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL COMMENT '父角色',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色英文名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 0, '超级管理员', 'Super Admin', NULL, '2021-04-16 11:53:45', '2021-04-16 11:53:46');
INSERT INTO `role` VALUES (2, 0, '管理员', 'Admin', NULL, '2021-04-27 17:24:54', '2021-04-27 17:24:57');
INSERT INTO `role` VALUES (3, 0, '常规用户', 'Regular User', NULL, '2021-04-27 17:25:24', '2021-04-27 17:25:26');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色 ID',
  `permission_id` bigint NOT NULL COMMENT '权限 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);
INSERT INTO `role_permission` VALUES (3, 1, 3);
INSERT INTO `role_permission` VALUES (4, 1, 4);
INSERT INTO `role_permission` VALUES (5, 2, 1);
INSERT INTO `role_permission` VALUES (6, 2, 2);
INSERT INTO `role_permission` VALUES (7, 2, 3);
INSERT INTO `role_permission` VALUES (9, 3, 1);
INSERT INTO `role_permission` VALUES (10, 3, 2);
INSERT INTO `role_permission` VALUES (11, 3, 3);
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户真实姓名',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `user_regtime` datetime NOT NULL COMMENT '注册时间',
  `user_solve_number` int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT '解题数量',
  `user_submission_number` int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT '提交次数',
  `user_total_score` int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT '用户总得分',
  `user_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'zh-CN' COMMENT '界面语言',
  `user_school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学校',
  `user_mood` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '心情',
  `user_blog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '博客',
  `user_avatar` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'Base64头像',
  `user_avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'OSS头像URL',
  `user_github` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Github Url',
  `is_deleted` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否删除',
  `is_disable` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否禁用',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (1, 'admin', 'Zhang Honglin', '$2a$10$M0aVKVV6KS9HRn5jbI8PxuParCH9VuQK1pP6t3i9Ndy.CeRYMFJ22', '2376817023@qq.com', '2021-04-15 15:17:51', 0000000000, 0000000000, 0000000000, 'zh-CN', '北华航天工业学院', '加油', '我没有博客', NULL, NULL, 'github', 0, 0);
INSERT INTO `user_info` VALUES (2, 'liaomu', 'Zhang Honglin', '$2a$10$3D.iwPyMOvMpGBJys0KyRu9QcRYqqlwof6CHM.QhHd2pILbP6Yjyi', '250188525@qq.com', '2021-05-08 18:10:45', 0000000000, 0000000000, 0000000000, 'zh-CN', '北华航天工业学院', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `user_info` VALUES (3, 'liangsheng', NULL, '$2a$10$5Yu7Ony9fZBwWAyzT3vrousBvuC8ZSblNAi5kweJ8j25TUGgqY9Gm', '2376917023@qq.com', '2021-06-09 23:28:55', 0000000002, 0000000002, 0000000000, 'zh-CN', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户 ID',
  `role_id` bigint NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
