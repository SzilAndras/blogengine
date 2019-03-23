DROP DATABASE IF EXISTS `blogengine`;
CREATE DATABASE `blogengine`;
USE `blogengine`;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

create table categories
(
  category_id int        not null
    primary key,
  name        varchar(6) null,
  constraint UK_t8o6pivur7nn124jehx7cygw5
    unique (name)
)
  engine = MyISAM;

INSERT INTO `categories` values (1, 'hí');
INSERT INTO `categories` values (2, 'hír');
INSERT INTO `categories` values (3, 'híre');
INSERT INTO `categories` values (4, 'híres');
INSERT INTO `categories` values (5, 'hírek');
INSERT INTO `categories` values (6, 'fehír');

create table posts
(
  post_id           int          not null
    primary key,
  addition_date     datetime     null,
  content           varchar(500) null,
  last_modification datetime     null,
  title             varchar(50)  null
)
  engine = MyISAM;

INSERT INTO `posts` values (10, null, 'Hosszú szöveg1.', null, 'Cím1');
INSERT INTO `posts` values (11, null, 'Hosszú szöveg2.', null, 'Cím2');
INSERT INTO `posts` values (12, null, 'Hosszú szöveg3.', null, 'Cím3');
INSERT INTO `posts` values (13, null, 'Hosszú szöveg4.', null, 'Cím4');
INSERT INTO `posts` values (14, null, 'Hosszú szöveg5.', null, 'Cím5');
INSERT INTO `posts` values (15, null, 'Hosszú szöveg6.', null, 'Cím6');