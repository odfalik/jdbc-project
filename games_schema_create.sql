-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema games
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema games
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `games` DEFAULT CHARACTER SET utf8 ;
USE `games` ;

-- -----------------------------------------------------
-- Table `games`.`Player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`Player` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `join_date` DATE NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`Game` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `releaseDate` DATE NULL,
  `version` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`GamesOwned`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`GamesOwned` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `playerID_go` BIGINT NOT NULL,
  `gameID_go` BIGINT NOT NULL,
  `purchaseDate` DATE NULL,
  `purchasePrice` DECIMAL NULL,
  PRIMARY KEY (`id`),
  INDEX `playerID_idx` (`playerID_go` ASC) VISIBLE,
  INDEX `gameID_idx` (`gameID_go` ASC) VISIBLE,
  CONSTRAINT `playerID_go`
    FOREIGN KEY (`playerID_go`)
    REFERENCES `games`.`Player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `gameID_go`
    FOREIGN KEY (`gameID_go`)
    REFERENCES `games`.`Game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`GamesPlayed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`GamesPlayed` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `playerID_gp` BIGINT NOT NULL,
  `gameID_gp` BIGINT NOT NULL,
  `timeFinished` DATE NULL,
  `score` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `playerID_idx` (`playerID_gp` ASC) VISIBLE,
  INDEX `gameID_idx` (`gameID_gp` ASC) VISIBLE,
  CONSTRAINT `playerID_gp`
    FOREIGN KEY (`playerID_gp`)
    REFERENCES `games`.`Player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `gameID_gp`
    FOREIGN KEY (`gameID_gp`)
    REFERENCES `games`.`Game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`CreditCard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`CreditCard` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `playerID_cc` BIGINT NOT NULL,
  `ccName` VARCHAR(45) NULL,
  `ccNumber` VARCHAR(45) NULL,
  `securityCode` INT NULL,
  `expDate` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `playerID_idx` (`playerID_cc` ASC) VISIBLE,
  CONSTRAINT `playerID_cc`
    FOREIGN KEY (`playerID_cc`)
    REFERENCES `games`.`Player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
