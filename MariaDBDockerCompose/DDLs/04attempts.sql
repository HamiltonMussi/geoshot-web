USE geoshot_db

CREATE TABLE `attempts` (
  `pub_id` int(11) NOT NULL,
  `owner_att_usr_id` int(11) NOT NULL,
  `attempt_date` datetime NOT NULL DEFAULT current_timestamp(),
  `accuracy` decimal(3,2) NOT NULL,
  PRIMARY KEY (`pub_id`,`owner_att_usr_id`),
  KEY `attempts_users_FK` (`owner_att_usr_id`),
  CONSTRAINT `attempts_publications_FK` FOREIGN KEY (`pub_id`) REFERENCES `publications` (`pub_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attempts_users_FK` FOREIGN KEY (`owner_att_usr_id`) REFERENCES `users` (`usr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;