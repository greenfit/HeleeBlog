/*
Export Author   : liyu
Source Database : heleeos_blog
Date            : 2017-03-09 22:00:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `lastTime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `managerId` int(11) NOT NULL COMMENT '发布者ID',
  `typeId` int(11) NOT NULL COMMENT '类型ID',
  `count` int(11) DEFAULT '0' COMMENT '阅读次数',
  `tags` varchar(255) DEFAULT NULL COMMENT '所有的标签',
  `content` text COMMENT '文章内容',
  `isDelete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `isTop` bit(1) DEFAULT b'0' COMMENT '是否置顶',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------

-- ----------------------------
-- Table structure for t_blog_module
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_module`;
CREATE TABLE `t_blog_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块ID',
  `name` varchar(64) NOT NULL COMMENT '模块名称',
  `url` varchar(64) NOT NULL COMMENT '模块访问URL',
  `image` varchar(255) NOT NULL COMMENT '模块缩略图',
  `content` varchar(255) DEFAULT NULL COMMENT '模块说明',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_module
-- ----------------------------
INSERT INTO `t_blog_module` VALUES ('1', '技术杂事', 'IT', '', null);
INSERT INTO `t_blog_module` VALUES ('2', '深思熟虑', 'book', '', null);
INSERT INTO `t_blog_module` VALUES ('3', '翩翩起舞', 'sf', '', null);

-- ----------------------------
-- Table structure for t_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(64) DEFAULT NULL COMMENT '标签',
  `count` int(11) DEFAULT '0' COMMENT '使用标签的文章个数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tag
-- ----------------------------

-- ----------------------------
-- Table structure for t_blog_type
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_type`;
CREATE TABLE `t_blog_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(64) NOT NULL COMMENT '分类名称',
  `moduleId` int(11) NOT NULL COMMENT '模块ID',
  `content` varchar(255) DEFAULT NULL COMMENT '分类说明',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `image` varchar(255) DEFAULT NULL COMMENT '头像',
  `loginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `info` varchar(255) DEFAULT NULL COMMENT '个人介绍',
  `state` bit(1) DEFAULT NULL COMMENT '当前的状态,是否禁用等',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_manager
-- ----------------------------
