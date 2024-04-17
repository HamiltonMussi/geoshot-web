CREATE DEFINER=`root`@`%` TRIGGER update_users_accuracy
AFTER INSERT
ON attempts
FOR EACH ROW
BEGIN
    UPDATE users
    SET accuracy = (users.attempts * users.accuracy + NEW.accuracy) / (users.attempts + 1),
        attempts = attempts + 1
    WHERE usr_id = NEW.owner_att_usr_id;
END;