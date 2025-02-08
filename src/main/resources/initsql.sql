DROP TABLE IF EXISTS
    `user`,
    `group`,
    `usergroup`,
    `friend`,
    `notify`,
    `friendnotify`,
    `vault`,
    `creditcard`,
    `paypal`,
    `currentaccount`,
    `cashbook`,
    `cashbooktransactions`,
    `transactions`,
    `virtualvault`
;
CREATE TABLE `user`                (id INTEGER PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL);
CREATE TABLE `group`               (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), user_id INT NOT NULL REFERENCES user(id) ON DELETE CASCADE);
CREATE TABLE `usergroup`           (user_id INT REFERENCES `user`(id), group_id INT REFERENCES `group`(id), PRIMARY KEY(user_id, group_id));
CREATE TABLE `friend`              ( user_id INT REFERENCES `user`(id) ON DELETE CASCADE, friend_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, PRIMARY KEY(user_id, friend_user_id));
CREATE TABLE `notify`              (id INTEGER AUTO_INCREMENT, user_id INT REFERENCES user(id) ON DELETE CASCADE,`description` TEXT NOT NULL, `type` CHAR(100), PRIMARY KEY(id));
CREATE TABLE `friendnotify`        (id INTEGER , to_user_id INT, from_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, PRIMARY KEY(id, to_user_id), FOREIGN KEY (id) REFERENCES `notify`(id) ON DELETE CASCADE);
CREATE TABLE `vault`               (id INTEGER AUTO_INCREMENT, user_id INT, saldo DOUBLE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES user(id));
CREATE TABLE `creditcard`          (numerocarta VARCHAR(16) NOT NULL, mese INT NOT NULL, anno INT NOT NULL, cvv INT NOT NULL, vault_id INT, PRIMARY KEY(numerocarta), FOREIGN KEY (vault_id) REFERENCES vault(id));
CREATE TABLE `paypal`              (numerocarta VARCHAR(16) NOT NULL, vault_id INT, PRIMARY KEY(numerocarta), FOREIGN KEY (vault_id) REFERENCES vault(id));
CREATE TABLE `currentaccount`      (iban VARCHAR(34) NOT NULL, vault_id INT, PRIMARY KEY(iban), FOREIGN KEY (vault_id) REFERENCES vault(id));
CREATE TABLE `transactions`         (id INT NOT NULL, amount DOUBLE NULL,type TINYINT NULL,sender VARCHAR(45) NULL, receiver VARCHAR(55) NULL,note VARCHAR(120) NULL,PRIMARY KEY (id));
CREATE TABLE `cashbook`            (id INTEGER AUTO_INCREMENT, user_id INTEGER , name VARCHAR(50) NOT NULL, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES user(id));
CREATE TABLE `cashbooktransactions` (transaction_id INT REFERENCES `transaction`(id), cashbook_id INT REFERENCES `cashbook`(id), PRIMARY KEY(transaction_id, cashbook_id));
CREATE TABLE `virtualvault`         (id INTEGER AUTO_INCREMENT, user_id INT, balance DOUBLE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES user(id));


INSERT INTO `user` (username, password, email)
    VALUES
    ("dada","ciao","aaa@hotmail.com"),
    ("cla","miao","bbb@ubuntumail.com"),
    ("dema","bau","ccc@gmail.com"),
    ("buso","pluto","ddd@sku.org"),
    ("tia","paperino","eee@it.gmail"),
    ("davide","gatto","fff");

INSERT INTO `group` (name, user_id)
VALUES
    ("GR1", 1),
    ("GR2", 2);

INSERT INTO `notify` (user_id, id, description, type)
VALUES
    (1, 1, "Benvenuto su LaVault =) Accetta la mia amicizia !", "notifyfriendrequest");


INSERT INTO `friendnotify` (to_user_id, id, from_user_id)
VALUES
    (1, 1, 2);

INSERT INTO usergroup (user_id, group_id) VALUES
    (1, 1),
    (2, 2),
    (2, 1),
    (3, 1),
    (3, 2),
    (4, 2),
    (6, 2);


INSERT INTO cashbook (user_id, name) VALUES
    (1, "default"),
    (2, "risparmio"),
    (2, "guadagno"),
    (3, "risparmio"),
    (4, "ciao"),
    (6, "dafult");

INSERT INTO transactions (id, amount, type, sender, receiver, note) VALUES
    (1, 100.00, 0, 'Alice', 'Bob', 'Payment for services'),
    (2, 50.00, 1, 'Bob', 'Charlie', 'Refund'),
    (3, 200.00, 1, 'Alice', 'David', 'Gift'),
    (4, 75.50, 0, 'Eve', 'Frank', 'Purchase');

INSERT INTO cashbooktransactions (transaction_id, cashbook_id) VALUES
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 3);

