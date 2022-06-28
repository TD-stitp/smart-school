/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : admin

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 24/04/2022 14:17:09
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `nickname`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `account`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `password`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `unique`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins`
VALUES (19, 'aosdijaiocjzx', 'oiasdkjopzkcpozxc', 'aosidjoaijsdoiasjd', 'CHANNEL');
INSERT INTO `admins`
VALUES (20, 'hjodisaj', 'hello', 'ioasjdozjixc', 'BANNER');
INSERT INTO `admins`
VALUES (25, 'K8lyN', '19240133', '182490', 'ALL');
INSERT INTO `admins`
VALUES (27, 'dfghvb', 'asdasd', 'dgdg', NULL);
INSERT INTO `admins`
VALUES (30, 'uikggh', 'asdaszxc', 'erdfg', 'BANNER');
INSERT INTO `admins`
VALUES (31, 'dfghdfg', 'asdzxcv', 'cvbxvcb', 'ALL');

-- ----------------------------
-- Table structure for bereviewed
-- ----------------------------
DROP TABLE IF EXISTS `bereviewed`;
CREATE TABLE `bereviewed`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `account`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `unique`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bereviewed
-- ----------------------------

SET
FOREIGN_KEY_CHECKS = 1;
