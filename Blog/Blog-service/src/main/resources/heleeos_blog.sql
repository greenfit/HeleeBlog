/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.68
Source Server Version : 50717
Source Host           : 192.168.0.68:3306
Source Database       : heleeos_blog

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-21 13:34:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `disp` varchar(255) DEFAULT NULL COMMENT '显示的URL',
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `lastTime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `managerId` int(11) NOT NULL COMMENT '发布者ID',
  `typeId` int(11) NOT NULL COMMENT '类型ID',
  `count` int(11) DEFAULT '0' COMMENT '阅读次数',
  `tags` json DEFAULT NULL COMMENT '所有的标签',
  `contentType` tinyint(4) DEFAULT NULL COMMENT '内容类型',
  `content` text COMMENT '文章内容',
  `state` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `dispIndex` tinyint(4) DEFAULT '0' COMMENT '是否置顶',
  PRIMARY KEY (`id`),
  UNIQUE KEY `disp` (`disp`) USING HASH
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('1', 'Spring介绍', 'spring-info', '个人编写了基于Spring的大大小小数个项目,能够熟练的编写,但是对于其内部原理不是精通,所以想写一系列的文章,从基础开始到深入原理进行学习和总结.', '2017-03-16 22:10:21', '2017-03-21 13:30:30', '1', '1', '0', null, '1', '## Spring 简介\n\nSpring是一个Java平台的开源框架，为开发Java应用程序提供全面的基础架构支持。Spring处理基础架构，以便开发人员可以专注于应用程序。Spring使开发人员能够使用\"纯Java对象\"（POJO）构建应用程序，并将服务非侵入性的应用于POJO。\n\n虽然Java平台提供了丰富的应用程序开发功能，但它缺乏将基本构建块组织成一个整体的手段，将该任务留给架构师和开发人员。可以使用Factory, Abstract Factory, Builder, Decorator等设计模式来组成构成应用程序的各种类和对象实例，但这些模式仅仅是：给定名称以及描述什么模式，应用它解决的问题。模式都是形式化的，开发人员必须在应用程序中实现自己的模式代码。Spring框架通过使用控制反转（IoC）组件，将完全不同的组件，组合成可以工作的应用程序，来解决这个问题。\n\n## Spring 组成\n\n![](https://image.heleeos.com/blog/spring-info/1.png)\n\n图片来源  [Spring 官方文档](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/overview.html#overview-modules)\n\n## Spring 模块说明\n\n| 模块名称 | 模块描述 |\n| :------ | :--------|\n| spring-aop| 基于代理的AOP |\n| spring-aspects| 基于AspectJ的切面 |\n| spring-beans| Bean支持，包括Groovy |\n| spring-context| 应用程序上下文运行时，包括调度和远程抽象 |\n| spring-context-support| 支持将常见的第三方库集成到Spring应用程序上下文中的类 |\n| spring-core| 核心实用程序，由许多其他Spring模块使用 |\n| spring-expression| Spring表达式语言（SpEL） |\n| spring-instrument| JVM引导的工具代理 |\n| spring-instrument-tomcat| Tomcat的工具代理 |\n| spring-jdbc| JDBC支持包，包括DataSource设置和JDBC访问支持 |\n| spring-jms| JMS支持包，包括用于发送和接收JMS消息的助手类 |\n| spring-messaging| 支持消息架构和协议 |\n| spring-orm| 对象/关系映射，包括JPA和Hibernate支持 |\n| spring-oxm| 对象/ XML映射 |\n| spring-test| 支持单元测试和集成测试Spring组件 |\n| spring-tx| 事务基础，包括DAO支持和JCA集成 |\n| spring-web| Web支持包，包括客户端和Web远程处理 |\n| spring-webmvc| Web应用程序的REST Web服务和模型 - 视图 - 控制器实现 |\n| spring-webmvc-portlet| 将在Portlet环境中使用的MVC实现 |\n| spring-websocket| WebSocket和SockJS实现，包括STOMP支持 |\n\n## Spring 部分类图\n\n### BeanFactory类图\n\n![BeanFactory类图](https://image.heleeos.com/blog/spring-info/2.png)\n\n## Spring 模块源码下载\n\n使用svn工具下载 Spring github 的部分源码\n\n### 打开Spring的github\n\n浏览器打开链接 [https://github.com/spring-projects/spring-framework](https://github.com/spring-projects/spring-framework)\n\n### 寻找要下载的模块\n\n![](https://image.heleeos.com/blog/spring-info/3.png)\n\n### 修改地址\n\n将地址中的 tree/master 修改为 trunk\n\n将地址 `https://github.com/spring-projects/spring-framework/`**tree/master**`/spring-web/`\n\n修改为 `https://github.com/spring-projects/spring-framework/`**trunk**`/spring-web/`\n\n### svn中导出\n\n![](https://image.heleeos.com/blog/spring-info/4.png)\n\n![](https://image.heleeos.com/blog/spring-info/5.png)', '0', '0');
INSERT INTO `t_blog` VALUES ('2', 'Spring入门', 'spring-disp-server-time', '编写一个显示服务器时间的小项目', '2017-03-17 21:13:05', '2017-03-21 13:30:40', '1', '1', '0', '[\"Spring\", \"Spring������\"]', '1', '## 编程前提\n\n1. 知道Maven是如何管理项目的. [Apache Maven](https://maven.apache.org/)\n2. 会使用Eclipse EE 或者 [Spring Tool Suite™ (STS)](https://spring.io/tools/sts)\n3. 有基本的Java编程基础,知道MVC等概念\n\n## 编程环境\n\n- JDK: 1.8.0_91\n- IDE: STS 3.7.3.RELEASE\n- Maven: Apache maven 3.3.9\n- Server: Apache Tomcat 7.0\n\n## 步骤详解\n\n### 新建一个Maven项目\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/1.png)\n\n### 选择建立简单项目\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/2.png)\n\n### 填写项目基本信息\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/3.png)\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/4.png)\n\n>刚建立完成有错误很正常,因为我们建立的是最简单的项目,不包含其他任何配置\n\n### 新增配置文件\n\n1. 修改pom.xml文件\n2. 先在webapp下面新建一个WEB-INF文件夹,然后新建一个文件,命名为web.xml,然后依次建立pages文件夹和dispatcher-servlet.xml\n3. 另外在src/main/resources 下面新建 applicationContext.xml文件\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/6.png)\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/5.png)\n\n### maven更新项目\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/7.png)\n\n\n### 新建控制器\n\n在src/main/java 下面新建包 com.helios.controller, 然后新建 MainController 类\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/8.png)\n\n### 编写首页内容\n\n在WEB-INF/pages 下面新建一个文件 名为 index.ftl\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/9.png)\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/10.png)\n\n### 运行和测试\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/11.png)\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/12.png)\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/13.png)\n\n### 编写时间内容\n\nMainController中新增加一个方法 toTime, pages 下新增一个 time.ftl 文件\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/14.png)\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/15.png)\n\n### 运行和测试\n\n![](https://image.heleeos.com/blog/spring-disp-server-time/16.png)\n\n## 文件附录\n\n[代码已上传码云](https://git.oschina.net/IP/LearnSpring)', '0', '0');

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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_module
-- ----------------------------
INSERT INTO `t_blog_module` VALUES ('1', '成长之旅', 'IT', '', null);
INSERT INTO `t_blog_module` VALUES ('2', '读书感悟', 'book', '', null);

-- ----------------------------
-- Table structure for t_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag` (
  `name` varchar(64) NOT NULL COMMENT '标签',
  `count` int(11) DEFAULT '0' COMMENT '使用标签的文章个数',
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tag
-- ----------------------------
INSERT INTO `t_blog_tag` VALUES ('Spring', '1');
INSERT INTO `t_blog_tag` VALUES ('Spring 生命周期', '1');

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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_type
-- ----------------------------
INSERT INTO `t_blog_type` VALUES ('1', 'Spring 精通之路', '1', null);
INSERT INTO `t_blog_type` VALUES ('2', '架构师之路', '1', null);

-- ----------------------------
-- Table structure for t_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL,
  `realname` varchar(64) DEFAULT NULL,
  `picture` varchar(64) DEFAULT NULL COMMENT '头像',
  `loginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `info` varchar(255) DEFAULT NULL COMMENT '个人介绍',
  `state` tinyint(1) DEFAULT NULL COMMENT '当前的状态,是否禁用等',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
