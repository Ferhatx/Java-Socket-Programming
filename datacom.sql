/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : datacom

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-12-20 22:04:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kullanicilar`
-- ----------------------------
DROP TABLE IF EXISTS `kullanicilar`;
CREATE TABLE `kullanicilar` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `kullanici_adi` varchar(15) DEFAULT NULL,
  `durum` int(5) DEFAULT NULL,
  `puan` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kullanicilar
-- ----------------------------
INSERT INTO `kullanicilar` VALUES ('22', 'Client18', '1', '20');
INSERT INTO `kullanicilar` VALUES ('23', 'Client26', '1', '10');
INSERT INTO `kullanicilar` VALUES ('24', 'Client46', '0', '0');

-- ----------------------------
-- Table structure for `sorular`
-- ----------------------------
DROP TABLE IF EXISTS `sorular`;
CREATE TABLE `sorular` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `sorular` varchar(50) DEFAULT NULL,
  `cevaplar` varchar(50) DEFAULT NULL,
  `durumu` int(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sorular
-- ----------------------------
INSERT INTO `sorular` VALUES ('1', 'Üni Adı?', 'karabuk', '0');
INSERT INTO `sorular` VALUES ('2', 'Adın?', 'ferhat', '0');
INSERT INTO `sorular` VALUES ('3', 'Bölüm?', 'pc', '0');
INSERT INTO `sorular` VALUES ('4', 'Nerde Kalıyosun?', 'kyk', '0');
INSERT INTO `sorular` VALUES ('5', 'Proje Ders Adı?', 'datacom', '0');
