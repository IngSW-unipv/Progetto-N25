DROP TABLE IF EXISTS
    `user`,
    `group`,
    `usergroup`,
    `friend`,
    `notify`,
    `groupinvitenotify`,
    `friendnotify`,
    `payrequestbyusernotify`,
    `cashbook`,
    `transactions`,
    `cashbooktransactions`,
    `vault`,
    `creditcard`,
    `paypal`,
    `currentaccount`,
    `virtualvault`,
    `spese`,
    `paymentmethod`
;
CREATE TABLE `user`                  (id INTEGER PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL);
CREATE TABLE `group`                 (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), user_id INT NOT NULL REFERENCES user(id) ON DELETE CASCADE);
CREATE TABLE `usergroup`             (user_id INT REFERENCES `user`(id) ON DELETE CASCADE, group_id INT REFERENCES `group`(id) ON DELETE CASCADE, PRIMARY KEY(user_id, group_id));
CREATE TABLE `friend`                (user_id INT REFERENCES `user`(id) ON DELETE CASCADE, friend_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, PRIMARY KEY(user_id, friend_user_id));
CREATE TABLE `notify`                (id INTEGER AUTO_INCREMENT, user_id INT REFERENCES user(id) ON DELETE CASCADE,`description` TEXT, `type` CHAR(100), PRIMARY KEY(id));
CREATE TABLE `friendnotify`          (id INTEGER , to_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, from_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, PRIMARY KEY(id), FOREIGN KEY (id) REFERENCES `notify`(id) ON DELETE CASCADE);
CREATE TABLE `payrequestbyusernotify` (id INTEGER , to_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, from_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, amount DOUBLE, PRIMARY KEY(id), FOREIGN KEY (id) REFERENCES `notify`(id) ON DELETE CASCADE);
CREATE TABLE `groupinvitenotify`     (id INTEGER , to_user_id INT REFERENCES `user`(id) ON DELETE CASCADE, from_group_id INT REFERENCES `group`(id) ON DELETE CASCADE, PRIMARY KEY(id), FOREIGN KEY (id) REFERENCES `notify`(id) ON DELETE CASCADE);
CREATE TABLE `virtualvault`          (id INTEGER AUTO_INCREMENT, nome VARCHAR(10), user_id INT, balance DOUBLE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE);
CREATE TABLE `vault`                 (id INTEGER AUTO_INCREMENT, virtualvault_id INTEGER, PRIMARY KEY(id), FOREIGN KEY (virtualvault_id) REFERENCES virtualvault(id) ON DELETE CASCADE);
CREATE TABLE `creditcard`            (id INTEGER AUTO_INCREMENT, numerocarta VARCHAR(16) NOT NULL, mese INT NOT NULL, anno INT NOT NULL, cvv INT NOT NULL, id_vault INT, PRIMARY KEY(id), FOREIGN KEY (id_vault) REFERENCES vault(id) ON DELETE CASCADE);
CREATE TABLE `paypal`                (id INTEGER AUTO_INCREMENT,numerocarta VARCHAR(16) NOT NULL, id_vault INT, PRIMARY KEY(id), FOREIGN KEY (id_vault) REFERENCES vault(id) ON DELETE CASCADE);
CREATE TABLE `currentaccount`        (id INTEGER AUTO_INCREMENT, iban VARCHAR(34) NOT NULL, id_vault INT, PRIMARY KEY(id), FOREIGN KEY (id_vault) REFERENCES vault(id) ON DELETE CASCADE);
CREATE TABLE `spese`                 (id INTEGER AUTO_INCREMENT, user_id INT, group_id INT, amount DOUBLE, note VARCHAR(55), PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES `user`(id) ON DELETE CASCADE, FOREIGN KEY (group_id) REFERENCES  `group`(id) ON DELETE CASCADE);
CREATE TABLE `paymentmethod`         (id INTEGER AUTO_INCREMENT, id_vault INTEGER, type VARCHAR(20), id_paymentmethod INTEGER, number VARCHAR(34), PRIMARY KEY(id), FOREIGN KEY (id_vault) REFERENCES vault(id) ON DELETE CASCADE);
CREATE TABLE `cashbook`              (id INTEGER AUTO_INCREMENT, user_id INTEGER, name VARCHAR(50) NOT NULL, type TINYINT NOT NULL, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE);
CREATE TABLE `transactions`          (id INTEGER AUTO_INCREMENT, type TINYINT NOT NULL, amount DOUBLE NULL, date VARCHAR(10), category VARCHAR(50) NULL, note VARCHAR(120) NULL, PRIMARY KEY (id));
CREATE TABLE `cashbooktransactions`  (cashbook_id INT REFERENCES `cashbook`(id) ON DELETE CASCADE, transaction_id INT REFERENCES `transactions`(id) ON DELETE CASCADE, PRIMARY KEY(cashbook_id, transaction_id));


INSERT INTO `user` (username, password, email)
    VALUES
    ('dada'  ,'ciao'    ,'aaa@gmail.com'),
    ('cla'   ,'miao'    ,'bbb@gmail.com'),
    ('dema'  ,'bau'     ,'ccc@gmail.com'),
    ('buso'  ,'pluto'   ,'ddd@gmail.com'),
    ('tia'   ,'paperino','eee@gmail.com'),
    ('davide','gatto'   ,'fff@gmail.com');

INSERT INTO `group` (name, user_id)
VALUES
    ('Vacanza a New York', 1),
    ('Regalo Marco', 2),
    ('Grigliata', 4),
    ('viaggio Malesia', 1),
    ('all togheder to Nigeria', 3);

INSERT INTO `notify` (id, user_id, description, type)
VALUES

    (1, 1, "Benvenuto su LaVault =)", "empty"),
    (2, 1, "cla ti ha inviato una richiesta di amicizia !", "friendrequest"),
    (3, 1, "cla ti ha invitato a partecipare al gruppo Regalo Marco !", "groupinvite");

INSERT INTO `friend` (user_id, friend_user_id)
VALUES
    (2, 1),
    (1, 2)
;

INSERT INTO `friendnotify` (id, to_user_id, from_user_id)
VALUES
    (2, 1, 2);
INSERT INTO `groupinvitenotify` (id, to_user_id, from_group_id)
VALUES
    (3, 1, 2);

INSERT INTO usergroup (user_id, group_id) VALUES
    (1, 1),
    (2, 2),
    (2, 1),
    (3, 1),
    (3, 2),
    (4, 2),
    (6, 2),
    (4,3),
    (5,3),
    (6,3),
    (1,3),
    (1,4),
    (2,4),
    (3,4),
    (4,4),
    (5,4),
    (3,5),
    (2,5),
    (1,5),
    (6,5);


INSERT INTO cashbook (user_id, name, type) VALUES
    (1, 'default', 0),
    (1, 'contanti', 1),
    (2, 'risparmio', 1),
    (2, 'guadagno', 1),
    (3, 'risparmio', 1),
    (4, 'ciao', 1),
    (6, 'dafault', 0);

INSERT INTO transactions (id, type, amount, date, category, note) VALUES
    (1, 0, -100.00, '2021',  'Bob', 'Payment for services'),
    (2, 1, 50.00, '2021', 'Charlie', 'Refund'),
    (3, 1, -200.00, '2021',  'David', 'Gift'),
    (4, 0, 75.50, '2021', 'Frank', 'Purchase'),
    (5, 1, 120.00, '2021', 'Alice', 'Salary deposit'),
    (6, 0, -30.00, '2021', 'Bob', 'Grocery shopping'),
    (7, 1, 200.00, '2021', 'Charlie', 'Bonus'),
    (8, 0, -50.00, '2021', 'David', 'Utility bill'),
    (9, 0, -25.00, '2021', 'Eve', 'Dining out'),
    (10, 1, 150.00, '2021', 'Frank', 'Freelance payment'),
    (11, 0, -60.00, '2021', 'Grace', 'Subscription fee');

INSERT INTO cashbooktransactions (transaction_id, cashbook_id) VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),

    (6, 2),
    (7, 2),
    (8, 2),

    (9, 3),
    (10, 3),
    (11, 3);


INSERT INTO spese (user_id, group_id,amount,note) VALUES

    (1,1 , 100, 'volo'),
    (2,1 , 36.5, 'pizza'),
    (3,1 , 15, 'birre'),
    (3,1 , 19.8, 'birre'),
    (3,1 , 122, 'volo'),
    (2,1 , 13.9, 'sneck'),
    (1,1 , 83, 'regali'),
    (1,1 , 56, 'regali'),
    (2,1,700,'Hotel'),
    (2,2,18,'Tabellone'),
    (6,2,210,'Regalo brick'),
    (4,3, 80, 'cibo per tutti'),
    (4,4,50,'cibo'),
    (2,4,1200, 'Casa'),
    (5,4,118.55, 'cena'),
    (6,5,470,'La primula'),
    (3,5,250, 'Betoniera'),
    (2,5, 200, 'L insime');


    

INSERT INTO virtualvault (nome, user_id, balance) VALUES
    ('Vault', 1, 200),
    ('Vault', 2, 200),
    ('Vault', 3, 200),
    ('Vault', 4, 200),
    ('Vault', 5, 200),
    ('Vault', 6, 200);

INSERT INTO vault (virtualvault_id) VALUES
	(1),
    (2),
    (3),
    (4),
    (5),
    (6);

INSERT INTO creditcard (numerocarta, mese, anno, cvv, id_vault) VALUES
	(123456789, 1, 2020, 465, 1);

INSERT INTO paypal (numerocarta, id_vault) VALUES
	(1234567, 1);

INSERT INTO currentaccount (iban, id_vault) VALUES
	(1234, 1);
