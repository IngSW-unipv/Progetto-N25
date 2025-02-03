**Documento di Specifica dei requisiti Software**  
   
**Indice**

[1\. Introduzione	15](#1.-introduzione)

[1.1 Scopo del documento	15](#1.1-scopo-del-documento)

[1.2 Scopo del Prodotto	15](#1.2-scopo-del-prodotto)

[1.3 Acronimi	15](#1.3-acronimi)

[1.4 Riferimenti	15](#1.4-riferimenti)

[1.5 Descrizione del resto del documento	15](#1.5-descrizione-del-resto-del-documento)

[2\. Descrizione generale	16](#2.-descrizione-generale)

[2.1 Prospettiva del prodotto	16](#2.1-prospettiva-del-prodotto)

[2.2 Funzionalità del prodotto	16](#2.2-funzionalità-del-prodotto)

[2.3 Caratteristiche utente	17](#2.3-caratteristiche-utente)

[2.4 Vincoli generali	17](#2.4-vincoli-generali)

[2.5 Assunzioni e dipendenze	18](#2.5-assunzioni-e-dipendenze)

[3\. Requisiti	18](#3.-requisiti)

[3.1 Requisiti di interfaccia esterna	18](#3.1-requisiti-di-interfaccia-esterna)

[3.1.1 Interfaccia utente	18](#3.1.1-interfaccia-utente)

[3.1.2 Interfaccia  hardware	18](#3.1.2-interfaccia-hardware)

[3.1.3 Interfaccia software	18](#3.1.3-interfaccia-software)

[3.1.4 Interfaccia di comunicazione	18](#3.1.4-interfaccia-di-comunicazione)

[3.2 Requisiti funzionali	18](#3.2-requisiti-funzionali)

[3.2.1 Aggiunta carta o conto	18](#3.2.1-aggiunta-carta-o-conto)

[3.2.1.1 Introduzione	18](#3.2.1.1-introduzione)

[3.2.1.2 Input	18](#3.2.1.2-input)

[3.2.1.3 Elaborazione	18](#3.2.1.3-elaborazione)

[3.2.1.4 Output	18](#3.2.1.4-output)

[3.2.2 Eliminazione carta o conto	18](#3.2.2-eliminazione-carta-o-conto)

[3.2.2.1 Introduzione	18](#3.2.2.1-introduzione)

[3.2.2.2 Input	18](#3.2.2.2-input)

[3.2.2.3 Elaborazione	19](#3.2.2.3-elaborazione)

[3.2.2.4 Output	19](#3.2.2.4-output)

[3.2.3 Deposito di denaro da Carte o Conti salvate	19](#3.2.3-deposito-di-denaro-da-carte-o-conti-salvate)

[3.2.3.1 Introduzione	19](#3.2.3.1-introduzione)

[3.2.3.2 Input	19](#3.2.3.2-input)

[3.2.3.3 Elaborazione	19](#3.2.3.3-elaborazione)

[3.2.3.4 Output	19](#3.2.3.4-output)

[3.2.4 Deposito di denaro one time	19](#3.2.4-deposito-di-denaro-one-time)

[3.2.4.1 Introduzione	19](#3.2.4.1-introduzione)

[3.2.4.2 Input	19](#3.2.4.2-input)

[3.2.4.3 Elaborazione	19](#3.2.4.3-elaborazione)

[3.2.4.4 Output	19](#3.2.4.4-output)

[3.2.5 Prelievo importo dal vault	19](#3.2.5-prelievo-importo-dal-vault)

[3.2.5.1 Introduzione	19](#3.2.5.1-introduzione)

[3.2.5.2 Input	19](#3.2.5.2-input)

[3.2.5.3 Elaborazione	19](#3.2.5.3-elaborazione)

[3.2.5.4 Output	19](#3.2.5.4-output)

[3.2.6 Esecuzione pagamento	19](#3.2.6-esecuzione-pagamento)

[3.2.6.2 Input	20](#3.2.6.2-input)

[3.2.6.4 Output	20](#3.2.6.4-output)

[3.2.7 Gestione delle spese e personalizzazione delle categorie	20](#3.2.7-gestione-delle-spese-e-personalizzazione-delle-categorie)

[3.2.7.1 Introduzione	20](#3.2.7.1-introduzione)

[3.2.7.2 Input	20](#3.2.7.2-input)

[3.2.7.3 Elaborazione	20](#3.2.7.3-elaborazione)

[3.2.7.4 Output	20](#3.2.7.4-output)

[3.2.8 Storico del Vault	20](#3.2.8-storico-del-vault)

[3.2.8.1 Introduzione	20](#3.2.8.1-introduzione)

[3.2.8.2 Input	20](#3.2.8.2-input)

[3.2.8.3 Elaborazione	20](#3.2.8.3-elaborazione)

[3.2.8.4 Output	20](#3.2.8.4-output)

[3.2.9 Creazione di un VirtualVault	20](#3.2.9-creazione-di-un-virtualvault)

[3.2.9.1 Introduzione	20](#3.2.9.1-introduzione)

[3.2.9.2 Input	20](#3.2.9.2-input)

[3.2.9.3 Elaborazione	20](#3.2.9.3-elaborazione)

[3.2.9.4 Output	20](#3.2.9.4-output)

[3.2.10 Cancellazione di un VirtualVault	21](#3.2.10-cancellazione-di-un-virtualvault)

[3.2.10.1 Introduzione	21](#3.2.10.1-introduzione)

[3.2.10.2 Input	21](#3.2.10.2-input)

[3.2.10.3 Elaborazione	21](#3.2.10.3-elaborazione)

[3.2.10.4 Output	21](#3.2.10.4-output)

[3.2.11 Spostamento saldo verso VirtualVault	21](#3.2.11-spostamento-saldo-verso-virtualvault)

[3.2.11.1 Introduzione	21](#3.2.11.1-introduzione)

[3.2.11.2 Input	21](#3.2.11.2-input)

[3.2.11.3 Elaborazione	21](#3.2.11.3-elaborazione)

[3.2.11.4 Output	21](#3.2.11.4-output)

[3.2.12 Creazione di un obiettivo (data)	21](#3.2.12-creazione-di-un-obiettivo-\(data\))

[3.2.12.1  Introduzione	21](#3.2.12.1-introduzione)

[3.2.12.2 Input	21](#3.2.12.2-input)

[3.2.12.3 Elaborazione	21](#3.2.12.3-elaborazione)

[3.2.12.4 Output	21](#3.2.12.4-output)

[3.2.13 Movimento automatico	21](#3.2.13-movimento-automatico)

[3.2.13.2  Input	21](#3.2.13.2-input)

[3.2.13.3  Elaborazione	21](#3.2.13.3-elaborazione)

[3.2.13.4  Output	21](#3.2.13.4-output)

[3.2.14 Visualizzazione info Virtual Vault	21](#3.2.14-visualizzazione-info-virtual-vault)

[3.2.14.2  Input	21](#3.2.14.2-input)

[3.2.14.3  Elaborazione	22](#3.2.14.3-elaborazione)

[3.2.14.4  Output	22](#3.2.14.4-output)

[3.2.15 Creazione CashBook	22](#3.2.15-creazione-cashbook)

[3.2.15.1  Introduzione	22](#3.2.15.1-introduzione)

[3.2.15.2 Input	22](#3.2.15.2-input)

[3.2.15.3 Elaborazione	22](#3.2.15.3-elaborazione)

[3.2.15.4 Output	22](#3.2.15.4-output)

[3.2.16 Eliminazione  CashBook	22](#3.2.16-eliminazione-cashbook)

[3.2.16.1  Introduzione	22](#3.2.16.1-introduzione)

[3.2.16.2 Input	22](#3.2.16.2-input)

[3.2.16.3 Elaborazione	22](#3.2.16.3-elaborazione)

[3.2.16.4 Output	22](#3.2.16.4-output)

[3.2.17 Aggiunta Watcher CCorrente/Carta a un CashBook	22](#3.2.17-aggiunta-watcher-ccorrente/carta-a-un-cashbook)

[3.2.17.1  Introduzione	22](#3.2.17.1-introduzione)

[3.2.17.2 Input	22](#3.2.17.2-input)

[3.2.17.3 Elaborazione	22](#3.2.17.3-elaborazione)

[3.2.17.4 Output	22](#3.2.17.4-output)

[3.2.18 Rimozione Watcher CCorrente/Carta da un CashBook	22](#3.2.18-rimozione-watcher-ccorrente/carta-da-un-cashbook)

[3.2.18.1  Introduzione	22](#3.2.18.1-introduzione)

[3.2.18.2 Input	22](#3.2.18.2-input)

[3.2.18.3 Elaborazione	22](#3.2.18.3-elaborazione)

[3.2.18.4 Output	22](#3.2.18.4-output)

[3.2.19 Aggiunta Movimento in CashBook	23](#3.2.19-aggiunta-movimento-in-cashbook)

[3.2.19.1  Introduzione	23](#3.2.19.1-introduzione)

[3.2.19.2 Input	23](#3.2.19.2-input)

[3.2.19.3 Elaborazione	23](#3.2.19.3-elaborazione)

[3.2.19.4 Output	23](#3.2.19.4-output)

[3.2.20 Rimozione Movimento in CashBook	23](#3.2.20-rimozione-movimento-in-cashbook)

[3.2.20.1  Introduzione	23](#3.2.20.1-introduzione)

[3.2.20.2 Input	23](#3.2.20.2-input)

[3.2.20.3 Elaborazione	23](#3.2.20.3-elaborazione)

[3.2.20.4 Output	23](#3.2.20.4-output)

[3.2.21 Modifica Movimento di un CashBook	23](#3.2.21-modifica-movimento-di-un-cashbook)

[3.2.21.1  Introduzione	23](#3.2.21.1-introduzione)

[3.2.21.2 Input	23](#3.2.21.2-input)

[3.2.21.3 Elaborazione	23](#3.2.21.3-elaborazione)

[3.2.21.4 Output	23](#3.2.21.4-output)

[3.2.22 Storico	23](#3.2.22-storico)

[3.2.22.1  Introduzione	23](#3.2.22.1-introduzione)

[3.2.22.2 Input	23](#3.2.22.2-input)

[3.2.22.3 Elaborazione	23](#3.2.22.3-elaborazione)

[3.2.22.4 Output	23](#3.2.22.4-output)

[3.2.23 Sommario	23](#3.2.23-sommario)

[3.2.23.1  Introduzione	23](#3.2.23.1-introduzione)

[3.2.23.2 Input	23](#3.2.23.2-input)

[3.2.23.3 Elaborazione	24](#3.2.23.3-elaborazione)

[3.2.23.4 Output	24](#3.2.23.4-output)

[3.2.24 Grafici (Premium)	24](#3.2.24-grafici-\(premium\))

[3.2.24.1  Introduzione	24](#3.2.24.1-introduzione)

[3.2.24.2 Input	24](#3.2.24.2-input)

[3.2.24.3 Elaborazione	24](#3.2.24.3-elaborazione)

[3.2.24.4 Output	24](#3.2.24.4-output)

[3.2.25 Invio denaro	24](#3.2.25-invio-denaro)

[3.2.25.1  Introduzione	24](#3.2.25.1-introduzione)

[3.2.25.2 Input	24](#3.2.25.2-input)

[3.2.25.3 Elaborazione	24](#3.2.25.3-elaborazione)

[3.2.25.4 Output	24](#3.2.25.4-output)

[3.2.26 Ricezione importo	24](#3.2.26-ricezione-importo)

[3.2.26.1  Introduzione	24](#3.2.26.1-introduzione)

[3.2.26.2 Input	24](#3.2.26.2-input)

[3.2.26.3 Elaborazione	24](#3.2.26.3-elaborazione)

[3.2.26.4 Output	24](#3.2.26.4-output)

[3.2.27 Invio Richiesta importo	24](#3.2.27-invio-richiesta-importo)

[3.2.27.1  Introduzione	24](#3.2.27.1-introduzione)

[3.2.27.2 Input	24](#3.2.27.2-input)

[3.2.27.3 Elaborazione	24](#3.2.27.3-elaborazione)

[3.2.27.4 Output	24](#3.2.27.4-output)

[3.2.28 Ricezione richiesta importo	25](#3.2.28-ricezione-richiesta-importo)

[3.2.28.1  Introduzione	25](#3.2.28.1-introduzione)

[3.2.28.2 Input	25](#3.2.28.2-input)

[3.2.28.3 Elaborazione	25](#3.2.28.3-elaborazione)

[3.2.28.4 Output	25](#3.2.28.4-output)

[3.2.29  Storico transazioni	25](#3.2.29-storico-transazioni)

[3.2.29.1  Introduzione	25](#3.2.29.1-introduzione)

[3.2.29.2 Input	25](#3.2.29.2-input)

[3.2.29.3 Elaborazione	25](#3.2.29.3-elaborazione)

[3.2.29.4 Output	25](#3.2.29.4-output)

[3.2.30 Generazione notifica	25](#3.2.30-generazione-notifica)

[3.2.30.1  Introduzione	25](#3.2.30.1-introduzione)

[3.2.30.2 Input	25](#3.2.30.2-input)

[3.2.30.3 Elaborazione	25](#3.2.30.3-elaborazione)

[3.2.30.4 Output	25](#3.2.30.4-output)

[3.2.31 Creazione di un gruppo	25](#3.2.31-creazione-di-un-gruppo)

[3.2.31.1 Introduzione	25](#3.2.31.1-introduzione)

[3.2.31.1 Input	25](#3.2.31.1-input)

[3.2.31.1 Elaborazione	25](#3.2.31.1-elaborazione)

[3.2.31.1 Output	25](#3.2.31.1-output)

[3.2.32 Aggiunta di membri al gruppo	25](#3.2.32-aggiunta-di-membri-al-gruppo)

[3.2.32.1 Introduzione	25](#3.2.32.1-introduzione)

[3.2.32.1 Input	26](#3.2.32.1-input)

[3.2.32.1 Elaborazione	26](#3.2.32.1-elaborazione)

[3.2.32.1 Output	26](#3.2.32.1-output)

[3.2.33 Approvazione o rifiuto di un invito	26](#3.2.33-approvazione-o-rifiuto-di-un-invito)

[3.2.33.1 Introduzione	26](#3.2.33.1-introduzione)

[3.2.33.1 Input	26](#3.2.33.1-input)

[3.2.33.1 Elaborazione	26](#3.2.33.1-elaborazione)

[3.2.33.1 Output	26](#3.2.33.1-output)

[3.2.34 Rimozione di membri del gruppo	26](#3.2.34-rimozione-di-membri-del-gruppo)

[3.2.34.1 Introduzione	26](#3.2.34.1-introduzione)

[3.2.34.1 Input	26](#3.2.34.1-input)

[3.2.34.1 Elaborazione	26](#3.2.34.1-elaborazione)

[3.2.34.1 Output	26](#3.2.34.1-output)

[3.2.35 Abbandono di un gruppo	26](#3.2.35-abbandono-di-un-gruppo)

[3.2.35.1 Introduzione	26](#3.2.35.1-introduzione)

[3.2.35.1 Input	26](#3.2.35.1-input)

[3.2.35.1 Elaborazione	26](#3.2.35.1-elaborazione)

[3.2.35.1 Output	26](#3.2.35.1-output)

[3.2.36 Eliminazione di un gruppo	26](#3.2.36-eliminazione-di-un-gruppo)

[3.2.36.1 Introduzione	26](#3.2.36.1-introduzione)

[3.2.36.1 Input	26](#3.2.36.1-input)

[3.2.36.1 Elaborazione	27](#3.2.36.1-elaborazione)

[3.2.36.1 Output	27](#3.2.36.1-output)

[3.2.37 Scambio di denaro tra utenti	27](#3.2.37-scambio-di-denaro-tra-utenti)

[3.2.37.1  Introduzione	27](#3.2.37.1-introduzione)

[3.2.37.2 Input	27](#3.2.37.2-input)

[3.2.37.3 Elaborazione	27](#3.2.37.3-elaborazione)

[3.2.37.4 Output	27](#3.2.37.4-output)

[3.2.38 Raccolta di denaro	27](#3.2.38-raccolta-di-denaro)

[3.2.38.1  Introduzione	27](#3.2.38.1-introduzione)

[3.2.38.2 Input	27](#3.2.38.2-input)

[3.2.38.3 Elaborazione	27](#3.2.38.3-elaborazione)

[3.2.38.4 Output	27](#3.2.38.4-output)

[3.2.39 Conto di gruppo	27](#3.2.39-conto-di-gruppo)

[3.2.39.1  Introduzione	27](#3.2.39.1-introduzione)

[3.2.39.2 Input	27](#3.2.39.2-input)

[3.2.39.3 Elaborazione	27](#3.2.39.3-elaborazione)

[3.2.39.4 Output	27](#3.2.39.4-output)

[3.2.40 Aggiunta di spesa condivisa	28](#3.2.40-aggiunta-di-spesa-condivisa)

[3.2.40.1  Introduzione	28](#3.2.40.1-introduzione)

[3.2.40.2 Input	28](#3.2.40.2-input)

[3.2.40.3 Elaborazione	28](#3.2.40.3-elaborazione)

[3.2.40.4 Output	28](#3.2.40.4-output)

[3.2.41 Eliminazione spese condivise	28](#3.2.41-eliminazione-spese-condivise)

[3.2.41.1  Introduzione	28](#3.2.41.1-introduzione)

[3.2.41.2 Input	28](#3.2.41.2-input)

[3.2.41.3 Elaborazione	28](#3.2.41.3-elaborazione)

[3.2.41.4 Output	28](#3.2.41.4-output)

[3.2.42 Riduzione delle transazioni per il passaggio di denaro	28](#3.2.42-riduzione-delle-transazioni-per-il-passaggio-di-denaro)

[3.2.42.1  Introduzione	28](#3.2.42.1-introduzione)

[3.2.42.2 Input	28](#3.2.42.2-input)

[3.2.42.3 Elaborazione	28](#3.2.42.3-elaborazione)

[3.2.42.4 Output	28](#3.2.42.4-output)

[3.2.43 Grafici di gruppo sugli andamenti	28](#3.2.43-grafici-di-gruppo-sugli-andamenti)

[3.2.43.1  Introduzione	28](#3.2.43.1-introduzione)

[3.2.43.2 Input	28](#3.2.43.2-input)

[3.2.43.3 Elaborazione	29](#3.2.43.3-elaborazione)

[3.2.43.4 Output	29](#3.2.43.4-output)

[3.2.44 Aggiunta di denaro sul conto di gruppo	29](#3.2.44-aggiunta-di-denaro-sul-conto-di-gruppo)

[3.2.44.1  Introduzione	29](#3.2.44.1-introduzione)

[3.2.44.2 Input	29](#3.2.44.2-input)

[3.2.44.3 Elaborazione	29](#3.2.44.3-elaborazione)

[3.2.44.4 Output	29](#3.2.44.4-output)

[3.2.45 Spostamenti soldi dal conto di gruppo	29](#3.2.45-spostamenti-soldi-dal-conto-di-gruppo)

[3.2.45.1  Introduzione	29](#3.2.45.1-introduzione)

[3.2.45.2 Input	29](#3.2.45.2-input)

[3.2.45.3 Elaborazione	29](#3.2.45.3-elaborazione)

[3.2.45.4 Output	29](#3.2.45.4-output)

[3.2.46 Richiesta Split	29](#3.2.46-richiesta-split)

[3.2.46.1  Introduzione	29](#3.2.46.1-introduzione)

[3.2.46.2 Input	29](#3.2.46.2-input)

[3.2.46.3 Elaborazione	29](#3.2.46.3-elaborazione)

[3.2.46.4 Output	29](#3.2.46.4-output)

[3.2.47 Calcolo debiti	29](#3.2.47-calcolo-debiti)

[3.2.47.1  Introduzione	29](#3.2.47.1-introduzione)

[3.2.47.2 Input	29](#3.2.47.2-input)

[3.2.47.3 Elaborazione	30](#3.2.47.3-elaborazione)

[3.2.47.4 Output	30](#3.2.47.4-output)

[3.2.48 Referral	30](#3.2.48-referral)

[3.2.48.1  Introduzione	30](#3.2.48.1-introduzione)

[3.2.48.2 Input	30](#3.2.48.2-input)

[3.2.48.3 Elaborazione	30](#3.2.48.3-elaborazione)

[3.2.48.4 Output	30](#3.2.48.4-output)

[3.2.49 Creazione account	30](#3.2.49-creazione-account)

[3.2.49.1 Introduzione	30](#3.2.49.1-introduzione)

[3.2.49.2 Input	30](#3.2.49.2-input)

[3.2.49.3 Elaborazione	30](#3.2.49.3-elaborazione)

[3.2.49.4 Output	30](#3.2.49.4-output)

[3.2.50 Elimina account	30](#3.2.50-elimina-account)

[3.2.50.1  Introduzione	30](#3.2.50.1-introduzione)

[3.2.50.2 Input	30](#3.2.50.2-input)

[3.2.50.3 Elaborazione	30](#3.2.50.3-elaborazione)

[3.2.50.4 Output	30](#3.2.50.4-output)

[3.2.51 Login	30](#3.2.51-login)

[3.2.51.1 Introduzione	30](#3.2.51.1-introduzione)

[3.2.51.2 Input	30](#3.2.51.2-input)

[3.2.51.3 Elaborazione	30](#3.2.51.3-elaborazione)

[3.2.51.4 Output	31](#3.2.51.4-output)

[3.2.52 Cambio password	31](#3.2.52-cambio-password)

[3.2.52.1  Introduzione	31](#3.2.52.1-introduzione)

[3.2.52.2 Input	31](#3.2.52.2-input)

[3.2.52.3 Elaborazione	31](#3.2.52.3-elaborazione)

[3.2.52.4 Output	31](#3.2.52.4-output)

[3.2.53 Lista amici	31](#3.2.53-lista-amici)

[3.2.53.1  Introduzione	31](#3.2.53.1-introduzione)

[3.2.53.2 Input	31](#3.2.53.2-input)

[3.2.53.3 Elaborazione	31](#3.2.53.3-elaborazione)

[3.2.53.4 Output	31](#3.2.53.4-output)

[3.2.54 Invio richiesta di amicizia	31](#3.2.54-invio-richiesta-di-amicizia)

[3.2.54.1 Introduzione	31](#3.2.54.1-introduzione)

[3.2.54.2 Input	31](#3.2.54.2-input)

[3.2.54.3 Elaborazione	31](#3.2.54.3-elaborazione)

[3.2.54.4 Output	31](#3.2.54.4-output)

[3.2.55 Accettare richieste di amicizia	31](#3.2.55-accettare-richieste-di-amicizia)

[3.2.55.1 Introduzione	31](#3.2.55.1-introduzione)

[3.2.55.2 Input	31](#3.2.55.2-input)

[3.2.55.3 Elaborazione	31](#3.2.55.3-elaborazione)

[3.2.55.4 Output	32](#3.2.55.4-output)

[3.2.56 Rimuovere amicizie	32](#3.2.56-rimuovere-amicizie)

[3.2.56.1 Introduzione	32](#3.2.56.1-introduzione)

[3.2.56.2 Input	32](#3.2.56.2-input)

[3.2.56.3 Elaborazione	32](#3.2.56.3-elaborazione)

[3.2.56.4 Output	32](#3.2.56.4-output)

[3.2.57 Ricerca utenti	32](#3.2.57-ricerca-utenti)

[3.2.57.1 Introduzione	32](#3.2.57.1-introduzione)

[3.2.57.2 Input	32](#3.2.57.2-input)

[3.2.57.3 Elaborazione	32](#3.2.57.3-elaborazione)

[3.2.57.4 Output	32](#3.2.57.4-output)

[3.2.58 Iscrizione a premium	32](#3.2.58-iscrizione-a-premium)

[3.2.58.1  Introduzione	32](#3.2.58.1-introduzione)

[3.2.58.2 Input	32](#3.2.58.2-input)

[3.2.58.3 Elaborazione	32](#3.2.58.3-elaborazione)

[3.2.58.4 Output	32](#3.2.58.4-output)

[3.2.59 Disdire l’abbonamento	32](#3.2.59-disdire-l’abbonamento)

[3.2.59.1  Introduzione	32](#3.2.59.1-introduzione)

[3.2.59.2 Input	32](#3.2.59.2-input)

[3.2.59.3 Elaborazione	32](#3.2.59.3-elaborazione)

[3.2.59.4 Output	33](#3.2.59.4-output)

[3.2.60 Rinnovo della versione premium	33](#3.2.60-rinnovo-della-versione-premium)

[3.2.60.1  Introduzione	33](#3.2.60.1-introduzione)

[3.2.60.2 Input	33](#3.2.60.2-input)

[3.2.60.3 Elaborazione	33](#3.2.60.3-elaborazione)

[3.2.60.4 Output	33](#3.2.60.4-output)

[3.3 Requisiti non funzionali	33](#3.3-requisiti-non-funzionali)

[3.3.1 MySql	33](#3.3.1-mysql)

[3.3.2 Frame Work interfaccia	33](#3.3.2-frame-work-interfaccia)

[3.3.3 persistenza azioni utente	33](#3.3.3-persistenza-azioni-utente)

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
* Aggiunta spese  
* Eliminazione spese  
* Grafici di gruppo sugli andamenti;  
* Riduzione delle transazioni per il passaggio di denaro;  
* Aggiunta valuta sul conto di gruppo   
* Spostamenti soldi dal conto di gruppo (Prelevo valuta)  
* Invito persone al gruppo  
* Rimozione persone dal gruppo  
* Cancellazione gruppo  
* Creazione gruppo  
* Richiesta Split (non so come scriverlo comunque quando si finalizza tutto e si generano le richieste sandMe per azzerare i debiti)  
* calcolo debiti   
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

### 

### **3.2.1 Aggiunta carta o conto**  {#3.2.1-aggiunta-carta-o-conto}

#### **3.2.1.1 Introduzione** {#3.2.1.1-introduzione}

		Consente all'utente di collegare una carta o conto al proprio account. 

#### **3.2.1.2 Input** {#3.2.1.2-input}

		dati della carta o del conto da collegare.

#### **3.2.1.3 Elaborazione** {#3.2.1.3-elaborazione}

viene inviata la richiesta alla banca di tale conto o carta aspettando un   
risconto di successo, in caso venga confermata la richiesta i dati   
della carta o conto vengono memorizzati nell'account dell’utente, in caso contrario non si fa nulla.

#### **3.2.1.4 Output** {#3.2.1.4-output}

		Registrazione conto / carta sull’account dell’utente 

### **3.2.2 Eliminazione carta o conto**  {#3.2.2-eliminazione-carta-o-conto}

#### **3.2.2.1 Introduzione** {#3.2.2.1-introduzione}

		Consente all'utente di eliminare una carta o conto al proprio account.

#### **3.2.2.2 Input** {#3.2.2.2-input}

		Carta o conto da eliminare

#### **3.2.2.3 Elaborazione** {#3.2.2.3-elaborazione}

		Nulla da elaborare

#### **3.2.2.4 Output** {#3.2.2.4-output}

		La carta o il conto viene eliminato dall’account dell’utente  
 

### **3.2.3 Deposito di denaro da Carte o Conti salvate** {#3.2.3-deposito-di-denaro-da-carte-o-conti-salvate}

#### **3.2.3.1 Introduzione** {#3.2.3.1-introduzione}

Consente all'utente di depositare denaro nel vault tramite le carte o conti 	memorizzate (precedentemente inserite).

#### **3.2.3.2 Input** {#3.2.3.2-input}

		Valore da depositare e carta o conto da cui prelevare

#### **3.2.3.3 Elaborazione** {#3.2.3.3-elaborazione}

Viene richiesta una transazione alla banca relativa al conto o carta di importo pari a quello scelto dall’utente, se la banca accetta la transazione il valore viene caricato nel vault, altrimenti operazione non effettuabile.

#### **3.2.3.4 Output**  {#3.2.3.4-output}

Caricamento del valore nel vault se operazione confermata

### **3.2.4 Deposito di denaro one time**  {#3.2.4-deposito-di-denaro-one-time}

#### **3.2.4.1 Introduzione** {#3.2.4.1-introduzione}

		Consente il deposito nel vault tramite una carta o conto inseriti al momento   
della ricarica. La carta/conto non è salvato nell'account

#### **3.2.4.2 Input** {#3.2.4.2-input}

		Valore da depositare e dati carta o conto con cui farlo.

#### **3.2.4.3 Elaborazione** {#3.2.4.3-elaborazione}

		Viene richiesta la transazione alla   
banca relativa al conto o carta e se la banca accetta la transazione il valore viene caricato nel vault, altrimenti operazione non effettuabile

#### **3.2.4.4 Output**  {#3.2.4.4-output}

		Caricamento del valore nel  vault

### **3.2.5 Prelievo importo dal vault** 	 {#3.2.5-prelievo-importo-dal-vault}

#### **3.2.5.1 Introduzione** {#3.2.5.1-introduzione}

	Consente all'utente di prelevare del denaro presente nel vault e inviarlo a una   
carta o conto tra quelle memorizzate.

#### **3.2.5.2 Input** {#3.2.5.2-input}

		Valore del denaro da prelevare e carta o conto dove inviarlo.

#### **3.2.5.3 Elaborazione** {#3.2.5.3-elaborazione}

		Viene controllato se e presente tale   
importo nel vault (altrimenti operazione non effettuabile) viene inviata una richiesta alla banca della carta o conto e in caso di conferma viene tolto il valore dal vault (altrimenti operazione non effettuabile).

#### **3.2.5.4 Output**  {#3.2.5.4-output}

Sottrazione del valore dal vault.

### 	**3.2.6 Esecuzione pagamento**  {#3.2.6-esecuzione-pagamento}

		**3.2.6.1 Introduzione**  
		Consente all'utente di eseguire pagamenti 

#### **3.2.6.2 Input** {#3.2.6.2-input}

		Dati operazione pagamento(importo, commerciante etc..)  
**3.2.6.3 Elaborazione**  
		Viene controllato che il valore richiesto dal  
pagamento sia disponibile nel vault(in caso contrario operazione non effettuabile), viene sottratto dal vault l’importo definito nel pagamento, 

#### **3.2.6.4 Output**  {#3.2.6.4-output}

Sottrazione dell’importo dal vault e conferma del pagamento, registrazione  transazione all’interno dello  storico del Vault e/o CashBook  associati

### **3.2.7 Gestione delle spese e personalizzazione delle categorie**  {#3.2.7-gestione-delle-spese-e-personalizzazione-delle-categorie}

#### **3.2.7.1 Introduzione**  {#3.2.7.1-introduzione}

Le spese possono essere gestite anche tramite la categoria a cui appartengono

#### **3.2.7.2 Input** {#3.2.7.2-input}

		Spesa

#### **3.2.7.3 Elaborazione** {#3.2.7.3-elaborazione}

Una volta eseguita la spesa l’utente può inserire la categoria a cui appartiene. Sarà poi disponibile un sommario che mostra le spese per categoria.

#### **3.2.7.4 Output** {#3.2.7.4-output}

		Aggiornamento delle spese eseguite

### **3.2.8 Storico del Vault** {#3.2.8-storico-del-vault}

#### **3.2.8.1 Introduzione**  {#3.2.8.1-introduzione}

Tutte le spese registrate sono tracciabili nell’applicazione

#### **3.2.8.2 Input** {#3.2.8.2-input}

		Movimenti

#### **3.2.8.3 Elaborazione** {#3.2.8.3-elaborazione}

Ogni volta che l’utente esegue un movimento di qualsiasi genere (deposito, spesa,...), viene registrato nello storico dell’applicazione il tipo di movimento e l’importo, disponibile nell’area personale dell’utente

#### **3.2.8.4 Output** {#3.2.8.4-output}

		Storico del Vault aggiornato

### **3.2.9 Creazione di un VirtualVault** {#3.2.9-creazione-di-un-virtualvault}

#### **3.2.9.1 Introduzione**  {#3.2.9.1-introduzione}

		Permette di creare un nuovo VirtualVault

#### **3.2.9.2 Input** {#3.2.9.2-input}

		Nome VirtualVault

#### **3.2.9.3 Elaborazione** {#3.2.9.3-elaborazione}

Viene controllato se non esiste già un VirtualVault con lo stesso nome dell'account dell’utente, altrimenti l’operazione non  è eseguibile 

#### **3.2.9.4 Output** {#3.2.9.4-output}

		Registrazione VirtualVault nell’account dell’utente  
		

### **3.2.10 Cancellazione di un VirtualVault** {#3.2.10-cancellazione-di-un-virtualvault}

#### **3.2.10.1 Introduzione** {#3.2.10.1-introduzione}

		Permette di cancellare un VirtualVault

#### **3.2.10.2 Input** {#3.2.10.2-input}

		VirtualVault

#### **3.2.10.3 Elaborazione** {#3.2.10.3-elaborazione}

		Verifica che il VirtualVault non sia quello principale (altrimenti l’operazione non   
è effettuabile), allora il saldo presente sul VirtualVault selezionato per la   
cancellazione vengono spostati in automatico nel Vault

#### **3.2.10.4 Output** {#3.2.10.4-output}

		Spostamento soldi dal VirtualVault a quello principale, VirtualVault rimosso

### **3.2.11 Spostamento saldo verso VirtualVault** {#3.2.11-spostamento-saldo-verso-virtualvault}

#### **3.2.11.1 Introduzione** {#3.2.11.1-introduzione}

		Permette di eseguire spostamenti di saldo tra il Vault e un VirtualVault

#### **3.2.11.2 Input** {#3.2.11.2-input}

		Importo da spostare, VirtualVault su cui farlo 

#### **3.2.11.3 Elaborazione** {#3.2.11.3-elaborazione}

		Viene controllato se il saldo inserito è disponibile nel Vault 

#### **3.2.11.4 Output** {#3.2.11.4-output}

L’importo viene spostato dal Vault al VirtualVault

### **3.2.12 Creazione di un obiettivo (data)** {#3.2.12-creazione-di-un-obiettivo-(data)}

#### **3.2.12.1  Introduzione** {#3.2.12.1-introduzione}

		Permette di creare un nuovo obiettivo di un VirtualVault

#### **3.2.12.2 Input** {#3.2.12.2-input}

		Valore che si vuol raggiungere, data in cui farlo (o in quanti giorni)

#### **3.2.12.3 Elaborazione** {#3.2.12.3-elaborazione}

		Calcolo dell’obiettivo 

#### **3.2.12.4 Output** {#3.2.12.4-output}

		Eliminazione del CashBook dall’account utente

### **3.2.13 Movimento automatico**  {#3.2.13-movimento-automatico}

		**3.2.13.1  Introduzione**  
		Movimento automatico di denaro del vault al virtual Vault

#### **3.2.13.2  Input** {#3.2.13.2-input}

		Automatico, valore da trasferire, Virtual Vault su cui eseguire il movimento

#### **3.2.13.3  Elaborazione** {#3.2.13.3-elaborazione}

		esecuzione automatica, sottrazione del valore dal vault e caricamento sul  
Virtual Vault selezionato

#### **3.2.13.4  Output** {#3.2.13.4-output}

		accredito sul Virtual Vault del importo

### **3.2.14 Visualizzazione info Virtual Vault** {#3.2.14-visualizzazione-info-virtual-vault}

		**3.2.14.1  Introduzione**  
		Permette di vedere lo stato del obbiettivo e il saldo del virtual vault 

#### **3.2.14.2  Input** {#3.2.14.2-input}

		Virtual vault di cui si vuole avere info

#### **3.2.14.3  Elaborazione** {#3.2.14.3-elaborazione}

		ricerca e prelievo dei dati

#### **3.2.14.4  Output** {#3.2.14.4-output}

		vari info

### **3.2.15 Creazione CashBook** {#3.2.15-creazione-cashbook}

#### **3.2.15.1  Introduzione** {#3.2.15.1-introduzione}

		Permette la creazione di un nuovo CashBook personale

#### **3.2.15.2 Input** {#3.2.15.2-input}

		Nome  CashBook

#### **3.2.15.3 Elaborazione** {#3.2.15.3-elaborazione}

Si verifica che non esista un altro CashBook con nome uguale a quello da   
creare, altrimenti l’operazione non è effettuabile

#### **3.2.15.4 Output** {#3.2.15.4-output}

		Registrazione del nuovo CashBook nell’account dell’utente

### **3.2.16 Eliminazione  CashBook** {#3.2.16-eliminazione-cashbook}

#### **3.2.16.1  Introduzione** {#3.2.16.1-introduzione}

		Permette l’eliminazione di un cashbook personale

#### **3.2.16.2 Input** {#3.2.16.2-input}

		CashBook (da eliminare)

#### **3.2.16.3 Elaborazione** {#3.2.16.3-elaborazione}

		Nessuna elaborazione

#### **3.2.16.4 Output** {#3.2.16.4-output}

		Eliminazione del CashBook dall’account utente

### **3.2.17 Aggiunta Watcher CCorrente/Carta a un CashBook** {#3.2.17-aggiunta-watcher-ccorrente/carta-a-un-cashbook}

#### **3.2.17.1  Introduzione** {#3.2.17.1-introduzione}

Permette a un cashbook di tenere conto delle spese fatte da un determinato ccorrente o carta dell’account utente

#### **3.2.17.2 Input** {#3.2.17.2-input}

		CashBook (su cui aggiungere il Watcher), CCorrente / Conto

#### **3.2.17.3 Elaborazione** {#3.2.17.3-elaborazione}

Si verifica che non esista un watcher già associato tra il ccorrente e conto allo stesso cashbook, altrimenti operazione non effettuabile

#### **3.2.17.4 Output** {#3.2.17.4-output}

		Registrazione watcher tra ccorrente e cashbook

### **3.2.18 Rimozione Watcher CCorrente/Carta da un CashBook** {#3.2.18-rimozione-watcher-ccorrente/carta-da-un-cashbook}

#### **3.2.18.1  Introduzione** {#3.2.18.1-introduzione}

		Permette la rimozione di un watcher da un cashbook

#### **3.2.18.2 Input** {#3.2.18.2-input}

		Watcher(da rimuovere)

#### **3.2.18.3 Elaborazione** {#3.2.18.3-elaborazione}

		Nessuna elaborazione

#### **3.2.18.4 Output** {#3.2.18.4-output}

		Rimozione del watcher dall’account dell’utente

### **3.2.19 Aggiunta Movimento in CashBook** {#3.2.19-aggiunta-movimento-in-cashbook}

#### **3.2.19.1  Introduzione** {#3.2.19.1-introduzione}

		Permette la registrazione di un movimento su un determinato cashbook

#### **3.2.19.2 Input** {#3.2.19.2-input}

		Data creazione, e altri dati relativi al documento di Visione.

#### **3.2.19.3 Elaborazione** {#3.2.19.3-elaborazione}

Si verifica che sia rispettato il formato e le regole date dal documento di Visione, altrimenti operazione non è effettuabile

#### **3.2.19.4 Output** {#3.2.19.4-output}

		Registrazione movimento  all’interno del cashbook

### **3.2.20 Rimozione Movimento in CashBook** {#3.2.20-rimozione-movimento-in-cashbook}

#### **3.2.20.1  Introduzione** {#3.2.20.1-introduzione}

		Permette la rimozione di un movimento da un determinato cashbook

#### **3.2.20.2 Input** {#3.2.20.2-input}

		Movimento

#### **3.2.20.3 Elaborazione** {#3.2.20.3-elaborazione}

		Nessuna elaborazione

#### **3.2.20.4 Output** {#3.2.20.4-output}

		Rimozione movimento dal cashbook

### **3.2.21 Modifica Movimento di un CashBook** {#3.2.21-modifica-movimento-di-un-cashbook}

#### **3.2.21.1  Introduzione** {#3.2.21.1-introduzione}

		Permette la modifica di un movimento di un determinato cashbook

#### **3.2.21.2 Input** {#3.2.21.2-input}

		Movimento (da modificare),  nuovi dati

#### **3.2.21.3 Elaborazione** {#3.2.21.3-elaborazione}

Si verifica che i nuovi dati rispettino il formato e le regole date dal documento di Visione, altrimenti operazione non è effettuabile

#### **3.2.21.4 Output** {#3.2.21.4-output}

		Modifica dei dati del movimento

### **3.2.22 Storico**  {#3.2.22-storico}

#### **3.2.22.1  Introduzione** {#3.2.22.1-introduzione}

		Permette di visualizzare lo storico dei movimenti 

#### **3.2.22.2 Input** {#3.2.22.2-input}

CashBook di cui si vuole vedere lo storico 

#### **3.2.22.3 Elaborazione** {#3.2.22.3-elaborazione}

Creazione della lista dei movimenti 

#### **3.2.22.4 Output** {#3.2.22.4-output}

		Lista dei movimenti (storico)

### **3.2.23 Sommario** {#3.2.23-sommario}

#### **3.2.23.1  Introduzione** {#3.2.23.1-introduzione}

		Permette di visualizzare entrate e uscite in un certo periodo 

#### **3.2.23.2 Input** {#3.2.23.2-input}

		CashBook di cui si vuole il sommario, periodo di tempo (sett, mes, ann)

#### **3.2.23.3 Elaborazione** {#3.2.23.3-elaborazione}

Conteggio entrate uscite e creazione sommario 

#### **3.2.23.4 Output** {#3.2.23.4-output}

		Sommario

### **3.2.24 Grafici (Premium)** {#3.2.24-grafici-(premium)}

#### **3.2.24.1  Introduzione** {#3.2.24.1-introduzione}

		Permette di visualizzare grafici che descrivono l’andamento del CashBook ovvero il valore del cashbook giorno per giorno 

#### **3.2.24.2 Input** {#3.2.24.2-input}

		Azione sull’interfaccia, account premiumCashBook

#### **3.2.24.3 Elaborazione** {#3.2.24.3-elaborazione}

Creazione grafico 

#### **3.2.24.4 Output** {#3.2.24.4-output}

		Grafico 

### **3.2.25 Invio denaro**  {#3.2.25-invio-denaro}

#### **3.2.25.1  Introduzione** {#3.2.25.1-introduzione}

		Permette di inviare denaro al vault di amici o utenti usando il credito del  
 	proprio vault 

#### **3.2.25.2 Input** {#3.2.25.2-input}

		Utente/amico a cui inviare, importo

#### **3.2.25.3 Elaborazione** {#3.2.25.3-elaborazione}

Se il saldo del vault è sufficiente viene detratto e iviato (l’utente ricevente lo vedra acreditato sul suo vault) nulla altrimenti

#### **3.2.25.4 Output** {#3.2.25.4-output}

		Importo detratto ed inviato 

### **3.2.26 Ricezione importo** {#3.2.26-ricezione-importo}

#### **3.2.26.1  Introduzione** {#3.2.26.1-introduzione}

		Permette di ricevere denaro da altri utenti 

#### **3.2.26.2 Input** {#3.2.26.2-input}

		Importo ricevuto 

#### **3.2.26.3 Elaborazione** {#3.2.26.3-elaborazione}

caricamento dell'importo nel vault 

#### **3.2.26.4 Output** {#3.2.26.4-output}

		nuovo credito vault 

### **3.2.27 Invio Richiesta importo**  {#3.2.27-invio-richiesta-importo}

#### **3.2.27.1  Introduzione** {#3.2.27.1-introduzione}

		Permette di richiedere un importo ad un amico 

#### **3.2.27.2 Input** {#3.2.27.2-input}

		Amico, importo da richiedere

#### **3.2.27.3 Elaborazione** {#3.2.27.3-elaborazione}

Invio richiesta all'amico, se accettata ricarico importo sul vault del richiedente, se rifiutata notificare il richiedente 

#### **3.2.27.4 Output** {#3.2.27.4-output}

		nuovo valore sul vault o notifica

### **3.2.28 Ricezione richiesta importo**  {#3.2.28-ricezione-richiesta-importo}

#### **3.2.28.1  Introduzione** {#3.2.28.1-introduzione}

		Permette di ricevere richieste, da accettare o rifiutare

#### **3.2.28.2 Input** {#3.2.28.2-input}

		Richiesta inviata da un amico 

#### **3.2.28.3 Elaborazione** {#3.2.28.3-elaborazione}

		Se si rifiuta non succede nulla e viene inviata una notifica al richiedente, se si  
accetta se si ha tale importo nel vault viene detratto ed inviato, se non lo si ha  
come se venisse rifiutata

#### **3.2.28.4 Output** {#3.2.28.4-output}

		Invio dell’ importo detrazione dell'importo o della notifica 

### **3.2.29  Storico transazioni** {#3.2.29-storico-transazioni}

#### **3.2.29.1  Introduzione** {#3.2.29.1-introduzione}

		Permette di avere una lista delle transazioni tra due utenti 

#### **3.2.29.2 Input** {#3.2.29.2-input}

		I due utenti 

#### **3.2.29.3 Elaborazione** {#3.2.29.3-elaborazione}

Generazione lista transazioni( ricezione invio annullamento)

#### **3.2.29.4 Output** {#3.2.29.4-output}

		lista transazioni

### **3.2.30 Generazione notifica**  {#3.2.30-generazione-notifica}

#### **3.2.30.1  Introduzione** {#3.2.30.1-introduzione}

		Permette di generare una notifica di ricezione o richiesta importo 

#### **3.2.30.2 Input** {#3.2.30.2-input}

		Utente che la invia, utente a cui inviarla, natura della notifica

#### **3.2.30.3 Elaborazione** {#3.2.30.3-elaborazione}

A seconda della natura viene generata una notifica specifica dell'evento al utente specificato 

#### **3.2.30.4 Output** {#3.2.30.4-output}

		Notifica

### 

### **3.2.31 Creazione di un gruppo** {#3.2.31-creazione-di-un-gruppo}

#### **3.2.31.1 Introduzione** {#3.2.31.1-introduzione}

		Un utente vuole creare un gruppo con la possibilità di aggiungere in seguito   
altri utenti già registrati al sistema

#### **3.2.31.1 Input** {#3.2.31.1-input}

		Nome del gruppo

#### **3.2.31.1 Elaborazione** {#3.2.31.1-elaborazione}

		L’utente amministratore darà un nome al gruppo 

#### **3.2.31.1 Output** {#3.2.31.1-output}

		Gruppo per condividere le spese

### **3.2.32 Aggiunta di membri al gruppo** {#3.2.32-aggiunta-di-membri-al-gruppo}

#### **3.2.32.1 Introduzione** {#3.2.32.1-introduzione}

		L'amministratore del gruppo vuole aggiungere nuovi membri al gruppo

#### **3.2.32.1 Input** {#3.2.32.1-input}

		utente da aggiungere

#### **3.2.32.1 Elaborazione** {#3.2.32.1-elaborazione}

		L’utente amministratore seleziona l’utente da invitare al gruppo, che una volta   
		ricevuto l’invito potrà decidere se accettare o declinare la proposta di   
partecipazione

#### **3.2.32.1 Output** {#3.2.32.1-output}

		Nuovo partecipante nel gruppo

### **3.2.33 Approvazione o rifiuto di un invito** {#3.2.33-approvazione-o-rifiuto-di-un-invito}

#### **3.2.33.1 Introduzione** {#3.2.33.1-introduzione}

		Un utente ha ricevuto un invito a partecipare ad un gruppo e vuole accettare  
		oppure declinare la proposta

#### **3.2.33.1 Input** {#3.2.33.1-input}

		L’utente comunica al sistema la sua scelta tramite interfaccia grafica

#### **3.2.33.1 Elaborazione** {#3.2.33.1-elaborazione}

		Il sistema approva la richiesta ed aggiunge l’utente ai partecipanti  
		oppure invia messaggio di declinazione all'amministratore

#### **3.2.33.1 Output** {#3.2.33.1-output}

		L’utente è membro del gruppo oppure l’amministratore viene informato  
		della volontà di non partecipare

### **3.2.34 Rimozione di membri del gruppo** {#3.2.34-rimozione-di-membri-del-gruppo}

#### **3.2.34.1 Introduzione** {#3.2.34.1-introduzione}

		L’amministratore vuole rimuovere un utente dal gruppo

#### **3.2.34.1 Input** {#3.2.34.1-input}

		Username dell’utente da rimuovere

#### **3.2.34.1 Elaborazione** {#3.2.34.1-elaborazione}

		L’utente da rimuovere non farà più parte del gruppo, eventuali spese   
incomplete a carico dell’utente rimosso verranno annullate

#### **3.2.34.1 Output** {#3.2.34.1-output}

		L’utente non fa più parte del gruppo

### **3.2.35 Abbandono di un gruppo** {#3.2.35-abbandono-di-un-gruppo}

#### **3.2.35.1 Introduzione** {#3.2.35.1-introduzione}

		Un utente vuole rimuoversi da un gruppo

#### **3.2.35.1 Input** {#3.2.35.1-input}

		Gruppo da cui rimuoversi

#### **3.2.35.1 Elaborazione** {#3.2.35.1-elaborazione}

		Tutte le spese aperte vengono annullate

#### **3.2.35.1 Output** {#3.2.35.1-output}

		L’utente non fa più parte del gruppo

### **3.2.36 Eliminazione di un gruppo** {#3.2.36-eliminazione-di-un-gruppo}

#### **3.2.36.1 Introduzione** {#3.2.36.1-introduzione}

		Un utente vuole eliminare un gruppo di cui è amministratore

#### **3.2.36.1 Input** {#3.2.36.1-input}

		Richiesta di eliminazione di un gruppo

#### **3.2.36.1 Elaborazione** {#3.2.36.1-elaborazione}

		Il gruppo viene eliminato e tutte le richieste di spese non completate vengono   
eliminate ed i fondi restituiti ai membri partecipanti

#### **3.2.36.1 Output** {#3.2.36.1-output}

		Nessun utente partecipante farà più parte di quel gruppo

### **3.2.37 Scambio di denaro tra utenti** {#3.2.37-scambio-di-denaro-tra-utenti}

#### **3.2.37.1  Introduzione** {#3.2.37.1-introduzione}

Transazioni eseguite per passare una somma di denaro ad un altro utente

#### **3.2.37.2 Input** {#3.2.37.2-input}

		Accordo o richiesta da parte di un utente di una certa somma di denaro

#### **3.2.37.3 Elaborazione** {#3.2.37.3-elaborazione}

Verrà richiesta la selezione di un utente, che sarà l’utente a cui spetta la cifra di denaro. Una volta inserito l’importo verrà richiesta una causale facoltativa. I soldi da inviare verranno prelevati dal Vault

#### **3.2.37.4 Output** {#3.2.37.4-output}

		Invio della somma di denaro all’utente destinatario

### **3.2.38 Raccolta di denaro** {#3.2.38-raccolta-di-denaro}

#### **3.2.38.1  Introduzione** {#3.2.38.1-introduzione}

Un gruppo ha la possibilità di raccogliere denaro per far eseguire poi una spesa all’amministratore. Ogni membro del gruppo potrà partecipare aggiungendo la propria quota derivante da una suddivisione in parti uguali dell'obiettivo specificato.

#### **3.2.38.2 Input** {#3.2.38.2-input}

		Inserimento da parte di un utente di un gruppo di una raccolta collettiva

#### **3.2.38.3 Elaborazione** {#3.2.38.3-elaborazione}

L’applicazione, presa in input l’ammontare della raccolta, dividerà equamente il denaro in quote da pagare per i membri del gruppo. Ogni volta che un membro del gruppo pagherà la sua parte, verrà detratta automaticamente dalla spesa totale

#### **3.2.38.4 Output** {#3.2.38.4-output}

		Raggiunta la somma di denaro necessaria, l’applicazione notificherà il   
raggiungimento dell’obiettivo e trasferirà i soldi sul conto del proprietario del gruppo, che procederà ad utilizzare il denaro per la propria spesa

### **3.2.39 Conto di gruppo** {#3.2.39-conto-di-gruppo}

#### **3.2.39.1  Introduzione** {#3.2.39.1-introduzione}

Funzionalità dei gruppi che permette di avere una sorta di conto condiviso

#### **3.2.39.2 Input** {#3.2.39.2-input}

		Creazione di un conto di gruppo

#### **3.2.39.3 Elaborazione** {#3.2.39.3-elaborazione}

Ogni utente facente parte del gruppo potrà eseguire delle transazioni verso questo conto. Tutto il denaro delle transazioni verrà sommato visualizzato in una schermata apposita. 

#### **3.2.39.4 Output** {#3.2.39.4-output}

		Conto condiviso

### **3.2.40 Aggiunta di spesa condivisa** {#3.2.40-aggiunta-di-spesa-condivisa}

#### **3.2.40.1  Introduzione** {#3.2.40.1-introduzione}

Possibilità di aggiungere una spesa comune per tutti i membri del gruppo

#### **3.2.40.2 Input** {#3.2.40.2-input}

		Aggiunta di una spesa

#### **3.2.40.3 Elaborazione** {#3.2.40.3-elaborazione}

Il proprietario del gruppo aggiunge una spesa inserendo: nome, tipo di spesa, una descrizione facoltativa e un importo

#### **3.2.40.4 Output** {#3.2.40.4-output}

		Spesa comune a tutti i membri del gruppo

### **3.2.41 Eliminazione spese condivise** {#3.2.41-eliminazione-spese-condivise}

#### **3.2.41.1  Introduzione** {#3.2.41.1-introduzione}

Possibilità di eliminare una spesa comune per tutti i membri del gruppo, o perché non più in programma o perché conclusa

#### **3.2.41.2 Input** {#3.2.41.2-input}

		Decisione di eliminare una spesa

#### **3.2.41.3 Elaborazione** {#3.2.41.3-elaborazione}

Il proprietario del gruppo avrà accesso ad una lista dove sono presenti le spese del gruppo, in corso e non. Premendo su una spesa avrà la possibilità di rimuoverla indicando anche una motivazione se necessaria. Una volta rimossa verranno notificati tutti i partecipanti della spesa

#### **3.2.41.4 Output** {#3.2.41.4-output}

		Eliminazione di una spesa

### **3.2.42 Riduzione delle transazioni per il passaggio di denaro** {#3.2.42-riduzione-delle-transazioni-per-il-passaggio-di-denaro}

#### **3.2.42.1  Introduzione** {#3.2.42.1-introduzione}

Funzionalità che permette di scambiare denaro tra utenti nella maniera più efficiente possibile. Utile quando si devono dei soldi a più utenti ad esempio

#### **3.2.42.2 Input** {#3.2.42.2-input}

		Scambio di denaro tra più utenti

#### **3.2.42.3 Elaborazione** {#3.2.42.3-elaborazione}

Valutando tutte le possibili transazioni che un utente deve eseguire per scambiare denaro con un altro utente, l’applicazione renderà nota la serie di transazioni che permetterà di eseguirne meno

#### **3.2.42.4 Output** {#3.2.42.4-output}

		Invio di denaro

### **3.2.43 Grafici di gruppo sugli andamenti** {#3.2.43-grafici-di-gruppo-sugli-andamenti}

#### **3.2.43.1  Introduzione** {#3.2.43.1-introduzione}

Schermata che mette a disposizione dei grafici che mostrano: spese del gruppo, variazioni del conto di gruppo, andamenti sui risparmi e altre funzionalità

#### **3.2.43.2 Input** {#3.2.43.2-input}

		Movimenti da parte degli utenti di un gruppo

#### **3.2.43.3 Elaborazione** {#3.2.43.3-elaborazione}

Ogni movimento che gli utenti di un gruppo fanno viene registrato e segnalato all’interno dell’applicazione, che ogni volta aggiornerà il/i grafici che riguardano la categoria del movimento

#### **3.2.43.4 Output** {#3.2.43.4-output}

		Grafici aggiornati

### **3.2.44 Aggiunta di denaro sul conto di gruppo** {#3.2.44-aggiunta-di-denaro-sul-conto-di-gruppo}

#### **3.2.44.1  Introduzione** {#3.2.44.1-introduzione}

Ogni utente che partecipa ad un gruppo può aggiungere fondi al conto di gruppo

#### **3.2.44.2 Input** {#3.2.44.2-input}

		Invio di denaro verso il conto di gruppo

#### **3.2.44.3 Elaborazione** {#3.2.44.3-elaborazione}

Un utente partecipante accede al conto di gruppo e tramite una schermata può inviare denaro presente nel proprio vault al conto di gruppo, inserendo importo e tutti i dati di sicurezza necessari

#### **3.2.44.4 Output** {#3.2.44.4-output}

		Aggiornamento del conto di gruppo

### **3.2.45 Spostamenti soldi dal conto di gruppo** {#3.2.45-spostamenti-soldi-dal-conto-di-gruppo}

#### **3.2.45.1  Introduzione** {#3.2.45.1-introduzione}

		Prelievo del denaro dal conto di gruppo al vault personale

#### **3.2.45.2 Input** {#3.2.45.2-input}

		Prelievo del denaro

#### **3.2.45.3 Elaborazione** {#3.2.45.3-elaborazione}

Accendendo al conto di gruppo da proprietario si ha la possibilità di prelevare il denaro presente. I soldi sul conto possono essere prelevati anche in parte. Una volta avviato il prelievo, terminata la transazione, i soldi verranno caricati sul Vault personale, vincolati però ad essere utilizzati per una spesa di gruppo

#### **3.2.45.4 Output** {#3.2.45.4-output}

		Aggiunta del denaro al Vault personale

### **3.2.46 Richiesta Split** {#3.2.46-richiesta-split}

#### **3.2.46.1  Introduzione** {#3.2.46.1-introduzione}

		permette di generare le richieste di split agli utenti per azzerare i debiti 

#### **3.2.46.2 Input** {#3.2.46.2-input}

		gruppo su cui farlo

#### **3.2.46.3 Elaborazione** {#3.2.46.3-elaborazione}

vengono generate le richieste per l’azzeramento dei debiti tra utenti 

#### **3.2.46.4 Output** {#3.2.46.4-output}

		invio richieste agli utenti 

### **3.2.47 Calcolo debiti** {#3.2.47-calcolo-debiti}

#### **3.2.47.1  Introduzione** {#3.2.47.1-introduzione}

		Calcolo dei debiti degli utenti di un gruppo

#### **3.2.47.2 Input** {#3.2.47.2-input}

		Prestito denaro

#### **3.2.47.3 Elaborazione** {#3.2.47.3-elaborazione}

Una volta che un utente appartenente ad un gruppo riceve denaro in prestito da un altro utente appartenente allo stesso gruppo, l’applicazione aggiorna lo stato dei debiti aggiungendo la somma prestata

#### **3.2.47.4 Output** {#3.2.47.4-output}

		Aggiornamento del debito dell’utente

### **3.2.48 Referral** {#3.2.48-referral}

#### **3.2.48.1  Introduzione** {#3.2.48.1-introduzione}

#### **3.2.48.2 Input** {#3.2.48.2-input}

#### **3.2.48.3 Elaborazione** {#3.2.48.3-elaborazione}

#### **3.2.48.4 Output** {#3.2.48.4-output}

### **3.2.49 Creazione account** {#3.2.49-creazione-account}

#### **3.2.49.1 Introduzione** {#3.2.49.1-introduzione}

Consente la creazione di un nuovo Utente sulla piattaforma

#### **3.2.49.2 Input** {#3.2.49.2-input}

Data corrente al momento della creazione, nome utente, email e password

#### **3.2.49.3 Elaborazione** {#3.2.49.3-elaborazione}

Per prima cosa si verifica che le informazioni email e/o nome utente non siano già assegnati ad altri Utenti già registrati. Se questo è verificato positivamente allora si potrà creare il nuovo Utente assegnandogli un codice univoco. Al nuovo account si applicano le regole definite al punto **2.2.UTENZA** in modo da inizializzare le funzionalità basi e renderlo pronto all’uso

#### **3.2.49.4 Output** {#3.2.49.4-output}

Nuovo Account registrato con i parametri definiti e convalidati

### **3.2.50 Elimina account** {#3.2.50-elimina-account}

#### **3.2.50.1  Introduzione** {#3.2.50.1-introduzione}

		Permette di eliminare l’account

#### **3.2.50.2 Input** {#3.2.50.2-input}

		richiesta eliminazione, conto su cui inviare 

#### **3.2.50.3 Elaborazione** {#3.2.50.3-elaborazione}

Prima del eliminazione vengono automaticamente raggruppati tutto l’ammontare di denaro nel vault ed inviati a una carta o conto selezionato, con la successiva eliminazione 

#### **3.2.50.4 Output** {#3.2.50.4-output}

		eliminazione account 

### **3.2.51 Login** {#3.2.51-login}

#### **3.2.51.1 Introduzione** {#3.2.51.1-introduzione}

Consente all’utente di accedere al proprio account.

#### **3.2.51.2 Input** {#3.2.51.2-input}

Nome utente/email e password.

#### **3.2.51.3 Elaborazione** {#3.2.51.3-elaborazione}

Una volta inserite il nome utente/email e la password, verrà controllato all’interno di un database se i dati inseriti sono associati a un account presente o meno. Nel caso di errore nell’inserimento password di ha la possibilità di recuperarla

#### **3.2.51.4 Output** {#3.2.51.4-output}

Login effettuato e sessione associata 

### **3.2.52 Cambio password** {#3.2.52-cambio-password}

#### **3.2.52.1  Introduzione** {#3.2.52.1-introduzione}

		Permette di cambiare password

#### **3.2.52.2 Input** {#3.2.52.2-input}

		vecchia password, nuova password

#### **3.2.52.3 Elaborazione** {#3.2.52.3-elaborazione}

controlli di sicurezza e in caso conferma modifica e cambio password

#### **3.2.52.4 Output** {#3.2.52.4-output}

		nuova password impostata

### **3.2.53 Lista amici**  {#3.2.53-lista-amici}

#### **3.2.53.1  Introduzione** {#3.2.53.1-introduzione}

		Permette di avere una lista amici 

#### **3.2.53.2 Input** {#3.2.53.2-input}

		Utente che richiede la lista

#### **3.2.53.3 Elaborazione** {#3.2.53.3-elaborazione}

generazione della lista di amici dell'utente

#### **3.2.53.4 Output** {#3.2.53.4-output}

		Lista

### **3.2.54 Invio richiesta di amicizia**  {#3.2.54-invio-richiesta-di-amicizia}

#### **3.2.54.1 Introduzione** {#3.2.54.1-introduzione}

Permette di richiedere l'amicizia ad un utente

#### **3.2.54.2 Input** {#3.2.54.2-input}

Utente inviante, utente ricevente

#### **3.2.54.3 Elaborazione** {#3.2.54.3-elaborazione}

viene inviata una richiesta all'utente ricevente, se accetta vicendevolmente i due utenti appariranno nella lista del un l’altro, altrimenti se rifiuta non accade nulla

#### **3.2.54.4 Output** {#3.2.54.4-output}

Aggiunta dell’utente (il quale ha accettato la richiesta) nella lista amici dell’utente che ha inviato la richiesta, e viceversa.

### **3.2.55 Accettare richieste di amicizia** {#3.2.55-accettare-richieste-di-amicizia}

#### **3.2.55.1 Introduzione** {#3.2.55.1-introduzione}

Consente all'utente di accettare una richiesta di amicizia effettuata da un altro utente

#### **3.2.55.2 Input** {#3.2.55.2-input}

Richiesta di amicizia ricevuta

#### **3.2.55.3 Elaborazione** {#3.2.55.3-elaborazione}

		L’utente approva la richiesta di amicizia, L'utente rifiuta non succede nulla

#### **3.2.55.4 Output** {#3.2.55.4-output}

Aggiunta dell’utente (il quale ha inviato la richiesta) nella lista amici dell’utente che ha accettato la richiesta, e viceversa.

### **3.2.56 Rimuovere amicizie** {#3.2.56-rimuovere-amicizie}

#### **3.2.56.1 Introduzione** {#3.2.56.1-introduzione}

Consente di rimuovere un utente dalla lista amici.

#### **3.2.56.2 Input** {#3.2.56.2-input}

Utente da rimuovere

#### **3.2.56.3 Elaborazione** {#3.2.56.3-elaborazione}

Si verifica che l’amico da rimuovere sia un’amico dell’utente che sta rimuovendo, altrimenti operazione non effettuabile

#### **3.2.56.4 Output** {#3.2.56.4-output}

Rimozione amicizia da entrambi gli utenti

### **3.2.57 Ricerca utenti** {#3.2.57-ricerca-utenti}

#### **3.2.57.1 Introduzione** {#3.2.57.1-introduzione}

Requisito che soddisfa la necessità dell’utente di ricercare un altro utente registrato all’applicazione.

#### **3.2.57.2 Input** {#3.2.57.2-input}

Username di un utente.

#### **3.2.57.3 Elaborazione** {#3.2.57.3-elaborazione}

L’utente avvia una ricerca tramite username, se non trova alcuna corrispondenza con lo username fornito allora la ricerca non è andata a buon fine

#### **3.2.57.4 Output** {#3.2.57.4-output}

Utente ricercato

### **3.2.58 Iscrizione a premium** {#3.2.58-iscrizione-a-premium}

#### **3.2.58.1  Introduzione** {#3.2.58.1-introduzione}

Permette all’utente di passare dalla versione base dell’applicazione ad una versione avanzata

#### **3.2.58.2 Input** {#3.2.58.2-input}

		Decisione dell’utente

#### **3.2.58.3 Elaborazione** {#3.2.58.3-elaborazione}

Prelievo del denaro da un conto per pagare l’abbonamento

#### **3.2.58.4 Output** {#3.2.58.4-output}

		Funzionalità aggiuntive per l’utente

### **3.2.59 Disdire l’abbonamento** {#3.2.59-disdire-l’abbonamento}

#### **3.2.59.1  Introduzione** {#3.2.59.1-introduzione}

Permette all’utente di disdire l’abbonamento alla versione premium

#### **3.2.59.2 Input** {#3.2.59.2-input}

		Decisione dell’utente di non proseguire con il piano avanzato

#### **3.2.59.3 Elaborazione** {#3.2.59.3-elaborazione}

Annullamento dell’iscrizione da parte dell’utente, con relativa motivazione facoltativa

#### **3.2.59.4 Output** {#3.2.59.4-output}

		Account base

### **3.2.60 Rinnovo della versione premium** {#3.2.60-rinnovo-della-versione-premium}

#### **3.2.60.1  Introduzione** {#3.2.60.1-introduzione}

Con cadenza annuale/mensile, verrà rinnovato l’abbonamento, a meno che l’utente non lo abbia disdetto prima del rinnovo

#### **3.2.60.2 Input** {#3.2.60.2-input}

		Scadenza dell’abbonamento

#### **3.2.60.3 Elaborazione** {#3.2.60.3-elaborazione}

Prelievo del denaro da un conto per rinnovare l’abbonamento. Nel caso in cui però l’utente abbia disdetto l’abbonamento, non verrà accreditata alcuna cifra

#### **3.2.60.4 Output** {#3.2.60.4-output}

		Rinnovo dell’abbonamento

## **3.3 Requisiti non funzionali** {#3.3-requisiti-non-funzionali}

### **3.3.1 MySql** {#3.3.1-mysql}

	utilizzo di MySql per la persistenza dei dati 

### **3.3.2 Frame Work interfaccia** {#3.3.2-frame-work-interfaccia}

	Utilizzo di “nome” per la creazione dell'interfaccia grafica 

### **3.3.3 persistenza azioni utente** {#3.3.3-persistenza-azioni-utente}

	Ogni azione compiuta da un utente deve essere elaborata, anche con dati riferiti ad   
altri utenti nel caso dei gruppi,  e resa persistente sul DB solo dal lato client cosi da   
non aver bisogno di un lato server 

#### 	

	

## 	

