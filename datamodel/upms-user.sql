SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for upms_user
-- ----------------------------
DROP TABLE IF EXISTS `upms_user`;
CREATE TABLE `upms_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `system_id` int(10) unsigned NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO `upms_user` VALUES ('1', '1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '管理员', null, null, null, '1', '1');
