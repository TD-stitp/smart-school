/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : channels

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 24/04/2022 14:17:29
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 19240133
-- ----------------------------
DROP TABLE IF EXISTS `19240133`;
CREATE TABLE `19240133`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `code`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `createtime` date NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 19240133
-- ----------------------------
INSERT INTO `19240133`
VALUES (12, 'J63FAC', 'HELLOWORLD', '2022-03-14');

-- ----------------------------
-- Table structure for asdasd
-- ----------------------------
DROP TABLE IF EXISTS `asdasd`;
CREATE TABLE `asdasd`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `code`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `createtime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of asdasd
-- ----------------------------

-- ----------------------------
-- Table structure for names
-- ----------------------------
DROP TABLE IF EXISTS `names`;
CREATE TABLE `names`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of names
-- ----------------------------
INSERT INTO `names`
VALUES (11, 'J63FAC', 'HELLOWORLD');

-- ----------------------------
-- Table structure for ojojisad
-- ----------------------------
DROP TABLE IF EXISTS `ojojisad`;
CREATE TABLE `ojojisad`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `code`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `createtime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ojojisad
-- ----------------------------

SET
FOREIGN_KEY_CHECKS = 1;
