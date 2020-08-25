CREATE SCHEMA `rooms_with_bulbs` ;

CREATE TABLE `rooms_with_bulbs`.`room` (
  `id` INT(11) NOT NULL COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `country` VARCHAR(45) NOT NULL COMMENT '',
  `light_is_on` TINYINT(1) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');
