-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 192.168.100.20    Database: blog_system
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_article`
--

DROP TABLE IF EXISTS `tb_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_article` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `user_avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `category_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类ID',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型（0表示富文本，1表示markdown）',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0表示已发布，1表示草稿，2表示删除）',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '摘要',
  `labels` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签',
  `view_count` int NOT NULL DEFAULT '0' COMMENT '阅读数量',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '封面图地址',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_user_article_on_user_id` (`user_id`) USING BTREE,
  KEY `fk_category_article_on_category_id` (`category_id`) USING BTREE,
  CONSTRAINT `fk_category_article_on_category_id` FOREIGN KEY (`category_id`) REFERENCES `tb_categories` (`id`),
  CONSTRAINT `fk_user_article_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_article`
--

LOCK TABLES `tb_article` WRITE;
/*!40000 ALTER TABLE `tb_article` DISABLE KEYS */;
INSERT INTO `tb_article` VALUES ('1','FFmpeg入门','1','123','lll','1','---\ntitle: FFmpeg入门\ndate: 2022-07-30 09:09:06\ntop_img: https://s1.ax1x.com/2022/07/30/vikHoV.png\ncover: https://s1.ax1x.com/2022/07/30/vikHoV.png\ncategories: \n- FFmpeg\ntags:\n- FFmpeg\n- 入门\n\n---\n\n### \n\n## FFmpeg的基本组成\n\n（1）FFmpeg的封装模块 AVFormat\n\n（2）FFmpeg的编解码模块AVCodec\n\n（3）FFmpeg的滤镜模块AVFilter\n\n（4）FFmpeg的视频图像转换计算模块swscale\n\n（2）FFmpeg的音频转换计算模块swresample\n\n\n\n## 一些常用命令\n\n\n\n分离音频命令：\n\nffmpeg -i xxx.avi -vn xxx.mp3\n\n-i表示源文件 -vn表示不转换视频\n\n分离视频命令：\n\nffmpeg -i xxx.avi -an xxx.mp4\n\n-an表示不转换音频\n\n显示设备	ffmpeg -devices\n\n显示-dshow的设备	ffmpeg -list_devices true -f dshow -i dummy				（dummy的意义是打印完毕就结束）\n\n录音命令	ffmpeg -f dshow -i audio=\"麦克风阵列 (英特尔® 智音技术)\" hello.mp3\n\n录制视频命令  ffmpeg -f dshow -i video=\"Integrated Webcam\" test.mp4\n\n播放PCM音频文件    ffplay -ar 44100 -ac 2 -f s16le pcmFile.pcm\n\n\n\n\n\n## Tips\n\nAndroidStudio中有时写C++代码会没有自动提示，此时需要\n\n1 在local.properties文件中配置NDK路劲:ndk.dir=D\\:\\\\SDK\\\\ndk\\\\21.1.6352462\n\n2 将我们的源代码在CMakeLists.txt文件中添加到add_library()这个方法里,添加完如果还不行尝试重新打开应用\n\nCMAKE message函数的TAG，\n\n(无) = 重要消息；\n STATUS = 非重要消息；\n WARNING = CMake 警告, 会继续执行；\n AUTHOR_WARNING = CMake 警告 (dev), 会继续执行；\n SEND_ERROR = CMake 错误, 继续执行，但是会跳过生成的步骤；\n FATAL_ERROR = CMake 错误, 终止所有处理过程；\n\n注意\n\n1 不要使用逗号\n\n2 错误TAG，会终止当前正在运行的\n\n\n\nQT 引入静态库与动态库\n\nLIBS += -L/usr/local/lib  -lvlc     不需要lib前缀\n\nLIBS +=  /usr/local/lib/libvlc.a    需要要lib前缀\n\n![FFmpeg结构体关系](https://s1.ax1x.com/2022/09/04/vTSsfS.png)\n\n\n\n[![FFmpeg解码流程](https://s1.ax1x.com/2022/09/04/vo7a1P.png)\n\n## FFmpeg关键结构体简介：\n\n### AVFormatContext\n\n封装格式上下文结构体，也是统领全局的结构体，保存了视频文件封装 格式相关信息。\n\niformat：输入视频的AVInputFormat \n\nnb_streams ：输入视频的AVStream 个数 \n\n streams ：输入视频的AVStream []数组 \n\n duration ：输入视频的时长（以微秒为单位）\n\n bit_rate ：输入视频的码率\n\n### AVInputFormat\n\n每种封装格式（例如FLV, MKV, MP4, AVI）对应一个该结构体。\n\nname：封装格式名称 \n\n long_name：封装格式的长名称 \n\n extensions：封装格式的扩展名 \n\n id：封装格式ID  一些封装格式处理的接口函数\n\n### AVStream\n\n视频文件中每个视频（音频）流对应一个该结构体。\n\nid：序号 \n\nindex:在封装格式AVFormatContext的streams数组中的位置\n\n codecpar： AVCodecParameters包含了一些流的属性，codec_type(媒体类型), codec_id（解码器ID）\n\n time_base：该流的时基 \n\n r_frame_rate：该流的帧率\n\n### AVCodecContext\n\n编码器上下文结构体，保存了视频（音频）编解码相关信息\n\ncodec：编解码器的AVCodec \n\n width, height：图像的宽高（只针对视频） \n\n pix_fmt：像素格式（只针对视频） \n\n sample_rate：采样率（只针对音频） \n\n channels：声道数（只针对音频） \n\n sample_fmt：采样格式（只针对音频）\n\n### AVCodec\n\n每种视频（音频）编解码器(例如H.264解码器)对应一个该结构体。\n\nname：编解码器名称 \n\n long_name：编解码器长名称 \n\n type：编解码器类型 \n\n id：编解码器ID \n\n 一些编解码的接口函数\n\n### AVPacket\n\n存储一帧压缩编码数据。\n\npts：显示时间戳 \n\n dts ：解码时间戳 \n\n data ：压缩编码数据 \n\nsize ：压缩编码数据大小 \n\n stream_index ：所属的AVStream\n\n### AVFrame \n\n 存储一帧解码后像素（采样）数据。\n\ndata：解码后的图像像素数据（音频采样数据）\n\n linesize：对视频来说是图像中一行像素的大小；对音频来说是整个音 频帧的大小。 \n\n width, height：图像的宽高（只针对视频）。 \n\n key_frame：是否为关键帧（只针对视频） 。 \n\n pict_type：帧类型（只针对视频） 。例如I，P，B。','1','0','FFmpeg入门','1',1,'2023-01-06 21:50:24','2023-01-06 21:50:27','https://s1.ax1x.com/2022/07/30/vikHoV.png'),('1144629969953267712','CMake用法记录','1',NULL,NULL,'1','## cmake 下载地址 [https://cmake.org/files/LatestRelease/](https://cmake.org/files/LatestRelease/)\n\n## CMake打印Log\n\n**message( [STATUS|WARNING|AUTHOR\\_WARNING|FATAL\\_ERROR|SEND\\_ERROR] \"message to display\" ...)**\n\n**STATUS = 非重要消息； **\n\n**WARNING = CMake 警告, 会继续执行； **\n\n**AUTHOR\\_WARNING = CMake 警告 (dev), 会继续执行；**\n\n**SEND\\_ERROR = CMake 错误, 继续执行，但是会跳过生成的步骤；**\n\n**FATAL\\_ERROR = CMake 错误, 终止所有处理过程；**\n\n## 二进制目录和源目录\n\n**Cmake会为每个项目的二进制目录和源目录 隐式生成两个变量：**\n\n**<project\\_name>\\_BINARY\\_DIR**\n\n**<project\\_name>\\_SOURCE\\_DIR**\n\n**同时也存在这样的两个变量：**\n\n**PROJECT\\_BINARY\\_DIR = <project\\_name>\\_BINARY\\_DIR**\n\n**PROJECT\\_SOURCE\\_DIR = <project\\_name>\\_SOURCE\\_DIR**\n\n**PROJECT\\_BINARY\\_DIR 对应的是执行cmake命令所在的目录**\n\n**PROJECT\\_SOURCE\\_DIR对应的是CMakeLists.txt文件所在的目录**\n\n![](https://cdn.nlark.com/yuque/0/2023/png/32727028/1683096292633-b247f690-a273-4640-8368-5025473a6150.png)\n\n![](https://cdn.nlark.com/yuque/0/2023/png/32727028/1683096279032-dfa97849-3fea-4716-be46-2208dfc1ae9e.png)\n\n## Cmake构建动态库和静态库\n\n**cmake中构建静态库或动态库需要使用add\\_library这个函数**\n\n**方法：**\n\n**add\\_libaray(lib\\_name [ SHARED | STATIC | MODULE ] [ EXCLUDE\\_FROM\\_ALL ] src1 src2 src3 .....srcN)**\n\n**参数说明**\n\n**lib\\_name 要生成的库的名称**\n\n**SHARED  生成动态库**\n\n**STATIC    生成静态库**\n\n**MODULE	使用dyid 生成苹果系统的动态库，如果不支持 自动变成SHARED**\n\n**EXCLUDE\\_FROM\\_ALL 指定此参数，则该库默认情况下不被构建，当有其他组件用到该库或者手动构建时才会被构建**\n\n**src1		用于构建该库的源文件**\n\n```\n//helloLibrary.h\n#ifndef __HELLO_LIBRARY__\n#define __HELLO_LIBRARY__\n\n#include <iostream>\n\nvoid hello_library();\n#endif\n\n//helloLibrary.cpp\n#include \"helloLibrary.h\"\n\nvoid hello_library()\n{\n    std::cout << \"hello library\" << std::endl;\n}\n\n//main.cpp\n#include \"helloLibrary.h\"\nint main()\n{\n    hello_library();\n    return 0;\n}\n```\n\n```\n#指定需要的最小cmake版本\ncmake_minimum_required(VERSION 3.6)\n# 执行项目名称\nproject(HelloLibrary)			\n\n # 生成动态库 名称hello_library 实际的库文件会自动加上lib前缀\nadd_library(hello_library SHARED helloLibrary.cpp)\n\n# 添加头文件\ninclude_directories(${PROJECT_SOURCE_DIR})\n\n# 生成可执行文件\nadd_executable(main main.cpp)\n\n# 指定某个目标的生成所依赖的库，也就是add_library生成的库，\n# 注意这里不需要lib前缀\ntarget_link_libraries(main hello_library) \n```\n\n![](https://cdn.nlark.com/yuque/0/2023/png/32727028/1683097385407-a485b359-d37e-4302-bfa6-55b3de00db59.png)\n\n```\n#指定需要的最小cmake版本\ncmake_minimum_required(VERSION 3.6)\n# 执行项目名称\nproject(HelloLibrary)			\n\n # 生成静态态库 名称hello_library 实际的库文件会自动加上lib前缀，Linux静态库的后缀为.a\nadd_library(hello_library STATIC helloLibrary.cpp)\n\n# 添加头文件\ninclude_directories(${PROJECT_SOURCE_DIR})\n\n# 生成可执行文件\nadd_executable(main main.cpp)\n\n# 指定某个目标的生成所依赖的库，也就是add_library生成的库，\n# 注意这里不需要lib前缀\ntarget_link_libraries(main hello_library) \n```\n\n![](https://cdn.nlark.com/yuque/0/2023/png/32727028/1683097798676-7657d227-fca8-4399-9d23-3d323690ae69.png)\n\n## Cmake添加工程子目录\n\n**add\\_subdirectory 函数**\n\n**add\\_subdirectory(src\\_dir [ bin\\_dir ] [ EXCLUDE\\_FROM\\_ALL ])**\n\n**src\\_dir 这个src\\_dir 这个目录加入项目**\n\n**bin\\_dir 指定编译输出（包含编译的中间结果）的目录 为bin\\_dir, 如果不指定 则默认输出路径为 src\\_dir,即与src\\_dir同名的一个目录，指定其实就是把src\\_dir 改为 bin\\_dir**\n\n**EXCLUDE\\_FROM\\_ALL 将给定的目录从编译中排除，即不编译它 比如一些示例的目录，需要等工程构建完后单独构建的**\n\n![](https://cdn.nlark.com/yuque/0/2023/png/32727028/1683099482329-ce203a36-b079-463d-af04-47b69ab2640f.png)\n\n**如何chapter03是外层目录， src是子目录，添加的子目录依然需要自己的CMakeLists.txt文件**\n\n```\ncmake_minimum_required(VERSION 3.8)\nproject(SubDirectory)\nadd_subdirectory(src)\n```\n\n```\nadd_executable(main main.cpp)\n```\n\n![](https://cdn.nlark.com/yuque/0/2023/png/32727028/1683099628651-04197d65-6b26-4341-9e7d-701875d82ca9.png)\n\n**执行了Cmake .. 和make命令后在build 生成了src目录，并且src下有目录需要生成的可执行文件main**\n\n## 指定目标保存目录\n\n**虽然add\\_subdirectory可以指定编译输出目录，但是也可以通过set函数来设置EXECUTABLE\\_OUTPUT\\_PATH 和 LIBRARY\\_OUTPUT\\_PATH来指定最终目标的存放路径，只是目标文件，不包含中间文件。**\n\n**EXECUTABLE\\_OUTPUT\\_PATH 可执行文件的存放目录。**\n\n**LIBRARY\\_OUTPUT\\_PATH 库文件存放目录**\n\n```\n├── build.sh\n├── CMakeLists.txt\n└── src\n├── CMakeLists.txt\n├── main.cpp\n├── sayHello.cpp\n└── sayHello.h\n# 外层CMakeLists.txt\ncmake_minimum_required(VERSION 3.6)\nproject(OUTPUT_PATH)\nadd_subdirectory(src)\n\n# 内层 src/CMakeLists.txt\n# 设置可执行文件输出目录\nset(EXECUTABLE_OUTPUT_PATH ${PROJECT_BINARY_DIR}/bin)\n# 设置库文件输出目录\nset(LIBRARY_OUTPUT_PATH ${PROJECT_BINARY_DIR}/lib)\nadd_library(say_hello SHARED sayHello.cpp)\ninclude_directories(${PROJECT_SOURCE_DIR}/src)\nadd_executable(main main.cpp)\ntarget_link_libraries(main say_hello)\n```\n\n**make结果**\n\n![](https://cdn.nlark.com/yuque/0/2023/png/32727028/1683957247262-b81959b6-4b22-4480-9299-02213dbe2fd5.png)\n\n## Cmake定义安装规则\n\n**CMake中可以通过install函数来定义安装规则，并配合 ****CMAKE\\_INSTALL\\_PREFIX** 变量指定安装的路径，即执行 cmake 命令时指定\n\n**cmake -DCMAKE\\_INSTALL\\_PREFIX=存放路径 .**\n\n**定义安装的规则内容可以是可执行文件，动态库，静态库，文件，目录，脚本等。**\n\n**定义安装规则包括以下几个方面**\n\n1. **二进制目标文件的安装（可执行文件，静态库，动态库）**\n2. **普通文件的安装**\n3. **非目标可执行程序（脚本）**\n4. **目录的安装**\n5. **安装时执行Cmake脚本**\n\n### 二进制目标文件的安装（可执行文件，静态库，动态库）\n\n**INSTALL(TARGETS targets... ****[**\n\n**[**ARCHIVE|LIBRARY|RUNTIME**]**\n\n**[**DESTINATION <dir>**]**\n\n**[**PERMISSION permissions...**]**\n\n**[**CONFIGURATIONS [DEBUG|release|...]**]**\n\n**...])**\n\n**TARGETS****：后面的targets就是add\\_executable 或 add\\_library 定义的目标**\n\n**ARCHIVE|LIBRARY|RUNTIME****：分别指静态库 动态库 和 可执行文件**\n\n**DESTINATION <dir>** **定义要安装的路径，**\n\n**1 相对路径 CMAKE\\_INSTALL\\_PREFIX/<dir>  CMAKE\\_INSTALL\\_PREFIX指定的路径拼接<dir>**\n\n**2 绝对路径 安装到绝对路径**\n\n**PERMISSION** **指定权限**\n\n**CONFIGURATIONS** 指定版本\n\n**其他 少用**\n\n### 普通文件的安装\n\n**INSTALL(FILE file...**\n\n**DESTINATION <dir>**\n\n**[**PERMISSION permissions...**]**\n\n**[**CONFIGURATIONS [DEBUG|release|...]\n\n**[COMPONENT <component>]**\n\n**[RENAME <name>]**\n\n**[OPTIONAL])**\n\n**用于安装一般文件，可指定访问权限，文件名是此指定所在路径下的相对路径，如果不定义权限，安装后的权限为；**\n\n**OWNER\\_WRITE, OWNER\\_READ,**\n\n**GROUP\\_READ,**\n\n**WORLD\\_READ**\n\n** 即644权限**\n\n### 非目标可执行文件（如脚本）的安装\n\n**INSTALL(PROGRAMS files...**\n\n**DESTINATION <dir>**\n\n**[**PERMISSION permissions...**]**\n\n**[**CONFIGURATIONS [DEBUG|release|...]\n\n**[COMPONENT <component>]**\n\n**[RENAME <name>]**\n\n**[OPTIONAL])**\n\n**与普通文件差不多，但是默认权限不同。**\n\n**OWNER\\_EXECUTE,OWNER\\_WRITE,OWNER\\_READ,**\n\n**GROUP\\_EXECUTE,GROUP\\_READ**\n\n**WORLD\\_EXECUTE,WORLD\\_READ**\n\n**即755权限**\n\n### 目录的安装\n\n**INSTALL(DIRECTORY dirs... **\n\n**DESTINATION <dir>**\n\n**[FILE\\_PERMISSIONS permissions...]**\n\n**[DIRECTORY\\_PERMISSIONS permissions]**\n\n**[USE\\_SOURCE\\_PERMISSIONS permissions]**\n\n**[**CONFIGURATIONS [DEBUG|release|...]\n\n**[COMPONENT <component>]**\n\n**[**\n\n**[PATTERN <pattern> | REGEX <regex>]**\n\n**[EXCLUDE] [PERMISSIONS permissions...]**\n\n**])**\n\n**DIRECTORY:后面接的是所在源目录的相对路径，目录后面有没有“/”，区别很大，**\n\n**dir 和 dir/ 是不一样的**\n\n**dir是将dir这个目录安装为目标路径下的dir**\n\n**dir/ 是将这个目录下的内容安装到目标目录，但不包含这个目录本身。**\n\n**PATTERN  使用正则表达式过滤**\n\n**PERMISSIONS：用于指定PATTERN过滤后的文件权限**\n\n**举例：**\n\n**INSTALL(DIRECTORY samples modules/**\n\n**DESTINATION share**\n\n**PATTERN \"TXT\" EXCLUDE**\n\n**PATTERN  modules/\\***\n\n**PERMISSIONS OWNER\\_EXECUTE OWNER\\_WRITE OWNER\\_READ GROUP\\_EXECUTE)**\n\n**将smaple目录安装到 \\${CMAKE\\_INSTALL\\_PREFIX}/share  目录下**\n\n**将modules 目录中的内容安装到 \\${CMAKE\\_INSTALL\\_PREFIX}/share  目录下。**\n\n**不包含目录名为TXT的目录，对modlues目录下的文件指定权限为 OWNER\\_EXECUTE OWNER\\_WRITE OWNER\\_READ GROUP\\_EXECUTE**\n\n### 安装时执行cmake脚本\n\n**INSTALL([[SCRIPT <file>] [CODE <code>]])**\n\n**SCRIPT 用于在安装时调用cmake脚本文件，即xxxx.cmake文件**\n\n**CODE  行cmake 指令 要用双引号 如 **\n\n**INSTALL(CODE \"MESSAGE(\\\\\"sample install msg\\\\\")\")**\n\n```\n# 外层CMakeLists.txt\ncmake_minimum_required(VERSION 3.6)\nproject(cmake_install)\nadd_subdirectory(src)\ninstall(FILES copyright readme DESTINATION customize_install)\ninstall(PROGRAMS run_main.sh DESTINATION customize_install/bin)\ninstall(DIRECTORY doc/ DESTINATION customize_install/share/doc)\n\n#内容 src CMakeLists.txt\nset(EXECUTABLE_OUTPUT_PATH ${PROJECT_BINARY_DIR}/bin)\nset(LIBRARY_OUTPUT_PATH ${PROJECT_BINARY_DIR}/lib)\nadd_executable(main main.cpp)\ninstall(TARGETS main DESTINATION customize_install/bin)\n#执行命令\ncmake -DCMAKE_INSTALL_PREFIX=/usr/local/sbin\nmake install\n#make install 执行结果\nInstall the project...\n-- Install configuration: \"\"\n-- Installing: /usr/local/sbin/customize_install/copyright\n-- Installing: /usr/local/sbin/customize_install/readme\n-- Installing: /usr/local/sbin/customize_install/bin/run_main.sh\n-- Installing: /usr/local/sbin/customize_install/share/doc\n-- Installing: /usr/local/sbin/customize_install/share/doc/doc_readme\n-- Installing: /usr/local/sbin/customize_install/bin/main\n```\n\n## 常用命令\n\n```\n# 设置cmake最小版本\ncmake_minimum_required(VERSION 3.5)  \n# 设置项目名称 加不加\"\" 都可\nproject(\"footinfo\")\n\n# 设置变量ffmpeg_lib_dir = ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}\nset(ffmpeg_lib_dir ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})\n\n# GLOB 会产生一个由所有匹配globbing表达式的文件组成的列表，\n# 并将其保存到变量中。Globbing 表达式与正则表达式类似，但更简单。\nfile(GLOB variable [RELATIVE path] [globbingexpressions]...)\n# eg. 保存所以src目录下的cpp结尾的文件到 SRC_FILES 使用 ${SRC_FILES} 使用这些文件\nfile(GLOB SRC_FILES ${PROJECT_SOURCE_DIR}/src/*.cpp)\n# 添加名为name的库，库的源文件可指定，也可用target_sources()后续指定。\n# 库的类型是STATIC(静态库)/SHARED(动态库)/MODULE(模块库)之一。\n# name属性必须全局唯一\n# 生成的library名会根据STATIC或SHARED成为name.a或name.lib\nadd_library(<name> [STATIC | SHARED | MODULE]\n            [EXCLUDE_FROM_ALL]\n            [source1] [source2 ...])\n# 这种用法直接导入已经生成的库，cmake不会给这类library添加编译规则。\n# 这种用法的关键在于添加变量IMPORTED。\nadd_library(<name> <SHARED|STATIC|MODULE|OBJECT|UNKNOWN> IMPORTED\n            [GLOBAL])\n\n# 设置目标的属性 这里目标是avutil PROPERTIES表示属性 \n# IMPORTED_LOCATION表示要设置导入目录这个属性 name\n# ${ffmpeg_lib_dir}/libavutil.so	这里是路径 属性的value\nset_target_properties( avutil\n        PROPERTIES IMPORTED_LOCATION\n        ${ffmpeg_lib_dir}/libavutil.so )\n\n# 生成可执行文件main\nadd_executable(main ${SRC_FILES} main.cpp)\n\n# 添加头文件\ninclude_directories(${CMAKE_CURRENT_SOURCE_DIR}/include)\n```\n\n# QMake\n\n**设置目标文件输出目录**\n\n**UI\\_DIR：UIC将ui转化为头文件所存放的目录**\n\n**RCC\\_DIR：RCC将qrc文件转化为头文件所存放的目录**\n\n**MOC\\_DIR：MOC命令将含Q\\_OBJECT的头文件转换为标准的头文件存放的目录**\n\n**OBJECTS\\_DIR：生成的目标文件存放的目录**\n\n**DEFINES：定义编译选项，在编写程序时可以使用#ifndef xx\\_xxx\\_ …**\n\n**RC\\_FILE ：程序中所用到的图片等资源文件**\n\n**RESOURCES：加载要用到的资源\\*.qrc文件**\n\n# 错误记录\n\n## 编译USB1.0 提示 aclocal-1.15 is missing错误解决\n\n1. **安装autoMake sudo apt install automake**\n2. **提示can not exec libtoolize错误， 解决：**安装sudo apt install libtool\n\n## 配置串口固定端口\n\n**1、在**/etc/udev/rules.d/目录创建99-hid.rules文件，填入一下内容 id需要修改，代码中有\n\n**SUBSYSTEM==\"usb\", ATTR{idVendor}==\"2207\", ATTR{idProduct}==\"0002\", MODE=\"0666\"**\n\n**KERNEL=\"hidraw\\*\" ,ATTR{idVendor}==\"2207\", ATTR{idProduct}==\"0002\", MODE=\"0666\"**\n\n**其中：2207为pid;0002为vid;0666为权限**\n\n**2、修改权限chmod 755 99-hid.rules**\n\n**3、执行以下命令重新加载**\n\n**sudo service udev reload**\n\n**sudo service udev restart**\n\n**参考：**[https://blog.csdn.net/YiYeZhiNian/article/details/126124881](https://blog.csdn.net/YiYeZhiNian/article/details/126124881)\n','1','0','摘要','标签',0,'2023-08-25 05:50:51','2023-08-25 05:50:51',NULL),('1148212797978746880','测试标题','1',NULL,NULL,'1','测试正文\n','1','0','摘要','标签',0,'2023-09-04 03:07:44','2023-09-04 03:07:44','http://localhost:8080/images/1148212786314387456'),('2','C++入门','1','234','ppp','1','# C++动态开辟空间的方式\r\n\r\n## malloc\r\n\r\nint *pa = (int *)malloc(sizeof(int));*\r\n\r\nmalloc方法开辟的空间的返回的类型为 void*类型，需要强转为目标类型，空间中的内容为随机值。\r\n\r\nmalloc方法开辟一块空间\r\n\r\n## calloc\r\n\r\nint *pb = (int*)calloc(2, sizeof(int));\r\n\r\ncalloc方法开辟\"N\"块空间(N等于参数一)，空间中的内容为0；\r\n\r\n## new\r\n\r\nint *a = new int;	//开辟空间\r\n\r\ndelete a;	//释放a\r\n\r\nnew 开辟空间相当于malloc 实际上是对malloc的一层封装,delete是对free的一层封装。\r\n\r\n开辟的空间需要及时释放','1','0','C++入门','2',1,'2022-11-08 22:47:58','2023-01-06 22:48:18','https://s1.ax1x.com/2022/07/30/vikHoV.png');
/*!40000 ALTER TABLE `tb_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_categories`
--

DROP TABLE IF EXISTS `tb_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categories` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `pinyin` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '拼音',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `order` int NOT NULL COMMENT '顺序',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态：0表示不使用，1表示正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categories`
--

LOCK TABLES `tb_categories` WRITE;
/*!40000 ALTER TABLE `tb_categories` DISABLE KEYS */;
INSERT INTO `tb_categories` VALUES ('1','信息技术','xinxijishu','it',1,'1','2023-01-06 21:49:29','2023-01-06 21:49:31');
/*!40000 ALTER TABLE `tb_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_comment`
--

DROP TABLE IF EXISTS `tb_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_comment` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `parent_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '父内容',
  `article_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论用户的ID',
  `user_avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论用户的头像',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论用户的名称',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0表示删除，1表示正常）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_user_comment_on_user_id` (`user_id`) USING BTREE,
  KEY `fk_article_comment_on_article_id` (`article_id`) USING BTREE,
  CONSTRAINT `fk_article_comment_on_article_id` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`),
  CONSTRAINT `fk_user_comment_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_comment`
--

LOCK TABLES `tb_comment` WRITE;
/*!40000 ALTER TABLE `tb_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_daily_view_count`
--

DROP TABLE IF EXISTS `tb_daily_view_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_daily_view_count` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `view_count` int NOT NULL DEFAULT '0' COMMENT '每天浏览量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_daily_view_count`
--

LOCK TABLES `tb_daily_view_count` WRITE;
/*!40000 ALTER TABLE `tb_daily_view_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_daily_view_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_friends`
--

DROP TABLE IF EXISTS `tb_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_friends` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接名称',
  `logo` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接logo',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接',
  `order` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '友情链接状态:0表示不可用，1表示正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_friends`
--

LOCK TABLES `tb_friends` WRITE;
/*!40000 ALTER TABLE `tb_friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_images`
--

DROP TABLE IF EXISTS `tb_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_images` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '状态（0表示删除，1表正常）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `real_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_user_images_on_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `fk_user_images_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_images`
--

LOCK TABLES `tb_images` WRITE;
/*!40000 ALTER TABLE `tb_images` DISABLE KEYS */;
INSERT INTO `tb_images` VALUES ('1148209466677960704','1','http://localhost:8080/upload/1/20230904/标准华人.png','1','2023-09-04 02:54:30','2023-09-04 02:54:30','E:\\springbootproject\\lisBlog\\images\\1\\20230904\\标准华人.png','标准华人.png'),('1148212786314387456','1','http://localhost:8080/images/1148212786314387456','1','2023-09-04 03:07:41','2023-09-04 03:07:41','E:\\springbootproject\\lisBlog\\images\\1\\20230904\\android_rebot.png','android_rebot.png');
/*!40000 ALTER TABLE `tb_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_labels`
--

DROP TABLE IF EXISTS `tb_labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_labels` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  `count` int NOT NULL DEFAULT '0' COMMENT '数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_labels`
--

LOCK TABLES `tb_labels` WRITE;
/*!40000 ALTER TABLE `tb_labels` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_labels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_looper`
--

DROP TABLE IF EXISTS `tb_looper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_looper` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '轮播图标题',
  `order` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态：0表示不可用，1表示正常',
  `target_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标URL',
  `image_url` varchar(2014) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_looper`
--

LOCK TABLES `tb_looper` WRITE;
/*!40000 ALTER TABLE `tb_looper` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_looper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_settings`
--

DROP TABLE IF EXISTS `tb_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_settings` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '键',
  `value` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_settings`
--

LOCK TABLES `tb_settings` WRITE;
/*!40000 ALTER TABLE `tb_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `roles` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色(0 游客  1 登录用户  2 超级管理员)',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像地址',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '签名',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态：0表示删除，1表示正常',
  `reg_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '注册ip',
  `login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录Ip',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('1','admin','$2a$10$tDNZ1Cae.CKiIXRxhZ6wwe8hdbAngcVLz1WW890b3ck6Cf62BGHee','1','123',NULL,NULL,'1','1','1','2023-01-06 21:48:41','2023-01-06 21:48:46');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'blog_system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04 11:12:10
