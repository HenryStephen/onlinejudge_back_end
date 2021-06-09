/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : onlinejudge_judgecenter

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 09/06/2021 23:52:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for checkpoint
-- ----------------------------
DROP TABLE IF EXISTS `checkpoint`;
CREATE TABLE `checkpoint` (
  `checkpoint_id` int NOT NULL AUTO_INCREMENT COMMENT '检查点ID',
  `problem_id` bigint NOT NULL COMMENT '题目ID与内容中心同步',
  `input` varchar(2560) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标准输入',
  `output` varchar(2560) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标准输出',
  `is_deleted` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`checkpoint_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of checkpoint
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for compile
-- ----------------------------
DROP TABLE IF EXISTS `compile`;
CREATE TABLE `compile` (
  `compile_id` int NOT NULL AUTO_INCREMENT COMMENT '编译配置的id',
  `config_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置的名称',
  `src_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exe_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `max_cpu_time` bigint NOT NULL,
  `max_real_time` bigint NOT NULL,
  `max_memory` bigint NOT NULL,
  `compile_command` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编译命令',
  `env` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '环境变量',
  PRIMARY KEY (`compile_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of compile
-- ----------------------------
BEGIN;
INSERT INTO `compile` VALUES (1, 'c_lang_config', 'main.c', 'main', 3000, 5000, 134217728, '/usr/bin/gcc -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c99 {src_path} -lm -o {exe_path}', NULL);
INSERT INTO `compile` VALUES (2, 'c_lang_spj_config', 'spj-{spj_version}.c', 'spj-{spj_version}', 3000, 5000, 1073741824, '/usr/bin/gcc -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c99 {src_path} -lm -o {exe_path}', NULL);
INSERT INTO `compile` VALUES (3, 'cpp_lang_config', 'main.cpp', 'main', 3000, 5000, 134217728, '/usr/bin/g++ -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c++11 {src_path} -lm -o {exe_path}', NULL);
INSERT INTO `compile` VALUES (4, 'java_lang_config', 'Main.java', 'Main', 3000, 3000, -1, '/usr/bin/javac {src_path} -d {exe_dir} -encoding UTF8', NULL);
INSERT INTO `compile` VALUES (5, 'py2_lang_config', 'solution.py', 'solution.pyc', 3000, 5000, 134217728, '/usr/bin/python -m py_compile {src_path}', NULL);
INSERT INTO `compile` VALUES (6, 'py3_lang_config', 'solution.py', '__pycache__/solution.cpython-36.pyc', 3000, 5000, 134217728, '/usr/bin/python3 -m py_compile {src_path}', NULL);
INSERT INTO `compile` VALUES (7, 'go_lang_config', 'main.go', 'main', 3000, 5000, 1073741824, '/usr/bin/go build -o {exe_path} {src_path}', 'GOCACHE=/tmp');
COMMIT;

-- ----------------------------
-- Table structure for languages
-- ----------------------------
DROP TABLE IF EXISTS `languages`;
CREATE TABLE `languages` (
  `language_id` int NOT NULL AUTO_INCREMENT COMMENT '语言ID',
  `language_slug` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言缩写',
  `language_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言名称',
  `language_compile_id` int NOT NULL COMMENT '编译命令id',
  `language_run_id` int NOT NULL COMMENT '运行命令id',
  `language_suffix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言文件后缀',
  `language_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标注',
  `is_spj` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否是特殊判题',
  `is_deleted` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`language_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of languages
-- ----------------------------
BEGIN;
INSERT INTO `languages` VALUES (1, 'c_lang', 'C', 1, 1, 'c', 'GCC 5.4', 0, 0);
INSERT INTO `languages` VALUES (2, 'c_lang_spj', 'C', 2, 2, 'c', 'GCC 5.4', 1, 0);
INSERT INTO `languages` VALUES (3, 'cpp_lang', 'C++', 3, 3, 'cpp', 'G++ 5.4', 0, 0);
INSERT INTO `languages` VALUES (4, 'java_lang', 'Java', 4, 4, 'java', 'OpenJDK 1.8', 0, 0);
INSERT INTO `languages` VALUES (5, 'py2_lang', 'Python2', 5, 5, 'py', 'Python 2', 0, 0);
INSERT INTO `languages` VALUES (6, 'py3_lang', 'Python3', 6, 6, 'py', 'Python 3', 0, 0);
INSERT INTO `languages` VALUES (7, 'go_lang', 'Golang', 7, 7, 'go', 'Go', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for run
-- ----------------------------
DROP TABLE IF EXISTS `run`;
CREATE TABLE `run` (
  `run_id` int NOT NULL AUTO_INCREMENT COMMENT '运行时配置的id',
  `config_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置的名称',
  `command` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `seccomp_rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exe_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `env` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `memory_limit_check_only` int DEFAULT NULL,
  PRIMARY KEY (`run_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of run
-- ----------------------------
BEGIN;
INSERT INTO `run` VALUES (1, 'c_lang_config', '{exe_path}', 'c_cpp', NULL, 'LANG=en_US.UTF-8,LANGUAGE=en_US:en,LC_ALL=en_US.UTF-8', NULL);
INSERT INTO `run` VALUES (2, 'c_lang_spj_config', '{exe_path} {in_file_path} {user_out_file_path}', 'c_cpp', 'spj-{spj_version}', NULL, NULL);
INSERT INTO `run` VALUES (3, 'cpp_lang_config', '{exe_path}', 'c_cpp', NULL, 'LANG=en_US.UTF-8,LANGUAGE=en_US:en,LC_ALL=en_US.UTF-8', NULL);
INSERT INTO `run` VALUES (4, 'java_lang_config', '/usr/bin/java -cp {exe_dir} -XX:MaxRAM={max_memory}k -Djava.security.manager -Dfile.encoding=UTF-8 -Djava.security.policy==/etc/java_policy -Djava.awt.headless=true Main', NULL, NULL, 'LANG=en_US.UTF-8,LANGUAGE=en_US:en,LC_ALL=en_US.UTF-8', 1);
INSERT INTO `run` VALUES (5, 'py2_lang_config', '/usr/bin/python {exe_path}', 'general', NULL, 'LANG=en_US.UTF-8,LANGUAGE=en_US:en,LC_ALL=en_US.UTF-8', NULL);
INSERT INTO `run` VALUES (6, 'py3_lang_config', '/usr/bin/python3 {exe_path}', 'general', NULL, 'PYTHONIOENCODING=UTF-8,LANG=en_US.UTF-8,LANGUAGE=en_US:en,LC_ALL=en_US.UTF-8', NULL);
INSERT INTO `run` VALUES (7, 'go_lang_config', '{exe_path}', '', NULL, 'GODEBUG=madvdontneed=1,GOCACHE=off,LANG=en_US.UTF-8,LANGUAGE=en_US:en,LC_ALL=en_US.UTF-8', 1);
COMMIT;

-- ----------------------------
-- Table structure for submission
-- ----------------------------
DROP TABLE IF EXISTS `submission`;
CREATE TABLE `submission` (
  `submission_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交记录id',
  `submission_user_id` bigint NOT NULL DEFAULT '0' COMMENT '提交用户id',
  `submission_problem_id` bigint NOT NULL DEFAULT '0' COMMENT '题目id',
  `submission_contest_id` bigint DEFAULT NULL COMMENT '竞赛id',
  `submission_language_id` int NOT NULL DEFAULT '0' COMMENT '解题使用语言id',
  `submission_source_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交源码',
  `submission_commit_time` datetime NOT NULL COMMENT '提交时间',
  `submission_status` int NOT NULL DEFAULT '9' COMMENT '判题状态',
  `submission_used_time` int DEFAULT NULL COMMENT '运行时间(MS)',
  `submission_used_memory` int DEFAULT NULL COMMENT '运行所需内存大小(KB)',
  PRIMARY KEY (`submission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of submission
-- ----------------------------
BEGIN;
INSERT INTO `submission` VALUES ('852333035671130112', 3, 1, 0, 3, '#include<iostream>\n#include<stack>\n#include<string>\nusing namespace std;\n\nint main()\n{\n	int n=0,m;\n	string s;\n	stack<string> S0,S1,S2;\n	while(cin>>m>>s)\n	{\n		n++;\n		if(!m)\n			S0.push(s);\n		else if(m==1)\n			S1.push(s);\n		else\n			S2.push(s);\n	}\n	for(m=0;m<10;m++)\n	{\n		if(S0.empty())\n			cout<<0<<\' \';\n		else\n		{\n			cout<<S0.top()<<\' \';\n			S0.pop();\n		}\n		if(S1.empty())\n			cout<<0<<\' \';\n		else\n		{\n			cout<<S1.top()<<\' \';\n			S1.pop();\n		}\n		if(S2.empty())\n			cout<<0<<endl;\n		else\n		{\n			cout<<S2.top()<<endl;\n			S2.pop();\n		}\n	}\n	while(!S0.empty())\n	{\n		cout<<S0.top()<<\' \';\n		S0.pop();\n	}\n	while(!S1.empty())\n	{\n		cout<<S1.top()<<\' \';\n		S1.pop();\n	}\n	while(!S2.empty())\n	{\n		cout<<S2.top()<<endl;\n		S2.pop();\n	}\n	return 0;\n}', '2021-06-09 23:47:12', 0, 2, 3387392);
INSERT INTO `submission` VALUES ('852333124456157184', 3, 1, 1, 3, '#include<iostream>\n#include<stack>\n#include<string>\nusing namespace std;\n\nint main()\n{\n	int n=0,m;\n	string s;\n	stack<string> S0,S1,S2;\n	while(cin>>m>>s)\n	{\n		n++;\n		if(!m)\n			S0.push(s);\n		else if(m==1)\n			S1.push(s);\n		else\n			S2.push(s);\n	}\n	for(m=0;m<10;m++)\n	{\n		if(S0.empty())\n			cout<<0<<\' \';\n		else\n		{\n			cout<<S0.top()<<\' \';\n			S0.pop();\n		}\n		if(S1.empty())\n			cout<<0<<\' \';\n		else\n		{\n			cout<<S1.top()<<\' \';\n			S1.pop();\n		}\n		if(S2.empty())\n			cout<<0<<endl;\n		else\n		{\n			cout<<S2.top()<<endl;\n			S2.pop();\n		}\n	}\n	while(!S0.empty())\n	{\n		cout<<S0.top()<<\' \';\n		S0.pop();\n	}\n	while(!S1.empty())\n	{\n		cout<<S1.top()<<\' \';\n		S1.pop();\n	}\n	while(!S2.empty())\n	{\n		cout<<S2.top()<<endl;\n		S2.pop();\n	}\n	return 0;\n}', '2021-06-09 23:47:33', 0, 2, 3457024);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
