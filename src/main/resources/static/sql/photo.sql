/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : photo_manage_system

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 13/12/2018 22:58:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album`  (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `album_desc` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `album_createtime` datetime(0) NULL DEFAULT NULL,
  `album_remark` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`album_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for association_role_user
-- ----------------------------
DROP TABLE IF EXISTS `association_role_user`;
CREATE TABLE `association_role_user`  (
  `association_id` int(11) NOT NULL,
  `person_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`association_id`) USING BTREE,
  INDEX `role_id_forignKey`(`role_id`) USING BTREE,
  INDEX `PERSONID_FORIGNKEY`(`person_id`) USING BTREE,
  CONSTRAINT `PERSONID_FORIGNKEY` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id_forignKey` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `photo_id` int(11) NOT NULL,
  `comment_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `COMMENT_PHOTOID_FORIGNKEY`(`photo_id`) USING BTREE,
  INDEX `COMMENT_PERSONID_FORINGIKEY`(`person_id`) USING BTREE,
  CONSTRAINT `COMMENT_PERSONID_FORINGIKEY` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `COMMENT_PHOTOID_FORIGNKEY` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`photo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_name` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `person_password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `person_avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`person_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `photo_id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_name` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `photo_desc` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo_createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `album_id` int(11) NULL DEFAULT NULL,
  `photo_original_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `photo_thum_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`photo_id`) USING BTREE,
  INDEX `album_id_forignkey`(`album_id`) USING BTREE,
  UNIQUE INDEX `album_id_photo_name`(`photo_name`, `album_id`) USING BTREE,
  CONSTRAINT `album_id_forignkey` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
