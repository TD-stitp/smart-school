/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : follows

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 24/04/2022 14:17:41
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 19110212
-- ----------------------------
DROP TABLE IF EXISTS `19110212`;
CREATE TABLE `19110212`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `lastmessage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 19110212
-- ----------------------------

-- ----------------------------
-- Table structure for 192
-- ----------------------------
DROP TABLE IF EXISTS `192`;
CREATE TABLE `192`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `lastmessage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 192
-- ----------------------------

-- ----------------------------
-- Table structure for 19240132
-- ----------------------------
DROP TABLE IF EXISTS `19240132`;
CREATE TABLE `19240132`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `lastmessage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 19240132
-- ----------------------------

-- ----------------------------
-- Table structure for 19240133
-- ----------------------------
DROP TABLE IF EXISTS `19240133`;
CREATE TABLE `19240133`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `lastmessage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 19240133
-- ----------------------------
INSERT INTO `19240133`
VALUES (11, 'J63FAC', '目前还没有消息哦~');

SET
FOREIGN_KEY_CHECKS = 1;
