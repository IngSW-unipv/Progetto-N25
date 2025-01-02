# Casi d’uso

| Caso d’uso 1: Gestione metodi di pagamento e conti |
| :---- |
| **Caso d’uso 1.1: Aggiunta carta o conto** |
| Breve descrizione: l’utente aggiunge un metodo di pagamento al suo Vault |
| Attore primario: utente |
| Attore secondario: gestore, banca   |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e deve disporre delle informazioni necessarie ad aggiungere il metodo di pagamento |
| Sequenza degli eventi principali: Si reca nella sezione apposita dell’applicazione Seleziona il metodo di pagamento Inserisce le informazioni del metodo di pagamento L’istituto finanziario a cui appartengono il conto o la carta dell’utente approvano il collegamento Aggiunta del metodo di pagamento |
| Postcondizioni: il metodo di pagamento scelto dall’utente è registrato correttamente nel sistema |
| Sequenza di eventi alternativi: L’istituto finanziario a cui appartengono il conto o la carta dell’utente rifiuta il collegamento o il loro sistema informatico non risponde Fallisce l’aggiunta del metodo di pagamento |
| **Caso d’uso 1.2: Rimozione carta o conto** |
| Breve descrizione: l’utente elimina un metodo di pagamento al suo Vault |
| Attore primario: utente |
| Attore secondario: gestore |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e aggiunto con successo un metodo di pagamento all’account |
| Sequenza degli eventi principali: L’utente si reca nella sezione apposita dell’applicazione Seleziona il metodo di pagamento da rimuovere  Conferma l’eliminazione tramite l’apposito pulsante |
| Postcondizioni: il metodo di pagamento è stato rimosso e non può più essere visualizzato dall’utente  |
| Sequenza di eventi alternativi: nessuna |
| **Caso d’uso 1.3: Deposito di denaro da carta o conto** |
| Breve descrizione: L’utente deposita denaro nel Vault tramite carte e conti memorizzati |
| Attore primario: utente |
| Attore secondario: gestore |
| Precondizioni:l’utente deve essere registrato, aver effettuato il login e l’utente deve aver aggiunto con successo almeno un metodo di pagamento dal quale effettuare il deposito |
| Sequenza degli eventi principali: L’utente si reca nella sezione: ”Vault” L’utente seleziona l’opzione per depositare denaro sul Vault Seleziona il metodo di pagamento tra quelli registrati L’utente specificaViene specificato l’importo da caricare L’utente conferma l’operazione Viene inviata alla banca una richiesta di pagamento al metodo selezionato La banca approva la transazione Il saldo del Vault verrà ricaricato |
| Postcondizioni: Il Vault verrà ricaricato con l’importo selezionato |
| Sequenza di eventi alternativi:  La banca rifiuta la transazione Il saldo del Vault rimarrà invariato il saldo della carta è insufficiente Il saldo del Vault rimarrà invariato |
| **Caso d’uso 1.4: Deposito di denaro one time** |
| Breve descrizione: deposito di denaro sul vault usando un metodo di pagamento non registrato ma di cui si dispongono le informazioni |
| Attore primario: utente |
| Attore secondario: gestore |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login el’utente dispone delle informazioni necessarie di un metodo di pagamento che non è stato già aggiunto |
| Sequenza degli eventi principali: L’utente si reca nella sezione: “Vault” L’utente seleziona l’opzione per depositare denaro sul Vault Seleziona: “altro metodo di pagamento” L’utente specifica Viene specificato l’importo da caricare L’utente inserisce le informazioni del metodo di pagamento L’utente conferma l’operazione Viene inviata alla banca una richiesta di pagamento al metodo selezionato La banca approva la transazione Il saldo del Vault viene incrementato |
| Postcondizioni: l’utente ha ricaricato il conto del Vault senza che il metodo di pagamento fosse già registrato sul suo account.Il suo metodo di pagamento (dopo l’operazione) non verrà registrato sull’account |
| Sequenza di eventi alternativi: La banca rifiuta la transazione Il saldo del Vault rimarrà invariatonon verrà incrementato |
| **Caso d’uso 1.5: Prelievo dal Vault** |
| Breve descrizione: l’utente preleva denaro dalnel Vault per inviarlo ad una carta o un conto tra quelli memorizzati (registrati) |
| Attore primario: utente |
| Attore secondario: gestore, banca |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login ,l’utente dispone di un saldo sul Vault e deve aver registrato al proprio account almeno una carta o conto (un metodo di pagamento) |
| Sequenza degli eventi principali: L’utente richiede il prelievo di un ammontare di denaro presente nel Vault Specifica la carta o conto di destinazione (precedentemente registrati) Specifica l’importo da prelevare  Viene contattato l’istituto finanziario a cui appartiene la carta o conto selezionato Viene inviato il denaro |
| Postcondizioni: il saldo nel Vault diminuisce e il saldo del metodo di pagamento selezionato viene incrementato  |
| Sequenza di eventi alternativi: l’utente richiede di prelevare di più di quanto ci sia effettivamente sul Vault l’operazione gli verrà negata dal sistema i saldi (Vault e della carta\\conto) rimarranno invariati |
| **Caso d’uso 1.6: Esecuzione di un pagamento** |
| Breve descrizione: l’utente vuole eseguire un pagamento fisico o online utilizzando l’app  |
| Attore primario: utente |
| Attore secondario: gestore |
| Precondizioni: l’utente deve essere registrato, aver effettuato il login e deve disporrel’utente dispone di un conto o una carta con sopra un saldo sufficiente al pagamento |
| Sequenza degli eventi principali:  l’utente si reca sul sito o presso il negozio fisico che supporta il metodo di pagamento selezionato viene generata la richiesta di pagamento da parte dell’iban o carta di credito del richiedente denaro l’utente approva la cessione di denaro viene inviata alla banca una richiesta di trasferimento di denaro da un conto a un altro |
| Postcondizioni: il pagamento va a buon fine |
| Sequenza di eventi alternativi: pagamento rifiutato |

| Caso d’uso 2: Creazione account |
| :---- |
| Breve descrizione: L’utente vuole crearecrea un account |
| Attore primario: utente |
| Attore secondario: gestore |
| Precondizioni: nessuna |
| Sequenza degli eventi principali: L’utente apre l’applicazione L’utente cliccando il tasto “registrati” inserisce usernamenome, email, password Il gestore verifica che le informazioni siano valide Le informazioni sono state approvate dal server L’account viene registrato |
| Postcondizioni: l’utente si è registrato e ha creato il suo accountl’account è stato registrato e l’utente potrà eseguire il login |
| Sequenza di eventi alternativi: L’utente apre l’applicazione L’utente inserisce nome, email, password Il gestore verifica che le informazioni siano valide Il server rileva che l’email ha già un account associato L’account non viene registrato |

| Caso d’uso 3: Login |
| :---- |
| Breve descrizione: L’utente si collega all’applicazione col proprio account |
| Attore primario: utente |
| Attore secondario: gestore |
| Precondizioni: l’utente deve aver creato l'account |
| Sequenza degli eventi principali: L’utente apre l’applicazione L’utente cliccando il tasto “login” inserisce username, email, password Il gestore verifica che le informazioni siano valide e che siano presenti nel server L’utente viene fatto accedere all’applicazione |
| Postcondizioni: l’utente si trova nella schermata principale dell’applicazione |
| Sequenza di eventi alternativi:  L’utente cerca di effettuare il login senza essersi prima registrato Il server manda un messaggio d’errore dicendo che l’utente con quell’ username non è presente all’interno del sistema La richiesta di login da parte dell’utente viene respinta e si rimane nella stessa schermata in attesa di altri input da parte dell’utente (magari perché ha sbagliato mail password) L’utente cerca di effettuare il login sbagliando email o password |

| Caso d’uso 4: Interazioni tra utenti |
| :---- |
| Breve descrizione:  |
| Attore primario: |
| Attore secondario: |
| Precondizioni: |
| Sequenza degli eventi principali: |
| Postcondizioni: |
| Sequenza di eventi alternativi: |

| Caso d’uso 5: Pagamento SendMe |
| :---- |
| **Caso d’uso 5.1: Invio e ricezione denaro** |
| Breve descrizione: ogni utente può inviare denaro ad altri utenti o amici prelevandolo dal Vault |
| Attore primario: utente1 |
| Attore secondario: utente2 |
| Precondizioni: entrambi gli utenti devono essere registrati e aver effettuato il login |
| Sequenza degli eventi principali: L’utente1 entra nell’apposita area premendo il pulsante di invio del denaro L’utente1 specifica l’importo da inviare L’utente1 conferma l’operazione Il saldo dal Vault dell’utente1 viene scalato dell’importo voluto e il saldo dal Vault dell'utente2 (che è il ricevente) viene incrementato dell’importo inserito dall'utente La transazione viene registrata |
| Postcondizioni:l’invio del denaro va a buon fine decrementando il saldo nel Vault dell’utente1 e incrementando quello dell’utente2 |
| Sequenza di eventi alternativi: il denaro inviato dall’utente1 è maggiore di quello che in realtà ha nel suo Vault, allora il sistema invia un messaggio d’errore all’utente1 e i saldi dei Vault dei due utenti rimangono invariati |
| **Caso d’uso 5.2: Richiedere denaro** |
| Breve descrizione: ogni utente può richiedere denaro ad altri utenti, ma solo se sono amici tra loro, prelevando il denaro dal Vault dell’emissario |
| Attore primario: utente1 |
| Attore secondario: utente2 |
| Precondizioni: entrambi gli utenti devono essere registrati, aver effettuato il login e i due utenti devono avere un legame di amicizia |
| Sequenza degli eventi principali: L’utente1 entra nell’apposita area premendo il pulsante di richiesta del denaro L’utente1 specifica l’importo da richiedere all’utente2 e una piccola descrizione del motivo della richiesta L’utente1 conferma l’operazione Nella chat tra utente1 e utente2 arriva un messaggio di richiesta di una certa cifra di denaro e la piccola descrizione data da utente1 Utente2 accetta la transazione All’utente2 viene scalato il saldo dal suo Vault, mentre al saldo del Vault dell’utente1 viene incrementato |
| Postcondizioni: l’utente1 vedrà il suo saldo incrementarsi della cifra che aveva richiesto |
| Sequenza di eventi alternativi: il denaro presente nel Vault dell’utente2 è minore della quantità richiesta dall’utente1, allora l’utente 2 potrà: ricaricare il saldo del suo Vault tramite carte o conti  richiedere a sua volta del denaro ad altri amici di utente2 arrivando così alla cifra richiesta da utente1  poter finalizzare il “debito” rifiutare la richiesta (anche se il suo saldo è sufficiente per finalizzare lo scambio di denaro) e succederà che: verrà avvisato il richiedente e non vi sarà alcuna variazione del saldo dei due Vault |

| Caso d’uso 6: Premium |
| :---- |
| Breve descrizione:  |
| Attore primario: |
| Attore secondario: |
| Precondizioni: |
| Sequenza degli eventi principali: |
| Postcondizioni: |
| Sequenza di eventi alternativi: |

| Caso d’uso 7: Gruppi |
| :---- |
| Breve descrizione:  |
| Attore primario: |
| Attore secondario: |
| Precondizioni:  |
| Sequenza degli eventi principali: |
| Postcondizioni: |
| Sequenza di eventi alternativi: |

| Caso d’uso 8: Gestione delle amicizie |
| :---- |
| **Caso d’uso 8.1: Aggiunta o rifiuto di un’amicizia** |
| Breve descrizione: un utente riceve una richiesta di amicizia da parte di un altro utente e quest’ultimo decide se accettare o declinare l’invito di amicizia |
| Attore primario: utente1 |
| Attore secondario: gestore, utente2 |
| Precondizioni: entrambi gli utenti devono essere registrati sull’applicazione |
| Sequenza degli eventi principali: L’utente1 apre l’area dell’applicazione predisposta alla gestione delle amicizie Visualizza la richiesta di amicizia inviata dall’utente2 Accetta la richiesta di amicizia |
| Postcondizioni: nell’area personale dei due utenti comparirà (vicendevolmente) l’icona dell’utente appena aggiunto come amico |
| Sequenza di eventi alternativi:  l’utente declina la richiesta di amicizia nessuno dei due utenti sarà aggiunto alla lista degli amici dell’altro utente |
| **Caso d’uso 8.2: Rimozione amicizia** |
| Breve descrizione: l’utente direttamente dalla lista amici può decidere di rimuovere l’amicizia ad un altro utente precedentemente suo amico |
| Attore primario: utente1 |
| Attore secondario: utente2 |
| Precondizioni: entrambi gli utenti devono essere registrati e aver effettuato il login al sistema e che l’utente al quale voglio togliere l’amicizia sia effettivamente mio amico (quindi sia nella lista amici) |
| Sequenza degli eventi principali: l’utente1 entra nell’apposita area amicizie l’utente1 clicca su utente2 tramite l’apposito pulsante l’utente1 rimuove l’amicizia a utente2 |
| Postcondizioni: in ambedue le liste degli utenti scompariranno le icone utente dei due ex amici, cosicché tutti e due gli utenti non saranno più amici tra loro |
| Sequenza di eventi alternativi: nessuna |
| **Caso d’uso 8.3: Ricerca utenti** |
| Breve descrizione: l’utente vuole cercare un altro utente per poter inviare una richiesta di amicizia |
| Attore primario: utente1 |
| Attore secondario: gestore, utente2 |
| Precondizioni: l’utente1 deve conoscere lo username dell’utente2 |
| Sequenza degli eventi principali:  L’utente1 apre l’area dell’applicazione predisposta alla gestione delle amicizie L'utente1 specifica un testo indicante il nome di un‘altro utente (l’username) L’utente1 preme il tasto di ricerca Il sistema mostra a schermo il risultato della ricerca L’utente1 invia la richiesta d’amicizia All’utente2 riceverà la richiesta d’amicizia e potrà decidere se accettarla o declinarla |
| Postcondizioni:  |
| Sequenza di eventi alternativi:  la ricerca non ha prodotto alcun risultato l’utente1 riceverà un messaggio di errore |

| Caso d’uso 9: VirtualVault |
| :---- |
| Breve descrizione:  |
| Attore primario: |
| Attore secondario: |
| Precondizioni |
| Sequenza degli eventi principali: |
| Postcondizioni: |
| Sequenza di eventi alternativi: |

| Caso d’uso 10: CashBook |
| :---- |
| Breve descrizione:  |
| Attore primario: |
| Attore secondario: |
| Precondizioni |
| Sequenza degli eventi principali: |
| Postcondizioni: |
| Sequenza di eventi alternativi: |

Tenere vuoto, da copiare e incollare altrove

| Caso d’uso 1:  |
| :---- |
| Breve descrizione:  |
| Attore primario: |
| Attore secondario: |
| Precondizioni |
| Sequenza degli eventi principali: |
| Postcondizioni: |
| Sequenza di eventi alternativi: |

