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

 Date: 24/12/2018 09:01:32
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
  `album_createtime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`album_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for assocation_role_resoureces
-- ----------------------------
DROP TABLE IF EXISTS `assocation_role_resoureces`;
CREATE TABLE `assocation_role_resoureces`  (
  `association_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `resourece_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`association_id`) USING BTREE,
  INDEX `ASSOCATION_ROLE_RESOURECES_RESOURECEID_FORGIKEY`(`resourece_id`) USING BTREE,
  INDEX `ASSOCATION_ROLE_RESOURECES_ROLEID_FORGIKEY`(`role_id`) USING BTREE,
  CONSTRAINT `ASSOCATION_ROLE_RESOURECES_RESOURECEID_FORGIKEY` FOREIGN KEY (`resourece_id`) REFERENCES `resoureces_type` (`resoureces_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ASSOCATION_ROLE_RESOURECES_ROLEID_FORGIKEY` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for association_role_user
-- ----------------------------
DROP TABLE IF EXISTS `association_role_user`;
CREATE TABLE `association_role_user`  (
  `association_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`association_id`) USING BTREE,
  INDEX `role_id_forignKey`(`role_id`) USING BTREE,
  INDEX `association_role_user_personid_fk`(`person_id`) USING BTREE,
  CONSTRAINT `association_role_user_personid_fk` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `association_role_user_roleid_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `photo_id` int(11) NOT NULL,
  `comment_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `COMMENT_PHOTOID_FORIGNKEY`(`photo_id`) USING BTREE,
  INDEX `COMMENT_PERSONID_FORIGNKEY`(`person_id`) USING BTREE,
  CONSTRAINT `COMMENT_PERSONID_FORIGNKEY` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `COMMENT_PHOTOID_FORIGNKEY` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`photo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_name` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `person_password` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person_avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `open_id` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`person_id`) USING BTREE,
  UNIQUE INDEX `person_name`(`person_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `photo_id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `photo_desc` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo_createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `album_id` int(11) NULL DEFAULT NULL,
  `photo_original_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `photo_thum_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`photo_id`) USING BTREE,
  UNIQUE INDEX `album_id_photo_name`(`photo_name`, `album_id`) USING BTREE,
  INDEX `album_id_forignkey`(`album_id`) USING BTREE,
  CONSTRAINT `album_id_forignkey` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resoureces_type
-- ----------------------------
DROP TABLE IF EXISTS `resoureces_type`;
CREATE TABLE `resoureces_type`  (
  `resoureces_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `resourece_type_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`resoureces_type_id`) USING BTREE,
  INDEX `resourece_type_name`(`resourece_type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resoureces_urls
-- ----------------------------
DROP TABLE IF EXISTS `resoureces_urls`;
CREATE TABLE `resoureces_urls`  (
  `resoureces_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `resoureces_url_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `resoureces_url_action` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`resoureces_type_id`) USING BTREE,
  CONSTRAINT `resoureces_urls_typeId_fk` FOREIGN KEY (`resoureces_type_id`) REFERENCES `resoureces_type` (`resoureces_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
