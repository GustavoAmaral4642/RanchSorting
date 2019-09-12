-- MySQL dump 10.13  Distrib 5.6.15, for Win64 (x86_64)
--
-- Host: localhost    Database: ranchsorting
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `tb_animal`
--

DROP TABLE IF EXISTS `tb_animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_animal` (
  `an_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `an_cor` varchar(50) DEFAULT NULL,
  `an_data_alteracao` datetime DEFAULT NULL,
  `an_etnia` varchar(15) DEFAULT NULL,
  `an_idade` bigint(20) DEFAULT NULL,
  `an_nome` varchar(120) NOT NULL,
  `an_raca` varchar(80) DEFAULT NULL,
  `an_competidor_id` bigint(20) DEFAULT NULL,
  `an_ocorrencia` bigint(20) DEFAULT NULL,
  `an_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`an_id`),
  KEY `FK_nno96ivbw1777x0d70juphtj4` (`an_competidor_id`),
  KEY `FK_qa3iudwchb9bkhjth3bs54m3n` (`an_ocorrencia`),
  KEY `FK_4a1jaw4vc0o3u4bobkalvqtgh` (`an_us_alteracao`),
  CONSTRAINT `FK_4a1jaw4vc0o3u4bobkalvqtgh` FOREIGN KEY (`an_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_nno96ivbw1777x0d70juphtj4` FOREIGN KEY (`an_competidor_id`) REFERENCES `tb_competidor` (`cp_id`),
  CONSTRAINT `FK_qa3iudwchb9bkhjth3bs54m3n` FOREIGN KEY (`an_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_animal`
--

LOCK TABLES `tb_animal` WRITE;
/*!40000 ALTER TABLE `tb_animal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_anuidade`
--

DROP TABLE IF EXISTS `tb_anuidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_anuidade` (
  `anu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `anu_data_alteracao` datetime DEFAULT NULL,
  `anu_data_pagamento` datetime NOT NULL,
  `anu_observacao` text,
  `anu_valor_pago` decimal(10,2) NOT NULL,
  `anu_campeonato` bigint(20) NOT NULL,
  `anu_competidor_id` bigint(20) NOT NULL,
  `anu_ocorrencia` bigint(20) DEFAULT NULL,
  `anu_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`anu_id`),
  KEY `FK_5d2mhj0dif3i05qpkkscm3sg5` (`anu_campeonato`),
  KEY `FK_ncf0wib6hcebhe7g8qsutt4lj` (`anu_competidor_id`),
  KEY `FK_gpnolcdtfsflymtwvcf6rt1i5` (`anu_ocorrencia`),
  KEY `FK_plfu64rfcnw1158ey9sfa1yvr` (`anu_us_alteracao`),
  CONSTRAINT `FK_5d2mhj0dif3i05qpkkscm3sg5` FOREIGN KEY (`anu_campeonato`) REFERENCES `tb_campeonato` (`cp_id`),
  CONSTRAINT `FK_gpnolcdtfsflymtwvcf6rt1i5` FOREIGN KEY (`anu_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`),
  CONSTRAINT `FK_ncf0wib6hcebhe7g8qsutt4lj` FOREIGN KEY (`anu_competidor_id`) REFERENCES `tb_competidor` (`cp_id`),
  CONSTRAINT `FK_plfu64rfcnw1158ey9sfa1yvr` FOREIGN KEY (`anu_us_alteracao`) REFERENCES `tb_usuario` (`us_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_anuidade`
--

LOCK TABLES `tb_anuidade` WRITE;
/*!40000 ALTER TABLE `tb_anuidade` DISABLE KEYS */;
INSERT INTO `tb_anuidade` VALUES (1,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,1,NULL,NULL),(2,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,2,NULL,NULL),(3,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,3,NULL,NULL),(4,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,4,NULL,NULL),(5,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,5,NULL,NULL),(6,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,6,NULL,NULL),(7,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,7,NULL,NULL),(8,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,8,NULL,NULL),(9,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,9,NULL,NULL),(10,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,10,NULL,NULL),(11,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,11,NULL,NULL),(12,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,12,NULL,NULL),(13,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,13,NULL,NULL),(14,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,14,NULL,NULL),(15,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,15,NULL,NULL),(16,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,16,NULL,NULL),(17,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,17,NULL,NULL),(18,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,18,NULL,NULL),(19,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,19,NULL,NULL),(20,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,20,NULL,NULL),(21,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,21,NULL,NULL),(22,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,22,NULL,NULL),(23,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,23,NULL,NULL),(24,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,24,NULL,NULL),(25,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,25,NULL,NULL),(26,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,26,NULL,NULL),(27,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,27,NULL,NULL),(28,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,28,NULL,NULL),(29,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,29,NULL,NULL),(30,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,30,NULL,NULL),(31,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,31,NULL,NULL),(32,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,32,NULL,NULL),(33,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,33,NULL,NULL),(34,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,34,NULL,NULL),(35,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,35,NULL,NULL),(36,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,36,NULL,NULL),(37,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,37,NULL,NULL),(38,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,38,NULL,NULL),(39,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,39,NULL,NULL),(40,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,40,NULL,NULL),(41,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,41,NULL,NULL),(42,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,42,NULL,NULL),(43,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,43,NULL,NULL),(44,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,44,NULL,NULL),(45,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,45,NULL,NULL),(46,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,46,NULL,NULL),(47,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,47,NULL,NULL),(48,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,48,NULL,NULL),(49,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,49,NULL,NULL),(50,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,50,NULL,NULL),(51,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,51,NULL,NULL),(52,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,52,NULL,NULL),(53,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,53,NULL,NULL),(54,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,54,NULL,NULL),(55,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,55,NULL,NULL),(56,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,56,NULL,NULL),(57,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,57,NULL,NULL),(58,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,58,NULL,NULL),(59,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,59,NULL,NULL),(60,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,60,NULL,NULL),(61,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,61,NULL,NULL),(62,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,62,NULL,NULL),(63,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,63,NULL,NULL),(64,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,64,NULL,NULL),(65,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,65,NULL,NULL),(66,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,66,NULL,NULL),(67,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,67,NULL,NULL),(68,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,68,NULL,NULL),(69,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,69,NULL,NULL),(70,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,70,NULL,NULL),(71,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,71,NULL,NULL),(72,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,72,NULL,NULL),(73,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,73,NULL,NULL),(74,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,74,NULL,NULL),(75,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,75,NULL,NULL),(76,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,76,NULL,NULL),(77,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,77,NULL,NULL),(78,NULL,'2019-08-10 00:00:00','Lançado na implantação',100.00,1,78,NULL,NULL),(80,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,79,NULL,NULL),(81,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,80,NULL,NULL),(82,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,81,NULL,NULL),(83,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,82,NULL,NULL),(84,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,83,NULL,NULL),(85,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,84,NULL,NULL),(87,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,85,NULL,NULL),(88,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,86,NULL,NULL),(89,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,87,NULL,NULL),(90,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,88,NULL,NULL),(91,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,89,NULL,NULL),(92,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,90,NULL,NULL),(93,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,91,NULL,NULL),(94,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,92,NULL,NULL),(95,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,93,NULL,NULL),(96,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,94,NULL,NULL),(97,NULL,'2019-08-10 00:00:00','Lançado na implantação',50.00,1,95,NULL,NULL),(98,NULL,'2019-08-11 00:00:00','',50.00,1,97,NULL,NULL);
/*!40000 ALTER TABLE `tb_anuidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_campeonato`
--

DROP TABLE IF EXISTS `tb_campeonato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_campeonato` (
  `cp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cp_data_abertura` date DEFAULT NULL,
  `cp_data_alteracao` datetime DEFAULT NULL,
  `cp_data_termino` date DEFAULT NULL,
  `cp_nome` varchar(120) NOT NULL,
  `cp_observacao` text,
  `cp_tipo_campeonato` varchar(15) NOT NULL,
  `cp_valor_anuidade` decimal(10,2) DEFAULT NULL,
  `cp_ocorrencia` bigint(20) DEFAULT NULL,
  `cp_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cp_id`),
  KEY `FK_ne4yti1nu41dequtcxhnoguem` (`cp_ocorrencia`),
  KEY `FK_b6kq5nm3f4ij19fv423bltsn` (`cp_us_alteracao`),
  CONSTRAINT `FK_b6kq5nm3f4ij19fv423bltsn` FOREIGN KEY (`cp_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_ne4yti1nu41dequtcxhnoguem` FOREIGN KEY (`cp_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_campeonato`
--

LOCK TABLES `tb_campeonato` WRITE;
/*!40000 ALTER TABLE `tb_campeonato` DISABLE KEYS */;
INSERT INTO `tb_campeonato` VALUES (1,'2019-01-01',NULL,'2019-12-31','For Friends','','RANCHSORTING',100.00,NULL,NULL);
/*!40000 ALTER TABLE `tb_campeonato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_competidor`
--

DROP TABLE IF EXISTS `tb_competidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_competidor` (
  `cp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cp_contato` varchar(160) DEFAULT NULL,
  `cp_data_alteracao` datetime DEFAULT NULL,
  `cp_dt_nascimento` date DEFAULT NULL,
  `cp_doc_responsavel` varchar(50) DEFAULT NULL,
  `cp_etnia` varchar(15) NOT NULL,
  `cp_idade` bigint(20) NOT NULL,
  `cp_nome` varchar(160) NOT NULL,
  `cp_responsavel` varchar(160) DEFAULT NULL,
  `cp_ocorrencia` bigint(20) DEFAULT NULL,
  `cp_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cp_id`),
  KEY `FK_6gdg6qjy6i64wwigtg17uf3va` (`cp_ocorrencia`),
  KEY `FK_3ifdxf90q4y7ad1m0i89ubv9h` (`cp_us_alteracao`),
  CONSTRAINT `FK_3ifdxf90q4y7ad1m0i89ubv9h` FOREIGN KEY (`cp_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_6gdg6qjy6i64wwigtg17uf3va` FOREIGN KEY (`cp_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_competidor`
--

LOCK TABLES `tb_competidor` WRITE;
/*!40000 ALTER TABLE `tb_competidor` DISABLE KEYS */;
INSERT INTO `tb_competidor` VALUES (1,'',NULL,NULL,'','MASCULINO',20,'Adriano José da Silva','',NULL,NULL),(2,'',NULL,NULL,'','MASCULINO',20,'Alef Silva','',NULL,NULL),(3,'',NULL,NULL,'','MASCULINO',20,'Allan Bife','',NULL,NULL),(4,'',NULL,NULL,'','MASCULINO',20,'Anderson Biconletto (Pitú) ','',NULL,NULL),(5,'',NULL,NULL,'','MASCULINO',20,'André Fernandes ','',NULL,NULL),(6,'',NULL,NULL,'','MASCULINO',20,'André Maciel ','',NULL,NULL),(7,'',NULL,NULL,'','MASCULINO',20,'André Perinotto ','',NULL,NULL),(8,'',NULL,NULL,'','MASCULINO',20,' André Tonizza','',NULL,NULL),(9,'',NULL,NULL,'','MASCULINO',20,'Andrezinho ','',NULL,NULL),(10,'',NULL,NULL,'','MASCULINO',20,'Barroquinha','',NULL,NULL),(11,'',NULL,NULL,'','MASCULINO',20,'Beto Tessari ','',NULL,NULL),(12,'',NULL,NULL,'','MASCULINO',20,'Bruninho Descalvado','',NULL,NULL),(13,'',NULL,NULL,'','MASCULINO',20,'Bruno Possato','',NULL,NULL),(14,'',NULL,NULL,'','MASCULINO',20,'Bruno Rita','',NULL,NULL),(15,'',NULL,NULL,'','MASCULINO',20,'Bruno Sundermann','',NULL,NULL),(16,'',NULL,NULL,'','MASCULINO',20,'Cirineu Dair','',NULL,NULL),(17,'',NULL,NULL,'','FEMININO',20,'Clarissa Tomazella','',NULL,NULL),(18,'',NULL,NULL,'','MASCULINO',20,'Claudinei ','',NULL,NULL),(19,'',NULL,NULL,'','MASCULINO',20,'Diego Baldissera','',NULL,NULL),(20,'',NULL,NULL,'','MASCULINO',20,'Diego Góes','',NULL,NULL),(21,'',NULL,NULL,'','MASCULINO',20,'Diogo Ap. F. da Costa','',NULL,NULL),(22,'',NULL,NULL,'','MASCULINO',20,'Douglas Pultz','',NULL,NULL),(23,'',NULL,NULL,'','MASCULINO',20,'Emerson Pereira','',NULL,NULL),(24,'',NULL,NULL,'','MASCULINO',20,'Everton Tetzner ','',NULL,NULL),(25,'',NULL,NULL,'','MASCULINO',20,'Fábio Melo ','',NULL,NULL),(26,'',NULL,NULL,'','MASCULINO',20,'Fernando Marini ','',NULL,NULL),(27,'',NULL,NULL,'','MASCULINO',20,'Fernando Santinelli','',NULL,NULL),(28,'',NULL,NULL,'','MASCULINO',20,'Gustavo Gomes','',NULL,NULL),(29,'',NULL,NULL,'','MASCULINO',20,'Gustavo Oliveira','',NULL,NULL),(30,'',NULL,NULL,'','MASCULINO',20,'Henrique Lamberti','',NULL,NULL),(31,'',NULL,NULL,'','MASCULINO',20,'Igor Pardal','',NULL,NULL),(32,'',NULL,NULL,'','MASCULINO',200,'João Batista','',NULL,NULL),(33,'',NULL,NULL,'','MASCULINO',20,'João Chinali ','',NULL,NULL),(34,'',NULL,NULL,'','MASCULINO',20,'João Grulli ','',NULL,NULL),(35,'',NULL,NULL,'','MASCULINO',20,'João Salla ','',NULL,NULL),(36,'',NULL,NULL,'','MASCULINO',20,'João Vitor','',NULL,NULL),(37,'',NULL,NULL,'','MASCULINO',20,'José Cássio Magalhães','',NULL,NULL),(38,'',NULL,NULL,'','MASCULINO',20,'Junior Cirelli','',NULL,NULL),(39,'',NULL,NULL,'','MASCULINO',20,'Kaio Campos ','',NULL,NULL),(40,'',NULL,NULL,'','MASCULINO',20,'Leandro Marquezi ','',NULL,NULL),(41,'',NULL,NULL,'','MASCULINO',20,'Leonardo Grossi','',NULL,NULL),(42,'',NULL,NULL,'','FEMININO',20,'Lindalva Soares','',NULL,NULL),(43,'',NULL,NULL,'','FEMININO',20,'Lívia Rozim ','',NULL,NULL),(44,'',NULL,NULL,'','FEMININO',20,' Lú Contato','',NULL,NULL),(45,'',NULL,NULL,'','MASCULINO',20,'Lucas Cirelli','',NULL,NULL),(46,'',NULL,NULL,'','MASCULINO',20,'Luis Guilherme Pepe','',NULL,NULL),(47,'',NULL,NULL,'','FEMININO',20,'Marcela Gomes ','',NULL,NULL),(48,'',NULL,NULL,'','MASCULINO',20,'Marcelo Martins ','',NULL,NULL),(49,'',NULL,NULL,'','MASCULINO',20,'Marcos Renato','',NULL,NULL),(50,'',NULL,NULL,'','MASCULINO',20,'Marcos Vinicius','',NULL,NULL),(51,'',NULL,NULL,'','MASCULINO',20,'Marlon Santos ','',NULL,NULL),(52,'',NULL,NULL,'','MASCULINO',2,'Matheus Secco','',NULL,NULL),(53,'',NULL,NULL,'','MASCULINO',20,'Maurício Chiarati ','',NULL,NULL),(54,'',NULL,NULL,'','MASCULINO',20,'Murilo Gallo','',NULL,NULL),(55,'',NULL,NULL,'','FEMININO',20,' Naiam Figueiredo ','',NULL,NULL),(56,'',NULL,NULL,'','MASCULINO',20,'Nani','',NULL,NULL),(57,'',NULL,NULL,'','MASCULINO',20,'Neto Carloni','',NULL,NULL),(58,'',NULL,NULL,'','MASCULINO',20,'Osmir Francisco','',NULL,NULL),(59,'',NULL,NULL,'','MASCULINO',20,'Paulão','',NULL,NULL),(60,'',NULL,NULL,'','MASCULINO',20,'Paulo Arrais','',NULL,NULL),(61,'',NULL,NULL,'','MASCULINO',20,'Paulo Gaúcho ','',NULL,NULL),(62,'',NULL,NULL,'','FEMININO',20,'Priscila','',NULL,NULL),(63,'',NULL,NULL,'','MASCULINO',20,'Raphael Lyra ','',NULL,NULL),(64,'',NULL,NULL,'','MASCULINO',20,'Renan Lana ','',NULL,NULL),(65,'',NULL,NULL,'','MASCULINO',20,'Renzo Cirelli ','',NULL,NULL),(66,'',NULL,NULL,'','MASCULINO',20,'Ricardo Carvalho','',NULL,NULL),(67,'',NULL,NULL,'','MASCULINO',20,'Ricardo Schimidt','',NULL,NULL),(68,'',NULL,NULL,'','MASCULINO',20,'Rick Soares','',NULL,NULL),(69,'',NULL,NULL,'','MASCULINO',20,'Roberto Lobão ','',NULL,NULL),(70,'',NULL,NULL,'','MASCULINO',20,'Rodrigo Góes','',NULL,NULL),(71,'',NULL,NULL,'','MASCULINO',20,'Rodrigo Pompeu','',NULL,NULL),(72,'',NULL,NULL,'','FEMININO',20,'Sandra Sanches ','',NULL,NULL),(73,'',NULL,NULL,'','MASCULINO',20,'Silvinho','',NULL,NULL),(74,'',NULL,NULL,'','MASCULINO',20,'Thiago Belan','',NULL,NULL),(75,'',NULL,NULL,'','MASCULINO',20,'Tiago Costa','',NULL,NULL),(76,'',NULL,NULL,'','MASCULINO',20,'Tiago Pompeu','',NULL,NULL),(77,'',NULL,NULL,'','MASCULINO',20,'Vagner Guerreiro ','',NULL,NULL),(78,'',NULL,NULL,'','MASCULINO',20,'Wellington Rossi ','',NULL,NULL),(79,'',NULL,NULL,'','FEMININO',20,'Ana Carolina ','',NULL,NULL),(80,'',NULL,NULL,'','MASCULINO',20,'Fábio Berestino','',NULL,NULL),(81,'',NULL,NULL,'','MASCULINO',20,'Fernando Mussolini ','',NULL,NULL),(82,'',NULL,NULL,'','FEMININO',20,'Gabriela Pimenta','',NULL,NULL),(83,'',NULL,NULL,'','MASCULINO',20,'Guilherme Monteiro ','',NULL,NULL),(84,'',NULL,NULL,'','MASCULINO',20,'José Raimundo','',NULL,NULL),(85,'',NULL,NULL,'','MASCULINO',20,'Marco Antonio','',NULL,NULL),(86,'',NULL,NULL,'','MASCULINO',20,'Tiago Moreira','',NULL,NULL),(87,'',NULL,NULL,'','MASCULINO',20,'Vinicius Mesquita','',NULL,NULL),(88,'',NULL,NULL,'','FEMININO',10,'Beatriz Ferreira','',NULL,NULL),(89,'',NULL,NULL,'','MASCULINO',10,'Eduardo Henrique ','',NULL,NULL),(90,'',NULL,NULL,'','MASCULINO',10,'Israel','',NULL,NULL),(91,'',NULL,NULL,'','FEMININO',10,' Lorena Nascimento','',NULL,NULL),(92,'',NULL,NULL,'','FEMININO',10,'Mariana Soares','',NULL,NULL),(93,'',NULL,NULL,'','MASCULINO',10,'Natã Cabelo ','',NULL,NULL),(94,'',NULL,NULL,'','MASCULINO',10,'Pietro','',NULL,NULL),(95,'',NULL,NULL,'','FEMININO',10,'Stella Carloni ','',NULL,NULL),(97,'',NULL,NULL,'','MASCULINO',20,'Deo Kezhan','',NULL,NULL),(98,'',NULL,NULL,'','MASCULINO',30,'Luiz Gustavo do Amaral Correa','',NULL,NULL),(99,'',NULL,NULL,'','MASCULINO',20,'Marcos Moraes','',NULL,NULL),(100,'',NULL,NULL,'','MASCULINO',20,'Binha','',NULL,NULL),(101,'',NULL,NULL,'','MASCULINO',20,'Danilo Duran','',NULL,NULL),(102,'',NULL,NULL,'','MASCULINO',20,'Rodolfo Fiocco','',NULL,NULL),(103,'',NULL,NULL,'','MASCULINO',20,'Nilo Cipriano','',NULL,NULL),(104,'',NULL,NULL,'','MASCULINO',20,'TALES (CAPIVARA)','',NULL,NULL),(105,'',NULL,NULL,'','MASCULINO',20,'Rafael Cruz','',NULL,NULL),(106,'',NULL,NULL,'','FEMININO',10,'Nayara Arrais Serodio','',NULL,NULL),(107,'',NULL,NULL,'','MASCULINO',20,'Alex Saleta','',NULL,NULL),(108,'',NULL,NULL,'','MASCULINO',20,'NATALIA','',NULL,NULL);
/*!40000 ALTER TABLE `tb_competidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_divisao`
--

DROP TABLE IF EXISTS `tb_divisao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_divisao` (
  `dv_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dv_data_alteracao` datetime DEFAULT NULL,
  `dv_idade_final` varchar(2) DEFAULT NULL,
  `dv_idade_inicial` varchar(2) DEFAULT NULL,
  `dv_nome` varchar(60) NOT NULL,
  `dv_observacao` text,
  `dv_tipo_ficha` varchar(15) NOT NULL,
  `dv_campeonato_id` bigint(20) DEFAULT NULL,
  `dv_ocorrencia` bigint(20) DEFAULT NULL,
  `dv_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dv_id`),
  KEY `FK_f1gsif6t9n77odxjvv2p7g316` (`dv_campeonato_id`),
  KEY `FK_ald44hvs06tvydib4o4abltbp` (`dv_ocorrencia`),
  KEY `FK_e05adtif8811f9rswna9bev86` (`dv_us_alteracao`),
  CONSTRAINT `FK_ald44hvs06tvydib4o4abltbp` FOREIGN KEY (`dv_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`),
  CONSTRAINT `FK_e05adtif8811f9rswna9bev86` FOREIGN KEY (`dv_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_f1gsif6t9n77odxjvv2p7g316` FOREIGN KEY (`dv_campeonato_id`) REFERENCES `tb_campeonato` (`cp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_divisao`
--

LOCK TABLES `tb_divisao` WRITE;
/*!40000 ALTER TABLE `tb_divisao` DISABLE KEYS */;
INSERT INTO `tb_divisao` VALUES (1,NULL,'99','0','AMADOR','','INDIVIDUAL',NULL,NULL,NULL),(2,NULL,'14','0','INICIANTE','','INDIVIDUAL',NULL,NULL,NULL),(3,NULL,'99','0','ABERTO','','DUPLA',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_divisao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_etapa`
--

DROP TABLE IF EXISTS `tb_etapa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_etapa` (
  `ep_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ep_contato_organizador` varchar(120) DEFAULT NULL,
  `ep_data_alteracao` datetime DEFAULT NULL,
  `ep_data_evento` date DEFAULT NULL,
  `ep_data_fim_inscricoes` date DEFAULT NULL,
  `ep_data_inicio_inscricoes` date DEFAULT NULL,
  `ep_local` varchar(150) NOT NULL,
  `ep_nome` varchar(100) NOT NULL,
  `ep_organizador` varchar(100) DEFAULT NULL,
  `ep_campeonato_id` bigint(20) NOT NULL,
  `ep_ocorrencia` bigint(20) DEFAULT NULL,
  `ep_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ep_id`),
  KEY `FK_3v6b5it2nkvw82s2h2biy2jcn` (`ep_campeonato_id`),
  KEY `FK_m23dfgd27emi1d99c2iuw20tj` (`ep_ocorrencia`),
  KEY `FK_kuvy6s8i98w7o16dym0rxewwn` (`ep_us_alteracao`),
  CONSTRAINT `FK_3v6b5it2nkvw82s2h2biy2jcn` FOREIGN KEY (`ep_campeonato_id`) REFERENCES `tb_campeonato` (`cp_id`),
  CONSTRAINT `FK_kuvy6s8i98w7o16dym0rxewwn` FOREIGN KEY (`ep_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_m23dfgd27emi1d99c2iuw20tj` FOREIGN KEY (`ep_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_etapa`
--

LOCK TABLES `tb_etapa` WRITE;
/*!40000 ALTER TABLE `tb_etapa` DISABLE KEYS */;
INSERT INTO `tb_etapa` VALUES (1,'',NULL,'2019-08-11','2019-08-11','2019-08-11','Fápil','Toca da Onça','Paulo Arrais',1,NULL,NULL),(2,'',NULL,'2019-09-08','2019-09-08','2019-09-08','LIMEIRA - C.T. RICARDO CARVALHO','C.T. RICARDO CARVALHO','RICARDO',1,NULL,NULL);
/*!40000 ALTER TABLE `tb_etapa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ficha_inscricao`
--

DROP TABLE IF EXISTS `tb_ficha_inscricao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ficha_inscricao` (
  `fi_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fi_cod_ficha` bigint(20) DEFAULT NULL,
  `fi_data_alteracao` datetime DEFAULT NULL,
  `fi_data_inscricao` datetime NOT NULL,
  `fi_observacao` varchar(100) DEFAULT NULL,
  `fi_qnt_fichas` bigint(20) DEFAULT NULL,
  `fi_status_ficha` varchar(15) DEFAULT NULL,
  `fi_valor_comprado` decimal(10,2) NOT NULL,
  `fi_valor_pago` decimal(10,2) DEFAULT NULL,
  `fi_campeonato` bigint(20) NOT NULL,
  `fi_competidor` bigint(20) NOT NULL,
  `fi_divisao` bigint(20) NOT NULL,
  `fi_etapa` bigint(20) NOT NULL,
  `fi_ocorrencia` bigint(20) DEFAULT NULL,
  `fi_passada_id` bigint(20) DEFAULT NULL,
  `fi_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fi_id`),
  KEY `FK_2b0xutkjpuw493ckhnbkvya33` (`fi_campeonato`),
  KEY `FK_2d1tvan7gi4j85v0uwjgf7bim` (`fi_competidor`),
  KEY `FK_hsw6rpfwwk1dq539fl128fydh` (`fi_divisao`),
  KEY `FK_kjl29dbf5d9gebvl9pqxc8iwy` (`fi_etapa`),
  KEY `FK_o1036u2o5scr3c5bovf6p1ux4` (`fi_ocorrencia`),
  KEY `FK_8g5pmu3de2j6uav199nje9ibl` (`fi_passada_id`),
  KEY `FK_h0cu45t3i11xvh3i4wqlyr2r1` (`fi_us_alteracao`),
  CONSTRAINT `FK_2b0xutkjpuw493ckhnbkvya33` FOREIGN KEY (`fi_campeonato`) REFERENCES `tb_campeonato` (`cp_id`),
  CONSTRAINT `FK_2d1tvan7gi4j85v0uwjgf7bim` FOREIGN KEY (`fi_competidor`) REFERENCES `tb_competidor` (`cp_id`),
  CONSTRAINT `FK_8g5pmu3de2j6uav199nje9ibl` FOREIGN KEY (`fi_passada_id`) REFERENCES `tb_passada` (`pa_id`),
  CONSTRAINT `FK_h0cu45t3i11xvh3i4wqlyr2r1` FOREIGN KEY (`fi_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_hsw6rpfwwk1dq539fl128fydh` FOREIGN KEY (`fi_divisao`) REFERENCES `tb_divisao` (`dv_id`),
  CONSTRAINT `FK_kjl29dbf5d9gebvl9pqxc8iwy` FOREIGN KEY (`fi_etapa`) REFERENCES `tb_etapa` (`ep_id`),
  CONSTRAINT `FK_o1036u2o5scr3c5bovf6p1ux4` FOREIGN KEY (`fi_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=386 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ficha_inscricao`
--

LOCK TABLES `tb_ficha_inscricao` WRITE;
/*!40000 ALTER TABLE `tb_ficha_inscricao` DISABLE KEYS */;
INSERT INTO `tb_ficha_inscricao` VALUES (1,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,12,1,1,NULL,34,NULL),(2,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,12,1,1,NULL,58,NULL),(3,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,12,1,1,NULL,15,NULL),(4,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,12,1,1,NULL,41,NULL),(5,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,12,1,1,NULL,22,NULL),(6,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,36,1,1,NULL,30,NULL),(7,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,36,1,1,NULL,39,NULL),(8,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,36,1,1,NULL,16,NULL),(9,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,36,1,1,NULL,13,NULL),(10,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,36,1,1,NULL,4,NULL),(11,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,53,1,1,NULL,50,NULL),(12,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,53,1,1,NULL,59,NULL),(13,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,53,1,1,NULL,54,NULL),(14,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,53,1,1,NULL,46,NULL),(15,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,53,1,1,NULL,6,NULL),(16,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,65,1,1,NULL,44,NULL),(17,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,65,1,1,NULL,59,NULL),(18,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,65,1,1,NULL,2,NULL),(19,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,65,1,1,NULL,11,NULL),(20,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,65,1,1,NULL,34,NULL),(21,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,28,1,1,NULL,1,NULL),(22,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,28,1,1,NULL,48,NULL),(23,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,28,1,1,NULL,9,NULL),(24,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,28,1,1,NULL,29,NULL),(25,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,28,1,1,NULL,57,NULL),(26,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,30,1,1,NULL,49,NULL),(27,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,30,1,1,NULL,26,NULL),(28,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,30,1,1,NULL,55,NULL),(29,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,30,1,1,NULL,5,NULL),(30,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,30,1,1,NULL,14,NULL),(31,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,50,1,1,NULL,38,NULL),(32,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,50,1,1,NULL,4,NULL),(33,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,50,1,1,NULL,45,NULL),(34,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,50,1,1,NULL,36,NULL),(35,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,50,1,1,NULL,50,NULL),(36,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,1,1,1,NULL,41,NULL),(37,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,1,1,1,NULL,12,NULL),(38,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,1,1,1,NULL,53,NULL),(39,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,1,1,1,NULL,56,NULL),(40,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,1,1,1,NULL,9,NULL),(41,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,3,1,1,NULL,17,NULL),(42,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,3,1,1,NULL,19,NULL),(43,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,3,1,1,NULL,8,NULL),(44,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,3,1,1,NULL,42,NULL),(45,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,3,1,1,NULL,54,NULL),(46,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,21,1,1,NULL,40,NULL),(47,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,21,1,1,NULL,25,NULL),(48,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,21,1,1,NULL,43,NULL),(49,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,21,1,1,NULL,51,NULL),(50,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,21,1,1,NULL,12,NULL),(51,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,16,1,1,NULL,36,NULL),(52,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,16,1,1,NULL,47,NULL),(53,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,16,1,1,NULL,27,NULL),(54,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,16,1,1,NULL,17,NULL),(55,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,16,1,1,NULL,25,NULL),(56,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,43,1,1,NULL,55,NULL),(57,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,43,1,1,NULL,19,NULL),(58,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,43,1,1,NULL,30,NULL),(59,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,43,1,1,NULL,21,NULL),(60,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,43,1,1,NULL,57,NULL),(61,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,27,1,1,NULL,33,NULL),(62,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,27,1,1,NULL,13,NULL),(63,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,27,1,1,NULL,48,NULL),(64,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,27,1,1,NULL,26,NULL),(65,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,27,1,1,NULL,37,NULL),(66,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,29,1,1,NULL,22,NULL),(67,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,29,1,1,NULL,24,NULL),(68,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,29,1,1,NULL,39,NULL),(69,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,29,1,1,NULL,3,NULL),(70,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,29,1,1,NULL,28,NULL),(71,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,48,1,1,NULL,44,NULL),(72,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,48,1,1,NULL,10,NULL),(73,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,48,1,1,NULL,31,NULL),(74,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,48,1,1,NULL,33,NULL),(75,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,48,1,1,NULL,8,NULL),(76,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,60,1,1,NULL,52,NULL),(77,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,60,1,1,NULL,16,NULL),(78,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,60,1,1,NULL,24,NULL),(79,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,60,1,1,NULL,2,NULL),(80,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,60,1,1,NULL,38,NULL),(81,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,34,1,1,NULL,51,NULL),(82,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,34,1,1,NULL,18,NULL),(83,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,34,1,1,NULL,28,NULL),(84,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,34,1,1,NULL,47,NULL),(85,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,34,1,1,NULL,49,NULL),(86,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,61,1,1,NULL,23,NULL),(87,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,61,1,1,NULL,42,NULL),(88,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,61,1,1,NULL,60,NULL),(89,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,61,1,1,NULL,27,NULL),(90,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,61,1,1,NULL,5,NULL),(96,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,22,1,1,NULL,35,NULL),(97,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,22,1,1,NULL,6,NULL),(98,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,22,1,1,NULL,40,NULL),(99,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,22,1,1,NULL,29,NULL),(100,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,22,1,1,NULL,18,NULL),(101,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,99,1,1,NULL,11,NULL),(102,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,99,1,1,NULL,32,NULL),(103,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,99,1,1,NULL,23,NULL),(104,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,99,1,1,NULL,56,NULL),(105,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,99,1,1,NULL,35,NULL),(106,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,100,1,1,NULL,21,NULL),(107,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,100,1,1,NULL,15,NULL),(108,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,100,1,1,NULL,3,NULL),(109,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,100,1,1,NULL,53,NULL),(110,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,0.00,1,100,1,1,NULL,43,NULL),(116,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,101,1,1,NULL,7,NULL),(117,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,101,1,1,NULL,10,NULL),(118,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,101,1,1,NULL,37,NULL),(119,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,101,1,1,NULL,14,NULL),(120,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,101,1,1,NULL,1,NULL),(121,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,102,1,1,NULL,7,NULL),(122,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,102,1,1,NULL,20,NULL),(123,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,102,1,1,NULL,45,NULL),(124,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,102,1,1,NULL,31,NULL),(125,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,40.00,1,102,1,1,NULL,58,NULL),(126,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,4.00,1,103,1,1,NULL,46,NULL),(127,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,4.00,1,103,1,1,NULL,52,NULL),(128,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,4.00,1,103,1,1,NULL,32,NULL),(129,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,4.00,1,103,1,1,NULL,20,NULL),(130,NULL,NULL,'2019-08-11 00:00:00',NULL,5,'EMORDEM',40.00,4.00,1,103,1,1,NULL,60,NULL),(131,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,36,3,1,NULL,63,NULL),(132,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,26,3,1,NULL,63,NULL),(133,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,83,3,1,NULL,64,NULL),(134,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,64,NULL),(135,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,65,3,1,NULL,65,NULL),(136,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,53,3,1,NULL,65,NULL),(137,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,66,NULL),(138,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,43,3,1,NULL,66,NULL),(139,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,67,NULL),(140,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,83,3,1,NULL,67,NULL),(141,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,65,3,1,NULL,68,NULL),(142,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,53,3,1,NULL,68,NULL),(143,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,18,3,1,NULL,69,NULL),(144,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,69,NULL),(145,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,70,NULL),(146,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,97,NULL),(147,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,18,3,1,NULL,70,NULL),(148,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,71,NULL),(149,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,65,3,1,NULL,71,NULL),(150,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,36,3,1,NULL,72,NULL),(151,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,12,3,1,NULL,72,NULL),(152,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,36,3,1,NULL,73,NULL),(153,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,65,3,1,NULL,73,NULL),(154,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,74,NULL),(155,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,103,3,1,NULL,74,NULL),(156,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,104,3,1,NULL,75,NULL),(157,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,75,NULL),(158,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,36,3,1,NULL,76,NULL),(159,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,53,3,1,NULL,76,NULL),(160,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,77,NULL),(161,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,77,NULL),(162,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,78,NULL),(163,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,78,NULL),(164,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,28,3,1,NULL,79,NULL),(165,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,27,3,1,NULL,79,NULL),(166,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,101,3,1,NULL,80,NULL),(167,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,97,3,1,NULL,80,NULL),(168,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,36,3,1,NULL,81,NULL),(169,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,26,3,1,NULL,81,NULL),(170,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,101,3,1,NULL,82,NULL),(171,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,104,3,1,NULL,82,NULL),(172,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,83,NULL),(173,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,83,NULL),(174,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,28,3,1,NULL,84,NULL),(175,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,27,3,1,NULL,84,NULL),(176,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,104,3,1,NULL,85,NULL),(177,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,22,3,1,NULL,85,NULL),(178,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,86,NULL),(179,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,86,NULL),(180,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,28,3,1,NULL,87,NULL),(181,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,43,3,1,NULL,87,NULL),(182,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,53,3,1,NULL,88,NULL),(183,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,26,3,1,NULL,88,NULL),(184,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,89,NULL),(185,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,22,3,1,NULL,89,NULL),(186,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,90,NULL),(187,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,18,3,1,NULL,90,NULL),(188,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,91,NULL),(189,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,97,3,1,NULL,91,NULL),(190,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,92,NULL),(191,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,22,3,1,NULL,92,NULL),(192,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,43,3,1,NULL,93,NULL),(193,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,97,3,1,NULL,93,NULL),(194,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,18,3,1,NULL,94,NULL),(195,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,65,3,1,NULL,94,NULL),(196,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,27,3,1,NULL,95,NULL),(197,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,95,NULL),(198,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,96,NULL),(199,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,83,3,1,NULL,96,NULL),(200,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,98,NULL),(201,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,68,3,1,NULL,97,NULL),(202,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,68,3,1,NULL,98,NULL),(203,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,18,3,1,NULL,99,NULL),(204,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,40.00,1,61,3,1,NULL,99,NULL),(205,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,100,NULL),(206,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,61,3,1,NULL,100,NULL),(207,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,101,NULL),(208,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,61,3,1,NULL,101,NULL),(209,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,102,NULL),(210,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,34,3,1,NULL,102,NULL),(211,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,103,NULL),(212,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,34,3,1,NULL,103,NULL),(213,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,83,3,1,NULL,104,NULL),(214,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,104,NULL),(215,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,18,3,1,NULL,105,NULL),(216,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,105,NULL),(217,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,106,NULL),(218,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,106,NULL),(219,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,107,NULL),(220,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,83,3,1,NULL,107,NULL),(221,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,39,3,1,NULL,108,NULL),(222,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,18,3,1,NULL,108,NULL),(223,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,39,3,1,NULL,109,NULL),(224,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,109,NULL),(225,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,39,3,1,NULL,110,NULL),(226,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,110,NULL),(227,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,39,3,1,NULL,111,NULL),(228,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,105,3,1,NULL,111,NULL),(229,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,39,3,1,NULL,112,NULL),(230,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,112,NULL),(231,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,39,3,1,NULL,113,NULL),(232,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,56,3,1,NULL,113,NULL),(233,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,114,NULL),(234,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,22,3,1,NULL,114,NULL),(235,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,115,NULL),(236,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,34,3,1,NULL,116,NULL),(237,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,61,3,1,NULL,116,NULL),(238,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,117,NULL),(239,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,43,3,1,NULL,117,NULL),(240,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'CADASTRADA',40.00,0.00,1,60,3,1,NULL,NULL,NULL),(241,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,43,3,1,NULL,118,NULL),(242,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,97,3,1,NULL,119,NULL),(243,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,50,3,1,NULL,119,NULL),(244,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,53,3,1,NULL,120,NULL),(245,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,50,3,1,NULL,120,NULL),(246,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,26,3,1,NULL,121,NULL),(247,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,50,3,1,NULL,121,NULL),(248,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,22,3,1,NULL,122,NULL),(249,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,122,NULL),(250,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,12,3,1,NULL,123,NULL),(251,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,26,3,1,NULL,123,NULL),(252,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,50,3,1,NULL,124,NULL),(253,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,61,3,1,NULL,124,NULL),(254,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,65,3,1,NULL,125,NULL),(255,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,58,3,1,NULL,125,NULL),(256,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,NULL,1,22,3,1,NULL,115,NULL),(257,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,107,3,1,NULL,118,NULL),(258,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,126,NULL),(259,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,106,3,1,NULL,126,NULL),(260,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,60,3,1,NULL,127,NULL),(261,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,106,3,1,NULL,127,NULL),(262,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,0.00,1,102,3,1,NULL,128,NULL),(263,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,NULL,1,60,3,1,NULL,128,NULL),(264,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,0.00,1,102,3,1,NULL,129,NULL),(265,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',40.00,0.00,1,101,3,1,NULL,129,NULL),(266,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',4.00,0.00,1,102,3,1,NULL,130,NULL),(267,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.04,NULL,1,101,3,1,NULL,130,NULL),(268,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,22,3,1,NULL,131,NULL),(269,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.20,NULL,1,97,3,1,NULL,131,NULL),(270,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,22,3,1,NULL,132,NULL),(271,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.01,NULL,1,103,3,1,NULL,132,NULL),(272,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,22,3,1,NULL,133,NULL),(273,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.02,NULL,1,43,3,1,NULL,133,NULL),(274,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.01,NULL,1,22,3,1,NULL,134,NULL),(275,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,101,3,1,NULL,134,NULL),(276,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,22,3,1,NULL,135,NULL),(277,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,101,3,1,NULL,135,NULL),(278,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,22,3,1,NULL,136,NULL),(279,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,39,3,1,NULL,136,NULL),(280,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,22,3,1,NULL,137,NULL),(281,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.10,NULL,1,28,3,1,NULL,137,NULL),(282,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,0.00,1,53,3,1,NULL,138,NULL),(283,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,22,3,1,NULL,138,NULL),(284,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,50,3,1,NULL,139,NULL),(285,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,39,3,1,NULL,139,NULL),(286,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,0.00,1,22,3,1,NULL,140,NULL),(287,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,26,3,1,NULL,140,NULL),(288,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,101,3,1,NULL,141,NULL),(289,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.01,NULL,1,27,3,1,NULL,141,NULL),(290,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,NULL,1,60,3,1,NULL,142,NULL),(291,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.01,NULL,1,107,3,1,NULL,142,NULL),(292,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.01,0.00,1,60,3,1,NULL,143,NULL),(293,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,0.00,1,107,3,1,NULL,143,NULL),(294,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,0.00,1,97,3,1,NULL,144,NULL),(295,NULL,NULL,'2019-08-11 00:00:00',NULL,1,'EMORDEM',0.40,0.00,1,108,3,1,NULL,144,NULL),(296,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,65,1,2,NULL,NULL,NULL),(297,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,65,1,2,NULL,NULL,NULL),(298,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,65,1,2,NULL,NULL,NULL),(299,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,65,1,2,NULL,NULL,NULL),(300,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,65,1,2,NULL,NULL,NULL),(301,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,53,1,2,NULL,NULL,NULL),(302,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,53,1,2,NULL,NULL,NULL),(303,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,53,1,2,NULL,NULL,NULL),(304,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,53,1,2,NULL,NULL,NULL),(305,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,53,1,2,NULL,NULL,NULL),(306,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,36,1,2,NULL,NULL,NULL),(307,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,36,1,2,NULL,NULL,NULL),(308,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,36,1,2,NULL,NULL,NULL),(309,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,36,1,2,NULL,NULL,NULL),(310,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,36,1,2,NULL,NULL,NULL),(311,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,37,1,2,NULL,NULL,NULL),(312,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,37,1,2,NULL,NULL,NULL),(313,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,37,1,2,NULL,NULL,NULL),(314,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,37,1,2,NULL,NULL,NULL),(315,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,37,1,2,NULL,NULL,NULL),(316,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,12,1,2,NULL,NULL,NULL),(317,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,12,1,2,NULL,NULL,NULL),(318,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,12,1,2,NULL,NULL,NULL),(319,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,12,1,2,NULL,NULL,NULL),(320,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,12,1,2,NULL,NULL,NULL),(321,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,43,1,2,NULL,NULL,NULL),(322,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,43,1,2,NULL,NULL,NULL),(323,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,43,1,2,NULL,NULL,NULL),(324,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,43,1,2,NULL,NULL,NULL),(325,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,43,1,2,NULL,NULL,NULL),(326,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,22,1,2,NULL,NULL,NULL),(327,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,22,1,2,NULL,NULL,NULL),(328,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,22,1,2,NULL,NULL,NULL),(329,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,22,1,2,NULL,NULL,NULL),(330,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,22,1,2,NULL,NULL,NULL),(331,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,60,1,2,NULL,NULL,NULL),(332,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,60,1,2,NULL,NULL,NULL),(333,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,60,1,2,NULL,NULL,NULL),(334,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,60,1,2,NULL,NULL,NULL),(335,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,60,1,2,NULL,NULL,NULL),(336,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,41,1,2,NULL,NULL,NULL),(337,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,41,1,2,NULL,NULL,NULL),(338,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,41,1,2,NULL,NULL,NULL),(339,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,41,1,2,NULL,NULL,NULL),(340,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,41,1,2,NULL,NULL,NULL),(341,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,103,1,2,NULL,NULL,NULL),(342,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,103,1,2,NULL,NULL,NULL),(343,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,103,1,2,NULL,NULL,NULL),(344,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,103,1,2,NULL,NULL,NULL),(345,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,103,1,2,NULL,NULL,NULL),(346,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,4,1,2,NULL,NULL,NULL),(347,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,4,1,2,NULL,NULL,NULL),(348,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,4,1,2,NULL,NULL,NULL),(349,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,4,1,2,NULL,NULL,NULL),(350,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,4,1,2,NULL,NULL,NULL),(351,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,71,1,2,NULL,NULL,NULL),(352,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,71,1,2,NULL,NULL,NULL),(353,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,71,1,2,NULL,NULL,NULL),(354,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,71,1,2,NULL,NULL,NULL),(355,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,71,1,2,NULL,NULL,NULL),(356,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,76,1,2,NULL,NULL,NULL),(357,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,76,1,2,NULL,NULL,NULL),(358,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,76,1,2,NULL,NULL,NULL),(359,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,76,1,2,NULL,NULL,NULL),(360,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,76,1,2,NULL,NULL,NULL),(361,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,27,1,2,NULL,NULL,NULL),(362,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,27,1,2,NULL,NULL,NULL),(363,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,27,1,2,NULL,NULL,NULL),(364,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,27,1,2,NULL,NULL,NULL),(365,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,NULL,1,27,1,2,NULL,NULL,NULL),(366,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,44,1,2,NULL,NULL,NULL),(367,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,44,1,2,NULL,NULL,NULL),(368,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,44,1,2,NULL,NULL,NULL),(369,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,44,1,2,NULL,NULL,NULL),(370,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,44,1,2,NULL,NULL,NULL),(371,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,28,1,2,NULL,NULL,NULL),(372,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,28,1,2,NULL,NULL,NULL),(373,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,28,1,2,NULL,NULL,NULL),(374,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,28,1,2,NULL,NULL,NULL),(375,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,28,1,2,NULL,NULL,NULL),(376,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,34,1,2,NULL,NULL,NULL),(377,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,34,1,2,NULL,NULL,NULL),(378,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,34,1,2,NULL,NULL,NULL),(379,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,34,1,2,NULL,NULL,NULL),(380,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,34,1,2,NULL,NULL,NULL),(381,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,75,1,2,NULL,NULL,NULL),(382,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,75,1,2,NULL,NULL,NULL),(383,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,75,1,2,NULL,NULL,NULL),(384,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,75,1,2,NULL,NULL,NULL),(385,NULL,NULL,'2019-09-08 00:00:00',NULL,5,'CADASTRADA',40.00,0.00,1,75,1,2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_ficha_inscricao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ocorrencia`
--

DROP TABLE IF EXISTS `tb_ocorrencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ocorrencia` (
  `oc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `oc_data` date DEFAULT NULL,
  `oc_descricao` varchar(255) DEFAULT NULL,
  `oc_hora` date DEFAULT NULL,
  `oc_programa` varchar(100) DEFAULT NULL,
  `oc_usuario` tinyblob,
  PRIMARY KEY (`oc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ocorrencia`
--

LOCK TABLES `tb_ocorrencia` WRITE;
/*!40000 ALTER TABLE `tb_ocorrencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ocorrencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ordem_entrada`
--

DROP TABLE IF EXISTS `tb_ordem_entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ordem_entrada` (
  `od_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `od_data` date DEFAULT NULL,
  `od_data_alteracao` datetime DEFAULT NULL,
  `od_hora` time DEFAULT NULL,
  `od_campeonato` bigint(20) NOT NULL,
  `od_divisao` bigint(20) NOT NULL,
  `od_etapa` bigint(20) NOT NULL,
  `od_ocorrencia` bigint(20) DEFAULT NULL,
  `od_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`od_id`),
  KEY `FK_c4isse3w5a0jma6qqaidnxax8` (`od_campeonato`),
  KEY `FK_l8ujwwq9yi48h5m7j0wcmsuv4` (`od_divisao`),
  KEY `FK_t8x49owg8kao9q1ki45e8lv2n` (`od_etapa`),
  KEY `FK_1vn0a0ho0w2nycwyad23yc973` (`od_ocorrencia`),
  KEY `FK_c8y78cai47j70rlb61iybovo4` (`od_us_alteracao`),
  CONSTRAINT `FK_1vn0a0ho0w2nycwyad23yc973` FOREIGN KEY (`od_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`),
  CONSTRAINT `FK_c4isse3w5a0jma6qqaidnxax8` FOREIGN KEY (`od_campeonato`) REFERENCES `tb_campeonato` (`cp_id`),
  CONSTRAINT `FK_c8y78cai47j70rlb61iybovo4` FOREIGN KEY (`od_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_l8ujwwq9yi48h5m7j0wcmsuv4` FOREIGN KEY (`od_divisao`) REFERENCES `tb_divisao` (`dv_id`),
  CONSTRAINT `FK_t8x49owg8kao9q1ki45e8lv2n` FOREIGN KEY (`od_etapa`) REFERENCES `tb_etapa` (`ep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ordem_entrada`
--

LOCK TABLES `tb_ordem_entrada` WRITE;
/*!40000 ALTER TABLE `tb_ordem_entrada` DISABLE KEYS */;
INSERT INTO `tb_ordem_entrada` VALUES (1,NULL,NULL,NULL,1,1,1,NULL,NULL),(2,'2019-08-11',NULL,NULL,1,3,1,NULL,NULL);
/*!40000 ALTER TABLE `tb_ordem_entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_passada`
--

DROP TABLE IF EXISTS `tb_passada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_passada` (
  `pa_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pa_data_alteracao` datetime DEFAULT NULL,
  `pa_numero_boi` bigint(20) DEFAULT NULL,
  `pa_numero_dupla` bigint(20) DEFAULT NULL,
  `pa_qnt_boi` bigint(20) DEFAULT NULL,
  `pa_ranking` bigint(20) DEFAULT NULL,
  `pa_sat` varchar(10) DEFAULT NULL,
  `pa_tempo` varchar(20) DEFAULT NULL,
  `pa_ocorrencia` bigint(20) DEFAULT NULL,
  `pa_ordem_entrada_id` bigint(20) DEFAULT NULL,
  `pa_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pa_id`),
  KEY `FK_rc61yt9obq2eeqbusmms336oo` (`pa_ocorrencia`),
  KEY `FK_jqqiyjh70aknvvue4s222fhn4` (`pa_ordem_entrada_id`),
  KEY `FK_1fm0kc2imeg1r2wix2mwdlnrm` (`pa_us_alteracao`),
  CONSTRAINT `FK_1fm0kc2imeg1r2wix2mwdlnrm` FOREIGN KEY (`pa_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_jqqiyjh70aknvvue4s222fhn4` FOREIGN KEY (`pa_ordem_entrada_id`) REFERENCES `tb_ordem_entrada` (`od_id`),
  CONSTRAINT `FK_rc61yt9obq2eeqbusmms336oo` FOREIGN KEY (`pa_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_passada`
--

LOCK TABLES `tb_passada` WRITE;
/*!40000 ALTER TABLE `tb_passada` DISABLE KEYS */;
INSERT INTO `tb_passada` VALUES (1,NULL,2,1,NULL,NULL,'S','',NULL,1,NULL),(2,NULL,4,2,NULL,NULL,'S','',NULL,1,NULL),(3,NULL,3,3,NULL,NULL,'S','',NULL,1,NULL),(4,NULL,7,4,NULL,NULL,'S','',NULL,1,NULL),(5,NULL,6,5,NULL,NULL,'S','',NULL,1,NULL),(6,NULL,0,6,NULL,NULL,'S','',NULL,1,NULL),(7,NULL,9,7,NULL,NULL,'S','',NULL,1,NULL),(8,NULL,5,8,10,NULL,'N','00:80:669',NULL,1,NULL),(9,NULL,8,9,NULL,NULL,'S','',NULL,1,NULL),(10,NULL,1,10,NULL,NULL,'S','',NULL,1,NULL),(11,NULL,4,11,NULL,NULL,'S','',NULL,1,NULL),(12,NULL,6,12,NULL,NULL,'S','',NULL,1,NULL),(13,NULL,8,13,NULL,NULL,'S','',NULL,1,NULL),(14,NULL,0,14,NULL,NULL,'S','',NULL,1,NULL),(15,NULL,2,15,NULL,NULL,'S','',NULL,1,NULL),(16,NULL,5,16,NULL,NULL,'S','',NULL,1,NULL),(17,NULL,1,17,NULL,NULL,'S','',NULL,1,NULL),(18,NULL,9,18,NULL,NULL,'S','',NULL,1,NULL),(19,NULL,7,19,NULL,NULL,'S','',NULL,1,NULL),(20,NULL,3,20,NULL,NULL,'S','',NULL,1,NULL),(21,NULL,8,21,NULL,NULL,'S','',NULL,1,NULL),(22,NULL,1,22,10,NULL,'N','00:59:678',NULL,1,NULL),(23,NULL,5,23,NULL,NULL,'S','',NULL,1,NULL),(24,NULL,7,24,10,NULL,'N','00:43:950',NULL,1,NULL),(25,NULL,0,25,NULL,NULL,'S','',NULL,1,NULL),(26,NULL,2,26,10,NULL,'N','00:67:920',NULL,1,NULL),(27,NULL,6,27,NULL,NULL,'S','',NULL,1,NULL),(28,NULL,9,28,NULL,NULL,'S','',NULL,1,NULL),(29,NULL,4,29,NULL,NULL,'S','',NULL,1,NULL),(30,NULL,3,30,10,NULL,'N','00:73:576',NULL,1,NULL),(31,NULL,0,31,NULL,NULL,'S','',NULL,1,NULL),(32,NULL,2,32,10,NULL,'N','00:71:442',NULL,1,NULL),(33,NULL,1,33,10,NULL,'N','00:51:019',NULL,1,NULL),(34,NULL,9,34,NULL,NULL,'S','',NULL,1,NULL),(35,NULL,4,35,NULL,NULL,'S','',NULL,1,NULL),(36,NULL,5,36,NULL,NULL,'S','',NULL,1,NULL),(37,NULL,7,37,10,NULL,'N','00:61:071',NULL,1,NULL),(38,NULL,8,38,10,NULL,'N','00:60:705',NULL,1,NULL),(39,NULL,3,39,NULL,NULL,'S','',NULL,1,NULL),(40,NULL,6,40,NULL,NULL,'S','',NULL,1,NULL),(41,NULL,8,41,NULL,NULL,'S','',NULL,1,NULL),(42,NULL,2,42,10,NULL,'N','00:46:839',NULL,1,NULL),(43,NULL,9,43,NULL,NULL,'S','',NULL,1,NULL),(44,NULL,7,44,NULL,NULL,'S','',NULL,1,NULL),(45,NULL,0,45,NULL,NULL,'S','',NULL,1,NULL),(46,NULL,4,46,10,NULL,'N','00:67:327',NULL,1,NULL),(47,NULL,3,47,NULL,NULL,'S','',NULL,1,NULL),(48,NULL,5,48,10,NULL,'N','00:66:070',NULL,1,NULL),(49,NULL,1,49,NULL,NULL,'S','',NULL,1,NULL),(50,NULL,6,50,10,NULL,'N','00:61:598',NULL,1,NULL),(51,NULL,3,51,NULL,NULL,'S','',NULL,1,NULL),(52,NULL,2,52,10,NULL,'N','00:41:905',NULL,1,NULL),(53,NULL,8,53,10,NULL,'N','00:51:595',NULL,1,NULL),(54,NULL,0,54,NULL,NULL,'S','',NULL,1,NULL),(55,NULL,9,55,10,NULL,'N','00:47:975',NULL,1,NULL),(56,NULL,1,56,NULL,NULL,'S','',NULL,1,NULL),(57,NULL,5,57,NULL,NULL,'S','',NULL,1,NULL),(58,NULL,7,58,NULL,NULL,'S','',NULL,1,NULL),(59,NULL,4,59,10,NULL,'N','00:37:612',NULL,1,NULL),(60,NULL,6,60,NULL,NULL,'S','',NULL,1,NULL),(63,NULL,NULL,40,NULL,NULL,'S','',NULL,2,NULL),(64,NULL,7,11,NULL,NULL,'S','',NULL,2,NULL),(65,NULL,7,22,10,NULL,'N','00:38:753',NULL,2,NULL),(66,NULL,1,65,NULL,NULL,'S','',NULL,2,NULL),(67,NULL,2,69,10,NULL,'N','00:39:713',NULL,2,NULL),(68,NULL,1,43,NULL,NULL,'S','',NULL,2,NULL),(69,NULL,2,5,10,NULL,'N','00:40:003',NULL,2,NULL),(70,NULL,8,59,NULL,NULL,'S','',NULL,2,NULL),(71,NULL,5,6,10,NULL,'N','00:50:407',NULL,2,NULL),(72,NULL,5,67,NULL,NULL,'S','',NULL,2,NULL),(73,NULL,7,49,NULL,NULL,'S','',NULL,2,NULL),(74,NULL,0,27,NULL,NULL,'S','',NULL,2,NULL),(75,NULL,3,51,NULL,NULL,'S','',NULL,2,NULL),(76,NULL,NULL,77,7,NULL,'N','00:57:606',NULL,2,NULL),(77,NULL,0,41,10,NULL,'N','00:42:989',NULL,2,NULL),(78,NULL,1,20,10,NULL,'N','00:45:156',NULL,2,NULL),(79,NULL,NULL,82,10,NULL,'','00:33:216',NULL,2,NULL),(80,NULL,1,54,NULL,NULL,'S','',NULL,2,NULL),(81,NULL,4,53,NULL,NULL,'S','',NULL,2,NULL),(82,NULL,9,16,NULL,NULL,'S','',NULL,2,NULL),(83,NULL,3,68,NULL,NULL,'S','',NULL,2,NULL),(84,NULL,9,31,NULL,NULL,'S','',NULL,2,NULL),(85,NULL,8,2,NULL,NULL,'S','',NULL,2,NULL),(86,NULL,7,10,NULL,NULL,'S','',NULL,2,NULL),(87,NULL,7,63,NULL,NULL,'S','',NULL,2,NULL),(88,NULL,3,33,NULL,NULL,'S','',NULL,2,NULL),(89,NULL,NULL,79,NULL,NULL,'S','',NULL,2,NULL),(90,NULL,NULL,66,NULL,NULL,'S','',NULL,2,NULL),(91,NULL,1,1,NULL,NULL,'S','',NULL,2,NULL),(92,NULL,4,32,10,NULL,'N','00:40:831',NULL,2,NULL),(93,NULL,8,14,10,NULL,'N','00:52:967',NULL,2,NULL),(94,NULL,6,78,10,NULL,'N','00:41:366',NULL,2,NULL),(95,NULL,3,18,10,NULL,'N','00:50:926',NULL,2,NULL),(96,NULL,8,25,NULL,NULL,'S','',NULL,2,NULL),(97,NULL,4,42,NULL,NULL,'S','',NULL,2,NULL),(98,NULL,9,52,10,NULL,'N','00:40:668',NULL,2,NULL),(99,NULL,1,34,NULL,NULL,'S','',NULL,2,NULL),(100,NULL,2,81,10,NULL,'N','00:36:775',NULL,2,NULL),(101,NULL,2,45,NULL,NULL,'S','',NULL,2,NULL),(102,NULL,7,37,NULL,NULL,'S','',NULL,2,NULL),(103,NULL,1,21,NULL,NULL,'S','',NULL,2,NULL),(104,NULL,4,64,NULL,NULL,'S','',NULL,2,NULL),(105,NULL,NULL,80,NULL,NULL,'S','',NULL,2,NULL),(106,NULL,7,44,NULL,NULL,'S','',NULL,2,NULL),(107,NULL,0,57,10,NULL,'N','00:37:163',NULL,2,NULL),(108,NULL,6,70,10,NULL,'N','00:52:201',NULL,2,NULL),(109,NULL,NULL,39,10,NULL,'N','00:50:488',NULL,2,NULL),(110,NULL,6,4,10,NULL,'N','00:53:466',NULL,2,NULL),(111,NULL,0,75,10,NULL,'N','00:44:779',NULL,2,NULL),(112,NULL,5,15,NULL,NULL,'S','',NULL,2,NULL),(113,NULL,4,23,10,NULL,'N','00:30:467',NULL,2,NULL),(114,NULL,9,9,10,NULL,'N','00:57:836',NULL,2,NULL),(115,NULL,5,56,10,NULL,'N','00:30:224',NULL,2,NULL),(116,NULL,4,7,NULL,NULL,'S','',NULL,2,NULL),(117,NULL,3,30,10,NULL,'N','00:27:143',NULL,2,NULL),(118,NULL,9,28,NULL,NULL,'S','',NULL,2,NULL),(119,NULL,2,55,NULL,NULL,'S','',NULL,2,NULL),(120,NULL,9,50,NULL,NULL,'S','',NULL,2,NULL),(121,NULL,9,71,NULL,NULL,'S','',NULL,2,NULL),(122,NULL,5,46,10,NULL,'N','00:34:687',NULL,2,NULL),(123,NULL,2,36,NULL,NULL,'S','',NULL,2,NULL),(124,NULL,8,72,NULL,NULL,'S','',NULL,2,NULL),(125,NULL,0,3,NULL,NULL,'S','',NULL,2,NULL),(126,NULL,4,12,NULL,NULL,'S','',NULL,2,NULL),(127,NULL,6,58,10,NULL,'N','00:46:709',NULL,2,NULL),(128,NULL,3,48,NULL,NULL,'S','',NULL,2,NULL),(129,NULL,8,35,NULL,NULL,'S','',NULL,2,NULL),(130,NULL,8,47,NULL,NULL,'S','',NULL,2,NULL),(131,NULL,0,62,NULL,NULL,'S','',NULL,2,NULL),(132,NULL,6,24,10,NULL,'N','00:47:445',NULL,2,NULL),(133,NULL,3,8,NULL,NULL,'S','',NULL,2,NULL),(134,NULL,2,26,10,NULL,'N','00:47:924',NULL,2,NULL),(135,NULL,6,13,NULL,NULL,'S','',NULL,2,NULL),(136,NULL,7,60,NULL,NULL,'S','',NULL,2,NULL),(137,NULL,0,17,NULL,NULL,'S','',NULL,2,NULL),(138,NULL,7,73,NULL,NULL,'S','',NULL,2,NULL),(139,NULL,2,19,NULL,NULL,'S','',NULL,2,NULL),(140,NULL,8,61,NULL,NULL,'S','',NULL,2,NULL),(141,NULL,5,38,NULL,NULL,'S','',NULL,2,NULL),(142,NULL,5,74,NULL,NULL,'S','',NULL,2,NULL),(143,NULL,5,29,10,NULL,'N','00:52:622',NULL,2,NULL),(144,NULL,NULL,76,NULL,NULL,'S','',NULL,2,NULL);
/*!40000 ALTER TABLE `tb_passada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_permissoes_usuario`
--

DROP TABLE IF EXISTS `tb_permissoes_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_permissoes_usuario` (
  `pu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pu_data_alteracao` datetime DEFAULT NULL,
  `pu_ocorrencia` bigint(20) DEFAULT NULL,
  `pu_usuario_id` bigint(20) NOT NULL,
  `pu_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pu_id`),
  KEY `FK_f7v5d6ste1dqycv2rqtro059a` (`pu_ocorrencia`),
  KEY `FK_lgd9a6ab9q4gm249revsuir4d` (`pu_usuario_id`),
  KEY `FK_6dk2dhtce2s01j68h6xj6dtqo` (`pu_us_alteracao`),
  CONSTRAINT `FK_6dk2dhtce2s01j68h6xj6dtqo` FOREIGN KEY (`pu_us_alteracao`) REFERENCES `tb_usuario` (`us_id`),
  CONSTRAINT `FK_f7v5d6ste1dqycv2rqtro059a` FOREIGN KEY (`pu_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`),
  CONSTRAINT `FK_lgd9a6ab9q4gm249revsuir4d` FOREIGN KEY (`pu_usuario_id`) REFERENCES `tb_usuario` (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_permissoes_usuario`
--

LOCK TABLES `tb_permissoes_usuario` WRITE;
/*!40000 ALTER TABLE `tb_permissoes_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_permissoes_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_recebimentos`
--

DROP TABLE IF EXISTS `tb_recebimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_recebimentos` (
  `re_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `re_banco` varchar(50) DEFAULT NULL,
  `re_data_alteracao` datetime DEFAULT NULL,
  `re_data_cadastro` date NOT NULL,
  `re_data_recebimento` datetime DEFAULT NULL,
  `re_frm_pagamento` varchar(15) DEFAULT NULL,
  `re_observacao` text,
  `re_valor_recebimento` decimal(10,2) DEFAULT NULL,
  `re_valor_titulo` decimal(10,2) NOT NULL,
  `re_campeonato` bigint(20) NOT NULL,
  `re_competidor` bigint(20) NOT NULL,
  `re_divisao` bigint(20) DEFAULT NULL,
  `re_etapa` bigint(20) NOT NULL,
  `re_ocorrencia` bigint(20) DEFAULT NULL,
  `re_us_alteracao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`re_id`),
  KEY `FK_7x1wgp65l06ehqhca6urgow9c` (`re_campeonato`),
  KEY `FK_brb0mmr6nuhy1xff9y9wjnaxf` (`re_competidor`),
  KEY `FK_lmjhtm8r2f31gtiot3yqcflqc` (`re_divisao`),
  KEY `FK_auci12eihseyt3cs6baa9mqwy` (`re_etapa`),
  KEY `FK_rvgu4uwivyncx4tkwf3ilt2dm` (`re_ocorrencia`),
  KEY `FK_sou7nenukds4i1f6p492ref1p` (`re_us_alteracao`),
  CONSTRAINT `FK_7x1wgp65l06ehqhca6urgow9c` FOREIGN KEY (`re_campeonato`) REFERENCES `tb_campeonato` (`cp_id`),
  CONSTRAINT `FK_auci12eihseyt3cs6baa9mqwy` FOREIGN KEY (`re_etapa`) REFERENCES `tb_etapa` (`ep_id`),
  CONSTRAINT `FK_brb0mmr6nuhy1xff9y9wjnaxf` FOREIGN KEY (`re_competidor`) REFERENCES `tb_competidor` (`cp_id`),
  CONSTRAINT `FK_lmjhtm8r2f31gtiot3yqcflqc` FOREIGN KEY (`re_divisao`) REFERENCES `tb_divisao` (`dv_id`),
  CONSTRAINT `FK_rvgu4uwivyncx4tkwf3ilt2dm` FOREIGN KEY (`re_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`),
  CONSTRAINT `FK_sou7nenukds4i1f6p492ref1p` FOREIGN KEY (`re_us_alteracao`) REFERENCES `tb_usuario` (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_recebimentos`
--

LOCK TABLES `tb_recebimentos` WRITE;
/*!40000 ALTER TABLE `tb_recebimentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_recebimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tarefas`
--

DROP TABLE IF EXISTS `tb_tarefas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tarefas` (
  `tf_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tf_codigo_programa` varchar(255) NOT NULL,
  `tf_nome_programa` varchar(255) NOT NULL,
  `tf_permissao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tf_id`),
  KEY `FK_t6v3iu8vtgxt9ican61p9p0qs` (`tf_permissao_id`),
  CONSTRAINT `FK_t6v3iu8vtgxt9ican61p9p0qs` FOREIGN KEY (`tf_permissao_id`) REFERENCES `tb_permissoes_usuario` (`pu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tarefas`
--

LOCK TABLES `tb_tarefas` WRITE;
/*!40000 ALTER TABLE `tb_tarefas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_tarefas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_usuario` (
  `us_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `confirmaSenha` varchar(255) DEFAULT NULL,
  `us_email` varchar(120) NOT NULL,
  `us_nome` varchar(120) NOT NULL,
  `us_senha` varchar(35) NOT NULL,
  `us_sobrenome` varchar(120) DEFAULT NULL,
  `us_ocorrencia` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`us_id`),
  KEY `FK_2jfyxks47dx4499tbbdktsvw6` (`us_ocorrencia`),
  CONSTRAINT `FK_2jfyxks47dx4499tbbdktsvw6` FOREIGN KEY (`us_ocorrencia`) REFERENCES `tb_ocorrencia` (`oc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-08 10:13:33
