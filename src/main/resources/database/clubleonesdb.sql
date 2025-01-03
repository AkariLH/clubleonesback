-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema club2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema club2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `club2` DEFAULT CHARACTER SET utf8mb3 ;
USE `club2` ;

-- -----------------------------------------------------
-- Table `club2`.`instalacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`instalacion` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`actividad` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `id_instalacion` INT NULL DEFAULT NULL,
  `duracion` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `instalacion_ref`
    FOREIGN KEY (`id`)
    REFERENCES `club2`.`instalacion` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`tipo_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`tipo_evento` (
  `id_tipo_evento` INT NOT NULL AUTO_INCREMENT,
  `categoria` ENUM('FEMENIL', 'VARONIL') NULL DEFAULT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `modalidad` ENUM('EQUIPO', 'INDIVIDUAL', 'RELEVOS') NULL DEFAULT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `participantes` INT NULL DEFAULT NULL,
  `unidades` JSON NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_evento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`administracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`administracion` (
  `id_Administrador` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `correo` VARCHAR(50) NOT NULL,
  `contraseña` VARCHAR(255) NOT NULL DEFAULT 'password',
  `rol` ENUM('ADMIN', 'ENTRENADOR') NOT NULL,
  `contrasena` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_Administrador`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`tipoevento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`tipoevento` (
  `id_tipoevento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `modalidad` ENUM('equipo', 'individual', 'relevos') NULL DEFAULT NULL,
  `unidades` JSON NULL DEFAULT NULL,
  `categoria` ENUM('FEMENIL', 'VARONIL') NULL DEFAULT NULL,
  `participantes` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipoevento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`evento` (
  `id_evento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `fecha_inicio_inscripciones` DATETIME(6) NOT NULL,
  `fecha_fin_inscripciones` DATETIME(6) NOT NULL,
  `fecha_inicio_evento` DATETIME NOT NULL,
  `fecha_fin_evento` DATETIME NOT NULL,
  `horario` VARCHAR(255) NOT NULL,
  `modalidades` VARCHAR(50) NOT NULL,
  `Categoria` VARCHAR(50) NOT NULL,
  `costo` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  `requisitos` LONGTEXT NOT NULL,
  `reglas` LONGTEXT NOT NULL,
  `tipo_evento` INT NOT NULL,
  `id_Entrenador` INT NOT NULL,
  `id_Administrador` INT NULL DEFAULT NULL,
  `estado` ENUM('INSCRIPCIONES', 'EN CURSO', 'CANCELADO', 'REPROGRAMADO') NOT NULL,
  PRIMARY KEY (`id_evento`),
  INDEX `tipoevento_idx` (`tipo_evento` ASC) VISIBLE,
  INDEX `id_administrador_idx` (`id_Administrador` ASC) VISIBLE,
  INDEX `id_entrenador_idx` (`id_Entrenador` ASC) VISIBLE,
  CONSTRAINT `FK2vmd6d3vopdiibsskny1qqljv`
    FOREIGN KEY (`tipo_evento`)
    REFERENCES `club2`.`tipo_evento` (`id_tipo_evento`),
  CONSTRAINT `id_administrador`
    FOREIGN KEY (`id_Administrador`)
    REFERENCES `club2`.`administracion` (`id_Administrador`),
  CONSTRAINT `id_entrenador`
    FOREIGN KEY (`id_Entrenador`)
    REFERENCES `club2`.`administracion` (`id_Administrador`),
  CONSTRAINT `tipo_evento`
    FOREIGN KEY (`tipo_evento`)
    REFERENCES `club2`.`tipoevento` (`id_tipoevento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`actividadevento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`actividadevento` (
  `id_actividad` INT NOT NULL,
  `id_evento` INT NOT NULL,
  `horario` TIMESTAMP NOT NULL,
  INDEX `actividadref_idx` (`id_actividad` ASC) VISIBLE,
  INDEX `eventoref_idx` (`id_evento` ASC) VISIBLE,
  CONSTRAINT `actividadref`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `club2`.`actividad` (`id`),
  CONSTRAINT `eventoref`
    FOREIGN KEY (`id_evento`)
    REFERENCES `club2`.`evento` (`id_evento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`equipo` (
  `id_Equipo` INT NOT NULL AUTO_INCREMENT,
  `nombre_equipo` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_Equipo`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre_equipo` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`atleta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`atleta` (
  `id_atleta` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido_paterno` VARCHAR(50) NULL DEFAULT NULL,
  `apellido_materno` VARCHAR(50) NULL DEFAULT NULL,
  `FechaDeNacimiento` DATE NULL DEFAULT NULL,
  `sexo` ENUM('MASCULINO', 'FEMENINO') NOT NULL,
  `correo` VARCHAR(50) NOT NULL,
  `Peso` DECIMAL(5,2) NULL DEFAULT NULL,
  `Estatura` DECIMAL(4,2) NULL DEFAULT NULL,
  `id_equipo` INT NULL DEFAULT NULL,
  `contrasena` VARCHAR(255) NULL DEFAULT 'password',
  `fecha_de_nacimiento` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id_atleta`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
  INDEX `id_equipo_idx` (`id_equipo` ASC) VISIBLE,
  CONSTRAINT `id_equipo`
    FOREIGN KEY (`id_equipo`)
    REFERENCES `club2`.`equipo` (`id_Equipo`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`atleta_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`atleta_evento` (
  `id_atleta` INT NULL DEFAULT NULL,
  `id_evento` INT NULL DEFAULT NULL,
  `participacion` JSON NULL DEFAULT NULL,
  INDEX `id_atleta_idx` (`id_atleta` ASC) VISIBLE,
  INDEX `id_evento_idx` (`id_evento` ASC) VISIBLE,
  CONSTRAINT `id_atleta_i`
    FOREIGN KEY (`id_atleta`)
    REFERENCES `club2`.`atleta` (`id_atleta`),
  CONSTRAINT `id_evento_i`
    FOREIGN KEY (`id_evento`)
    REFERENCES `club2`.`evento` (`id_evento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `club2`.`equipo_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `club2`.`equipo_evento` (
  `id_equipo` INT NOT NULL,
  `id_evento` INT NOT NULL,
  `participacion` JSON NULL DEFAULT NULL,
  `num_integrantes_evento` INT NOT NULL,
  INDEX `id_Equipo_idx` (`id_equipo` ASC) VISIBLE,
  INDEX `id_evento_idx` (`id_evento` ASC) VISIBLE,
  CONSTRAINT `id_equipo_t`
    FOREIGN KEY (`id_equipo`)
    REFERENCES `club2`.`equipo` (`id_Equipo`),
  CONSTRAINT `id_evento_t`
    FOREIGN KEY (`id_evento`)
    REFERENCES `club2`.`evento` (`id_evento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
