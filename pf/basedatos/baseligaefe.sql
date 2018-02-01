-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: baseligaefe
-- ------------------------------------------------------
-- Server version	5.1.59-community

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `idCategorias` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idCategorias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipos` (
  `idCategorias` int(11) NOT NULL,
  `idIntituciones` int(11) NOT NULL,
  `nombreEquipo` varchar(45) NOT NULL,
  `idEntrenador` int(11) NOT NULL,
  PRIMARY KEY (`idCategorias`,`idIntituciones`,`nombreEquipo`),
  KEY `fk_categorias_has_intituciones_intituciones1_idx` (`idIntituciones`),
  KEY `fk_categorias_has_intituciones_categorias_idx` (`idCategorias`),
  KEY `fk_Equipos_Persona1_idx` (`idEntrenador`),
  CONSTRAINT `fk_categorias_has_intituciones_categorias` FOREIGN KEY (`idCategorias`) REFERENCES `categorias` (`idCategorias`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_categorias_has_intituciones_intituciones1` FOREIGN KEY (`idIntituciones`) REFERENCES `instituciones` (`idIntituciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipos_Persona1` FOREIGN KEY (`idEntrenador`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equiposjugadores`
--

DROP TABLE IF EXISTS `equiposjugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equiposjugadores` (
  `idPersona` int(11) NOT NULL,
  `idCategorias` int(11) NOT NULL,
  `idIntituciones` int(11) NOT NULL,
  `nombreEquipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idPersona`,`idCategorias`,`idIntituciones`,`nombreEquipo`),
  KEY `fk_Persona_has_Equipos_Equipos1_idx` (`idCategorias`,`idIntituciones`,`nombreEquipo`),
  KEY `fk_Persona_has_Equipos_Persona1_idx` (`idPersona`),
  CONSTRAINT `fk_Persona_has_Equipos_Equipos1` FOREIGN KEY (`idCategorias`, `idIntituciones`, `nombreEquipo`) REFERENCES `equipos` (`idCategorias`, `idIntituciones`, `nombreEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Persona_has_Equipos_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equiposjugadores`
--

LOCK TABLES `equiposjugadores` WRITE;
/*!40000 ALTER TABLE `equiposjugadores` DISABLE KEYS */;
/*!40000 ALTER TABLE `equiposjugadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipostorneos`
--

DROP TABLE IF EXISTS `equipostorneos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipostorneos` (
  `idCategorias` int(11) NOT NULL,
  `idIntituciones` int(11) NOT NULL,
  `nombreEquipo` varchar(45) NOT NULL,
  `idTorneos` int(11) NOT NULL,
  PRIMARY KEY (`idCategorias`,`idIntituciones`,`nombreEquipo`,`idTorneos`),
  KEY `fk_equipos_has_torneos_torneos1_idx` (`idTorneos`),
  KEY `fk_equipos_has_torneos_equipos1_idx` (`idCategorias`,`idIntituciones`,`nombreEquipo`),
  CONSTRAINT `fk_equipos_has_torneos_equipos1` FOREIGN KEY (`idCategorias`, `idIntituciones`, `nombreEquipo`) REFERENCES `equipos` (`idCategorias`, `idIntituciones`, `nombreEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipos_has_torneos_torneos1` FOREIGN KEY (`idTorneos`) REFERENCES `torneos` (`idTorneos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipostorneos`
--

LOCK TABLES `equipostorneos` WRITE;
/*!40000 ALTER TABLE `equipostorneos` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipostorneos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instituciones`
--

DROP TABLE IF EXISTS `instituciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instituciones` (
  `idIntituciones` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombreLocalia` varchar(45) NOT NULL,
  `direccionLocalia` varchar(45) NOT NULL,
  `nombreDelegado` varchar(45) NOT NULL,
  `apellidoDelegado` varchar(45) NOT NULL,
  `telefonoDelegado` varchar(45) NOT NULL,
  PRIMARY KEY (`idIntituciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituciones`
--

LOCK TABLES `instituciones` WRITE;
/*!40000 ALTER TABLE `instituciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `instituciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornadas`
--

DROP TABLE IF EXISTS `jornadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornadas` (
  `idJornadas` int(11) NOT NULL,
  `idTorneos` int(11) NOT NULL,
  `fechaDescripcion` date DEFAULT NULL,
  `idTipoEstado` int(11) NOT NULL,
  PRIMARY KEY (`idJornadas`,`idTorneos`),
  KEY `fk_jornadas_torneos1_idx` (`idTorneos`),
  KEY `fk_Jornadas_TipoEstado1_idx` (`idTipoEstado`),
  CONSTRAINT `fk_Jornadas_TipoEstado1` FOREIGN KEY (`idTipoEstado`) REFERENCES `tipoestado` (`idTipoEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jornadas_torneos1` FOREIGN KEY (`idTorneos`) REFERENCES `torneos` (`idTorneos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornadas`
--

LOCK TABLES `jornadas` WRITE;
/*!40000 ALTER TABLE `jornadas` DISABLE KEYS */;
/*!40000 ALTER TABLE `jornadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugadorespartidos`
--

DROP TABLE IF EXISTS `jugadorespartidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugadorespartidos` (
  `idPartidos` int(11) NOT NULL,
  `idJugadores` int(11) NOT NULL,
  `cantidadTarjetasAmarillas` int(11) DEFAULT NULL,
  `cantidadTarjetasRojas` int(11) DEFAULT NULL,
  `cantidadGoles` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPartidos`,`idJugadores`),
  KEY `fk_Partidos_has_Persona_Persona1_idx` (`idJugadores`),
  KEY `fk_Partidos_has_Persona_Partidos1_idx` (`idPartidos`),
  CONSTRAINT `fk_Partidos_has_Persona_Partidos1` FOREIGN KEY (`idPartidos`) REFERENCES `partidos` (`idPartidos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Partidos_has_Persona_Persona1` FOREIGN KEY (`idJugadores`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadorespartidos`
--

LOCK TABLES `jugadorespartidos` WRITE;
/*!40000 ALTER TABLE `jugadorespartidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugadorespartidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partidos` (
  `idPartidos` int(11) NOT NULL,
  `idJornadas` int(11) NOT NULL,
  `idArbrito` int(11) NOT NULL,
  `idCategoriasLocal` int(11) NOT NULL,
  `idIntitucionesLocal` int(11) NOT NULL,
  `nombreEquipoLocal` varchar(45) NOT NULL,
  `idCategoriasVisitante` int(11) NOT NULL,
  `idIntitucionesVisitante` int(11) NOT NULL,
  `nombreEquipoVisitante` varchar(45) NOT NULL,
  `golesVisitante` int(11) DEFAULT NULL,
  `golesLocales` int(11) DEFAULT NULL,
  `idTipoEstado` int(11) NOT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `hora` time NOT NULL,
  PRIMARY KEY (`idPartidos`),
  UNIQUE KEY `idIntitucionesLocal_UNIQUE` (`idIntitucionesLocal`),
  UNIQUE KEY `nombreEquipoLocal_UNIQUE` (`nombreEquipoLocal`),
  KEY `fk_partidas_jornadas1_idx` (`idJornadas`),
  KEY `fk_Partidos_Persona1_idx` (`idArbrito`),
  KEY `fk_Partidos_Equipos1_idx` (`idCategoriasLocal`,`idIntitucionesLocal`,`nombreEquipoLocal`),
  KEY `fk_Partidos_Equipos2_idx` (`idCategoriasVisitante`,`idIntitucionesVisitante`,`nombreEquipoVisitante`),
  KEY `fk_Partidos_TipoEstadoPartido1_idx` (`idTipoEstado`),
  CONSTRAINT `fk_partidas_jornadas1` FOREIGN KEY (`idJornadas`) REFERENCES `jornadas` (`idJornadas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Partidos_Equipos1` FOREIGN KEY (`idCategoriasLocal`, `idIntitucionesLocal`, `nombreEquipoLocal`) REFERENCES `equipos` (`idCategorias`, `idIntituciones`, `nombreEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Partidos_Equipos2` FOREIGN KEY (`idCategoriasVisitante`, `idIntitucionesVisitante`, `nombreEquipoVisitante`) REFERENCES `equipos` (`idCategorias`, `idIntituciones`, `nombreEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Partidos_Persona1` FOREIGN KEY (`idArbrito`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Partidos_TipoEstadoPartido1` FOREIGN KEY (`idTipoEstado`) REFERENCES `tipoestado` (`idTipoEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `idPersona` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apelido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `tipoDocumento` varchar(45) NOT NULL,
  `numeroDocumento` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `idTipoPersona` int(11) NOT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `fk_Persona_TipoPersona1_idx` (`idTipoPersona`),
  CONSTRAINT `fk_Persona_TipoPersona1` FOREIGN KEY (`idTipoPersona`) REFERENCES `tipopersona` (`idTipoPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoestado`
--

DROP TABLE IF EXISTS `tipoestado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoestado` (
  `idTipoEstado` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipoEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoestado`
--

LOCK TABLES `tipoestado` WRITE;
/*!40000 ALTER TABLE `tipoestado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoestado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopersona`
--

DROP TABLE IF EXISTS `tipopersona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipopersona` (
  `idTipoPersona` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopersona`
--

LOCK TABLES `tipopersona` WRITE;
/*!40000 ALTER TABLE `tipopersona` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipopersona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneos`
--

DROP TABLE IF EXISTS `torneos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneos` (
  `idTorneos` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `idTipoEstado` int(11) NOT NULL,
  `idCategoriasCampeo` int(11) DEFAULT NULL,
  `idIntitucionesCampeo` int(11) DEFAULT NULL,
  `nombreEquipoCampeo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTorneos`),
  KEY `fk_Torneos_TipoEstado1_idx` (`idTipoEstado`),
  KEY `fk_Torneos_Equipos1_idx` (`idCategoriasCampeo`,`idIntitucionesCampeo`,`nombreEquipoCampeo`),
  CONSTRAINT `fk_Torneos_Equipos1` FOREIGN KEY (`idCategoriasCampeo`, `idIntitucionesCampeo`, `nombreEquipoCampeo`) REFERENCES `equipos` (`idCategorias`, `idIntituciones`, `nombreEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Torneos_TipoEstado1` FOREIGN KEY (`idTipoEstado`) REFERENCES `tipoestado` (`idTipoEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneos`
--

LOCK TABLES `torneos` WRITE;
/*!40000 ALTER TABLE `torneos` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'baseligaefe'
--

--
-- Dumping routines for database 'baseligaefe'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-31 23:56:20
