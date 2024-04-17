CREATE DEFINER=`root`@`%` TRIGGER update_users_accuracy_on_delete
BEFORE DELETE
ON attempts
FOR EACH ROW
BEGIN
    UPDATE users
    SET accuracy = CASE
                        WHEN (users.attempts - 1) = 0 THEN 0
                        ELSE ((users.attempts * users.accuracy) - OLD.accuracy) / (users.attempts - 1)
                    END,
        attempts = CASE
                        WHEN (users.attempts - 1) = 0 THEN 0
                        ELSE attempts - 1
                    END
    WHERE usr_id = OLD.owner_att_usr_id;
END;