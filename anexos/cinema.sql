-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8 ;
USE `cinema` ;

-- -----------------------------------------------------
-- Table `cinema`.`pelicula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`pelicula` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_original` VARCHAR(45) NOT NULL,
  `nombre_traducido` VARCHAR(45) NOT NULL,
  `fecha_estreno` DATE NOT NULL,
  `fecha_baja` DATE NOT NULL,
  `url_imagen` VARCHAR(200) NOT NULL,
  `sipnosis` VARCHAR(800) NOT NULL,
  `formato` VARCHAR(45) NOT NULL,
  `duracion` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`ciudad` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `tipo_identificacion` VARCHAR(45) NOT NULL,
  `numero_identificacion` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(50) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`sucursal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `ciudad_id` INT(11) NOT NULL,
  `administrador_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `ciudad_id`, `administrador_id`),
  INDEX `fk_SUCURSAL_CIUDAD_idx` (`ciudad_id` ASC),
  INDEX `fk_SUCURSAL_USUARIO1_idx` (`administrador_id` ASC),
  CONSTRAINT `fk_SUCURSAL_CIUDAD`
    FOREIGN KEY (`ciudad_id`)
    REFERENCES `cinema`.`ciudad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SUCURSAL_USUARIO1`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `cinema`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`catalogo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`catalogo` (
  `pelicula_id` INT(11) NOT NULL AUTO_INCREMENT,
  `sucursal_id` INT(11) NOT NULL,
  PRIMARY KEY (`pelicula_id`, `sucursal_id`),
  INDEX `fk_PELICULA_has_SUCURSAL_SUCURSAL1_idx` (`sucursal_id` ASC),
  INDEX `fk_PELICULA_has_SUCURSAL_PELICULA1_idx` (`pelicula_id` ASC),
  CONSTRAINT `fk_PELICULA_has_SUCURSAL_PELICULA1`
    FOREIGN KEY (`pelicula_id`)
    REFERENCES `cinema`.`pelicula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PELICULA_has_SUCURSAL_SUCURSAL1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `cinema`.`sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`genero` (
  `nombre` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`informacion_adicional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`informacion_adicional` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `label_campo` VARCHAR(200) NOT NULL,
  `tipo_campo` VARCHAR(100) NOT NULL,
  `valor_campo` VARCHAR(45) NOT NULL,
  `pelicula_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_INFORMACION_ADICIONAL_PELICULA1_idx` (`pelicula_id` ASC),
  CONSTRAINT `fk_INFORMACION_ADICIONAL_PELICULA1`
    FOREIGN KEY (`pelicula_id`)
    REFERENCES `cinema`.`pelicula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`pelicula_genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`pelicula_genero` (
  `pelicula_id` INT(11) NOT NULL,
  `genero` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`pelicula_id`, `genero`),
  INDEX `fk_PELICULA_has_GENERO_GENERO1_idx` (`genero` ASC),
  INDEX `fk_PELICULA_has_GENERO_PELICULA1_idx` (`pelicula_id` ASC),
  CONSTRAINT `fk_PELICULA_has_GENERO_GENERO1`
    FOREIGN KEY (`genero`)
    REFERENCES `cinema`.`genero` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PELICULA_has_GENERO_PELICULA1`
    FOREIGN KEY (`pelicula_id`)
    REFERENCES `cinema`.`pelicula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`tipo_sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`tipo_sala` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`sala` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo_sala_id` INT(11) NOT NULL,
  `numero_filas` INT(11) NOT NULL,
  `max_sillas_fila` INT(11) NOT NULL,
  `sucursal_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `tipo_sala_id`, `sucursal_id`),
  INDEX `fk_SALA_TIPO_SALA1_idx` (`tipo_sala_id` ASC),
  INDEX `fk_SALA_SUCURSAL1_idx` (`sucursal_id` ASC),
  CONSTRAINT `fk_SALA_SUCURSAL1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `cinema`.`sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SALA_TIPO_SALA1`
    FOREIGN KEY (`tipo_sala_id`)
    REFERENCES `cinema`.`tipo_sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `chk_NUMERO_FILAS`
    CHECK (numero_filas <= 27 and numero_filas >= 3))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`programacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`programacion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `pelicula_id` INT(11) NOT NULL,
  `sala_id` INT(11) NOT NULL,
  `funcion` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PELICULA_has_SALA_SALA1_idx` (`sala_id` ASC),
  INDEX `fk_PELICULA_has_SALA_PELICULA1_idx` (`pelicula_id` ASC),
  CONSTRAINT `fk_PELICULA_has_SALA_PELICULA1`
    FOREIGN KEY (`pelicula_id`)
    REFERENCES `cinema`.`pelicula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PELICULA_has_SALA_SALA1`
    FOREIGN KEY (`sala_id`)
    REFERENCES `cinema`.`sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`reserva` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ciudad_id` INT(11) NOT NULL,
  `sucursal_id` INT(11) NOT NULL,
  `programacion_id` INT(11) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `sillas` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `ciudad_id`, `sucursal_id`, `programacion_id`),
  INDEX `fk_RESERVA_CIUDAD1_idx` (`ciudad_id` ASC),
  INDEX `fk_RESERVA_SUCURSAL1_idx` (`sucursal_id` ASC),
  INDEX `fk_RESERVA_PROGRAMACION1_idx` (`programacion_id` ASC),
  CONSTRAINT `fk_RESERVA_CIUDAD1`
    FOREIGN KEY (`ciudad_id`)
    REFERENCES `cinema`.`ciudad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RESERVA_PROGRAMACION1`
    FOREIGN KEY (`programacion_id`)
    REFERENCES `cinema`.`programacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RESERVA_SUCURSAL1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `cinema`.`sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`rol` (
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`usuario_rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`usuario_rol` (
  `usuario_id` INT(11) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usuario_id`, `rol`),
  INDEX `fk_USUARIO_has_ROL_ROL1_idx` (`rol` ASC),
  INDEX `fk_USUARIO_has_ROL_USUARIO1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_USUARIO_has_ROL_ROL1`
    FOREIGN KEY (`rol`)
    REFERENCES `cinema`.`rol` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_ROL_USUARIO1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `cinema`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinema`.`CONFIGURACION_SISTEMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`CONFIGURACION_SISTEMA` (
  `id` VARCHAR(45) NOT NULL,
  `valor` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
