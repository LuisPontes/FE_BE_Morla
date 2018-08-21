CREATE DATABASE  IF NOT EXISTS `schema_bd_cat` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `schema_bd_cat`;
-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: schema_bd_cat
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_content`
--

DROP TABLE IF EXISTS `tb_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_content` (
  `id` int(10) unsigned NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `sinopse` varchar(45) DEFAULT NULL,
  `ano` varchar(45) DEFAULT NULL,
  `coreografia` varchar(45) DEFAULT NULL,
  `interpretacao` varchar(45) DEFAULT NULL,
  `musica` varchar(45) DEFAULT NULL,
  `figurinos` varchar(45) DEFAULT NULL,
  `edicaoMusical` varchar(45) DEFAULT NULL,
  `fotografia` varchar(45) DEFAULT NULL,
  `video` varchar(45) DEFAULT NULL,
  `duracao` varchar(45) DEFAULT NULL,
  `classE` varchar(45) DEFAULT NULL,
  `ap_publicas` varchar(45) DEFAULT NULL,
  `agradecimentos` varchar(45) DEFAULT NULL,
  `option1` varchar(45) DEFAULT NULL,
  `option2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_content`
--

LOCK TABLES `tb_content` WRITE;
/*!40000 ALTER TABLE `tb_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_separadores`
--

DROP TABLE IF EXISTS `tb_separadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_separadores` (
  `id` int(10) unsigned NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `img` varchar(45) DEFAULT NULL,
  `lastUpdate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_separadores`
--

LOCK TABLES `tb_separadores` WRITE;
/*!40000 ALTER TABLE `tb_separadores` DISABLE KEYS */;
INSERT INTO `tb_separadores` VALUES (1,'tes','tse','tset'),(2,'dfgdfg','gdfgdfg','tdfgdfg'),(3,'dgd','dfgd','dfg');
/*!40000 ALTER TABLE `tb_separadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'schema_bd_cat'
--

--
-- Dumping routines for database 'schema_bd_cat'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-20 18:32:38
