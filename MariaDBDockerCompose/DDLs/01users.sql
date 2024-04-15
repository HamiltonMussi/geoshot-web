USE geoshot_db

CREATE TABLE `users` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `photo` mediumtext NOT NULL,
  `email` varchar(200) NOT NULL,
  `accuracy` decimal(3,2) NOT NULL DEFAULT 0.00,
  `attempts` int(11) NOT NULL DEFAULT 0,
  `password` varchar(65) NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `users_username_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;