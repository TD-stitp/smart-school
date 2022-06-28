/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : subscribers

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 24/04/2022 14:18:13
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for j63fac
-- ----------------------------
DROP TABLE IF EXISTS `j63fac`;
CREATE TABLE `j63fac`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `studentID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of j63fac
-- ----------------------------
INSERT INTO `j63fac`
VALUES (1, '19240133');

SET
FOREIGN_KEY_CHECKS = 1;
