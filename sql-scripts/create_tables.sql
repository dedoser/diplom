-- -----------------------------------------------------
-- Schema full-stack-ecommerce
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `diplom`;

CREATE SCHEMA `diplom`;
USE `diplom`;


-- -----------------------------------------------------
-- Table `diplom`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diplom`.`task`
(
    id            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    name          varchar(255) not null,
    description   text,
    task_file_loc varchar(255) not null,
    images_loc    varchar(255) default null,
    input_block   varchar(255) not null,
    output_block  varchar(255) not null,
    rise_time      varchar(255) not null,
    settling_time  varchar(255) not null,
    settling_max   varchar(255) not null,
    settling_min   varchar(255) not null,
    overshoot     varchar(255) not null,
    undershoot    varchar(255) not null,
    peak          varchar(255) not null,
    peak_time      varchar(255) not null,
    primary key (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;


create table if not exists diplom.quality
(
    id            bigint(20) not null auto_increment,
    rise_time     float      not null,
    settling_time float      not null,
    settling_min  float      not null,
    settling_max  float      not null,
    overshoot     float      not null,
    undershoot    float      not null,
    peak          float      not null,
    peak_time     float      not null,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `diplom`.`solution`
-- -----------------------------------------------------
create table if not exists `diplom`.`solution`
(
    id           bigint(20) not null auto_increment,
    task_id      bigint(20) not null,
    sol_file_loc varchar(255),
    image_system varchar(255),
    image_plot   varchar(255),
    log_file     varchar(255),
    quality_id   bigint(20) not null,
    primary key (id),
    foreign key (task_id) references task (id),
    foreign key (quality_id) references quality (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------
