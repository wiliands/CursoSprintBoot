CREATE TABLE `person` (
  `person_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  PRIMARY KEY (`person_id`)
)