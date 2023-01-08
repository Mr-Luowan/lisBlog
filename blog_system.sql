/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : blog_system

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 08/01/2023 21:27:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `user_avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `category_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类ID',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型（0表示富文本，1表示markdown）',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0表示已发布，1表示草稿，2表示删除）',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '摘要',
  `labels` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签',
  `view_count` int(11) NOT NULL DEFAULT 0 COMMENT '阅读数量',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_article_on_user_id`(`user_id`) USING BTREE,
  INDEX `fk_category_article_on_category_id`(`category_id`) USING BTREE,
  CONSTRAINT `fk_category_article_on_category_id` FOREIGN KEY (`category_id`) REFERENCES `tb_categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_article_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', 'FFmpeg入门', '1', '123', 'lll', '1', '---\ntitle: FFmpeg入门\ndate: 2022-07-30 09:09:06\ntop_img: https://s1.ax1x.com/2022/07/30/vikHoV.png\ncover: https://s1.ax1x.com/2022/07/30/vikHoV.png\ncategories: \n- FFmpeg\ntags:\n- FFmpeg\n- 入门\n\n---\n\n### \n\n## FFmpeg的基本组成\n\n（1）FFmpeg的封装模块 AVFormat\n\n（2）FFmpeg的编解码模块AVCodec\n\n（3）FFmpeg的滤镜模块AVFilter\n\n（4）FFmpeg的视频图像转换计算模块swscale\n\n（2）FFmpeg的音频转换计算模块swresample\n\n\n\n## 一些常用命令\n\n\n\n分离音频命令：\n\nffmpeg -i xxx.avi -vn xxx.mp3\n\n-i表示源文件 -vn表示不转换视频\n\n分离视频命令：\n\nffmpeg -i xxx.avi -an xxx.mp4\n\n-an表示不转换音频\n\n显示设备	ffmpeg -devices\n\n显示-dshow的设备	ffmpeg -list_devices true -f dshow -i dummy				（dummy的意义是打印完毕就结束）\n\n录音命令	ffmpeg -f dshow -i audio=\"麦克风阵列 (英特尔® 智音技术)\" hello.mp3\n\n录制视频命令  ffmpeg -f dshow -i video=\"Integrated Webcam\" test.mp4\n\n播放PCM音频文件    ffplay -ar 44100 -ac 2 -f s16le pcmFile.pcm\n\n\n\n\n\n## Tips\n\nAndroidStudio中有时写C++代码会没有自动提示，此时需要\n\n1 在local.properties文件中配置NDK路劲:ndk.dir=D\\:\\\\SDK\\\\ndk\\\\21.1.6352462\n\n2 将我们的源代码在CMakeLists.txt文件中添加到add_library()这个方法里,添加完如果还不行尝试重新打开应用\n\nCMAKE message函数的TAG，\n\n(无) = 重要消息；\n STATUS = 非重要消息；\n WARNING = CMake 警告, 会继续执行；\n AUTHOR_WARNING = CMake 警告 (dev), 会继续执行；\n SEND_ERROR = CMake 错误, 继续执行，但是会跳过生成的步骤；\n FATAL_ERROR = CMake 错误, 终止所有处理过程；\n\n注意\n\n1 不要使用逗号\n\n2 错误TAG，会终止当前正在运行的\n\n\n\nQT 引入静态库与动态库\n\nLIBS += -L/usr/local/lib  -lvlc     不需要lib前缀\n\nLIBS +=  /usr/local/lib/libvlc.a    需要要lib前缀\n\n![FFmpeg结构体关系](https://s1.ax1x.com/2022/09/04/vTSsfS.png)\n\n\n\n[![FFmpeg解码流程](https://s1.ax1x.com/2022/09/04/vo7a1P.png)\n\n## FFmpeg关键结构体简介：\n\n### AVFormatContext\n\n封装格式上下文结构体，也是统领全局的结构体，保存了视频文件封装 格式相关信息。\n\niformat：输入视频的AVInputFormat \n\nnb_streams ：输入视频的AVStream 个数 \n\n streams ：输入视频的AVStream []数组 \n\n duration ：输入视频的时长（以微秒为单位）\n\n bit_rate ：输入视频的码率\n\n### AVInputFormat\n\n每种封装格式（例如FLV, MKV, MP4, AVI）对应一个该结构体。\n\nname：封装格式名称 \n\n long_name：封装格式的长名称 \n\n extensions：封装格式的扩展名 \n\n id：封装格式ID  一些封装格式处理的接口函数\n\n### AVStream\n\n视频文件中每个视频（音频）流对应一个该结构体。\n\nid：序号 \n\nindex:在封装格式AVFormatContext的streams数组中的位置\n\n codecpar： AVCodecParameters包含了一些流的属性，codec_type(媒体类型), codec_id（解码器ID）\n\n time_base：该流的时基 \n\n r_frame_rate：该流的帧率\n\n### AVCodecContext\n\n编码器上下文结构体，保存了视频（音频）编解码相关信息\n\ncodec：编解码器的AVCodec \n\n width, height：图像的宽高（只针对视频） \n\n pix_fmt：像素格式（只针对视频） \n\n sample_rate：采样率（只针对音频） \n\n channels：声道数（只针对音频） \n\n sample_fmt：采样格式（只针对音频）\n\n### AVCodec\n\n每种视频（音频）编解码器(例如H.264解码器)对应一个该结构体。\n\nname：编解码器名称 \n\n long_name：编解码器长名称 \n\n type：编解码器类型 \n\n id：编解码器ID \n\n 一些编解码的接口函数\n\n### AVPacket\n\n存储一帧压缩编码数据。\n\npts：显示时间戳 \n\n dts ：解码时间戳 \n\n data ：压缩编码数据 \n\nsize ：压缩编码数据大小 \n\n stream_index ：所属的AVStream\n\n### AVFrame \n\n 存储一帧解码后像素（采样）数据。\n\ndata：解码后的图像像素数据（音频采样数据）\n\n linesize：对视频来说是图像中一行像素的大小；对音频来说是整个音 频帧的大小。 \n\n width, height：图像的宽高（只针对视频）。 \n\n key_frame：是否为关键帧（只针对视频） 。 \n\n pict_type：帧类型（只针对视频） 。例如I，P，B。', '1', '0', 'FFmpeg入门', '1', 1, '2023-01-06 21:50:24', '2023-01-06 21:50:27');
INSERT INTO `tb_article` VALUES ('2', 'C++入门', '1', '234', 'ppp', '1', '# C++动态开辟空间的方式\r\n\r\n## malloc\r\n\r\nint *pa = (int *)malloc(sizeof(int));*\r\n\r\nmalloc方法开辟的空间的返回的类型为 void*类型，需要强转为目标类型，空间中的内容为随机值。\r\n\r\nmalloc方法开辟一块空间\r\n\r\n## calloc\r\n\r\nint *pb = (int*)calloc(2, sizeof(int));\r\n\r\ncalloc方法开辟\"N\"块空间(N等于参数一)，空间中的内容为0；\r\n\r\n## new\r\n\r\nint *a = new int;	//开辟空间\r\n\r\ndelete a;	//释放a\r\n\r\nnew 开辟空间相当于malloc 实际上是对malloc的一层封装,delete是对free的一层封装。\r\n\r\n开辟的空间需要及时释放', '1', '0', 'C++入门', '2', 1, '2022-11-08 22:47:58', '2023-01-06 22:48:18');

-- ----------------------------
-- Table structure for tb_categories
-- ----------------------------
DROP TABLE IF EXISTS `tb_categories`;
CREATE TABLE `tb_categories`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `pinyin` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '拼音',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `order` int(11) NOT NULL COMMENT '顺序',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态：0表示不使用，1表示正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_categories
-- ----------------------------
INSERT INTO `tb_categories` VALUES ('1', '信息技术', 'xinxijishu', 'it', 1, '1', '2023-01-06 21:49:29', '2023-01-06 21:49:31');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `parent_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '父内容',
  `article_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论用户的ID',
  `user_avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论用户的头像',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论用户的名称',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0表示删除，1表示正常）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_comment_on_user_id`(`user_id`) USING BTREE,
  INDEX `fk_article_comment_on_article_id`(`article_id`) USING BTREE,
  CONSTRAINT `fk_article_comment_on_article_id` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_comment_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_daily_view_count
-- ----------------------------
DROP TABLE IF EXISTS `tb_daily_view_count`;
CREATE TABLE `tb_daily_view_count`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `view_count` int(11) NOT NULL DEFAULT 0 COMMENT '每天浏览量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_daily_view_count
-- ----------------------------

-- ----------------------------
-- Table structure for tb_friends
-- ----------------------------
DROP TABLE IF EXISTS `tb_friends`;
CREATE TABLE `tb_friends`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接名称',
  `logo` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接logo',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接',
  `order` int(11) NOT NULL DEFAULT 0 COMMENT '顺序',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接状态:0表示不可用，1表示正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_friends
-- ----------------------------

-- ----------------------------
-- Table structure for tb_images
-- ----------------------------
DROP TABLE IF EXISTS `tb_images`;
CREATE TABLE `tb_images`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0表示删除，1表正常）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_images_on_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_images_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_images
-- ----------------------------

-- ----------------------------
-- Table structure for tb_labels
-- ----------------------------
DROP TABLE IF EXISTS `tb_labels`;
CREATE TABLE `tb_labels`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  `count` int(11) NOT NULL DEFAULT 0 COMMENT '数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_labels
-- ----------------------------

-- ----------------------------
-- Table structure for tb_looper
-- ----------------------------
DROP TABLE IF EXISTS `tb_looper`;
CREATE TABLE `tb_looper`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '轮播图标题',
  `order` int(11) NOT NULL DEFAULT 0 COMMENT '顺序',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态：0表示不可用，1表示正常',
  `target_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标URL',
  `image_url` varchar(2014) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_looper
-- ----------------------------

-- ----------------------------
-- Table structure for tb_settings
-- ----------------------------
DROP TABLE IF EXISTS `tb_settings`;
CREATE TABLE `tb_settings`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '键',
  `value` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_settings
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `roles` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色(0 游客  1 登录用户  2 超级管理员)',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像地址',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签名',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态：0表示删除，1表示正常',
  `reg_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '注册ip',
  `login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录Ip',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'lll', 'admin', '1', '123', NULL, NULL, '1', '1', '1', '2023-01-06 21:48:41', '2023-01-06 21:48:46');

SET FOREIGN_KEY_CHECKS = 1;
