-- User: highperf
-- Pwd: test4MySQL@Mac


CREATE TABLE film (
`id` INT UNSIGNED AUTO_INCREMENT,
`film_id` INT UNSIGNED NOT NULL,
`name` VARCHAR(30) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE film_actor (
`id` INT UNSIGNED AUTO_INCREMENT,
`film_id` INT UNSIGNED NOT NULL,
`name` VARCHAR(30) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- SQL
SELECT * FROM film
WHERE film_id IN (
  SELECT film_id
  FROM film_actor
  WHERE name = "Bennett"
);

-- explain
-- explain extended 来查看查询被改写成了什么样子

-- todo 实验