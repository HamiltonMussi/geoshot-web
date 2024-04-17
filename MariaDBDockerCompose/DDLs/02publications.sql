USE geoshot_db

CREATE TABLE `publications` (
  `pub_id` int(11) NOT NULL AUTO_INCREMENT,
  `photo` mediumtext NOT NULL,
  `owner_user_id` int(11) NOT NULL,
  `date_of_creation` datetime NOT NULL DEFAULT current_timestamp(),
  `correct_value` varchar(50) NOT NULL,
  PRIMARY KEY (`pub_id`),
  KEY `publications_users_FK` (`owner_user_id`),
  CONSTRAINT `publications_users_FK` FOREIGN KEY (`owner_user_id`) REFERENCES `users` (`usr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;