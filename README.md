# La Vault - un software per la gestione delle tue finanze
Progetto di Busin Lorenzo, D'adamo Andrea, Demartini Riccardo, Dervishi Claudio, Rizzi Davide, Sanua Mattia per il corso di Ingegneria del Software università di Pavia.
## Obiettivi 
Ispirati da svariate app di bugget e finanza nasce "La Vault", un software che punta ad unificare tutte le più famose funzioni per la gestione finanziaria, dal classico conto allo scambio di denaro sino all'agenda delle spese con anche uno sguardo verso le spese di gruppo.

## Panoramica Generale
- LaVault e un software implemantato in java, con una archittettura a livelli (interfaccia - logica - persistenza) dove i primi due livelli sono stati inglobati in una seconda architettura MVC e il livello di persistenza è implementato utilizando il pattern DAO.
- L'intero progetto si basa fortemente sul corso di "Programmazione a oggetti e ingengeria del software" del università di Pavia facendo quindi uso di svariati insegnamenti tra cui pattern, architetture, e l'intero linguaggio java.
- Il software nasce come un progetto universitario svolto da sei studenti con l'idea di unificare diverse funzionalita dei software di bugget e finanze in un unico e comodo software, per farlo si è pensato anche al multi utente cosi che i dati non siano locali ma collegati ad un account senza però mai arrivare ad una vera e propria architettura client-server. 

## Tecnologie utilizzate
Durante lo sviluppo sono state utilizzate alcune tecnologie tra cui:
- MySQL connector
- [JSwing](https://docs.oracle.com/javase/8/docs/api///?javax/swing/package-summary.html)

## SetUp (necessario) per l'utilizzo
- Software: per la compilazione del codice (consigliato: IntlliJ IDEA o Eclipse)
- Database: MySQL Workbench
### Codice dalla repository
- Nel software scelto per la compilazione è importare la seguente repository git 
```markdown
git clone https://github.com/IngSW-unipv/Progetto-n25.git LaVault
```
### Inizializzazione database
- Creare un istanza nel database
- Eseguire il codice presente in [initsql.sql](https://github.com/IngSW-unipv/Progetto-N25/blob/main/src/main/resources/initsql.sql) che genere un istanza con dati per una piccola demo
### Inizializzazione properties
- Il software funziona tramite alcune proprietà utilizzate per la selezione del metodo di persistenza (MySQLDAO già implementato)
- All'interno del file [app.properties](https://github.com/IngSW-unipv/Progetto-N25/blob/main/src/main/resources/app.properties) modificare le seguenti proprietà per la connessione al proprio database
```markdown
dbname   = <DBname>
host     = <Host>
port     = <Port>
username = <Username>
password = <Password>
```
Ovvero:
- `<DBname>`: nome dello skima da utilizzare
- `<Host>`: indirizzo IP della macchina che osptita il DB
- `<Port>`: soket su cui connettersi 
- `<Username>`: Username del utente che utilizera il DB (si consiglia di crearne uno apposito)
- `<Password>`: Password del utente 

### Avvio del Software 
- Il software e avviabile tramite l'editor eseguendo la classe main al interno di [MainApp.java](https://github.com/IngSW-unipv/Progetto-N25/blob/main/src/main/java/it/unipv/ingsw/lasout/MainApp.java)
- Elencati ora alcuni acount demo (Username, Password, Email) pronti all'utilizzo:
```markdown
('dada'  ,'ciao'    ,'aaa@gmail.com')
('cla'   ,'miao'    ,'bbb@gmail.com')
('dema'  ,'bau'     ,'ccc@gmail.com')
('buso'  ,'pluto'   ,'ddd@gmail.com')
('tia'   ,'paperino','eee@gmail.com')
('davide','gatto'   ,'fff@gmail.com')
```
### Uso autonomo
- Il software è tranquillamente trasformabile in un eseguibile seguendo le linee guida dell'editor utilizzato.
- Il database può essere anche creato vuoto e gli utenti creati singolarmente tramite le registrazioni (in tal caso eseguire solo la parte DDL del file ```initsql.sql```)
