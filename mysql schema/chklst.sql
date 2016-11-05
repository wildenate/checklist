CREATE DATABASE  IF NOT EXISTS `chklst` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `chklst`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: chklst
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `itm`
--

DROP TABLE IF EXISTS `itm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itm` (
  `itm_id` int(11) NOT NULL AUTO_INCREMENT,
  `lst_id` int(11) NOT NULL,
  `itm_txt` varchar(45) NOT NULL,
  `cmpltd` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`itm_id`),
  UNIQUE KEY `itm_id_UNIQUE` (`itm_id`),
  KEY `fk_itm_lst_idx` (`lst_id`),
  CONSTRAINT `fk_itm_lst` FOREIGN KEY (`lst_id`) REFERENCES `lst` (`lst_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itm`
--

LOCK TABLES `itm` WRITE;
/*!40000 ALTER TABLE `itm` DISABLE KEYS */;
INSERT INTO `itm` VALUES (21,12,'Milk','N'),(22,12,'Chicken','N'),(23,12,'Cheese','N'),(24,13,'Mow','N'),(25,13,'Rake','N'),(26,14,'Founders All Day','N'),(27,14,'Fantasy Factory','N'),(28,16,'Vacuum','N'),(29,16,'Dust','N'),(30,17,'Fix broken stove','N'),(31,17,'Clean dryer vent','N');
/*!40000 ALTER TABLE `itm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lst`
--

DROP TABLE IF EXISTS `lst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lst` (
  `lst_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_id` int(11) NOT NULL,
  `lst_nm` varchar(45) NOT NULL,
  PRIMARY KEY (`lst_id`),
  UNIQUE KEY `lst_id_UNIQUE` (`lst_id`),
  KEY `fk_usr_lst_idx` (`usr_id`),
  CONSTRAINT `fk_lst_usr` FOREIGN KEY (`usr_id`) REFERENCES `usr` (`usr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lst`
--

LOCK TABLES `lst` WRITE;
/*!40000 ALTER TABLE `lst` DISABLE KEYS */;
INSERT INTO `lst` VALUES (12,18,'Groceries'),(13,18,'Yard work'),(14,19,'Beer run'),(16,20,'Chores'),(17,20,'TODO');
/*!40000 ALTER TABLE `lst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usr`
--

DROP TABLE IF EXISTS `usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usr` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_nm` varchar(45) NOT NULL,
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `usr_id_UNIQUE` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usr`
--

LOCK TABLES `usr` WRITE;
/*!40000 ALTER TABLE `usr` DISABLE KEYS */;
INSERT INTO `usr` VALUES (18,'Frank'),(19,'Bob'),(20,'Joe');
/*!40000 ALTER TABLE `usr` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-05 17:15:19
