# La Vault - un software per la gestione delle tue finanze
Progetto di Busin Lorenzo, D'adamo Andrea, Demartini Riccardo, Dervihsi Claudio, Rizzi Davide, Sanua Mattia per il corso di Ingegneria del Software universita di Pavia
## Obbiettivi 
Ispirati da svariate app di bugget e finanza nasce La Vault un software che punta ad unificare tutte le piu famose funzioni per la gestione finanziaria, dal classico conto allo scambio di denaro sino al agenda delle spese con uno sguardo anche alle spese di gruppo.

## Panoramica Generale
- LaVault e un software implemantato in java, con una archittettura a livelli (interfaccia - logica - persistenza) dove i primi due livelli sono stati inglobati in una seconda architettura MVC e il livello di persistenza e implementato utilizando il DAOpattern
- L'intero progetto si basa fortemente sul corso di ingengeria del software del universita di pavia facendo quindi uso di svariati insegnamenti tra cui pattern, architetture, e l'intero linguaggio java.
- Il software nasce come un progetto universitario svolto da sei studenti con l'idea di unificare diverse funzionalita dei software di bugget e finanze in un unico e comodo software, per farlo si e pensato anche al multi utente cosi che i dati non siano locali ma collegati ad un account senza pero mai arrivare ad una vera e propria architettura client server. 

## Tecnologie utilizate
Durante lo sviluppo sonon state utilizate alcune tecnologie tra cui:
- MySQL connector
- [JSwing](https://docs.oracle.com/javase/8/docs/api///?javax/swing/package-summary.html)

## SetUp (necessario) per l'utilizo
- Software per la compilazione del codice (consigliato: intllij o eclipse)
- Database MySQL
### Codice dalla repository
- Nel software scelto per la compilazione importare la seguente repository git 
```markdown
git clone https://github.com/IngSW-unipv/Progetto-n25.git LaVault
```
### Inizializzazione database
- Creare un istanza nel database
- Eseguire il codice presente in [initsql.sql](https://github.com/IngSW-unipv/Progetto-N25/blob/main/src/main/resources/initsql.sql) che generera un istanza con dati per una piccola demo
### Inizializzazione properties
- Il software funziona tramite alcune proprieta utilizate per la selezione del metodo di persistenza (MySQLDAO gia implementato)
- Al interno del file [app.properties](https://github.com/IngSW-unipv/Progetto-N25/blob/main/src/main/resources/app.properties) modificare le seguenti proprieta per la connessione al DataBase
```markdown
dbname   = <DBname>
host     = <Host>
port     = <Port>
username = <Username>
password = <Password>
```
Ovvero:
- `<DBname>`: nome dello skima da utilizzare
- `<Host>`: indirizzo ip della machina che osptita il db
- `<Port>`: soket su coi connettersi 
- `<Username>`: Username del utente che utilizera il db (si consiglia di crearne uno apposito)
- `<Password>`: Password del utente 

### Avvio del Software 
- Il software e avviabile tramite l'editor eseguendo la classe main al interno di [MainApp.java](https://github.com/IngSW-unipv/Progetto-N25/blob/main/src/main/java/it/unipv/ingsw/lasout/MainApp.java)
- Elencati ora alcuni acount demo(Username, Password, Email) pronti al utilizzo:
```markdown
('dada'  ,'ciao'    ,'aaa@gmail.com')
('cla'   ,'miao'    ,'bbb@gmail.com')
('dema'  ,'bau'     ,'ccc@gmail.com')
('buso'  ,'pluto'   ,'ddd@gmail.com')
('tia'   ,'paperino','eee@gmail.com')
('davide','gatto'   ,'fff@gmail.com')
```
### Uso autonomo
- Il software e tranquillamnte trasformabile in un eseguibile seguendo le linee guida del editro utilizato
- Il Database puo essere anche creato vuoto e gli utenti creati singolarmente tramite le registrazioni ( in tal caso eseguire solo la parte DDL del file ```initsql.sql```)
