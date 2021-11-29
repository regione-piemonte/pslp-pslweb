# Prodotto 
PSLP - PSLWEB

# Descrizione del prodotto
Questa componente è una web application che segue il paradigma "Single Page Application (SPA)", espone servizi REST alla componente PSLWCL (Angular) e si connette al DB per le operazioni CRUD.

# Configurazioni iniziali
Adattare i file di properties nella directory buildfiles alla propria configurazione. Configurare il datasource con i dati del DB che si intende utilizzare.

# Getting Started
Una volta prelevata e portata in locale dal repository la componente ("git clone"), procedere con la modifica dei file di configurazione in base al proprio ambiente di deploy e quindi procedere al build.

# Prerequisiti di sistema
E' necessario prima predisporre il DB Schema utilizzato da questa componente, e popolarlo con i dati iniziali.
Nella directory "lib" sono disponibili le librerie sviluppate da CSI e rese disponibili con le licenze indicate nel BOM.csv .
Inoltre è necessario avere a disposizione la libreria "weblogic-client-3.0.0.jar" di Oracle (non rilasciabile).

# Installazione
Installare il file "ear" generato con il build sul proprio ambiente JBoss.

# Versioning
Per il versionamento del software si usa la tecnica Semantic Versioning (http://semver.org).

# Authors
Fare riferimento a quanto riportato nel file AUTHORS.txt.

# Copyrights
© Copyright Regione Piemonte – 2021
© Copyright CSI-Piemonte – 2021
Questo stesso elenco dei titolari del software è anche riportato in Copyrights.txt .

# License
Il prodotto software è sottoposto alla licenza EUPL-1.2 o versioni successive.
SPDX-License-Identifier: EUPL-1.2-or-later
Vedere il file EUPL v1_2 IT-LICENSE.txt per i dettagli.
