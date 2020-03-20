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
-- Table `games`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`player` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `join_date` DATE NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`game` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(512) NULL,
  `release_date` DATE NULL,
  `version` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`gamesOwned`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`gamesOwned` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `player_id_go` BIGINT NOT NULL,
  `game_id_go` BIGINT NOT NULL,
  `purchase_date` DATE NULL,
  `purchase_price` DECIMAL NULL,
  PRIMARY KEY (`id`),
  INDEX `player_id_idx` (`player_id_go` ASC) VISIBLE,
  INDEX `game_id_idx` (`game_id_go` ASC) VISIBLE,
  CONSTRAINT `player_id_go`
    FOREIGN KEY (`player_id_go`)
    REFERENCES `games`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_id_go`
    FOREIGN KEY (`game_id_go`)
    REFERENCES `games`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`gamesPlayed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`gamesPlayed` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `player_id_gp` BIGINT NOT NULL,
  `game_id_gp` BIGINT NOT NULL,
  `time_finished` DATE NULL,
  `score` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `player_id_idx` (`player_id_gp` ASC) VISIBLE,
  INDEX `game_id_idx` (`game_id_gp` ASC) VISIBLE,
  CONSTRAINT `player_id_gp`
    FOREIGN KEY (`player_id_gp`)
    REFERENCES `games`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_id_gp`
    FOREIGN KEY (`game_id_gp`)
    REFERENCES `games`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `games`.`creditCard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `games`.`creditcard` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `player_id_cc` BIGINT NOT NULL,
  `cc_name` VARCHAR(45) NULL,
  `cc_number` VARCHAR(45) NULL,
  `security_code` INT NULL,
  `exp_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `player_id_idx` (`player_id_cc` ASC) VISIBLE,
  CONSTRAINT `player_id_cc`
    FOREIGN KEY (`player_id_cc`)
    REFERENCES `games`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
