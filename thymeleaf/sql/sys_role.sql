/*
 Navicat Premium Data Transfer

 Source Server         : mysql002
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 212.129.135.144:3306
 Source Schema         : thymeleaf

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 19/11/2020 08:56:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `deleted` datetime(3) NULL DEFAULT NULL,
  `updated` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `role_name` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
