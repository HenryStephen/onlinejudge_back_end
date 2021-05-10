/*
 Navicat Premium Data Transfer

 Source Server         : Connection
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_contentcenter

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/05/2021 13:11:59
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
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`announcement_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, 0, 'liaomu', 'Welcome !', 'Hey guys. Welcome to the online judge of NCIAE.', '2021-04-11 08:00:00', '2021-04-11 00:00:00', 0);
INSERT INTO `announcement` VALUES (2, 0, 'liangsheng', 'Welcome My Competition!', 'Hey, Competition!', '2021-04-17 00:00:00', '2021-04-17 00:00:00', 0);
INSERT INTO `announcement` VALUES (3, 1, 'liaomu1', 'Update Test1', 'Update Test Content', '2021-04-17 00:00:00', '2021-04-18 00:00:00', 0);

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
  `problem_submit_number` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT '提交次数',
  `problem_solved_number` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT '解决人数',
  `problem_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目作者',
  `problem_special_judge` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '特殊判题默认否0 是1',
  `problem_reminder` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `problem_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目来源',
  `problem_difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目难度',
  `problem_status` int(10) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000000 COMMENT '状态0 public，1 private',
  `problem_testcase_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试用例id',
  `is_spj` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否为特殊判题',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`problem_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (1, 1, 'A + B Problem', '<p>Calculate a+b<br></p>', '<p>Two integer a,b (0&amp;lt;=a,b&amp;lt;=10)<br></p>', '<p>Output a plus b.<br></p>', 1000, 32, 0000000000, 0000000000, 'liaomu', 0, '<p>#include &lt;iostream&gt;</p><p>using namespace std;</p><p>int&nbsp; main()</p><p>{</p><p style=\"margin-left: 40px;\">int a,b;</p><p style=\"margin-left: 40px;\">cin &gt;&gt; a &gt;&gt; b;</p><p style=\"margin-left: 40px;\">cout &lt;&lt; a+b &lt;&lt; endl;</p><p style=\"margin-left: 40px;\">return 0;</p><p>}</p>', '网络来源', 'Low', 0000000000, '803a2d1cecdda488e4b7c8a9bf3bcee3', 0, 0);
INSERT INTO `problem` VALUES (2, 1, 'A + B', '<p>Calculate a+b<br></p>', '<p>Two integer a,b (0&amp;lt;=a,b&amp;lt;=10)<br></p>', '<p>Output a plus b.<br></p>', 1000, 32, 0000000000, 0000000000, 'liaomu', 0, '<p>#include &lt;iostream&gt;</p><p>using namespace std;</p><p>int&nbsp; main()</p><p>{</p><p style=\"margin-left: 40px;\">int a,b;</p><p style=\"margin-left: 40px;\">cin &gt;&gt; a &gt;&gt; b;</p><p style=\"margin-left: 40px;\">cout &lt;&lt; a+b &lt;&lt; endl;</p><p style=\"margin-left: 40px;\">return 0;</p><p>}</p>', '网络来源', 'Low', 0000000000, '803a2d1cecdda488e4b7c8a9bf3bcee3', 0, 0);

-- ----------------------------
-- Table structure for problem_language
-- ----------------------------
DROP TABLE IF EXISTS `problem_language`;
CREATE TABLE `problem_language`  (
  `problem_language_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '题目 语言 id',
  `problem_id` bigint(20) NOT NULL COMMENT '题目id',
  `language_id` int(20) NOT NULL COMMENT '语言id',
  PRIMARY KEY (`problem_language_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_language
-- ----------------------------
INSERT INTO `problem_language` VALUES (1, 1, 1);
INSERT INTO `problem_language` VALUES (2, 1, 3);
INSERT INTO `problem_language` VALUES (3, 2, 1);
INSERT INTO `problem_language` VALUES (4, 2, 3);

-- ----------------------------
-- Table structure for problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `problem_tag`;
CREATE TABLE `problem_tag`  (
  `problem_tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '问题标签关系主键',
  `problem_id` bigint(20) NOT NULL COMMENT '问题id',
  `tag_id` bigint(20) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`problem_tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_tag
-- ----------------------------
INSERT INTO `problem_tag` VALUES (1, 1, 1);
INSERT INTO `problem_tag` VALUES (2, 1, 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sample
-- ----------------------------
INSERT INTO `sample` VALUES (1, 1, '1 2', '3', 0);
INSERT INTO `sample` VALUES (2, 1, '3 4', '7', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '水题', '水', 0);
INSERT INTO `tag` VALUES (2, '测试', '测试', 0);
INSERT INTO `tag` VALUES (3, '系统测试', '系统测试', 0);
INSERT INTO `tag` VALUES (4, '水', '水', 0);

SET FOREIGN_KEY_CHECKS = 1;
