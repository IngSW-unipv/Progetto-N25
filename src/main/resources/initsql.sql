DROP TABLE IF EXISTS
    `user`,
    `group`,
    `usergroup`,
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
CREATE TABLE `user`                (id INTEGER PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL);
CREATE TABLE `group`               (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), user_id INT NOT NULL REFERENCES user(id) ON DELETE CASCADE);
CREATE TABLE `usergroup`           (user_id INT REFERENCES `user`(id), group_id INT REFERENCES `group`(id), PRIMARY KEY(user_id, group_id));
CREATE TABLE `notify`              (id INTEGER AUTO_INCREMENT, user_id INT REFERENCES user(id) ON DELETE CASCADE,`description` TEXT NOT NULL, `type` CHAR(100), PRIMARY KEY(id, user_id));
CREATE TABLE `friendnotify`        (id INTEGER , to_user_id INT, from_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, PRIMARY KEY(id, to_user_id), FOREIGN KEY (id, to_user_id) REFERENCES `notify`(id, user_id) ON DELETE CASCADE);
CREATE TABLE `vault`               (id INTEGER AUTO_INCREMENT, user_id INT, saldo DOUBLE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES user(id));
CREATE TABLE `creditcard`          (numerocarta VARCHAR(16) NOT NULL, mese INT NOT NULL, anno INT NOT NULL, cvv INT NOT NULL, vault_id INT, PRIMARY KEY(numerocarta), FOREIGN KEY (vault_id) REFERENCES vault(id));
CREATE TABLE `paypal`              (numerocarta VARCHAR(16) NOT NULL, vault_id INT, PRIMARY KEY(numerocarta), FOREIGN KEY (vault_id) REFERENCES vault(id));
CREATE TABLE `currentaccount`      (iban VARCHAR(34) NOT NULL, vault_id INT, PRIMARY KEY(iban), FOREIGN KEY (vault_id) REFERENCES vault(id));
CREATE TABLE `transactions`        (id INT NOT NULL, amount DOUBLE NULL, sender INT NULL, receiver INT NULL, type TINYINT NULL, note VARCHAR(120) NULL,PRIMARY KEY (id));
CREATE TABLE `cashbook`            (id INTEGER AUTO_INCREMENT, user_id INTEGER , name VARCHAR(50) NOT NULL, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES user(id));
CREATE TABLE `cashbooktransactions` (transaction_id INT REFERENCES `transaction`(id), cashbook_id INT REFERENCES `cashbook`(id), PRIMARY KEY(transaction_id, cashbook_id));
CREATE TABLE `virtualvault`         (id INTEGER AUTO_INCREMENT, user_id INT, balance DOUBLE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES user(id));


INSERT INTO `user` (username, password)
    VALUES
    ("dada","ciao"),
    ("cla","miao"),
    ("dema","bau"),
    ("buso","pluto"),
    ("tia","paperino"),
    ("davide","gatto");

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
    (6, "dafault"),
    (2, "viaggio"),
    (3, "ristorante"),
    (4, "carte"),
    (6, "divano");

INSERT INTO transactions (id, amount, sender, receiver, type, note) VALUES
    (1, 150.00, 301, 401, 1, 'Acquisto attrezzatura sportiva'),
    (2, 2000.00, 302, 402, 3, 'Pagamento affitto mensile'),
    (3, 35.00, 303, 403, 1, 'Cena al fast food'),
    (4, 500.00, 304, 404, 2, 'Regalo matrimonio'),
    (5, 250.00, 305, 405, 3, 'Riparazione smartphone'),
    (6, 15.00, 306, 406, 1, 'Colazione al bar'),
    (7, 1200.00, 307, 407, 3, 'Acquisto elettrodomestico'),
    (8, 60.00, 308, 408, 1, 'Abbonamento streaming'),
    (9, 350.00, 309, 409, 2, 'Prestito a un amico'),
    (10, 100.00, 310, 410, 1, 'Ricarica cellulare');


INSERT INTO cashbooktransactions (transaction_id, cashbook_id) VALUES
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 3);

