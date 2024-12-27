**Documento di Specifica dei requisiti Software**  
   
**Indice**

[1\. Introduzione	3](#1.-introduzione)

[1.1 Scopo del documento	3](#1.1-scopo-del-documento)

[1.2 Scopo del Prodotto	3](#1.2-scopo-del-prodotto)

[1.3 Acronimi	4](#1.3-acronimi)

[1.4 Riferimenti	4](#1.4-riferimenti)

[1.5 Descrizione del resto del documento	4](#1.5-descrizione-del-resto-del-documento)

[2\. Descrizione generale	4](#2.-descrizione-generale)

[2.1 Prospettiva del prodotto	4](#2.1-prospettiva-del-prodotto)

[2.2 Funzionalità del prodotto	4](#2.2-funzionalità-del-prodotto)

[2.3 Caratteristiche utente	6](#2.3-caratteristiche-utente)

[2.4 Vincoli generali	6](#2.4-vincoli-generali)

[2.5 Assunzioni e dipendenze	6](#2.5-assunzioni-e-dipendenze)

[3\. Requisiti	6](#3.-requisiti)

[3.1 Requisiti di interfaccia esterna	6](#3.1-requisiti-di-interfaccia-esterna)

[3.1.1 Interfaccia utente	6](#3.1.1-interfaccia-utente)

[3.1.2 Interfaccia  hardware	6](#3.1.2-interfaccia-hardware)

[3.1.3 Interfaccia software	6](#3.1.3-interfaccia-software)

[3.1.4 Interfaccia di comunicazione	6](#3.1.4-interfaccia-di-comunicazione)

[3.2 Requisiti funzionali	7](#3.2-requisiti-funzionali)

[3.2.1 Creazione account	7](#3.2.1-creazione-account)

[3.2.1.1 Introduzione	7](#3.2.1.1-introduzione)

[3.2.1.2 Input	7](#3.2.1.2-input)

[3.2.1.3 Elaborazione	7](#3.2.1.3-elaborazione)

[3.2.1.4 Output	7](#3.2.1.4-output)

[3.2.2 Login	7](#3.2.2-login)

[3.2.1.1 Introduzione	7](#3.2.1.1-introduzione-1)

[3.2.1.2 Input	7](#3.2.1.2-input-1)

[3.2.1.3 Elaborazione	7](#3.2.1.3-elaborazione-1)

[3.2.1.4 Output	7](#3.2.1.4-output-1)

[3.2.3 Aggiunta carta o conto	7](#3.2.3-aggiunta-carta-o-conto)

[3.2.3.1 Introduzione	7](#3.2.3.1-introduzione)

[3.2.3.2 Input	7](#3.2.3.2-input)

[3.2.3.3 Elaborazione	7](#3.2.3.3-elaborazione)

[3.2.3.4 Output	7](#3.2.3.4-output)

[3.2.4 Eliminazione carta o conto	8](#3.2.4-eliminazione-carta-o-conto)

[3.2.4.1 Introduzione	8](#3.2.4.1-introduzione)

[3.2.4.2 Input	8](#3.2.4.2-input)

[3.2.4.3 Elaborazione	8](#3.2.4.3-elaborazione)

[3.2.4.4 Output	8](#3.2.4.4-output)

[3.2.5 Deposito di denaro da Carte o Conti salvate	8](#3.2.5-deposito-di-denaro-da-carte-o-conti-salvate)

[3.2.5.1 Introduzione	8](#3.2.5.1-introduzione)

[3.2.5.2 Input	8](#3.2.5.2-input)

[3.2.5.3 Elaborazione	8](#3.2.5.3-elaborazione)

[3.2.5.4 Output	8](#3.2.5.4-output)

[3.2.6 Deposito di denaro one time	8](#3.2.6-deposito-di-denaro-one-time)

[3.2.6.1 Introduzione	8](#3.2.6.1-introduzione)

[3.2.6.2 Input	8](#3.2.6.2-input)

[3.2.6.3 elaborazione	8](#3.2.6.3-elaborazione)

[3.2.5.4 Output	8](#3.2.5.4-output-1)

[3.2.7 Prelievo importo dal vault	8](#3.2.7-prelievo-importo-dal-vault)

[3.2.7.1 Introduzione	8](#3.2.7.1-introduzione)

[3.2.7.2 Input	8](#3.2.7.2-input)

[3.2.7.3 Elaborazione	8](#3.2.7.3-elaborazione)

[3.2.7.4 Output	9](#3.2.7.4-output)

[3.2.8 Esecuzione pagamento	9](#3.2.8-esecuzione-pagamento)

[3.2.8.2 Input	9](#3.2.8.2-input)

[3.2.8.4 Output	9](#3.2.8.4-output)

[3.2.9 Accettare richieste di amicizia	9](#3.2.9-accettare-richieste-di-amicizia)

[3.2.9.1 Introduzione	9](#3.2.9.1-introduzione)

[3.2.9.2 Input	9](#3.2.9.2-input)

[3.2.9.3 Elaborazione	9](#3.2.9.3-elaborazione)

[3.2.9.4 Output	9](#3.2.9.4-output)

[3.2.10 Rimuovere amicizie	9](#3.2.10-rimuovere-amicizie)

[3.2.10.1 Introduzione	9](#3.2.10.1-introduzione)

[3.2.10.2 Input	9](#3.2.10.2-input)

[3.2.10.3 Elaborazione	9](#3.2.10.3-elaborazione)

[3.2.10.4 Output	9](#3.2.10.4-output)

[3.2.11 Ricerca utenti	9](#3.2.11-ricerca-utenti)

[3.2.11.1 Introduzione	9](#3.2.11.1-introduzione)

[3.2.11.2 Input	10](#3.2.11.2-input)

[3.2.11.3 Elaborazione	10](#3.2.11.3-elaborazione)

[3.2.11.4 Output	10](#3.2.11.4-output)

[3.2.12 Creazione di un VirtualVault	10](#3.2.12-creazione-di-un-virtualvault)

[3.2.12.1 Introduzione	10](#3.2.12.1-introduzione)

[3.2.12.2 Input	10](#3.2.12.2-input)

[3.2.12.3 Elaborazione	10](#3.2.12.3-elaborazione)

[3.2.12.4 Output	10](#3.2.12.4-output)

[3.2.13 Cancellazione di un VirtualVault	10](#3.2.13-cancellazione-di-un-virtualvault)

[3.2.13.1 Introduzione	10](#3.2.13.1-introduzione)

[3.2.13.2 Input	10](#3.2.13.2-input)

[3.2.13.3 Elaborazione	10](#3.2.13.3-elaborazione)

[3.2.13.4 Output	10](#3.2.13.4-output)

[3.2.14 Spostamento saldo verso VirtualVault	10](#3.2.14-spostamento-saldo-verso-virtualvault)

[3.2.14.1 Introduzione	10](#3.2.14.1-introduzione)

[3.2.14.2 Input	10](#3.2.14.2-input)

[3.2.14.3 Elaborazione	10](#3.2.14.3-elaborazione)

[3.2.14.4 Output	10](#3.2.14.4-output)

[3.2.15 Creazione CashBook	10](#3.2.16-creazione-cashbook)

[3.2.15.1  Introduzione	10](#3.2.16.1-introduzione)

[3.2.15.2 Input	10](#3.2.16.2-input)

[3.2.15.3 Elaborazione	11](#si-verifica-che-non-esista-un-altro-cashbook-con-nome-uguale-a-quello-da-creare,-altrimenti-l’operazione-non-è-effettuabile)

[3.2.15.4 Output	11](#3.2.16.4-output)

[3.2.16 Eliminazione  CashBook	11](#3.2.17-eliminazione-cashbook)

[3.2.15.1  Introduzione	11](#3.2.17.1-introduzione)

[3.2.15.2 Input	11](#3.2.17.2-input)

[3.2.15.3 Elaborazione	11](#3.2.17.3-elaborazione)

[3.2.15.4 Output	11](#3.2.17.4-output)

[3.2.17 Aggiunta Watcher CCorrente/Carta a un CashBook	11](#3.2.18-aggiunta-watcher-ccorrente/carta-a-un-cashbook)

[3.2.15.1  Introduzione	11](#3.2.18.1-introduzione)

[3.2.15.2 Input	11](#3.2.18.2-input)

[3.2.15.3 Elaborazione	11](#3.2.18.3-elaborazione)

[3.2.15.4 Output	11](#3.2.18.4-output)

[3.2.18 Rimozione Watcher CCorrente/Carta da un CashBook	11](#3.2.19-rimozione-watcher-ccorrente/carta-da-un-cashbook)

[3.2.15.1  Introduzione	11](#3.2.19.1-introduzione)

[3.2.15.2 Input	11](#3.2.19.2-input)

[3.2.15.3 Elaborazione	11](#3.2.19.3-elaborazione)

[3.2.15.4 Output	11](#3.2.19.4-output)

[3.2.19 Aggiunta Movimento in CashBook	11](#3.2.20-aggiunta-movimento-in-cashbook)

[3.2.15.1  Introduzione	11](#3.2.20.1-introduzione)

[3.2.15.2 Input	11](#3.2.20.2-input)

[3.2.15.3 Elaborazione	11](#3.2.20.3-elaborazione)

[3.2.15.4 Output	11](#3.2.1209.4-output)

[3.2.20 Rimozione Movimento in CashBook	11](#3.2.21-rimozione-movimento-in-cashbook)

[3.2.15.1  Introduzione	11](#3.2.21.1-introduzione)

[3.2.15.2 Input	12](#3.2.21.2-input)

[3.2.15.3 Elaborazione	12](#3.2.21.3-elaborazione)

[3.2.15.4 Output	12](#3.2.21.4-output)

# 

# 

# 

# **1\. Introduzione** {#1.-introduzione}

## **1.1 Scopo del documento** {#1.1-scopo-del-documento}

Lo scopo del documento è quello di specificare i requisiti software del “LaVault” per facilitare la realizzazione e la validazione. 

## **1.2 Scopo del Prodotto**  {#1.2-scopo-del-prodotto}

Questo progetto punta a creare un software per la gestione o il risparmio delle finanze personali o di gruppo tutto in un'unica formula, rendendo il più semplice possibile la visione e il controllo delle spese personali o collettive, online e non, tramite l’utilizzo di **gruppi**, **VirtualVault** e **Vault**. Con la possibilità di fare un upgrade premium che espanda le funzionalità e che introduca un sistema di punti e referral.

## **1.3 Acronimi** {#1.3-acronimi}

* **Vault :** conto principale del singolo utente. Luogo da dove avverranno le transazioni con l’esterno del software  
* **VirtualVault** **:** conto secondario creato dall'utente dove non possono avvenire transazioni ma solo movimenti dal/verso il vault   
* **CashBook** **:** funzionalità del software per gestione budget e finanze dove l'utente vedrà gli storici delle transazioni fatte dall’app o aggiungerà le transazioni di terze parti (fatte a mano e non)   
* **sendMe:** funzionalità del software per lo scambio di denaro tra utenti  
* **Gruppo :** Insieme di utenti che vogliono gestire in maniera comune delle spese (debiti vacanze) o un importo comune (conto di gruppo)  
* **Conto Di Gruppo** **:** funzionalità di tutti i gruppi che rappresenta un **VirtualVault** del gruppo   
* **Watcher :** 

## **1.4 Riferimenti** {#1.4-riferimenti}

Nessuno

## **1.5 Descrizione del resto del documento**  {#1.5-descrizione-del-resto-del-documento}

La restante parte di questo documento contiene una descrizione dettagliata e approfondita delle funzionalità richieste al sistema software secondo gli obiettivi espressi al punto 1.2. e secondo la visione di progetto descritta dal “documento di visione”

# **2\. Descrizione generale**  {#2.-descrizione-generale}

## **2.1 Prospettiva del prodotto** {#2.1-prospettiva-del-prodotto}

Il software “LaVault” è un sistema autonomo che ha anche la possibilità di interagire con sistemi finanziari esterni per la gestione del saldo sull’applicazione. Le varie operazioni saranno interne e di conseguenza non ci saranno interazioni con ulteriori software.

## **2.2 Funzionalità del prodotto** {#2.2-funzionalità-del-prodotto}

- Vault   
* Deposito di denaro (ricarica, accredito, aggiunta di più carte);  
* Monitorare/Gestire le spese con personalizzazione delle categorie;  
* Storico dei pagamenti e del denaro ricevuto e/o depositato;  
* Ricarica **Vault** tramite carte e conti  
* Ricarica OneTime  
* Prelievo importo dal **Vault** verso altre carte o conti   
* Collegamento carte e conti all'account   
* Cancellazione conto collegato  
* Esecuzione di pagamenti;  
- VirtualVault  
* Creazione di più **VirtualVault**  
* Gestione **VirtualVault** come salvadanaio o conto risparmio o conto secondario;  
* Cancellazione **VirtualVault**   
* Creazione di obiettivi per il **VirtualVault**  
* Esecuzione movimenti dal **Vault** ai **VirtualVault**  
* Movimenti automatici per conseguire gli obiettivi   
* Modifiche al **VirtualVault**  
* Visualizzazione informazioni obiettivi e saldo   
- CashBook  
* Creazione di più **CashBook** oltre al principale   
* Aggiunta di transizioni ad un **CashBook**  
* Aggiunta di una transazione eseguita dal **Vault** ad un **CashBook**  
* Modifica transazioni   
* Storico di ogni singolo **CashBook**  
* Sommario con un riassunto di tutte entrate e uscite per ogni **CashBook** o per lasso di tempo (settimana, giorno, mese, anno, tot)  
* Grafici per la versione premium


- SendMe  
* Invia importo dal **Vault** ad amici o utenti  
* Ricezione importo sul **Vault** ad amici o utenti  
* Richiesta importo ad amici o utenti  
* Storico transazioni con un utente  
* Notificazione ricezione o richiesta  
- Gruppi  
* Scambio di denaro tra utenti;  
* Divisione delle spese;  
* Conto di gruppo;  
* Grafici di gruppo sugli andamenti;  
* Riduzione delle transazioni per il passaggio di denaro;  
- Utenza  
* Referral link per invitare amici  
* Gestione dell’account (login, creazione account, elimina account, cambia password)  
* Lista amici  
* Gestione delle amicizie (accettare richieste amicizia, rimuovere amicizie, lista amici)  
* Ricerca altri utenti tramite username  
- Premium  
* Iscrizione ad un account Premium per maggiori funzionalità;  
* Disdire l’iscrizione;  
* Grafici personali;  
    
  Le varie funzionalità dell’applicazione saranno gestite da un team di admin che disporrà delle conoscenze necessarie per garantire un corretto funzionamento dell’applicazione.

## **2.3 Caratteristiche utente** {#2.3-caratteristiche-utente}

Il sistema software “LaVault” è rivolto ad un’utenza con discreta conoscenza del dominio applicativo, ossia sulle attività che riguardano la gestione delle finanze, ma senza particolari conoscenze informatiche.

## **2.4 Vincoli generali** {#2.4-vincoli-generali}

Per quanto riguarda questo tipo di software non ci sono particolari tipi di vincoli, in quanto l’applicazione può essere utilizzata in qualsiasi momento e per qualsiasi esigenza. Le uniche accortezze riguardano il rispetto della privacy dell’utente, ossia che le informazioni personali devono essere consultabili dall’utente proprietario dell’account e non da esterni. Di conseguenza un singolo utente avrà accesso solo ed esclusivamente alle proprie informazioni finanziarie. Nel caso dei gruppi, i permessi dell’utente sono limitati alla visione degli obiettivi dei gruppi a cui appartiene, nel contesto di gruppo ci sarà un amministratore che può gestire il gruppo stesso. Gli utenti facenti parte di un gruppo possono vedere esclusivamente il saldo del **Conto Di Gruppo** e i soldi che deve o gli devono.

## **2.5 Assunzioni e dipendenze** {#2.5-assunzioni-e-dipendenze}

Il sistema software “LaVault” potrà essere utilizzato su macchine dotate di sistema operativo Microsoft Windows, Unix, GNU/Linux, Ubuntu e Mac OS, nello specifico l’unica  dipendenza è avere una JVM.

# **3\. Requisiti** {#3.-requisiti}

## **3.1 Requisiti di interfaccia esterna** {#3.1-requisiti-di-interfaccia-esterna}

### **3.1.1 Interfaccia utente** {#3.1.1-interfaccia-utente}

Il sistema software “LaVault” deve essere dotato di un’interfaccia intuitiva con le informazioni necessarie riguardanti il conto generale, una barra sottostante per le varie funzionalità, finestre e pulsanti.

### **3.1.2 Interfaccia  hardware** {#3.1.2-interfaccia-hardware}

Il software “LaVault” dovrà interfacciarsi con le periferiche hardware facenti parte di un calcolatore;

### **3.1.3 Interfaccia software** {#3.1.3-interfaccia-software}

		Il software “LaVault” dovrà interfacciarsi con i software dei sistemi finanziari 		esterni collegati al Vault. 

### **3.1.4 Interfaccia di comunicazione** {#3.1.4-interfaccia-di-comunicazione}

Il software “LaVault” non possiede un’interfaccia di comunicazione.

##  **3.2 Requisiti funzionali** {#3.2-requisiti-funzionali}

### **3.2.1 Creazione account** {#3.2.1-creazione-account}

#### **3.2.1.1 Introduzione** {#3.2.1.1-introduzione}

Consente la creazione di un nuovo Utente sulla piattaforma

#### **3.2.1.2 Input** {#3.2.1.2-input}

Data corrente al momento della creazione, nome utente, email e password

#### **3.2.1.3 Elaborazione** {#3.2.1.3-elaborazione}

Per prima cosa si verifica che le informazioni email e/o nome utente non siano già assegnati ad altri Utenti già registrati.Se questo è verificato positivamente allora si potrà creare il nuovo Utente assegnandogli un codice univoco. Al nuovo account si applicano le regole definite al punto **2.2.UTENZA** in modo da inizializzare le funzionalità basi e renderlo pronto all’uso

#### **3.2.1.4 Output** {#3.2.1.4-output}

Nuovo Account registrato con i parametri definiti e convalidati

### **3.2.2 Login** {#3.2.2-login}

#### **3.2.1.1 Introduzione** {#3.2.1.1-introduzione-1}

Consente all’utente di accedere al proprio account.

#### **3.2.1.2 Input** {#3.2.1.2-input-1}

Nome utente/email e password.

#### **3.2.1.3 Elaborazione** {#3.2.1.3-elaborazione-1}

Una volta inserite il nome utente/email e la password, verrà controllato all’interno di un database se i dati inseriti sono associati a un accountpresenti o meno. Nel caso in cui siano presenti l’utente potrà accedere al proprio account, nel caso invece in cui i dati non corrispondano, ci sarà la possibilità per l’utente di cambiare la password inserendo una email di recupero, oppure la possibilità di creare l’account nel caso in cui non sia stato ancora creato.

#### **3.2.1.4 Output** {#3.2.1.4-output-1}

Login effettuato e sessione associata 

### 

### **3.2.3 Aggiunta carta o conto**  {#3.2.3-aggiunta-carta-o-conto}

#### **3.2.3.1 Introduzione** {#3.2.3.1-introduzione}

		Consente all'utente di collegare una carta o conto al proprio account. 

#### **3.2.3.2 Input** {#3.2.3.2-input}

		dati della carta o del conto da collegare.

#### **3.2.3.3 Elaborazione** {#3.2.3.3-elaborazione}

		   
viene inviata la richiesta alla banca di tale conto o carta aspettando un   
risconto di successo, in caso venga confermata la richiesta i dati   
della carta o conto vengono memorizzati nell'account dell’utente, in caso contrario non si fa nulla.

#### **3.2.3.4 Output** {#3.2.3.4-output}

		Registrazione conto / carta sull’account dell’utente 

### **3.2.4 Eliminazione carta o conto**  {#3.2.4-eliminazione-carta-o-conto}

#### **3.2.4.1 Introduzione** {#3.2.4.1-introduzione}

		Consente all'utente di eliminare una carta o conto al proprio account.

#### **3.2.4.2 Input** {#3.2.4.2-input}

		Carta o conto da eliminare

#### **3.2.4.3 Elaborazione** {#3.2.4.3-elaborazione}

		Nulla da elaborare

#### **3.2.4.4 Output** {#3.2.4.4-output}

		La carta o il conto viene eliminato dall’account dell’utente  
 

### **3.2.5 Deposito di denaro da Carte o Conti salvate** {#3.2.5-deposito-di-denaro-da-carte-o-conti-salvate}

#### **3.2.5.1 Introduzione** {#3.2.5.1-introduzione}

Consente all'utente di depositare denaro nel vault tramite le carte o conti 	memorizzate (precedentemente inserite).

#### **3.2.5.2 Input** {#3.2.5.2-input}

		Valore da depositare e carta o conto da cui prelevare

#### **3.2.5.3 Elaborazione** {#3.2.5.3-elaborazione}

Viene richiesta una transazione alla banca relativa al conto o carta di importo pari a quello scelto dall’utente, se la banca accetta la transazione il valore viene caricato nel vault, altrimenti operazione non effettuabile.

#### **3.2.5.4 Output**  {#3.2.5.4-output}

Caricamento del valore nel vault se operazione confermata

### **3.2.6 Deposito di denaro one time**  {#3.2.6-deposito-di-denaro-one-time}

#### **3.2.6.1 Introduzione** {#3.2.6.1-introduzione}

		Consente il deposito nel vault tramite una carta o conto inseriti al momento   
della ricarica. La carta/conto non è salvato nell'account

#### **3.2.6.2 Input** {#3.2.6.2-input}

		Valore da depositare e dati carta o conto con cui farlo.

#### **3.2.6.3 Elaborazione** {#3.2.6.3-elaborazione}

		Viene richiesta la transazione alla   
banca relativa al conto o carta e se la banca accetta la transazione il valore viene caricato nel vault, altrimenti operazione non effettuabile

#### **3.2.5.4 Output**  {#3.2.5.4-output-1}

		Caricamento del valore nel  vault

### **3.2.7 Prelievo importo dal vault** 	 {#3.2.7-prelievo-importo-dal-vault}

#### **3.2.7.1 Introduzione** {#3.2.7.1-introduzione}

	Consente all'utente di prelevare del denaro presente nel vault e inviarlo a una   
carta o conto tra quelle memorizzate.

#### **3.2.7.2 Input** {#3.2.7.2-input}

		Valore del denaro da prelevare e carta o conto dove inviarlo.

#### **3.2.7.3 Elaborazione** {#3.2.7.3-elaborazione}

		Viene controllato se e presente tale   
importo nel vault (altrimenti operazione non effettuabile) viene inviata una richiesta alla banca della carta o conto e in caso di conferma viene tolto il valore dal vault (altrimenti operazione non effettuabile).

#### **3.2.7.4 Output**  {#3.2.7.4-output}

Sottrazione del valore dal vault.

### 	**3.2.8 Esecuzione pagamento**  {#3.2.8-esecuzione-pagamento}

		**3.2.8.1 Introduzione**  
		Consente all'utente di eseguire pagamenti 

#### **3.2.8.2 Input** {#3.2.8.2-input}

		Dati operazione pagamento(importo, commerciante etc..)  
**3.2.8.3 Elaborazione**  
		Viene controllato che il valore richiesto dal  
pagamento sia disponibile nel vault(in caso contrario operazione non effettuabile), viene sottratto dal vault l’importo definito nel pagamento, 

#### **3.2.8.4 Output**  {#3.2.8.4-output}

	Sottrazione dell’importo dal vault e conferma del pagamento, registrazione transazione all’interno dello  storico del Vault e/o CashBook  associati

### **3.2.9 Accettare richieste di amicizia** {#3.2.9-accettare-richieste-di-amicizia}

#### **3.2.9.1 Introduzione** {#3.2.9.1-introduzione}

Consente all'utente di accettare/ rifiutare una richiesta di amicizia

#### **3.2.9.2 Input** {#3.2.9.2-input}

Richiesta di amicizia

#### **3.2.9.3 Elaborazione** {#3.2.9.3-elaborazione}

#### **3.2.9.4 Output** {#3.2.9.4-output}

Aggiunta dell’utente (chi ha fatto la richiesta) nella lista amici dell’utente che ha accettato la  richiesta, e viceversa.

### **3.2.10 Rimuovere amicizie** {#3.2.10-rimuovere-amicizie}

#### **3.2.10.1 Introduzione** {#3.2.10.1-introduzione}

Consente di rimuovere un utente dalla lista amici.

#### **3.2.10.2 Input** {#3.2.10.2-input}

Utente da rimuovere

#### **3.2.10.3 Elaborazione** {#3.2.10.3-elaborazione}

Si verifica che l’amico da rimuovere sia un’amico dell’utente che sta rimuovendo, altrimenti operazione non effettuabile

#### **3.2.10.4 Output** {#3.2.10.4-output}

Rimozione amicizia da entrambi gli utenti

### **3.2.11 Ricerca utenti** {#3.2.11-ricerca-utenti}

#### **3.2.11.1 Introduzione** {#3.2.11.1-introduzione}

Requisito che soddisfa la necessità dell’utente di ricercare un altro utente registrato all’applicazione.

#### **3.2.11.2 Input** {#3.2.11.2-input}

Username di un utente.

#### **3.2.11.3 Elaborazione** {#3.2.11.3-elaborazione}

Si ricerca  l’utente nella lista di amici dell’utente che sta cercando,  se non  si trova allora l’operazione non  è effettuabile 

#### **3.2.11.4 Output** {#3.2.11.4-output}

Utente ricercato

### **3.2.12 Creazione di un VirtualVault** {#3.2.12-creazione-di-un-virtualvault}

#### **3.2.12.1 Introduzione**  {#3.2.12.1-introduzione}

		Permette di creare un nuovo VirtualVault

#### **3.2.12.2 Input** {#3.2.12.2-input}

		Nome VirtualVault

#### **3.2.12.3 Elaborazione** {#3.2.12.3-elaborazione}

Viene controllato se non esiste già un VirtualVault con lo stesso nome dell'account dell’utente, altrimenti l’operazione non  è eseguibile 

#### **3.2.12.4 Output** {#3.2.12.4-output}

		Registrazione VirtualVault nell’account dell’utente  
		

### **3.2.13 Cancellazione di un VirtualVault** {#3.2.13-cancellazione-di-un-virtualvault}

#### **3.2.13.1 Introduzione** {#3.2.13.1-introduzione}

		Permette di cancellare un VirtualVault

#### **3.2.13.2 Input** {#3.2.13.2-input}

		VirtualVault

#### **3.2.13.3 Elaborazione** {#3.2.13.3-elaborazione}

		Verifica che il VirtualVault non sia quello principale (altrimenti l’operazione non   
è effettuabile), allora il saldo presente sul VirtualVault selezionato per la   
cancellazione vengono spostati in automatico nel Vault

#### **3.2.13.4 Output** {#3.2.13.4-output}

		Spostamento soldi dal VirtualVault a quello principale, VirtualVault rimosso

### **3.2.14 Spostamento saldo verso VirtualVault** {#3.2.14-spostamento-saldo-verso-virtualvault}

#### **3.2.14.1 Introduzione** {#3.2.14.1-introduzione}

		Permette di eseguire spostamenti di saldo tra il Vault e un VirtualVault

#### **3.2.14.2 Input** {#3.2.14.2-input}

		Importo da spostare, VirtualVault su cui farlo 

#### **3.2.14.3 Elaborazione** {#3.2.14.3-elaborazione}

		Viene controllato se il saldo inserito è disponibile nel Vault 

#### **3.2.14.4 Output** {#3.2.14.4-output}

L’importo viene spostato dal Vault al VirtualVault

### **3.2.15 Creazione di un obiettivo (data)**

#### **3.2.15.1  Introduzione**

		Permette di creare un nuovo obiettivo di un VirtualVault

#### **3.2.15.2 Input**

		Valore che si vuol raggiungere, data in cui farlo (o in quanti giorni 

#### **3.2.15.3 Elaborazione**

		Calcolo dell’obiettivo 

#### **3.2.15.4 Output**

		Eliminazione del CashBook dall’account utente

### **3.2.16 Creazione CashBook** {#3.2.16-creazione-cashbook}

#### **3.2.16.1  Introduzione** {#3.2.16.1-introduzione}

		Permette la creazione di un nuovo CashBook personale

#### **3.2.16.2 Input** {#3.2.16.2-input}

		Nome  CashBook

#### **3.2.16.3 Elaborazione** {#si-verifica-che-non-esista-un-altro-cashbook-con-nome-uguale-a-quello-da-creare,-altrimenti-l’operazione-non-è-effettuabile}

#### Si verifica che non esista un altro CashBook con nome uguale a quello da creare, altrimenti l’operazione non è effettuabile {#si-verifica-che-non-esista-un-altro-cashbook-con-nome-uguale-a-quello-da-creare,-altrimenti-l’operazione-non-è-effettuabile}

#### **3.2.16.4 Output** {#3.2.16.4-output}

		Registrazione del nuovo CashBook nell’account dell’utente

### **3.2.17 Eliminazione  CashBook** {#3.2.17-eliminazione-cashbook}

#### **3.2.17.1  Introduzione** {#3.2.17.1-introduzione}

		Permette l’eliminazione di un cashbook personale

#### **3.2.17.2 Input** {#3.2.17.2-input}

		CashBook (da eliminare)

#### **3.2.17.3 Elaborazione** {#3.2.17.3-elaborazione}

		Nessuna elaborazione

#### **3.2.17.4 Output** {#3.2.17.4-output}

		Eliminazione del CashBook dall’account utente

### **3.2.18 Aggiunta Watcher CCorrente/Carta a un CashBook** {#3.2.18-aggiunta-watcher-ccorrente/carta-a-un-cashbook}

#### **3.2.18.1  Introduzione** {#3.2.18.1-introduzione}

Permette a un cashbook di tenere conto delle spese fatte da un determinato ccorrente o carta dell’account utente

#### **3.2.18.2 Input** {#3.2.18.2-input}

		CashBook (su cui aggiungere il Watcher), CCorrente / Conto

#### **3.2.18.3 Elaborazione** {#3.2.18.3-elaborazione}

Si verifica che non esista un watcher già associato tra il ccorrente e conto allo stesso cashbook, altrimenti operazione non effettuabile

#### **3.2.18.4 Output** {#3.2.18.4-output}

		Registrazione watcher tra ccorrente e cashbook

### **3.2.19 Rimozione Watcher CCorrente/Carta da un CashBook** {#3.2.19-rimozione-watcher-ccorrente/carta-da-un-cashbook}

#### **3.2.19.1  Introduzione** {#3.2.19.1-introduzione}

		Permette la rimozione di un watcher da un cashbook

#### **3.2.19.2 Input** {#3.2.19.2-input}

		Watcher(da rimuovere)

#### **3.2.19.3 Elaborazione** {#3.2.19.3-elaborazione}

		Nessuna elaborazione

#### **3.2.19.4 Output** {#3.2.19.4-output}

		Rimozione del watcher dall’account dell’utente

### **3.2.20 Aggiunta Movimento in CashBook** {#3.2.20-aggiunta-movimento-in-cashbook}

#### **3.2.20.1  Introduzione** {#3.2.20.1-introduzione}

		Permette la registrazione di un movimento su un determinato cashbook

#### **3.2.20.2 Input** {#3.2.20.2-input}

		Data creazione, e altri dati relativi al documento di Visione.

#### **3.2.20.3 Elaborazione** {#3.2.20.3-elaborazione}

Si verifica che sia rispettato il formato e le regole date dal documento di Visione, altrimenti operazione non è effettuabile

#### **3.2.1209.4 Output** {#3.2.1209.4-output}

		Registrazione movimento  all’interno del cashbook

### **3.2.21 Rimozione Movimento in CashBook** {#3.2.21-rimozione-movimento-in-cashbook}

#### **3.2.21.1  Introduzione** {#3.2.21.1-introduzione}

		Permette la rimozione di un movimento da un determinato cashbook

#### **3.2.21.2 Input** {#3.2.21.2-input}

		Movimento

#### **3.2.21.3 Elaborazione** {#3.2.21.3-elaborazione}

		Nessuna elaborazione

#### **3.2.21.4 Output** {#3.2.21.4-output}

		Rimozione movimento dal cashbook

### **3.2.22 Modifica Movimento di un CashBook**

#### **3.2.22.1  Introduzione**

		Permette la modifica di un movimento di un determinato cashbook

#### **3.2.22.2 Input**

		Movimento (da modificare),  nuovi dati

#### **3.2.22.3 Elaborazione**

Si verifica che i nuovi dati rispettino il formato e le regole date dal documento di Visione, altrimenti operazione non è effettuabile

#### **3.2.22.4 Output**

		Modifica dei dati del movimento  
	**3.2.23 Iscrizione a premium**

#### **3.2.23.1  Introduzione**

Permette all’utente di passare dalla versione base dell’applicazione ad una versione avanzata

#### **3.2.23.2 Input**

		Decisione dell’utente

#### **3.2.23.3 Elaborazione**

Prelievo del denaro da un conto per pagare l’abbonamento

#### **3.2.23.4 Output**

		Funzionalità aggiuntive per l’utente  
**3.2.24 Disdire l’abbonamento**

#### **3.2.24.1  Introduzione**

Permette all’utente di disdire l’abbonamento alla versione premium

#### **3.2.24.2 Input**

		Decisione dell’utente di non proseguire con il piano avanzato

#### **3.2.24.3 Elaborazione**

Annullamento dell’iscrizione da parte dell’utente, con relativa motivazione facoltativa

#### **3.2.24.4 Output**

		Account base  
	**3.2.25 Rinnovo della versione premium**

#### **3.2.25.1  Introduzione**

Con cadenza annuale/mensile, verrà rinnovato l’abbonamento, a meno che l’utente non lo abbia disdetto prima del rinnovo

#### **3.2.25.2 Input**

		Scadenza dell’abbonamento

#### **3.2.25.3 Elaborazione**

Prelievo del denaro da un conto per rinnovare l’abbonamento. Nel caso in cui però l’utente abbia disdetto l’abbonamento, non verrà accreditata alcuna cifra

#### **3.2.25.4 Output**

		Rinnovo dell’abbonamento

	

#### 		

	

## 	

