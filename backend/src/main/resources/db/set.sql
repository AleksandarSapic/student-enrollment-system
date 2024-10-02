DROP TABLE IF EXISTS `application_form`;
DROP TABLE IF EXISTS `exam_event`;
DROP TABLE IF EXISTS `exam_event_type`;
DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `first_name`  varchar(255) NOT NULL,
    `middle_name` varchar(255) NOT NULL,
    `last_name`   varchar(255) NOT NULL,
    `email`       varchar(255) NOT NULL UNIQUE,
    `password`    varchar(255) NOT NULL,
    `price`       int(11)      NOT NULL,
    `created_at`  datetime     NOT NULL,
    `updated_at`  datetime     NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `chk_price_positive_user` CHECK (`price` >= 0)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `admin`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name`  varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL UNIQUE,
    `password`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `exam_event_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `exam_event_type` (`id`, `name`)
VALUES (1, 'Matematika'),
       (2, 'Opšta informisanost');

CREATE TABLE `exam_event`
(
    `id`                     int(11)      NOT NULL AUTO_INCREMENT,
    `name`                   varchar(255) NOT NULL,
    `maintenance_date`       date         NOT NULL,
    `application_date_start` date         NOT NULL,
    `application_date_end`   date         NOT NULL,
    `price`                  int(11)      NOT NULL,
    `number_of_applicants`   int(11)      NOT NULL,
    `created_by`             int(11)      NOT NULL,
    `created_at`             datetime     NOT NULL,
    `updated_by`             int(11)      NOT NULL,
    `updated_at`             datetime     NOT NULL,
    `type_id`                int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_name_type_id` (`name`, `type_id`),
    KEY `FK_exam_event_admin_created_by` (`created_by`),
    KEY `FK_exam_event_admin_updated_by` (`updated_by`),
    CONSTRAINT `FKdltbr5t0nljpuuo4isxgslt82` FOREIGN KEY (`created_by`) REFERENCES `admin` (`id`),
    CONSTRAINT `FKo4omy56oktgee21ndp4c7vkkb` FOREIGN KEY (`updated_by`) REFERENCES `admin` (`id`),
    CONSTRAINT `chk_price_positive_exam` CHECK (`price` >= 0),
    CONSTRAINT `chk_number_of_applicants_positive` CHECK (`number_of_applicants` >= 0),
    CONSTRAINT `chk_application_date_end_after_start` CHECK (`application_date_end` > `application_date_start`),
    CONSTRAINT `chk_maintenance_date_after_end` CHECK (`maintenance_date` > `application_date_end`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `application_form`
(
    `exam_event_id`      int(11)  NOT NULL,
    `user_id`            int(11)  NOT NULL,
    `application_number` int(11),
    `created_at`         datetime NOT NULL,
    PRIMARY KEY (`exam_event_id`, `user_id`),
    KEY `FK_application_form_exam_event` (`exam_event_id`),
    KEY `FK_application_form_user` (`user_id`),
    CONSTRAINT `FKa50hnsv7lvbmvrqh6uk4a5dck` FOREIGN KEY (`exam_event_id`) REFERENCES `exam_event` (`id`),
    CONSTRAINT `FKbcikdu9rc1eyfus48sytcq2ux` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
