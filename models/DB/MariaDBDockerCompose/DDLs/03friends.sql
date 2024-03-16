USE geoshot_db

CREATE TABLE `friends` (
  `usr_id1` int(11) NOT NULL,
  `usr_id2` int(11) NOT NULL,
  PRIMARY KEY (`usr_id1`,`usr_id2`),
  KEY `friends_users_FK_2` (`usr_id2`),
  CONSTRAINT `friends_users_FK_1` FOREIGN KEY (`usr_id1`) REFERENCES `users` (`usr_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friends_users_FK_2` FOREIGN KEY (`usr_id2`) REFERENCES `users` (`usr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;