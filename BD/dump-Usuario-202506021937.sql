/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: Usuario
-- ------------------------------------------------------
-- Server version	10.5.29-MariaDB-ubu2004

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Campaña`
--

DROP TABLE IF EXISTS `Campaña`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Campaña` (
  `idCampaña` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` longtext DEFAULT NULL,
  `imagen` int(11) DEFAULT NULL,
  `Manual_id` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `archivada` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idCampaña`),
  KEY `fk_Campaña_Manual1_idx` (`Manual_id`),
  CONSTRAINT `fk_Campaña_Manual1` FOREIGN KEY (`Manual_id`) REFERENCES `Manual` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Campaña`
--

LOCK TABLES `Campaña` WRITE;
/*!40000 ALTER TABLE `Campaña` DISABLE KEYS */;
INSERT INTO `Campaña` VALUES
(2,'brea','re fachera la campaña',1,'the_stange','chupame la polla',0);
/*!40000 ALTER TABLE `Campaña` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Caracteristicas`
--

DROP TABLE IF EXISTS `Caracteristicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Caracteristicas` (
  `nombre` varchar(45) NOT NULL,
  `reservaMax` int(11) DEFAULT NULL,
  `reservaAct` int(11) DEFAULT NULL,
  `ventaja` int(11) DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) NOT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_Caracteristicas_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  CONSTRAINT `fk_Caracteristicas_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Caracteristicas`
--

LOCK TABLES `Caracteristicas` WRITE;
/*!40000 ALTER TABLE `Caracteristicas` DISABLE KEYS */;
/*!40000 ALTER TABLE `Caracteristicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Competencias`
--

DROP TABLE IF EXISTS `Competencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Competencias` (
  `nombre` varchar(45) NOT NULL,
  `especializado` tinyint(4) DEFAULT NULL,
  `descripcion` longtext DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) DEFAULT NULL,
  `Recursion_idRecursion` int(11) DEFAULT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_Competencias_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  KEY `fk_Competencias_Recursion1_idx` (`Recursion_idRecursion`),
  CONSTRAINT `fk_Competencias_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Competencias_Recursion1` FOREIGN KEY (`Recursion_idRecursion`) REFERENCES `Recursion` (`idRecursion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Competencias`
--

LOCK TABLES `Competencias` WRITE;
/*!40000 ALTER TABLE `Competencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `Competencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipo`
--

DROP TABLE IF EXISTS `Equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Equipo` (
  `idEquipo` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` longtext DEFAULT NULL,
  `daño` int(11) DEFAULT NULL,
  `modificador` int(11) DEFAULT NULL,
  `arma` tinyint(4) DEFAULT NULL,
  `Recursion_idRecursion` int(11) NOT NULL,
  PRIMARY KEY (`idEquipo`),
  KEY `fk_Equipo_Recursion1_idx` (`Recursion_idRecursion`),
  CONSTRAINT `fk_Equipo_Recursion1` FOREIGN KEY (`Recursion_idRecursion`) REFERENCES `Recursion` (`idRecursion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipo`
--

LOCK TABLES `Equipo` WRITE;
/*!40000 ALTER TABLE `Equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FichaMejora`
--

DROP TABLE IF EXISTS `FichaMejora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `FichaMejora` (
  `Mejora_idMejora` int(11) NOT NULL,
  `Ficha_TheStrange_id` int(11) NOT NULL,
  `cogida` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`Ficha_TheStrange_id`,`Mejora_idMejora`),
  KEY `fk_FichaMejora_Mejora1_idx` (`Mejora_idMejora`),
  KEY `fk_FichaMejora_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  CONSTRAINT `fk_FichaMejora_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_FichaMejora_Mejora1` FOREIGN KEY (`Mejora_idMejora`) REFERENCES `Mejora` (`idMejora`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FichaMejora`
--

LOCK TABLES `FichaMejora` WRITE;
/*!40000 ALTER TABLE `FichaMejora` DISABLE KEYS */;
/*!40000 ALTER TABLE `FichaMejora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ficha_TheStrange`
--

DROP TABLE IF EXISTS `Ficha_TheStrange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ficha_TheStrange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `clase` varchar(45) DEFAULT NULL,
  `experiencia` int(11) DEFAULT NULL,
  `esfuerzo` int(11) DEFAULT NULL,
  `limiteDispositivos` int(11) DEFAULT NULL,
  `recuperacion` varchar(10) DEFAULT NULL,
  `trasfondo` blob DEFAULT NULL,
  `descriptor` varchar(45) DEFAULT NULL,
  `vinculoDescriptor` varchar(45) DEFAULT NULL,
  `Usuario_idUsuario` int(11) NOT NULL,
  `Manual_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Ficha_TheStrange_Usuario1_idx` (`Usuario_idUsuario`),
  KEY `fk_Ficha_TheStrange_Manual1_idx` (`Manual_id`),
  CONSTRAINT `fk_Ficha_TheStrange_Manual1` FOREIGN KEY (`Manual_id`) REFERENCES `Manual` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ficha_TheStrange_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ficha_TheStrange`
--

LOCK TABLES `Ficha_TheStrange` WRITE;
/*!40000 ALTER TABLE `Ficha_TheStrange` DISABLE KEYS */;
INSERT INTO `Ficha_TheStrange` VALUES
(1,'yuan el arkero','arkero',69,420,5,'no >:(','Estoy muerto vivo situacion de la hostia. Yo queria trabajar en la mar porque la tierra esta jodido jodido *llora*','describeme esta','no me describes no me vinuclo',1,'the_stange'),
(2,'yuan el arkero','arkero',69,420,5,'no >:(','Estoy muerto vivo situacion de la hostia. Yo queria trabajar en la mar porque la tierra esta jodido jodido *llora*','describeme esta','no me describes no me vinuclo',1,'the_stange');
/*!40000 ALTER TABLE `Ficha_TheStrange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Habilidades`
--

DROP TABLE IF EXISTS `Habilidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Habilidades` (
  `nombre` varchar(45) NOT NULL,
  `coste` varchar(45) DEFAULT NULL,
  `descripcion` longtext DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) DEFAULT NULL,
  `Competencias_nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_Habilidades_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  KEY `fk_Habilidades_Competencias1_idx` (`Competencias_nombre`),
  CONSTRAINT `fk_Habilidades_Competencias1` FOREIGN KEY (`Competencias_nombre`) REFERENCES `Competencias` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Habilidades_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Habilidades`
--

LOCK TABLES `Habilidades` WRITE;
/*!40000 ALTER TABLE `Habilidades` DISABLE KEYS */;
/*!40000 ALTER TABLE `Habilidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IndicadorDaño`
--

DROP TABLE IF EXISTS `IndicadorDaño`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `IndicadorDaño` (
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `cansado` tinyint(4) DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) NOT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_IndicadorDaño_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  CONSTRAINT `fk_IndicadorDaño_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IndicadorDaño`
--

LOCK TABLES `IndicadorDaño` WRITE;
/*!40000 ALTER TABLE `IndicadorDaño` DISABLE KEYS */;
/*!40000 ALTER TABLE `IndicadorDaño` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListaDispositivos`
--

DROP TABLE IF EXISTS `ListaDispositivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ListaDispositivos` (
  `nombre` varchar(45) NOT NULL,
  `forma` varchar(45) DEFAULT NULL,
  `efecto` longtext DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) NOT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_ListaDispositivos_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  CONSTRAINT `fk_ListaDispositivos_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListaDispositivos`
--

LOCK TABLES `ListaDispositivos` WRITE;
/*!40000 ALTER TABLE `ListaDispositivos` DISABLE KEYS */;
/*!40000 ALTER TABLE `ListaDispositivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manual`
--

DROP TABLE IF EXISTS `Manual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Manual` (
  `id` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `imagen` longtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manual`
--

LOCK TABLES `Manual` WRITE;
/*!40000 ALTER TABLE `Manual` DISABLE KEYS */;
INSERT INTO `Manual` VALUES
('the_stange','The Strange',NULL);
/*!40000 ALTER TABLE `Manual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mejora`
--

DROP TABLE IF EXISTS `Mejora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Mejora` (
  `idMejora` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` longtext DEFAULT NULL,
  PRIMARY KEY (`idMejora`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mejora`
--

LOCK TABLES `Mejora` WRITE;
/*!40000 ALTER TABLE `Mejora` DISABLE KEYS */;
INSERT INTO `Mejora` VALUES
(1,'Increasing Capabilities','You gain 4 new points to add to your stat Pools. You can allocate the points among your Pools however you wish.You gain 4 new points to add to your stat Pools. You can allocate the points among your Pools however you wish.'),
(2,'Moving Toward Perfection','You add 1 to your Might Edge, your Speed Edge, or your Intellect Edge (your choice).'),
(3,'Extra Effort','Your Effort score increases by 1.'),
(4,'Skills','You become trained in one skill of your choice, other than attacks or defense. If you choose a skill that you are already trained in, you become specialized in that skill, reducing the difficulty of related tasks by two steps instead of one.'),
(5,'Other Options','Players can also spend 4 XP to purchase other special options. Selecting any of these options counts as purchasing one of the four stages necessary to advance to the next tier. The special options are as follows: • Reduce the cost for wearing armor. This option lowers the Might cost by 1 and lowers the Speed reduction by 1. • Add 2 to your recovery rolls');
/*!40000 ALTER TABLE `Mejora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notas`
--

DROP TABLE IF EXISTS `Notas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Notas` (
  `idNotas` int(11) NOT NULL,
  `texto` longtext DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) NOT NULL,
  PRIMARY KEY (`idNotas`),
  KEY `fk_Notas_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  CONSTRAINT `fk_Notas_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notas`
--

LOCK TABLES `Notas` WRITE;
/*!40000 ALTER TABLE `Notas` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recursion`
--

DROP TABLE IF EXISTS `Recursion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Recursion` (
  `idRecursion` int(11) NOT NULL,
  `rasgo` varchar(45) DEFAULT NULL,
  `dinero` int(11) DEFAULT NULL,
  `armadura` int(11) DEFAULT NULL,
  `apariencia` blob DEFAULT NULL,
  `raza` varchar(45) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `actual` tinyint(4) DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) NOT NULL,
  PRIMARY KEY (`idRecursion`),
  KEY `fk_Recursion_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  CONSTRAINT `fk_Recursion_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recursion`
--

LOCK TABLES `Recursion` WRITE;
/*!40000 ALTER TABLE `Recursion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Recursion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TiradasRecuperacion`
--

DROP TABLE IF EXISTS `TiradasRecuperacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `TiradasRecuperacion` (
  `id` int(11) NOT NULL,
  `tiempo` varchar(45) DEFAULT NULL,
  `descansado` tinyint(4) DEFAULT NULL,
  `Ficha_TheStrange_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_TiradasRecuperacion_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  CONSTRAINT `fk_TiradasRecuperacion_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TiradasRecuperacion`
--

LOCK TABLES `TiradasRecuperacion` WRITE;
/*!40000 ALTER TABLE `TiradasRecuperacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `TiradasRecuperacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `contraseña` varchar(128) DEFAULT NULL,
  `token` varchar(345) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES
(1,'juan','673d4b1d7deabe33d0037d3a39927ec3d56397a45f5eb9ac0512c75808c293f0d022e04adc5555cd3644d18cf79e9e9ebaea7e3a8e96744b0c49312a7f8af398','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiUkdzQ2FWZVBuSGQ4VWZtU3VSZnNhZm1zUEZTOFRqWi9TMkFVU1FPRlZsYm1haDgrSEJybXIxVlBkdWYrdk1ZQ0t6UTBWNE9xVjgxUnRSSmNibG9hK2oraG5qVXU4K1VBVDViNW0wbFd0T0czTkpaQTF2ZHUramJUKzVVM3M3eE9RMkVsVDR6bmVjODJwM01uS0MxTmJBPT0ifQ.j1Lb270e4pKV0xesOtAvI3oF2nbXEZYEWNkabqXl_Gk'),
(2,'Carol','ebcf3408f648078d81ed814f63651c88113e8e2d8a6489ff03781f7ee4229c29f115a976e40d0137007ac78fb131a628cb9baaebb15dd82aae87ffea147790d0','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiVkMvM2RpN2tOb3J0K043UktBOTIvN2VuaEM1TkEzL0FraHg4cEN3YmIyR2RYNDVxdHFRc2VsMlhtaFMyMTA5VHRMbWNiOUEvWUU4RjRTR1lVQ0ZkR3o2L3RHNlNuNGFRbHk5NFZwcGxNQ0ZSYmk4aG56YlllcFJKRVJTbnlrU2ZLdFQxcitjU3lHc3FJVmd5VmNlNlZBPT0ifQ.atmxl1oEAH_45l621Lm9u85TxmS9KiHZZp89U9vIkSI');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsuarioCampaña`
--

DROP TABLE IF EXISTS `UsuarioCampaña`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `UsuarioCampaña` (
  `Usuario_idUsuario` int(11) NOT NULL,
  `Campaña_idCampaña` int(11) NOT NULL,
  `Ficha_TheStrange_id` int(11) DEFAULT NULL,
  `master` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Usuario_idUsuario`,`Campaña_idCampaña`),
  KEY `fk_UsuarioCampaña_Ficha_TheStrange1_idx` (`Ficha_TheStrange_id`),
  KEY `fk_UsuarioCampaña_Usuario1_idx` (`Usuario_idUsuario`),
  KEY `fk_UsuarioCampaña_Campaña1_idx` (`Campaña_idCampaña`),
  CONSTRAINT `fk_UsuarioCampaña_Campaña1` FOREIGN KEY (`Campaña_idCampaña`) REFERENCES `Campaña` (`idCampaña`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UsuarioCampaña_Ficha_TheStrange1` FOREIGN KEY (`Ficha_TheStrange_id`) REFERENCES `Ficha_TheStrange` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UsuarioCampaña_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsuarioCampaña`
--

LOCK TABLES `UsuarioCampaña` WRITE;
/*!40000 ALTER TABLE `UsuarioCampaña` DISABLE KEYS */;
/*!40000 ALTER TABLE `UsuarioCampaña` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'Usuario'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-02 19:37:32
