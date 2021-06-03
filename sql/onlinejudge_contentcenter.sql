/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_contentcenter

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 03/06/2021 09:54:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `announcement_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `competition_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '竞赛id',
  `nickname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人用户名',
  `announcement_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `announcement_content` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `visible` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可见',
  PRIMARY KEY (`announcement_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, 0, 'liaomu', 'Welcome !', 'Hey guys. Welcome to the online judge of NCIAE.', '2021-04-11 08:00:00', '2021-04-11 00:00:00', 1);
INSERT INTO `announcement` VALUES (4, 0, 'admin', 'Test Announcement', '<p>Test Announcement Content<br></p>', '2021-05-16 16:21:54', '2021-05-16 16:21:54', 1);
INSERT INTO `announcement` VALUES (5, 0, 'admin', 'Announcement Test 1', '<p>Announcement Test 1 Content Upate<br></p>', '2021-05-17 10:42:11', '2021-05-17 10:42:37', 1);
INSERT INTO `announcement` VALUES (6, 1, 'admin', 'Contest Announcement Test 1', '<p>Contest Announcement Test 1 Content<br></p>', '2021-05-17 10:53:21', '2021-05-17 10:53:21', 1);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `problem_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `add_user_id` bigint(20) NOT NULL COMMENT '添加者ID',
  `problem_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目名称',
  `problem_description` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目描述',
  `problem_input_formation` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入格式',
  `problem_output_formation` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输出格式',
  `problem_time_limit` int(11) NOT NULL COMMENT '时间限制(MS)',
  `problem_memory_limit` int(11) NOT NULL COMMENT '内存限制(MB)',
  `problem_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目作者',
  `problem_reminder` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `problem_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目来源',
  `problem_difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目难度',
  `problem_testcase_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试用例id',
  `problem_create_time` datetime(6) NOT NULL COMMENT '题目创建时间',
  `is_spj` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否为特殊判题',
  `visible` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可见',
  PRIMARY KEY (`problem_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (1, 1, '送分题-A+B Problem', '<p>Calculate a+b</p>', '<p>Two integer a,b (0&lt;=a,b&lt;=10)</p>', '<p>Output a+b</p>', 1000, 32, 'admin', '<p>#include#includeusing namespace std;int main(){#ifndef ONLINE_JUDGE	freopen(\"in.txt\",\"r\",stdin);#endif	int a,b;	while(cin &gt;&gt;a &gt;&gt;b)	{		cout &lt;</p>', '系统原理，熟悉OJ', 'Low', 'f6bf7fa502fe99f088e11403f88677bd', '2021-05-17 16:58:30.000000', 0, 1);
INSERT INTO `problem` VALUES (8, 1, 'test 2', '<p>test 2<br></p>', '<p>test 2<br></p>', '<p>test 2 update<br></p>', 1000, 32, 'admin', '<p>test 2<br></p>', 'test 2', 'Low', 'f6bf7fa502fe99f088e11403f88677bd', '2021-06-02 15:43:43.000000', 0, 1);

-- ----------------------------
-- Table structure for problem_language
-- ----------------------------
DROP TABLE IF EXISTS `problem_language`;
CREATE TABLE `problem_language`  (
  `problem_language_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '题目 语言 id',
  `problem_id` bigint(20) NOT NULL COMMENT '题目id',
  `language_id` int(20) NOT NULL COMMENT '语言id',
  PRIMARY KEY (`problem_language_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of problem_language
-- ----------------------------
INSERT INTO `problem_language` VALUES (25, 8, 1);
INSERT INTO `problem_language` VALUES (26, 8, 3);
INSERT INTO `problem_language` VALUES (27, 8, 4);
INSERT INTO `problem_language` VALUES (28, 8, 5);
INSERT INTO `problem_language` VALUES (29, 1, 1);
INSERT INTO `problem_language` VALUES (30, 1, 3);

-- ----------------------------
-- Table structure for problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `problem_tag`;
CREATE TABLE `problem_tag`  (
  `problem_tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '问题标签关系主键',
  `problem_id` bigint(20) NOT NULL COMMENT '问题id',
  `tag_id` bigint(20) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`problem_tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of problem_tag
-- ----------------------------
INSERT INTO `problem_tag` VALUES (8, 8, 8);
INSERT INTO `problem_tag` VALUES (9, 8, 9);
INSERT INTO `problem_tag` VALUES (10, 1, 1);
INSERT INTO `problem_tag` VALUES (11, 1, 2);

-- ----------------------------
-- Table structure for sample
-- ----------------------------
DROP TABLE IF EXISTS `sample`;
CREATE TABLE `sample`  (
  `sample_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '样例id',
  `problem_id` bigint(20) NOT NULL COMMENT '题目id',
  `sample_input` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样例输入',
  `sample_output` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样例输出',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`sample_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sample
-- ----------------------------
INSERT INTO `sample` VALUES (17, 8, '1 2', '4', 0);
INSERT INTO `sample` VALUES (18, 1, '1 2', '3', 0);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Tag ID',
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `tag_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签描述',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '水题', '水', 0);
INSERT INTO `tag` VALUES (2, '测试', '测试', 0);
INSERT INTO `tag` VALUES (5, 'Easy', 'Easy', 0);
INSERT INTO `tag` VALUES (6, 'Low', 'Low', 0);
INSERT INTO `tag` VALUES (7, 'Mid', 'Mid', 0);
INSERT INTO `tag` VALUES (8, 'High', 'High', 0);
INSERT INTO `tag` VALUES (9, 'Tag1', 'Tag1', 0);

-- ----------------------------
-- Table structure for website_info
-- ----------------------------
DROP TABLE IF EXISTS `website_info`;
CREATE TABLE `website_info`  (
  `website_base_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站基本url',
  `website_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名字',
  `website_name_shortcut` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站简称',
  `website_footer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '页脚',
  `website_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'LiaoMu' COMMENT '网站作者',
  `allow_registry` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否允许注册',
  `submission_list_show_all` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示全部题目提交'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website_info
-- ----------------------------
INSERT INTO `website_info` VALUES ('www.nciaeoj.com', 'NCIAE Online Judge', 'NCIAE-OJ', 'Code and Code,Born for More', 'LiaoMu', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
