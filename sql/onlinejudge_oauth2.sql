/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_oauth2

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 09/06/2021 23:52:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resourceIds` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appSecret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `grantTypes` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `redirectUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additionalInformation` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `autoApproveScopes` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`appId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clientId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL,
  `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('client', 'backend-resources', '$2a$10$BoqYt3szH/H.ahbx82OnKucXVIIDa7s2ukFbO.ayFH7oz5.HJEJqW', 'backend', 'password,refresh_token', NULL, NULL, 86400, 2592000, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_problem
-- ----------------------------
DROP TABLE IF EXISTS `user_problem`;
CREATE TABLE `user_problem` (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `problem_id` bigint NOT NULL COMMENT '题目id',
  `status` int NOT NULL COMMENT '状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_problem
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
