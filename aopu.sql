-- MySQL dump 10.13  Distrib 5.7.32, for Win64 (x86_64)
--
-- Host: localhost    Database: aopu
-- ------------------------------------------------------
-- Server version	5.7.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `id` char(36) NOT NULL,
  `province` varchar(10) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `corporatename` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `agentname` varchar(64) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(64) DEFAULT NULL COMMENT '联系方式',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `education` varchar(10) DEFAULT NULL COMMENT '学历',
  `agentbrand` varchar(64) DEFAULT NULL COMMENT '代理品牌',
  `cooperationtime` datetime DEFAULT NULL COMMENT '合作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES ('0040bf7f-ca22-11ee-96d8-bce92ffe0af2','浙江省','杭州市','测试1','1111','111',11,'11','111','2024-02-11 16:00:00'),('4a8d40d5-cf2e-11ee-9001-bce92ffe0af2','浙江省','杭州市','asdasd','啊实打实的','18857111111',11,'本科','12123','2024-02-18 00:00:00'),('4fcdf52d-cf2e-11ee-9001-bce92ffe0af2','浙江省','杭州市','asdasd111','啊实打实的','18857111111',11,'本科','12123','2024-02-18 00:00:00'),('54c7ec52-cf2e-11ee-9001-bce92ffe0af2','浙江省','杭州市','asdasd1111','啊实打实的','1885711111111',111,'本科1','121231','2024-02-18 00:00:00'),('8d627c93-c713-11ee-96d8-bce92ffe0af2','浙江省','杭州市','test','admin','18812345678',18,'本科','test','2024-02-05 18:52:22'),('8d62807a-c713-11ee-96d8-bce92ffe0af2','浙江省','杭州市','2','admin2','18812345678',18,'本科','test11111','2024-02-05 16:00:00');
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `id` varchar(36) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `descr` varchar(255) DEFAULT NULL COMMENT '简介',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `user_id` varchar(36) DEFAULT NULL COMMENT '发布人id',
  `date` datetime DEFAULT NULL COMMENT '发布日期',
  `read_count` int(11) DEFAULT NULL COMMENT '阅读量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES ('106ee89f-cd73-11ee-bffc-bce92ffe0af2','111','111',NULL,NULL,'123456','2024-02-17 00:00:00',NULL),('5f59b51c-cd73-11ee-bffc-bce92ffe0af2','123123','<p>1231231</p>',NULL,NULL,'123456','2024-02-17 00:00:00',NULL),('820b273a-cf92-11ee-9001-bce92ffe0af2','测试','<p>测试</p>',NULL,NULL,'123456','2024-02-20 00:00:00',NULL),('d9c2f289-cd5c-11ee-bffc-bce92ffe0af2','测试写文章','<p style=\"text-align: left;\">今天天气不错</p><p style=\"text-align: left;\"><strong>我要起飞！</strong></p><p style=\"text-align: left;\"><u>111</u></p><p style=\"text-align: left;\"><span style=\"color: rgb(245, 219, 77);\">我好帅</span></p><p style=\"text-align: left;\"><br></p>',NULL,NULL,'123456','2024-02-17 00:00:00',NULL),('dc7fdf65-cf92-11ee-9001-bce92ffe0af2','测试2','<p>222</p>',NULL,NULL,'123456','2024-02-20 00:00:00',NULL);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `id` char(36) NOT NULL,
  `category` varchar(30) DEFAULT NULL COMMENT '品类',
  `goodname` varchar(60) DEFAULT NULL COMMENT '商品名称',
  `factoryprice` double DEFAULT NULL COMMENT '出厂价',
  `transactionprice` double DEFAULT NULL COMMENT '成交价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES ('32426b71-cbd0-11ee-b774-bce92ffe0af2','test','test',110,120),('6253a7ee-cbd1-11ee-b774-bce92ffe0af2','111','111',111,111);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasing`
--

DROP TABLE IF EXISTS `purchasing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchasing` (
  `id` char(36) NOT NULL,
  `corporatename` varchar(192) DEFAULT NULL COMMENT '公司名称',
  `purchasedate` datetime DEFAULT NULL COMMENT '进货日期',
  `category` varchar(30) DEFAULT NULL COMMENT '类别',
  `goodname` varchar(30) DEFAULT NULL COMMENT '商品名称',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` double DEFAULT NULL COMMENT '金额',
  `discount` double DEFAULT NULL COMMENT '折扣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasing`
--

LOCK TABLES `purchasing` WRITE;
/*!40000 ALTER TABLE `purchasing` DISABLE KEYS */;
INSERT INTO `purchasing` VALUES ('0934a869-c714-11ee-96d8-bce92ffe0af2','test','2024-02-05 18:54:04','test','123',100,100,0.5);
/*!40000 ALTER TABLE `purchasing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale` (
  `id` char(36) NOT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `agentname` varchar(30) DEFAULT NULL COMMENT '代理商公司名称',
  `storename` varchar(30) DEFAULT NULL COMMENT '门店名称',
  `salesman` varchar(30) DEFAULT NULL COMMENT '业务员',
  `price` double DEFAULT NULL COMMENT '销售金额',
  `goodname` varchar(60) DEFAULT NULL COMMENT '商品名称',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `writedate` datetime DEFAULT NULL COMMENT '填写日期',
  `orderimgurl` varchar(256) DEFAULT NULL COMMENT '订单图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES ('1da51c26-c714-11ee-96d8-bce92ffe0af2','浙江','杭州','corporate','store','admin',100,'商品1',1,'2024-02-05 16:00:00','http://localhost:8089/images/1708077987644.png'),('2e247e67-cf28-11ee-9001-bce92ffe0af2','浙江省','杭州市','某公司','某门店','我',111,'某商品',100,'2024-02-18 00:00:00',''),('b8cfdead-ccbd-11ee-b774-bce92ffe0af2','浙江省','杭州市','111公司','啊啊','a11',111,'商品2',11,'2024-02-16 00:00:00','http://localhost:8089/images/1708077987644.png');
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesman`
--

DROP TABLE IF EXISTS `salesman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesman` (
  `id` char(36) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `corporatename` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `education` varchar(64) DEFAULT NULL COMMENT '学历',
  `joindate` datetime DEFAULT NULL COMMENT '入司时间',
  `managearea` varchar(64) DEFAULT NULL COMMENT '管理区域'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesman`
--

LOCK TABLES `salesman` WRITE;
/*!40000 ALTER TABLE `salesman` DISABLE KEYS */;
INSERT INTO `salesman` VALUES ('3f8ebb62-c714-11ee-96d8-bce92ffe0af2','浙江省','杭州市','AOPU','admin','18812345678',20,'本科','2024-02-05 16:00:00','杭州'),('23a7126f-cbd2-11ee-b774-bce92ffe0af2','江苏省','南京市','111','111','111',111,'111','2024-02-13 16:00:00','111');
/*!40000 ALTER TABLE `salesman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` char(36) NOT NULL,
  `province` varchar(30) DEFAULT NULL COMMENT '省',
  `city` varchar(30) DEFAULT NULL,
  `corporatename` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `storename` varchar(30) DEFAULT NULL COMMENT '门店名称',
  `storeclass` varchar(30) DEFAULT NULL COMMENT '门店类别',
  `area` varchar(30) DEFAULT NULL COMMENT '面积',
  `segmentation` varchar(30) DEFAULT NULL COMMENT '市场分类',
  `opentime` datetime DEFAULT NULL COMMENT '开业时间',
  `shopguide` varchar(30) DEFAULT NULL COMMENT '导购',
  `telephone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES ('04b317c8-cf3c-11ee-9001-bce92ffe0af2','浙江省','杭州市','11111','1111','11111','111','111','2024-02-18 16:00:00','我','110'),('17afcf27-cf3c-11ee-9001-bce92ffe0af2','浙江省','杭州市','11111','1111','11111','111','111','2024-02-18 16:00:00','我','110'),('26109d96-c720-11ee-96d8-bce92ffe0af2','上海市','黄浦区',NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL),('2ce87c0e-cf3c-11ee-9001-bce92ffe0af2','浙江省','杭州市','11111','1111','11111','111','111','2024-02-18 16:00:00','我','110'),('4c4c9bec-c72f-11ee-96d8-bce92ffe0af2','上海市','黄浦区',NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL),('68868c4d-c71f-11ee-96d8-bce92ffe0af2','浙江省','杭州市','111','111','0','100.3','上海','2024-02-23 00:00:00','我','123'),('6b359a2c-c7fd-11ee-96d8-bce92ffe0af2','浙江省','杭州市','公司','商店','1','6.66','?','2611-05-22 16:36:22','老王','12234567890'),('7683239f-cd4e-11ee-bffc-bce92ffe0af2','浙江省','杭州市','111111','1111','1','11111','1','2024-02-15 16:00:00','我','10086'),('87243f98-c71f-11ee-96d8-bce92ffe0af2','浙江省','杭州市','222','123','0','13.2','111','2024-02-04 00:00:00','老王','123'),('933f32b0-c8d8-11ee-96d8-bce92ffe0af2','浙江省','杭州市','1111111','1','1','1','0','2024-02-09 16:00:00','1','1'),('ad2dfeee-cd4e-11ee-bffc-bce92ffe0af2','浙江省','杭州市','111111','1111','1','11111','1','2024-02-15 16:00:00','我','10086'),('b771fb15-cf3b-11ee-9001-bce92ffe0af2','浙江省','杭州市','11111','1111','11111','111','111','2024-02-18 16:00:00','我','110'),('c27bcb95-c71f-11ee-96d8-bce92ffe0af2','浙江省','杭州市','1','1','1','1','1','2024-02-09 00:00:00','1','1'),('d0430019-c71f-11ee-96d8-bce92ffe0af2','浙江省','杭州市','1','1','1','123','123','2024-02-20 00:00:00','123','123'),('d92a53b9-c71f-11ee-96d8-bce92ffe0af2','浙江省','杭州市','22','2123','1','123','123','2024-02-20 00:00:00','123','123'),('e2da9d3a-c71f-11ee-96d8-bce92ffe0af2','浙江省','杭州市','22','22','2','2','22','2024-02-22 00:00:00','为','22'),('e8c0885f-c829-11ee-96d8-bce92ffe0af2','北京市','东城区','111','123','123','123','更新记录','2024-02-08 16:00:00','123','123'),('fea43872-c712-11ee-96d8-bce92ffe0af2','浙江省','杭州市','test2','test1','1','100','杭州','2024-02-09 00:00:00','admin','18812345678'),('fea43a44-c712-11ee-96d8-bce92ffe0af2','浙江省','杭州市','test','test1','1','100','杭州','2024-02-05 00:00:00','admin','18812345678'),('fef3eed6-c71f-11ee-96d8-bce92ffe0af2','江苏省','南京市','公司1','不知道','3','100','分类1','2024-02-08 16:00:00','我','10086');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` char(36) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `corporatename` varchar(60) DEFAULT NULL COMMENT '公司名称',
  `storename` varchar(60) DEFAULT NULL COMMENT '门店名称',
  `password` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('347f3d7a-ccdb-11ee-b774-bce92ffe0af2','whc','111','111',NULL,NULL,'123456'),('51a41551-c714-11ee-96d8-bce92ffe0af2','admin','111112222','18857112432','杭州市江干区21号大街210号','杭州市江干区21号大街210','123456'),('655464b6-ccdd-11ee-b774-bce92ffe0af2','whhc','123456','12306','公司1','代理商1','123456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-25 18:05:17
