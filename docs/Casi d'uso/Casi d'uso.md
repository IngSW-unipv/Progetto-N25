# Casi d’uso

| Caso d’uso 1: Gestione dell’utente |
| :---- |
| **Caso d’uso 1.1: Creazione account** |
| Breve descrizione: l’utente vuole creare un account |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: nessuna |
| Sequenza degli eventi principali: L’utente apre l’applicazione Cliccando l’apposito pulsante, l’utente inserisce username, email, password Il sistema verifica che le informazioni siano valide Le informazioni sono state approvate dal server L’account viene registrato nel sistema |
| Postcondizioni: l’utente si è registrato e ha creato il suo account |
| Sequenza di eventi alternativi: Il server rileva che c’è già un account con quelle credenziali L’account non viene registrato |
| **Caso d’uso 1.2: Eliminazione account** |
| Breve descrizione: l’utente vuole eliminare il proprio account |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato all’applicazione e aver effettuato il login |
| Sequenza degli eventi principali: L’utente entra nell’apposita sezione per poter eliminare il suo account Il sistema chiede all’utente su quale conto\\carta inviare tutto il denaro del suo Vault (non implementata) Il sistema chiede conferma all’utente dicendogli di scrivere una frase Il sistema verifica che le informazioni siano valide Le informazioni vengono approvate L’account viene eliminato dal sistema |
| Postcondizioni: l’account dell’utente viene eliminato |
| Sequenza di eventi alternativi:  Le informazioni non vengono approvate dal sistema l’account non viene liminato |
| **Caso d’uso 1.3: Cambio password** |
| Breve descrizione: l’utente vuole cambiare la password al proprio account |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato e aver effettuato il login |
| Sequenza degli eventi principali: L'utente entra nell’apposita sezione per poter modificare la sua password Il sistema chiede all’utente di inserire le credenziali dell’account in questione Il sistema chiede all’utente di inserire la nuova password che andrà a modificare quella vecchia Il sistema verifica che le informazioni siano valide Le informazioni vengono approvate |
| Postcondizioni: la password dell’account dell’utente è stata reimpostata |
| Sequenza di eventi alternativi: l’utente inserisce delle credenziali errate la sua password non viene modificata |
| **Caso d’uso 1.4: Login** |
| Breve descrizione: L’utente si collega all’applicazione col proprio account |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve aver creato l'account (deve aver effettuato la registrazione) |
| Sequenza degli eventi principali: L’utente apre l’applicazione L’utente cliccando il tasto “login” inserisce username-password o email-password Il sistema verifica che le informazioni siano valide e che siano presenti nel server L’utente viene fatto accedere all’applicazione |
| Postcondizioni: l’utente si trova nella schermata principale dell’applicazione |
| Sequenza di eventi alternativi: L’utente cerca di effettuare il login senza essersi prima registrato Il server manda un messaggio d’errore dicendo che lo username o email (associato alla password) inserito non è presente all’interno del sistema La richiesta di login da parte dell’utente viene respinta e viene rimandato alla pagina del login L’utente cerca di effettuare il login con password errata, anche in questo caso verrà rimandato alla pagina di login |

| Caso d’uso 2: Gestione Vault |
| :---- |
| **Caso d’uso 2.1: Aggiunta metodo di pagamento** |
| Breve descrizione: l’utente aggiunge un metodo di pagamento al suo Vault |
| Attore primario: utente |
| Attore secondario: banca |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e deve disporre delle informazioni necessarie ad aggiungere il metodo di pagamento |
| Sequenza degli eventi principali: L’utente si reca nella sezione apposita dell’applicazione Seleziona il metodo di pagamento Inserisce le informazioni del metodo di pagamento L’istituto finanziario a cui appartengono il conto o la carta dell’utente approvano il collegamento Aggiunta del metodo di pagamento |
| Postcondizioni: il metodo di pagamento scelto dall’utente è registrato correttamente nel sistema |
| Sequenza di eventi alternativi: L’istituto finanziario a cui appartengono il conto o la carta dell’utente rifiuta il collegamento o il loro sistema informatico non risponde Fallisce l’aggiunta del metodo di pagamento |
| **Caso d’uso 2.2: Rimozione metodo di pagamento** |
| Breve descrizione: l’utente elimina un metodo di pagamento al suo Vault |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e aggiunto con successo un metodo di pagamento all’account |
| Sequenza degli eventi principali: L’utente si reca nella sezione apposita dell’applicazione Seleziona il metodo di pagamento da rimuovere  Conferma l’eliminazione tramite l’apposito pulsante |
| Postcondizioni: il metodo di pagamento è stato rimosso e non può più essere visualizzato dall’utente  |
| Sequenza di eventi alternativi: nessuna |
| **Caso d’uso 2.3: Deposito di denaro sul Vault** |
| Breve descrizione: L’utente deposita denaro nel Vault tramite carte e conti memorizzati |
| Attore primario: utente |
| Attore secondario: banca |
| Precondizioni: l’utente deve aver aggiunto con successo almeno un metodo di pagamento dal quale effettuare il deposito |
| Sequenza degli eventi principali: L’utente si reca nella sezione: ”Vault” L’utente seleziona l’opzione per depositare denaro sul Vault Seleziona il metodo di pagamento tra quelli registrati L’utente specifica l’importo  L’utente conferma l’operazione Viene inviata alla banca una richiesta di pagamento al metodo selezionato La banca approva la transazione Il saldo del Vault verrà ricaricato |
| Postcondizioni: Il Vault verrà ricaricato con l’importo selezionato |
| Sequenza di eventi alternativi:  La banca rifiuta la transazione Il saldo del Vault rimarrà invariato il saldo della carta è insufficiente Il saldo del Vault rimarrà invariato |
| **Caso d’uso 2.4: Prelievo dal Vault** |
| Breve descrizione: l’utente preleva denaro dal Vault per inviarlo ad una carta o un conto tra quelli memorizzati (registrati) |
| Attore primario: utente |
| Attore secondario: banca |
| Precondizioni: l’utente dispone di un saldo sul Vault e deve aver registrato al proprio account almeno una carta o conto (un metodo di pagamento) |
| Sequenza degli eventi principali: L’utente richiede il prelievo di un ammontare di denaro presente nel Vault Specifica la carta o conto di destinazione (precedentemente registrati) Specifica l’importo da prelevare  Viene contattato l’istituto finanziario a cui appartiene la carta o conto selezionato Viene inviato il denaro |
| Postcondizioni: il saldo nel Vault diminuisce e il saldo del metodo di pagamento selezionato viene incrementato |
| Sequenza di eventi alternativi: l’utente richiede di prelevare di più di quanto ci sia effettivamente sul Vault l’operazione gli verrà negata dal sistema i saldi (Vault e della carta\\conto) rimarranno invariati |
| **Caso d’uso 2.5: Esecuzione pagamento fisico/online** |
| Breve descrizione: l’utente vuole eseguire un pagamento fisico o online utilizzando l’app  |
| Attore primario: utente |
| Attore secondario: banca |
| Precondizioni: l’utente dispone di un conto o una carta con sopra un saldo sufficiente al pagamento |
| Sequenza degli eventi principali:  l’utente si reca sul sito o presso il negozio fisico che supporta il metodo di pagamento selezionato viene generata la richiesta di pagamento da parte dell’iban o carta di credito del richiedente denaro l’utente approva la cessione di denaro viene inviata alla banca una richiesta di trasferimento di denaro da un conto a un altro |
| Postcondizioni: il pagamento va a buon fine |
| Sequenza di eventi alternativi: pagamento rifiutato |
| **Caso d’uso 2.6: Storico movimenti del Vault** |
| Breve descrizione: l’utente vuole consultare lo storico delle delle spese eseguite tramite il Vault che sono state registrate nell’applicazione |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni:  |
| Sequenza degli eventi principali: ogni volta che l’utente esegue una spesa il sistema aggiorna il proprio storico spese l’utente agisce sull’interfaccia grafica per raggiungere la sezione riguardante lo storico delle spese l’utente visualizza l’elenco delle spese |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  |

| Caso d’uso 3: SendMe |
| :---- |
| **Caso d’uso 3.1: Invio e ricezione di denaro** |
| Breve descrizione: ogni utente può inviare denaro ad altri utenti o amici prelevandolo dal Vault |
| Attore primario: utente1 (il mittente) |
| Attore secondario: utente2 (il destinatario) |
| Precondizioni: entrambi gli utenti devono essere registrati e aver effettuato il login |
| Sequenza degli eventi principali: L’utente1 entra nell’apposita area premendo il pulsante di invio del denaro L’utente1 specifica l’importo da inviare L’utente1 conferma l’operazione Il saldo dal Vault dell’utente1 viene scalato dell’importo appena confermato e il saldo dal Vault dell'utente2 viene incrementato dell’importo inserito dall'utente La transazione viene registrata |
| Postcondizioni: l’invio del denaro va a buon fine decrementando il saldo nel Vault dell’utente1 e incrementando quello dell’utente2  |
| Sequenza di eventi alternativi: il denaro inviato dall’utente1 è maggiore di quello che in realtà ha nel suo Vault, allora: non viene prelevata nessuna somma dal Vault dell’utente1  il sistema invia un messaggio d’errore all’utente1 e i saldi dei Vault dei due utenti rimangono invariati |
| **Caso d’uso 3.2: Generazione notifica** |
| Breve descrizione: l’applicazione invia una notifica all’utente (destinatario) di ricezione o richiesta importo (natura della notifica) sulla base di un altro utente (mittente) |
| Attore primario: utente1 (mittente) |
| Attore secondario: utente2 (destinatario) |
| Precondizioni: entrambi gli utenti devono essersi registrati e aver effettuato il login |
| Sequenza degli eventi principali:  L’utente1 entra nell’apposita sezione L’utente1 invia una richiesta di pagamento Il sistema invia una notifica all’utente2 (destinatario) |
| Postcondizioni: a seconda della natura della notifica (ricezione o richiesta), l’utente2 riceverà una notifica dell’evento |
| Sequenza di eventi alternativi: nessuna |

| Caso d’uso 4: Gestione delle amicizie |
| :---- |
| **Caso d’uso 4.1: Lista amici** |
| Breve descrizione: consente di avere in una sezione apposita una lista con l’elenco delle amicizie correnti |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato e aver effettuato il login |
| Sequenza degli eventi principali:  L’utente si reca nella sezione apposita L’utente richiede di mostrare la lista degli amici |
| Postcondizioni: l’utente visualizza una lista con tutti i suoi amici |
| Sequenza di eventi alternativi: nessuna |
| **Caso d’uso 4.2: Approvazione richiesta di amicizia** |
| Breve descrizione: un utente riceve una richiesta di amicizia da parte di un altro utente e quest’ultimo decide se accettare o declinare l’invito di amicizia |
| Attore primario: utente 1 |
| Attore secondario: utente 2 |
| Precondizioni: entrambi gli utenti devono essere registrati sull’applicazione |
| Sequenza degli eventi principali: L’utente1 apre l’area dell’applicazione predisposta alla gestione delle amicizie Visualizza la richiesta di amicizia inviata dall’utente 2 Accetta la richiesta di amicizia |
| Postcondizioni: nell’area personale dei due utenti comparirà (vicendevolmente) l’icona dell’utente appena aggiunto come amico |
| Sequenza di eventi alternativi:  l’utente declina la richiesta di amicizia nessuno dei due utenti sarà aggiunto alla lista degli amici dell’altro utente |
| **Caso d’uso 4.3: Rimozione dell'amicizia** |
| Breve descrizione: l’utente direttamente dalla lista amici può decidere di rimuovere l’amicizia ad un altro utente che è suo amico |
| Attore primario: utente 1 |
| Attore secondario: utente 2 |
| Precondizioni: entrambi gli utenti devono essere registrati e aver effettuato il login al sistema e che l’utente al quale voglio togliere l’amicizia sia effettivamente mio amico (quindi sia nella lista amici) |
| Sequenza degli eventi principali: L’utente1 entra nell’apposita area amicizie L’utente1 clicca su utente2 Tramite l’apposito pulsante l’utente1 rimuove l’amicizia a utente2 |
| Postcondizioni: utente1 e utente2 non saranno più amici e non potranno più vedersi l’un l’altro dalla propria lista amici (a utente1 non comparirà più utente2 e viceversa) |
| Sequenza di eventi alternativi: nessuna |
| **Caso d’uso 4.4: Ricerca utenti ed invio richiesta di amicizia** |
| Breve descrizione: l’utente vuole cercare un altro utente per poter inviare una richiesta di amicizia |
| Attore primario: utente 1 |
| Attori secondari: utente 2 |
| Precondizioni: entrambi gli utenti devono essere registrati, aver effettuato il login e l’utente1 deve conoscere lo username dell’utente2 |
| Sequenza degli eventi principali:  L’utente1 apre l’area dell’applicazione predisposta alla gestione delle amicizie L'utente1 digita lo username dell’utente2 L’utente1 preme il tasto di ricerca Il sistema mostra a schermo il risultato della ricerca L’utente1 invia la richiesta d’amicizia all’utente2 generando una notifica  L’utente2 riceverà la notifica di richiesta d’amicizia e potrà decidere se accettarla o declinarla |
| Postcondizioni: l’utente1 ha mandato trovato utente2 e gli ha mandato la richiesta di amicizia |
| Sequenza di eventi alternativi:  la ricerca non ha prodotto alcun risultato, allora: non verra aggiunto l’utente ricercato |

| Caso d’uso 5: Gestione Gruppo |
| :---- |
| **Caso d’uso 5.1: Creazione di un gruppo** |
| Breve descrizione: un utente vuole creare un gruppo con la possibilità di aggiungere in seguito utenti già registrati al sistema |
| Attore primario: utente che vuole creare il gruppo |
| Attore secondario: nessuno |
| Precondizioni:  |
| Sequenza degli eventi principali:  l’utente agisce sull’interfaccia approvando per seguire la procedura di creazione di un gruppo l’utente inserisce il nome del gruppo il sistema crea il gruppo che avrà come unico partecipante l’utente che ha creato il gruppo, il quale sarà l’amministratore |
| Postcondizioni: a seguito di questa operazione l’amministratore potrà aggiungere in qualsiasi momento membri al gruppo |
| Sequenza di eventi alternativi: il gruppo non si riesce a creare l’operazione viene annullata senza creare nulla |
| **Caso d’uso 5.2: Invito a partecipare a un gruppo** |
| Breve descrizione: l’amministratore invia ad un utente un invito a partecipare ad un gruppo e dovrà decidere se accettare oppure declinare la proposta |
| Attore primario: amministratore del gruppo |
| Attore secondario: utente |
| Precondizioni: l’utente deve essere registrato alla piattaforma |
| Sequenza degli eventi principali:  l’amministratore invia una richiesta per partecipare al gruppo ad un utente l’utente riceve l’invito di partecipazione al gruppo con specificato il nome del gruppo e gli utenti partecipanti l’utente accetta la proposta viene aggiunto come membro del gruppo e può contribuire quindi alle spese condivise create dal gruppo |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  l’utente rifiuta la proposta l’utente non diventa membro del gruppo |
| **Caso d’uso 5.3: Rimozione di membri dal gruppo** |
| Breve descrizione: l’amministratore vuole rimuovere un utente dal gruppo |
| Attore primario: amministratore |
| Attore secondario: utente da rimuovere |
| Precondizioni:  |
| Sequenza degli eventi principali:  l’utente amministratore seleziona l’utente da rimuovere conferma la rimozione l’utente non fa più parte del gruppo |
| Postcondizioni: viene eseguito un ricalcolo dei debiti sui restanti partecipanti |
| Sequenza di eventi alternativi:  errore nel abbandonare il gruppo l’utente e ancora dentro al gruppo l'utente non e amministratore  l'utente non viene tolto dal gruppo  |
| **Caso d’uso 5.4: Abbandono di un gruppo** |
| Breve descrizione: un utente vuole rimuoversi da un gruppo |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente e dentro al gruppo da cui vuole uscire |
| Sequenza degli eventi principali:  l’utente seleziona il gruppo da cui rimuoversi la piattaforma comprende la richiesta e procede con la rimozione |
| Postcondizioni:  viene eseguito un ricalco dei debiti sui restanti partecipanti |
| Sequenza di eventi alternativi:  errore nel abbandonare il gruppo l'utente e ancora dentro il gruppo |
| **Caso d’uso 5.5: Eliminazione di un gruppo** |
| Breve descrizione: un utente vuole eliminare un gruppo di cui è amministratore |
| Attore primario: amministratore |
| Attore secondario: membri del gruppo |
| Precondizioni: l’utente a fare la richiesta deve essere amministratore del gruppo |
| Sequenza degli eventi principali:  l’amministratore seleziona il gruppo da eliminare approva la richiesta di eliminazione tutte le raccolte e le spese condivise saranno eliminate |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  errore nell'eliminazione del gruppo il gruppo non e stato eliminato l’utente non e amministratore il gruppo non viene eliminato |

| Caso d’uso 6: Gestione spese di gruppo |
| :---- |
| **Caso d’uso 6.1: Aggiunta di spesa condivisa** |
| Breve descrizione: un utente vuole aggiungere una spesa condivisa il cui importo verrà suddiviso tra i membri del gruppo |
| Attore primario: membro del gruppo |
| Attore secondario: nessuno |
| Precondizioni: partecipare al gruppo a cui si vuole aggiungere la spesa |
| Sequenza degli eventi principali:  viene indicato il nome, il tipo di spesa, una descrizione facoltativa e l’importo il sistema provvederà a suddividere in parti uguali la spesa tra i vari utenti |
| Postcondizioni: l’ammontare verrà sommato ai debiti individuali dei vari utenti, e si procederà solo una volta terminata la finalizzazione dei debiti al pagamento dell’importo dovuto |
| Sequenza di eventi alternativi:  errore nell'inserimento della spesa la spesa non e stata inserita e non sara visualizzata |
| **Caso d’uso 6.2: Eliminazione spesa condivisa** |
| Breve descrizione: Si vuole eliminare una spesa condivisa aggiunta per errore oppure perché non più necessaria. |
| Attore primario: membro del gruppo |
| Attore secondario: nessuno |
| Precondizioni: nel gruppo è stata creata una spesa condivisa |
| Sequenza degli eventi principali: il membro del gruppo che ha creato la spesa condivisa agisce sull’interfaccia grafica seleziona la spesa di gruppo che vuole eliminare comunica al sistema l’eliminazione della spesa la spesa viene eliminata |
| Postcondizioni: nessun membro del gruppo dovrà più alcuna cifra riguardante quella spesa condivisa |
| Sequenza di eventi alternativi:  errore nel eliminazione della spesa la spesa e ancora presente   |
| **Caso d’uso 6.3: Richiesta Split  (Finalizzazione spese di gruppo)** |
| Breve descrizione: serve ad approvare l’azzeramento di debiti tra gli utenti, in quanto le spese condivise non sono un trasferimento immediato di denaro, ma rappresentano una lista di debiti e crediti, e per utilizzare il minor numero di transazioni per ogni utente |
| Attore primario: Amministratore del gruppo |
| Attore secondario: Gruppo |
| Precondizioni: sono state effettuate una o più spese condivise e si vuole procedere con l’invio effettivo del denaro |
| Sequenza degli eventi principali:  l’amministratore vuole che tutti i debiti tra i membri del gruppo vengano azzerati procede tramite l’interfaccia ad approvare le spese condivise tutti i debiti vengono formalizzati sarà richiesto agli utenti partecipanti di inviare la propria somma di denaro dovuta, calcolata dall’applicazione tramite richiesta payme |
| Postcondizioni: gli utenti devono pagare la somma dovuta |
| Sequenza di eventi alternativi:  errore nel esecuzione dello split  non viene inviata nessuna richiesta |

| Caso d’uso 7: VirtualVault |
| :---- |
| **Caso d’uso 7.1: Creazione di un VirtualVault** |
| Breve descrizione: l’utente vuole creare VirtualVault, ovvero uno spazio in cui depositare e programmare risparmi, per poi prelevare in un futuro l’ammontare voluto |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: |
| Sequenza degli eventi principali:  l’utente inserisce il nome del VirtualVault che vuole creare se l’utente non ne possiede altri con lo stesso nome allora verrà correttamente creato l’utente ora potrà utilizzare tutte le funzionalità del VirtualVault |
| Postcondizioni:  |
| Sequenza di eventi alternativi: |
| **Caso d’uso 7.2: Cancellazione di un VirtualVault**  |
| Breve descrizione: l’utente vuole eliminare un virtualvault precedentemente creato |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni:  |
| Sequenza degli eventi principali:  l’utente seleziona quale VirtualVault eliminare il sistema verifica che non si tratti di un VirtualVault principale altrimenti l’operazione non può essere eseguita il sistema elimina il VirtualVault selezionato |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  |
| **Caso d’uso 7.3: Trasferimento denaro verso VirtualVault** |
| Breve descrizione: si vuole spostare denaro dal Vault al proprio VirtualVault |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: |
| Sequenza degli eventi principali:  l’utente seleziona il VirtualVault seleziona il saldo con il quale ricaricare il VirtualVault viene controllato se il saldo da trasferire è disponibile nel Vault il denaro viene spostato sul VirtualVault |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  |
| **Caso d’uso 7.4: Creazione di un obiettivo da raggiungere** |
| Breve descrizione: si vuole creare un obiettivo di soldi da risparmiare per il VirtualVault, specificando la data entro la quale deve essere raggiunto. Il software calcolerà l’importo da versare di giorno in giorno per raggiungerlo entro la data prestabilita e questo verrà automaticamente detratto dal Vault. |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni:  |
| Sequenza degli eventi principali:  viene calcolato l'obiettivo il VirtualVault viene eliminato nella data stabilita |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  |
| **Caso d’uso 7.5: Visualizzazione informazioni** |
| Breve descrizione: si vuole visualizzare il saldo del VirtualVault, l'obiettivo di risparmio da raggiungere e quanto è stato già versato per raggiungerlo |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni:  |
| Sequenza degli eventi principali: l’utente visualizza nell’applicazione il denaro raccolto e quanto denaro manca al raggiungimento dell obiettivo |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  |

| Caso d’uso 8: CashBook |
| :---- |
| **Caso d’uso 8.1: Creazione di un Cashbook personale** |
| Breve descrizione: l’utente ha la possibilità di accedere alla lista di movimenti eseguiti dal Vault (questa lista sarà denominata Cashbook), e oltre a questa lista ha la possibilità di crearne ulteriori per aggiungere manualmente le transazioni eseguite in contanti (simil budget e finanze) |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato e aver effettuato il login |
| Sequenza degli eventi principali: L’utente si reca nella sezione apposita dell’applicazione L’utente clicca il pulsante per la creazione del suo Cashbook personalizzato L’utente gli assegna un nome |
| Postcondizioni: il nuovo CashBook viene registrato nell’account dell’utente |
| Sequenza di eventi alternativi:  C’è già un Cashbook con lo stesso nome che ha appena scelto allora: il sistema non gli permetterà di crearlo mandando un messaggio d’errore non verrà aggiunto nessun Cashbook all’account dell'utente |
| **Caso d’uso 8.2: Eliminazione di un Cashbook** |
| Breve descrizione: l’utente ha la possibilità di eliminare un Cashbook esistente, eccetto il cashbook principale |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e che il Cashbook che vuole eliminare sia presente sul suo account |
| Sequenza degli eventi principali:  L’utente si reca nella sezione apposita dell’applicazione L’utente clicca sul Cashbook che desidera eliminare L’utente clicca il pulsante per eliminarlo definitivamente |
| Postcondizioni: Eliminazione del CashBook dall’account dell’utente ed eventuale trasferimento automatico dei soldi dal Cashbook appena eliminato al suo Vault |
| Sequenza di eventi alternativi: L’utente prova ad eliminare il Cashbook principale L’utente viene avvertito dell’impossibilità di eseguire tale operazione |
| **Caso d’uso 8.3: Aggiunta di un movimento** |
| **Caso d’uso 8.3.1: Automatico** |
| Breve descrizione: il sistema aggiunge in maniera  automatica una qualsiasi transazione (spesa o entrata) eseguita dal Vault al Cashbook principale (quello di default) |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato e aver effettuato il login  |
| Sequenza degli eventi principali:  L’utente effettua una qualsiasi transazione (invio, ricezione, richiesta, pagamento di una spesa...) L’utente si reca nella sezione apposita dell’applicazione e visualizza il default Cashbook che conterrà l’operazione appena eseguita |
| Postcondizioni: l’utente può vedere nel Cashbook principale la spesa appena effettuata con il suo relativo importo e categoria (detto anche tipo) al quale appartiene |
| Sequenza di eventi alternativi:  L’utente decide che il movimento deve essere registrato anche in un Cashbook personalizzato deciso da lui (ovviamente precedentemente creato) L’utente si reca nel default Cashbook, nel quale è già stata registrata la transazione L’utente seleziona la transazione da copiare sul Cashbook personalizzato L’utente seleziona il Cashbook nel quale importare la transazione L’utente potrà vedere la transazione sia nel Cashbook principale (di default) che in quello personalizzato |
| **Caso d’uso 8.3.2: Manuale** |
| Breve descrizione: l’utente ha la possibilità di aggiungere manualmente un movimento di denaro al Cashbook principale o ad un Cashbook personalizzato (ad esempio per tracciare un pagamento in contanti quindi non tracciabile dal Vault) |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato e aver effettuato il login |
| Sequenza degli eventi principali:  L’utente si reca nella sezione apposita dell’applicazione L’utente crea il movimento specificando: importo (obbligatorio) data (obbligatoria) categoria (obbligatoria) un breve commento (facoltativo) per indicare il motivo della spesa o il tipo di spesa svolta L’utente conferma la creazione |
| Postcondizioni: l’utente vede nel Cashbook principale la transazione appena aggiunta con i suoi relativi dati. |
| Sequenza di eventi alternativi:  L’utente importa un movimento dal Cashbook principale ad un Cashbook personalizzato deciso da lui (ovviamente precedentemente creato) e potrà vedere la transazione sia in quello principale (di default) che in quello personalizzato Oppure L’utente non rispetta il formato o il fatto che alcuni campi (importo, data, categoria) devono essere obbligatori, allora il sistema manda un messaggio d’errore per far capire all’utente che deve modificare qualcosa rispettando anche i loro formati |
| **Caso d’uso 8.4: Rimozione di un movimento** |
| Breve descrizione: l’utente ha la possibilità di eliminare un dato movimento dal Cashbook che vuole |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e il movimento nel Cashbook che vuole eliminare deve essere effettivamente presente |
| Sequenza degli eventi principali:  L’utente si reca nella sezione apposita dell’applicazione L’utente clicca sul Cashbook desiderato L’utente clicca sul movimento che vuole cancellare L’utente clicca il pulsante per eliminarlo definitivamente |
| Postcondizioni: Eliminazione del movimento desiderato all’interno del CashBook scelto dall’utente. L’eliminazione di un movimento dal Cashbook principale comporta l’eliminazione del movimento da tutti i Cashbook che importano il movimento |
| Sequenza di eventi alternativi: L’utente prova ad eliminare un movimento Automatico L’utente riceve una segnalazione che riporta l’impossibilità di eliminare tale movimento |
| **Caso d’uso 8.5: Modifica di un movimento** |
| Breve descrizione: l’utente vuole modificare informazioni riguardanti un movimento in un determinato Cashbook |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e il movimento nel Cashbook che vuole modificare deve essere effettivamente presente |
| Sequenza degli eventi principali:  L’utente si reca nella sezione apposita dell’applicazione L’utente clicca sul Cashbook desiderato L’utente clicca sul movimento che vuole modificare L’utente può modificare, se si tratta di una transazione Manuale: importo (che deve rimanere obbligatorio) data (che deve rimanere obbligatoria) categoria (che deve rimanere obbligatoria) commento (che rimane facoltativo) L’utente clicca il pulsante per confermare le modifiche |
| Postcondizioni: l’utente vede il movimento con i dati modificati |
| Sequenza di eventi alternativi: L’utente prova a modificare una transazione Automatica, in quel momento riceverà un avvertimento L’utente non rispetta il formato o il fatto che alcuni campi (importo, data, categoria) devono essere obbligatori, allora il sistema manda un messaggio d’errore per far capire all’utente che deve modificare qualcosa rispettando anche i loro formati |
| **Caso d’uso 8.6: Visualizzazione Storico** |
| Breve descrizione: l’utente ha la possibilità di visualizzare lo storico di un determinato Cashbook esistente |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e, per visualizzare lo storico di un Cashbook, questo deve essere già stato creato in passato (il Cashbook principale è sempre presente di default) |
| Sequenza degli eventi principali:  L’utente si reca nella sezione apposita dell’applicazione L’utente clicca sul Cashbook desiderato |
| Postcondizioni: l’utente vede lo storico del Cashbook da lui selezionato |
| Sequenza di eventi alternativi:  |
| **Caso d’uso 8.7: Filtra per data** |
| Breve descrizione: l’utente ha la possibilità di visualizzare entrate e uscite effettuate in un periodo di tempo specificato dall’utente stesso |
| Attore primario: utente |
| Attore secondario: nessuno |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e deve esser presente (a meno che non si tratti del Cashbook principale che quindi c’è di default) il Cashbook del quale l’utente vuole vedere il sommario |
| Sequenza degli eventi principali:  L’utente si reca nella sezione dedicata al CashBook L’utente clicca l’apposito pulsante che permette di visualizzare il sommario calcolato sul periodo di tempo desiderato L’utente seleziona quale intervallo di tempo (giornaliero, settimanale, mensile o annuale) |
| Postcondizioni: l’utente visualizza il sommario di tutte le entrate ed uscite desiderate |
| Sequenza di eventi alternativi: nessuno |

