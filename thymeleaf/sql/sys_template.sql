/*
 Navicat Premium Data Transfer

 Source Server         : 212.129.135.144
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 212.129.135.144:3306
 Source Schema         : thymeleaf

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 01/12/2020 13:29:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_template`;
CREATE TABLE `sys_template`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `created` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `deleted` datetime(3) NULL DEFAULT NULL,
  `updated` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `template_type` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板类型',
  `role_id` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `user_id` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `template` tinytext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '模板内容',
  `operator_id` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人id',
  `template_enable` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板启用与否；0.代表启用；1.代表未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
