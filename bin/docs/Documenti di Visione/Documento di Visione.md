Documento di Visione \- LaSout  
Partecipanti: Dervishi Claudio, Busin Lorenzo, Sanua Mattia, D’Adamo Andrea, Rizzi Davide, Demartini Riccardo

LaVault  
**Obiettivo del progetto**  
Questo progetto punta a creare un software per la gestione o il risparmio delle finanze personali (individuali) o di gruppo (collettive), in un'unica formula.  
Un utente ha la possibilità di gestire le sue finanze registrando manualmente e/o automaticamente uscite ed entrate.  
Tutte le funzionalità implementate in questo progetto servono a rendere il più semplice possibile la visione e il controllo delle varie spese tramite l’utilizzo di gruppi, salvadanai e conti.

Il software vuole essere diviso in due parti principali: lato utente, ovvero una parte privata, e lato gruppo, dove più utenti (amici, coinquilini, parenti ecc.) potranno creare dei gruppi con diverso obiettivo (risparmio, divisione spese ecc.).

**LATO UTENTE**  
**(LATO PRIVATO)**

**Vault**  
Ogni utente avrà: Il “Vault", ovvero il fulcro del lato utente (un contenitore privato del saldo del singolo utente (che non potrà mai andare in rosso ma al massimo a zero)) ed è un vero e proprio conto virtuale (senza l’IBAN perchè non è una banca) visualizzato tramite un’interfaccia nella quale si può:

* vedere il saldo presente sull'app;  
* effettuare pagamenti:(permette di fare da bridge nel contesto di pagamento) si consumerà solo il saldo presente sul vault  con la possibilità di selezionare un metodo di pagamento supportato, che può essere ad esempio una carta di credito;  
* eseguire un “giroconto”: spostare dal Vault a una carta/conto registrato una qualsiasi cifra che però non superi il saldo del Vault stesso (sennò manderà un messaggio d’errore);  
* ricaricare il Vault: un Vault può essere ricaricato in diversi modi:  
  * direttamente da una carta/conto tra quelli registrati;  
  * usare una carta non ancora registrata (nel caso in cui la ricarica verrà fatta una volta ogni tanto);  
  * da un altro Vault (tramite la funzionalità **sendMe**).

  Nel caso in cui la ricarica venga confermata l’importo verrà aggiunto al saldo del Vault (il saldo del vault rimarrà invariato nel caso di transazione negata).

* rimuovere conti/carte associate: ogni utente potrà gestire le varie carte/conti collegati eseguendo su di essi:  
  * l’inserimento (in qualsiasi momento);  
  * la rimozione (in qualsiasi momento);  
  * modificare lo stato di una carta/conto scegliendo se renderla il metodo “predefinito” per i pagamenti.  
* collegare conti di terze parti: si potrà collegare un conto terzo al vault tramite diversi modi:  
  * collegando direttamente il conto bancario  
  * tramite una carta di credito o debito a 16 cifre.  
* lo storico movimenti: è una lista di tutte le transazioni, dalla più recente alla meno recente, in entrata e in uscita dal conto che siano essi pagamenti o trasferimenti di denaro da/verso altri conti, o spostamenti da/verso salvadanai (detti “VirtualVault”). Ogni transazione avrà alcune informazioni come l’importo, la data, nome, e una categoria che si potrà selezionare tra quelle preesistenti (sarà possibile anche crearne di nuove).

**CashBook**  
Un cashbook è una lista di movimenti che raccoglie automaticamente le transazioni eseguite dai conti associati all'account. A queste transazioni è possibile aggiungere o rimuovere manualmente quelle effettuate in contante.   
Ogni cashbook rappresenta, ad esempio, un portafoglio personale (ogni utente avrà il proprio cashbook) e vuole essere una sezione dedicata alla gestione del budget e delle finanze, in grado di tracciare storici delle spese e delle entrate.   
Gli utenti potranno creare differenti cashbook, scegliendo tra predefiniti o personalizzati, ai quali aggiungere spese o entrate anche non effettuate tramite l'app, con la possibilità di avere più sezioni o storici separati.   
Rimarrà comunque uno storico generale, o “principale”, che racchiude tutte le transazioni, le quali possono essere etichettate scegliendo tra le categorie di spesa predefinite o tra quelle personalizzate.  
Per ogni movimento eseguito tramite il vault, sarà possibile decidere se aggiungerlo automaticamente al conteggio.  
Ci sarà la possibilità di aggiungere tali spese alla lista indicando importo, categoria, un commento facoltativo (per indicare il motivo della spesa o il tipo di spesa svolta).  
I cashbook all’interno dell’applicazione possono essere di diverso tipo:

* cashbook personalizzato: conterrà un certo tipo di spese a seconda delle necessità dell’utente;  
* cashbook predefinito: che registra tutte le spese a prescindere che siano online o fisiche

Caratteristiche:

* ci sarà una sezione “Sommario” con un riassunto di tutte le entrate e uscite per ogni cashBook o per intervalli di tempo (settimana, giorno, mese, anno, totale)  
* le transazioni possono essere eliminate manualmente oppure essere spostate in un altro cashbook  
* tutti i cashbook, ad eccezione del principale e quello relativo ai conti associati, sono eliminabili in qualsiasi momento

Un movimento di un cashbook si compone di:

* importo  
* data  
* tipologia (entrata o uscita)  
* categoria (descrizione compresa di icona utile all’utente per categorizzare le spese)

Per ogni nuova transazione si potrà decidere a quale categoria appartiene e a che cashbook appartiene (anche molteplici).  
Ci sono 10 categorie default, alle quali è possibile aggiungerne manualmente ulteriori:  
Carta, Contanti, Svago, Vacanza, Cena, Investimento, Shopping, Spesa, Stipendio, Abbonamento.

Ogni cashbook ha la possibilità di visualizzare uno storico filtrato secondo le esigenze dell’utente, ad esempio per categoria, importo, data, tipologia.   
L’utente deve poter modificare in qualsiasi momento il filtro e visualizzare in tempo reale lo storico dei movimenti filtrato, inoltre ogni storico ha un sommario per la visualizzazione dell’andamento del CashBook.  
Una volta applicato il filtro, il cashbook mostra il credito residuo dato dai movimenti presenti applicando il filtro.

**VirtualVault**  
Ogni utente potrà creare più VirtualVault che sono dei conti aggiuntivi su cui ogni utente ci può eseguire spostamenti, ogni VirtualVault è caratterizzato da:

* un nome (univoco);  
* un obiettivo (opzionale): in cui è definito l’eventuale importo che si vuole raggiungere definendo quindi o la data in cui lo si vuole raggiungere e un importo ricorrente, specificando ogni quanto depositarlo (un VirtualVault senza obiettivo è sostanzialmente un “conto deposito”).  
  Una volta che l’obiettivo verrà raggiunto il VirtualVault non farà altro e diventerà come un conto deposito senza obiettivi (ci saranno informazioni sull’avanzamento dell'obiettivo, e quanti soldi mancano a raggiungerlo).  
* l’importo depositato.

Una volta creato ogni VirtualVault potrà essere modificato sia nel nome che nell’obiettivo (anche per eventualmente azzerarlo).

Ci sono di 3 tipi principali di spostamenti:

* ricarica manuale: spostamento di una certa somma di denaro da Vault a VirtualVault e viceversa;  
* ricarica automatica: modalità consigliata se si vuole raggiungere un obbiettivo. Si tratta dello spostamento automatico di una determinata quantità di denaro a intervalli di tempo prestabiliti (ogni giorno, ogni settimana, ogni mese, o personalizzata dall’utente ad esempio ogni 16 del mese);  
* arrotondamento delle spese: quando si effettua una spesa questa verrà arrotondata all’intero successivo e la differenza dovuta all’arrotondamento verrà depositata nel VirtualVault;

Nel caso in cui nel Vault non sia presente l’importo da poter caricare in un preciso VirtualVault non verrà effettuato alcun il movimento, e verrà ricalcolato l’ammontare dovuto alla prossima ricarica ricorrente per rispettare l’obiettivo di data o di importo.  
I soldi caricati nei VirtualVault possono provenire solo dal saldo del Vault e non dalle carte/conti collegati ad esso oppure da altri VirtualVault.

**SendMe**  
Ogni utente ha una sezione SendMe per lo scambio di denaro tra utenti, infatti potrà:

* inviare denaro ad altri utenti (amici, link, ...): all’utente che invia denaro verrà sottratto l’importo esclusivamente dal Vault non dalle altre carte/conti collegati (l’operazione va a buon fine solamente se il saldo da inviare non supera il saldo presente nel Vault, altrimenti riceverà un messaggio d’errore);  
* ricevere denaro da altri utenti (amici, link, ...): all’utente che riceve il denaro verrà aggiunto l’importo al Vault e verrà registrata la transazione;  
* richiedere denaro ad un amico: ogni utente potrà richiedere un importo ad un amico con un messaggio e l’amico può decidere se accettare o meno la transazione:  
  * se accetta eseguirà la transazione (se il saldo del Vault è sufficiente);  
  * se rifiuterà verrà avvisato il richiedente e non vi sarà alcuna variazione del saldo;

Ogni utente potrà anche vedere lo storico delle transazioni o richieste con un un altro utente, con la possibilità di cancellare lo storico.

**Account e Amicizie**  
Ogni utente ha una sezione di gestione del proprio account con:

* username(univoco);  
* email;  
* email di recupero(diversa dalla email principale);  
* numero di telefono di recupero;  
* password: con Autenticazione a 2 Fattori per la protezione dell’account;  
* una sezione per la gestione delle proprie amicizie:  
  * accettare o declinare la richiesta di amicizia;  
  * le amicizie attive;  
  * le richieste in sospeso.


Un’amicizia è una relazione che lega due account, si è in stato di amicizia se si accetta un invito da parte di un utente a diventare tale, o viceversa.  
Ogni utente alla creazione dell'account deve immettere una email, un nome utente (username) UNIVOCO e una password.  
L’utente in questa sezione può quindi gestire le proprie amicizie accettando o declinando le nuove richieste, con la possibilità di rimuovere quelle già esistenti.

**LATO GRUPPO**

**Gruppi**  
Ogni utente del gruppo può aggiungere manualmente una spesa, specificando il nome della spesa, una breve descrizione e l'importo che sarà successivamente diviso tra i partecipanti secondo un metodo specificato (divisione in quote uguali oppure importo differenziato per utente). In alternativa, è possibile dividere una spesa già effettuata importandola direttamente dal proprio vault.   
Ad ogni nuova spesa aggiunta, l'applicazione ricalcolerà automaticamente i debiti tra i membri del gruppo, ottimizzando così il saldo finale e riducendo al minimo gli scambi di denaro tra utenti. Per ogni spesa registrata, sarà necessario selezionare gli utenti che vi partecipano.   
La sezione dedicata ai gruppi include anche uno storico delle spese, che fornisce informazioni dettagliate come il nome della spesa, l'importo, il creatore e i partecipanti.  
Inoltre, ogni membro del gruppo può visualizzare il proprio debito temporaneo direttamente in questa sezione.

Tutte le spese registrate all'interno del gruppo rimangono "virtuali", ovvero non vengono immediatamente detratte dal Vault personale, fino a quando l'amministratore del gruppo non procede con la “finalizzazione” della spesa. Questo passaggio comporta l'invio di una richiesta **sendMe** a tutti i partecipanti, che serve per avviare lo scambio di denaro necessario a saldare i debiti. Una volta ricevuta la richiesta **sendMe**, ciascun utente dovrà inviare individualmente l'importo richiesto per completare la transazione e azzerare i debiti.

**Creazione e partecipazione**

* ogni utente potrà creare e partecipare ad un gruppo, l'utente amministratore potrà accettare l'ingresso di nuovi utenti  
* ogni utente ha la possibilità di creare un gruppo con un nome e una breve descrizione  
* l’amministratore del gruppo può inviare un invito ad altri utenti per partecipare al gruppo  
* solo l’utente amministratore ha la possibilità di eliminare il gruppo  
* ogni partecipante può abbandonare il gruppo solo se i debiti sono stati saldati oppure su consenso dell’amministratore 