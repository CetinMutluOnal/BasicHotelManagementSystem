-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: hotelreservation
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `calisanotel`
--

DROP TABLE IF EXISTS `calisanotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calisanotel` (
  `calisan_id` int NOT NULL,
  `otel_id` int NOT NULL,
  KEY `calisan_id` (`calisan_id`),
  KEY `otel_id` (`otel_id`),
  CONSTRAINT `calisanotel_ibfk_2` FOREIGN KEY (`otel_id`) REFERENCES `otel` (`id`),
  CONSTRAINT `calisanotel_ibfk_3` FOREIGN KEY (`calisan_id`) REFERENCES `kullanici` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calisanotel`
--

LOCK TABLES `calisanotel` WRITE;
/*!40000 ALTER TABLE `calisanotel` DISABLE KEYS */;
INSERT INTO `calisanotel` VALUES (3,1),(4,2);
/*!40000 ALTER TABLE `calisanotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isim` text NOT NULL,
  `sifre` text NOT NULL,
  `email` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici`
--

LOCK TABLES `kullanici` WRITE;
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` VALUES (1,'admin','admin','admin@admin.com'),(3,'user1','user1','user1@admin.com'),(4,'user2','user2','user2@admin.com');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanicirol`
--

DROP TABLE IF EXISTS `kullanicirol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanicirol` (
  `kullanici_id` int NOT NULL,
  `rol_id` int NOT NULL,
  KEY `kullanici_id` (`kullanici_id`,`rol_id`),
  KEY `rol_id` (`rol_id`),
  CONSTRAINT `kullanicirol_ibfk_2` FOREIGN KEY (`rol_id`) REFERENCES `roller` (`id`),
  CONSTRAINT `kullanicirol_ibfk_3` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanicirol`
--

LOCK TABLES `kullanicirol` WRITE;
/*!40000 ALTER TABLE `kullanicirol` DISABLE KEYS */;
INSERT INTO `kullanicirol` VALUES (1,1),(3,2),(4,2);
/*!40000 ALTER TABLE `kullanicirol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musteri`
--

DROP TABLE IF EXISTS `musteri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musteri` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isim` text NOT NULL,
  `sifre` text NOT NULL,
  `mail` text NOT NULL,
  `telefon_no` varchar(25) NOT NULL,
  `kimlik_no` varchar(25) NOT NULL,
  `adres` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musteri`
--

LOCK TABLES `musteri` WRITE;
/*!40000 ALTER TABLE `musteri` DISABLE KEYS */;
INSERT INTO `musteri` VALUES (1,'Çetin Mutlu Önal','cetin3838','cetinmutluonal@gmail.com','5514128010','11111111111','Antalya'),(2,'Şafak Yaral','safak3535','safakyaral1905@gmail.com','3535353535','53535353535','bergama'),(3,'Onur Kablan','onur1234','onurkablan@hotmail.com','5534379281','33333333333','Eyüp'),(4,'Oğuzhan Yaşar','ogi6161','oguzhanyasar61@gmail.com','6161616161','16161616161','rize'),(6,'Fatih Terim','fatih1905','imparator@gmail.com','5359051905','53490590534','Galatasaray'),(9,'test','test','test','1234567890','12345678900','local');
/*!40000 ALTER TABLE `musteri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oda`
--

DROP TABLE IF EXISTS `oda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numara` int NOT NULL,
  `otel_id` int NOT NULL,
  `rezervasyon_id` int DEFAULT NULL,
  `yatak_sayisi` int NOT NULL,
  `fiyat` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rezz` (`rezervasyon_id`),
  KEY `rezervasyon_id` (`rezervasyon_id`),
  KEY `otel_id` (`otel_id`),
  KEY `id` (`id`),
  CONSTRAINT `oda_ibfk_3` FOREIGN KEY (`rezervasyon_id`) REFERENCES `rezervasyon` (`id`),
  CONSTRAINT `oda_ibfk_4` FOREIGN KEY (`otel_id`) REFERENCES `otel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oda`
--

LOCK TABLES `oda` WRITE;
/*!40000 ALTER TABLE `oda` DISABLE KEYS */;
INSERT INTO `oda` VALUES (1,2,1,17,2,300),(2,3,1,NULL,1,350),(22,306,1,NULL,0,100),(26,310,1,1,4,350),(28,312,2,8,3,300),(31,314,2,14,2,250);
/*!40000 ALTER TABLE `oda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otel`
--

DROP TABLE IF EXISTS `otel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `otel_isim` text NOT NULL,
  `sehir` text,
  `ilce` text,
  `aciklama` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otel`
--

LOCK TABLES `otel` WRITE;
/*!40000 ALTER TABLE `otel` DISABLE KEYS */;
INSERT INTO `otel` VALUES (1,'KYK Suite','Tekirdağ','Çorlu','4 yıldızlı'),(2,'Çorlu Palace','Tekirdağ','Çorlu','3 yıldızlı');
/*!40000 ALTER TABLE `otel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervasyon`
--

DROP TABLE IF EXISTS `rezervasyon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezervasyon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `baslangic_tarihi` date NOT NULL,
  `bitis_tarihi` date NOT NULL,
  `musteri_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rezervasyon_1_idx` (`musteri_id`),
  CONSTRAINT `rezervasyon_ibfk_1` FOREIGN KEY (`musteri_id`) REFERENCES `musteri` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervasyon`
--

LOCK TABLES `rezervasyon` WRITE;
/*!40000 ALTER TABLE `rezervasyon` DISABLE KEYS */;
INSERT INTO `rezervasyon` VALUES (1,'2022-05-02','2022-05-10',NULL),(5,'2022-07-30','2022-08-30',2),(8,'2022-08-15','2022-08-30',NULL),(9,'2021-01-01','2021-01-02',NULL),(10,'2021-01-01','2021-01-02',NULL),(12,'2022-05-26','2022-05-28',6),(14,'2022-05-27','2022-05-31',4),(15,'2022-05-26','2022-05-28',6),(16,'2022-05-26','2022-05-31',6),(17,'2022-05-26','2022-05-26',6);
/*!40000 ALTER TABLE `rezervasyon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roller`
--

DROP TABLE IF EXISTS `roller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roller` (
  `id` int NOT NULL,
  `rol_isim` varchar(50) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roller`
--

LOCK TABLES `roller` WRITE;
/*!40000 ALTER TABLE `roller` DISABLE KEYS */;
INSERT INTO `roller` VALUES (1,'Sistem Yöneticisi'),(2,'Sistem Kullanıcısı');
/*!40000 ALTER TABLE `roller` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-16  1:21:14
