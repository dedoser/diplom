-- -----------------------------------------------------
-- Schema full-stack-ecommerce
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `diplom`;

CREATE SCHEMA `diplom`;
USE `diplom` ;

-- -----------------------------------------------------
-- Table `full-stack-ecommerce`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diplom`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(255) not NULL,
  `role` varchar(255) not null, 
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `full-stack-ecommerce`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diplom`.`task` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) not null,
  `task_file_loc` varchar(255) not null,
  `images_loc` varchar(255) default null,
  `user_id` BIGINT(20) not null,
  primary key(`id`),
  foreign key(`user_id`) references users (id)
) ENGINE=InnoDB AUTO_INCREMENT = 1;

create table if not exists `diplom`.`solution` (
	id bigint(20) not null auto_increment,
    user_id bigint(20) not null,
    task_id bigint(20) not null,
    sol_file_loc varchar(255),
    sol_images_loc varchar(255),
    primary key(id),
    foreign key(user_id) references users (id),
    foreign key(task_id) references task (id)
) ENGINE=InnoDB AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------
