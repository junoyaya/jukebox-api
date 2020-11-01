-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: jukebox
-- ------------------------------------------------------
-- Server version	5.6.24-enterprise-commercial-advanced-log

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
-- Table structure for table `hardware_component`
--

DROP TABLE IF EXISTS `hardware_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hardware_component` (
  `id` varchar(255) NOT NULL,
  `archived` bit(1) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hardware_component`
--

LOCK TABLES `hardware_component` WRITE;
/*!40000 ALTER TABLE `hardware_component` DISABLE KEYS */;
INSERT INTO `hardware_component` VALUES ('1144bb54-d4d9-45cb-93b7-9709f41ae28f',NULL,NULL,NULL,NULL,NULL,'camera'),('3e3f8fc2-eaec-48f3-9ca8-eb8fc86de248',NULL,NULL,NULL,NULL,NULL,'led_panel'),('4c49e309-d9bb-4659-9735-c0e4364dbbf6',NULL,NULL,NULL,NULL,NULL,'amplifier'),('b45e9eae-83c6-4e8c-8e5c-cbc6736041af',NULL,NULL,NULL,NULL,NULL,'pcb'),('b54dd9eb-0dd1-4652-84c4-32fdf88f78ef',NULL,NULL,NULL,NULL,NULL,'money_pcb'),('b6d9e027-16f0-4c8b-9885-7ab004e2782d',NULL,NULL,NULL,NULL,NULL,'money_receiver'),('d9073335-585a-47ed-939d-d9ce72bd403d',NULL,NULL,NULL,NULL,NULL,'speaker'),('e712f394-d659-4d9a-814a-425380af366b',NULL,NULL,NULL,NULL,NULL,'touchscreen');
/*!40000 ALTER TABLE `hardware_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juke`
--

DROP TABLE IF EXISTS `juke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juke` (
  `id` varchar(255) NOT NULL,
  `archived` bit(1) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juke`
--

LOCK TABLES `juke` WRITE;
/*!40000 ALTER TABLE `juke` DISABLE KEYS */;
INSERT INTO `juke` VALUES ('1b739e81-eef2-4e2d-8cb4-e65496fb1395',NULL,NULL,NULL,NULL,NULL,'mars'),('8823149e-6e06-4c66-9aaf-00e24907b4fb',NULL,NULL,NULL,NULL,NULL,'moon'),('de64243e-b217-4423-a162-7d769d0a28f2',NULL,NULL,NULL,NULL,NULL,'fusion');
/*!40000 ALTER TABLE `juke` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juke_component`
--

DROP TABLE IF EXISTS `juke_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juke_component` (
  `juke_id` varchar(255) NOT NULL,
  `hardware_component_id` varchar(255) NOT NULL,
  KEY `FKb2xwd0tqea31e8n1nnjde594m` (`hardware_component_id`),
  KEY `FK2jji8b1pigcqmh618f3x0vdwp` (`juke_id`),
  CONSTRAINT `FK2jji8b1pigcqmh618f3x0vdwp` FOREIGN KEY (`juke_id`) REFERENCES `juke` (`id`),
  CONSTRAINT `FKb2xwd0tqea31e8n1nnjde594m` FOREIGN KEY (`hardware_component_id`) REFERENCES `hardware_component` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juke_component`
--

LOCK TABLES `juke_component` WRITE;
/*!40000 ALTER TABLE `juke_component` DISABLE KEYS */;
INSERT INTO `juke_component` VALUES ('de64243e-b217-4423-a162-7d769d0a28f2','b6d9e027-16f0-4c8b-9885-7ab004e2782d'),('de64243e-b217-4423-a162-7d769d0a28f2','3e3f8fc2-eaec-48f3-9ca8-eb8fc86de248'),('de64243e-b217-4423-a162-7d769d0a28f2','4c49e309-d9bb-4659-9735-c0e4364dbbf6'),('1b739e81-eef2-4e2d-8cb4-e65496fb1395','b6d9e027-16f0-4c8b-9885-7ab004e2782d'),('1b739e81-eef2-4e2d-8cb4-e65496fb1395','e712f394-d659-4d9a-814a-425380af366b'),('1b739e81-eef2-4e2d-8cb4-e65496fb1395','3e3f8fc2-eaec-48f3-9ca8-eb8fc86de248'),('8823149e-6e06-4c66-9aaf-00e24907b4fb','e712f394-d659-4d9a-814a-425380af366b'),('8823149e-6e06-4c66-9aaf-00e24907b4fb','4c49e309-d9bb-4659-9735-c0e4364dbbf6');
/*!40000 ALTER TABLE `juke_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `id` varchar(255) NOT NULL,
  `archived` bit(1) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES ('2bec84a9-30ad-451e-9c97-fc6d6f5063fe',NULL,NULL,NULL,NULL,NULL,'currency'),('ab8faa56-15ae-48a5-883e-a12fa7ed783f',NULL,NULL,NULL,NULL,NULL,'animation_type'),('c021e427-a113-4ae6-930c-214ce6003acd',NULL,NULL,NULL,NULL,NULL,'outdoor');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting_component`
--

DROP TABLE IF EXISTS `setting_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting_component` (
  `setting_id` varchar(255) NOT NULL,
  `hardware_component_id` varchar(255) NOT NULL,
  KEY `FKgq5krhtqs7o0jb73yn87qaqxc` (`hardware_component_id`),
  KEY `FK885qatd44ko9djg8t8rsymi1w` (`setting_id`),
  CONSTRAINT `FK885qatd44ko9djg8t8rsymi1w` FOREIGN KEY (`setting_id`) REFERENCES `setting` (`id`),
  CONSTRAINT `FKgq5krhtqs7o0jb73yn87qaqxc` FOREIGN KEY (`hardware_component_id`) REFERENCES `hardware_component` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting_component`
--

LOCK TABLES `setting_component` WRITE;
/*!40000 ALTER TABLE `setting_component` DISABLE KEYS */;
INSERT INTO `setting_component` VALUES ('2bec84a9-30ad-451e-9c97-fc6d6f5063fe','b6d9e027-16f0-4c8b-9885-7ab004e2782d'),('ab8faa56-15ae-48a5-883e-a12fa7ed783f','3e3f8fc2-eaec-48f3-9ca8-eb8fc86de248'),('c021e427-a113-4ae6-930c-214ce6003acd','4c49e309-d9bb-4659-9735-c0e4364dbbf6');
/*!40000 ALTER TABLE `setting_component` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-01 12:29:50
