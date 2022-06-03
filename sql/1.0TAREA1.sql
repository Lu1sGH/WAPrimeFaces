-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TAREA1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TAREA1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TAREA1` DEFAULT CHARACTER SET utf8 ;
USE `TAREA1` ;

-- -----------------------------------------------------
-- Table `TAREA1`.`tbl_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`tbl_user` (
  `User` VARCHAR(50) NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Correo` VARCHAR(100) NULL,
  `Password` VARCHAR(40) NULL,
  PRIMARY KEY (`User`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAREA1`.`tbl_rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`tbl_rol` (
  `Rol` VARCHAR(50) NOT NULL,
  `Descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`Rol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAREA1`.`tbl_user_has_tbl_rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`tbl_user_has_tbl_rol` (
  `tbl_user_User` VARCHAR(50) NOT NULL,
  `tbl_rol_Rol` VARCHAR(50) NOT NULL,
  `Inicio` DATETIME NULL,
  `Fin` DATETIME NULL,
  PRIMARY KEY (`tbl_user_User`, `tbl_rol_Rol`),
  INDEX `fk_tbl_user_has_tbl_rol_tbl_rol1_idx` (`tbl_rol_Rol` ASC) VISIBLE,
  INDEX `fk_tbl_user_has_tbl_rol_tbl_user_idx` (`tbl_user_User` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_user_has_tbl_rol_tbl_user`
    FOREIGN KEY (`tbl_user_User`)
    REFERENCES `TAREA1`.`tbl_user` (`User`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_user_has_tbl_rol_tbl_rol1`
    FOREIGN KEY (`tbl_rol_Rol`)
    REFERENCES `TAREA1`.`tbl_rol` (`Rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
