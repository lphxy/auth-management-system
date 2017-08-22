SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for upms_system
-- ----------------------------
DROP TABLE IF EXISTS `upms_system`;
CREATE TABLE `upms_system` (
  `system_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `icon` varchar(20) DEFAULT NULL,
  `basepath` varchar(100) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  `orders` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统';

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO `upms_system` VALUES ('1', null, 'http://upms.w1992wishes.cn:1113', '1', 'upms-app1', '1', '1');
INSERT INTO `upms_system` VALUES ('2', null, 'http://upms.w1992wishes.cn:1114', '1', 'upms-app2', '2', '2');
INSERT INTO `upms_system` VALUES ('3', null, 'http://cms.w1992wishes.cn:2222', '1', 'cms-admin', '3', '3');