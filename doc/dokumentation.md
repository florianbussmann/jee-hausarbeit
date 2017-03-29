# Semesteraufgabe: Java EE Architektur SS 2017

## Kurzbeschreibung der Anwendung

Die entwickelte Softwarelösung orientiert sich an der Model 2-Architektur. Die Komponenten der Anwendung lassen sich demnach unterschiedlichen Schichten zuordnen.

Die erste Schicht behandelt die Darstellung der Inhalte und wird daher im folgenden als Präsentationsschicht bezeichnet. Zu ihr zählen die Views welche im Verzeichnis src/main/webapp/ liegen.

Die Schicht der Geschäftslogik hingegen beschäftigt sich mit der konkreten Implementierung jeglicher Funktionalitäten zur Realisierung der User-Stories. Zu ihr zählen daher die Controller- und Request-Komponenten.

Die letzte Schicht beschäftigt sich mit der Speicherung der Daten und wird daher als Persistenzschicht bezeichnet. Zu ihr zählen die Datenmodelle und die Service-Komponenten, welche die Historisierung in H2 realisieren.

### Tabelle 1: Architektur der Anwendung

| Anwendungsschicht | Beschreibung                                                  |
| ----------------- | ------------------------------------------------------------- |
| Präsentation      | Views für die Abbildung der User-Stories                      |
| Geschäftslogik    | Steuerung der Präsentation und Abbildung von Funktionalitäten |
| Persistenz        | Datenmodell und Historisierung in H2                          |

## Umsetzung der User-Stories

> Liste aller beteiligten Komponenten und Klassen, sowie deren Aufgaben und Zugehörigkeit der Anwendungsschicht.
> Kurze Beschreibung der Schritte, die ein Nutzer für die Anwendung der Story ausführen muss.

### 1. Veranstaltung anlegen

| Komponente        | Aufgabe                   | Anwendungsschicht |
| ----------------- | ------------------------- | ----------------: |
| createEvent.jsf   | Darstellung des Formulars |      Präsentation |
| EventBean.java    | Steuerung des Formulars   |    Geschäftslogik |
| EventService.java | Historisierung            |        Persistenz |

> Der Manager wählt nach dem Login zunächst im linken Navigationsbereich den Eintrag `Veranstaltung anlegen` aus. Danach erscheint ein Formular in dem die nötigen Angaben zum Erstellen einer Veranstaltung abgefragt werden. Ist der Manager fertig mit der Eingabe der benötigten Daten, kann die Veranstaltung über den gleichnamigen Button erstellt werden.

### 2. Veranstaltung veröffentlichen

| Komponente        | Aufgabe                                                    | Anwendungsschicht |
| ----------------- | ---------------------------------------------------------- | ----------------: |
| publishEvents.jsf | Auflistung der noch nicht veröffentlichten Veranstaltungen |      Präsentation |
| EventService.java | Durchführen der Veröffentlichung inklusive Historisierung  |        Persistenz |

> Der Manager wählt nach dem Login zunächst im linken Navigationsbereich den Eintrag `Veranstaltung veröffentlichen` aus. Danach erscheint eine Liste der noch nicht veröffentlichten Veranstaltungen. Hier hat der Manager die Option eine Veranstaltung aus der Liste direkt zu veröffentlichen. Alternativ hierzu kann er sich auch zunächst die Details anzeigen lassen um vorher noch noch einmal die eingegebenen Daten näher zu kontrollieren und ggf. zu bearbeiten.

### 3. Veranstaltung bearbeiten

### 4. Veranstaltung suchen

### 5. Veranstaltung ansehen

### 6. Ticketreservierung

### 7. Reservierungsbestätätigung

### 8. Reservierungsübersicht

### 9. Noch reservierbare Tickets

## Sprint-Backlog

> mit allen erledigten Tasks und der Person, die den Task Umgesetzt haben. (Task, Beschreibung, Story, Entwickler)

## Datenmodell

### Klassendiagramm

### Beschreibung der Datenbankstruktur
