-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ggdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ggdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ggdb` DEFAULT CHARACTER SET utf8 ;
USE `ggdb` ;

-- -----------------------------------------------------
-- Table `ggdb`.`TOKEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`TOKEN` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(255) NOT NULL,
  `created_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ggdb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`USER` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(70) NULL,
  `phone` CHAR(10) NULL,
  `avatar` VARCHAR(45) NULL,
  `name` VARCHAR(20) NULL,
  `age` TINYINT NULL,
  `sex` TINYINT NULL,
  `link_contact_info` VARCHAR(70) NULL,
  `role` VARCHAR(45) NULL,
  `TOKEN_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_USER_TOKEN_idx` (`TOKEN_id` ASC),
  CONSTRAINT `fk_USER_TOKEN`
    FOREIGN KEY (`TOKEN_id`)
    REFERENCES `ggdb`.`TOKEN` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ggdb`.`DONATION_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`DONATION_TYPE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ggdb`.`AREA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`AREA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ggdb`.`DONATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`DONATION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `AREA_id` INT NOT NULL,
  `link_images` TEXT NULL,
  `TYPE_id` INT NOT NULL,
  `description` TEXT NOT NULL,
  `created_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_received` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_PRODUCT_TYPE1_idx` (`TYPE_id` ASC),
  INDEX `fk_PRODUCT_AREA1_idx` (`AREA_id` ASC),
  CONSTRAINT `fk_PRODUCT_TYPE1`
    FOREIGN KEY (`TYPE_id`)
    REFERENCES `ggdb`.`DONATION_TYPE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCT_AREA1`
    FOREIGN KEY (`AREA_id`)
    REFERENCES `ggdb`.`AREA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ggdb`.`COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`COMMENT` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `USER_id` INT NOT NULL,
  `DONATION_id` INT NOT NULL,
  `content` TEXT NOT NULL,
  `created_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_COMMENT_USER1_idx` (`USER_id` ASC),
  INDEX `fk_COMMENT_PRODUCT1_idx` (`DONATION_id` ASC),
  CONSTRAINT `fk_COMMENT_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `ggdb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COMMENT_PRODUCT1`
    FOREIGN KEY (`DONATION_id`)
    REFERENCES `ggdb`.`DONATION` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ggdb`.`REPORT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`REPORT` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `USER_id` INT NOT NULL,
  `DONATION_id` INT NOT NULL,
  `description` TEXT NOT NULL,
  `reply` TEXT NULL,
  `created_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_REPORT_USER1_idx` (`USER_id` ASC),
  INDEX `fk_REPORT_PRODUCT1_idx` (`DONATION_id` ASC),
  CONSTRAINT `fk_REPORT_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `ggdb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REPORT_PRODUCT1`
    FOREIGN KEY (`DONATION_id`)
    REFERENCES `ggdb`.`DONATION` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ggdb`.`RELATIONSHIP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ggdb`.`RELATIONSHIP` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `USER_id` INT NOT NULL,
  `DONATION_id` INT NOT NULL,
  `is_donor` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_RELATIONSHIP_USER1_idx` (`USER_id` ASC),
  INDEX `fk_RELATIONSHIP_PRODUCT1_idx` (`DONATION_id` ASC),
  CONSTRAINT `fk_RELATIONSHIP_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `ggdb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RELATIONSHIP_PRODUCT1`
    FOREIGN KEY (`DONATION_id`)
    REFERENCES `ggdb`.`DONATION` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
