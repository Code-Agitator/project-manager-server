/*
 Navicat Premium Data Transfer

 Source Server         : main
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : user_manager

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 21/03/2023 22:10:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_defect
-- ----------------------------
DROP TABLE IF EXISTS `t_defect`;
CREATE TABLE `t_defect`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'defect-缺陷 improve-改进 experience-体验',
  `priority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优先级 high-紧急 mid-中 low-低 delay-延迟',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'none' COMMENT '严重程度 none-无 death-致命 important-严重 normal-普通 low-轻微',
  `repeated_probability` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'none' COMMENT '重复概率 none-无 must-必然 high-大概率 mid-偶然 low-难以复现',
  `report_user_id` int NULL DEFAULT NULL COMMENT '报告人id',
  `user_id` int NULL DEFAULT NULL COMMENT '经办人id',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int NULL DEFAULT 1 COMMENT '1-待解决 2-正在解决 3-已解决 4-关闭',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '缺陷表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_defect
-- ----------------------------
INSERT INTO `t_defect` VALUES (1, '有重要bug', 'defect', 'high', 'death', 'high', 5, 5, '很重要', 1, NULL);

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `user_id` int NULL DEFAULT NULL COMMENT '部门主管id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, '测试部门', 4, '2023-03-15 20:22:36');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限字符',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `menu` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin', '系统管理', '[\"User\",\"Department\",\"Role\",\"Plan\",\"Defect\",\"Case\"]');
INSERT INTO `t_role` VALUES (2, 'test', '测试人员', '[\"User\",\"Department\",\"Role\",\"Plan\",\"Defect\",\"Case\"]');
INSERT INTO `t_role` VALUES (3, 'dev', '开发人员', '[\"User\",\"Department\",\"Role\",\"Plan\",\"Defect\",\"Case\"]');
INSERT INTO `t_role` VALUES (4, 'major', '主管', '[\"Department\",\"Role\",\"Plan\",\"Defect\",\"Case\",\"User\"]');

-- ----------------------------
-- Table structure for t_testing_case
-- ----------------------------
DROP TABLE IF EXISTS `t_testing_case`;
CREATE TABLE `t_testing_case`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `plant_id` int NOT NULL COMMENT '用例计划id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用例标题',
  `user_id` int NULL DEFAULT NULL COMMENT '输出者',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用例链接',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '测试结果',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试用例' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_testing_case
-- ----------------------------
INSERT INTO `t_testing_case` VALUES (1, 5, '123', 1, '123', '123', '123');

-- ----------------------------
-- Table structure for t_testing_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_testing_plan`;
CREATE TABLE `t_testing_plan`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '测试计划名称',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '测试计划连接',
  `user_id` int NULL DEFAULT NULL COMMENT '负责人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试计划管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_testing_plan
-- ----------------------------
INSERT INTO `t_testing_plan` VALUES (5, 'aaa', NULL, 1, '2023-03-16 17:56:09');
INSERT INTO `t_testing_plan` VALUES (6, 'a', 'TVoECR_Uninst.exe', 1, '2023-03-16 17:58:00');
INSERT INTO `t_testing_plan` VALUES (7, '234', '1679291074858.xlsx', 2, '2023-03-20 14:44:19');
INSERT INTO `t_testing_plan` VALUES (8, '测试计划', '1679291074858.xlsx', 1, '2023-03-21 21:04:43');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工号',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `seat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '座位',
  `status` int NULL DEFAULT NULL COMMENT '0-离职 1-在职 2-实习生',
  `department_id` int NULL DEFAULT NULL COMMENT '部门id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '556556', 1, 'asd', '11111', 'admin', '111', 1, 1, '2023-03-15 19:17:28', '21232f297a57a5a743894a0e4a801fc3');
INSERT INTO `t_user` VALUES (4, '1637707889027543040', 4, '新用户', '123456561', '123445234', '123124', 1, 1, '2023-03-20 14:49:22', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `t_user` VALUES (5, '1637709882768977920', 3, '新用户', '1234565621', '1234452342', '1232124', 1, 1, '2023-03-20 14:57:17', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `t_user` VALUES (6, '1637710108154097664', 2, '新用户', '12345265621', '12324452342', '12322124', 1, 1, '2023-03-20 14:58:11', '5f4dcc3b5aa765d61d8327deb882cf99');

SET FOREIGN_KEY_CHECKS = 1;
