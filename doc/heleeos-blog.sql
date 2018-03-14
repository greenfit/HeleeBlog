SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_blog`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `blog_title` varchar(255) NOT NULL COMMENT '文章标题',
  `disp_url` varchar(255) DEFAULT NULL COMMENT '显示的URL',
  `blog_summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `manager_id` int(11) NOT NULL COMMENT '发布者ID',
  `blog_type` int(11) NOT NULL COMMENT '类型ID',
  `read_count` int(11) DEFAULT '0' COMMENT '阅读次数',
  `blog_tags` varchar(255) DEFAULT NULL COMMENT '所有的标签',
  `content_type` tinyint(4) DEFAULT NULL COMMENT '内容类型',
  `blog_content` text COMMENT '文章内容',
  `blog_state` tinyint(4) DEFAULT '0' COMMENT '状态',
  `disp_index` tinyint(4) DEFAULT '0' COMMENT '显示顺序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `disp` (`disp_url`) USING HASH
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_blog_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_type`;
CREATE TABLE `t_blog_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_module` varchar(10) DEFAULT NULL,
  `type_name` varchar(100) DEFAULT NULL,
  `type_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_manager`
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(32) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `manager_picture` varchar(64) DEFAULT NULL COMMENT '头像',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `manager_state` tinyint(1) DEFAULT NULL COMMENT '当前的状态,是否禁用等',
  `login_token` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
