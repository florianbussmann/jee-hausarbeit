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
> Kurze Beschreibung der Schritte, die ein Nutzer für die Anwendung der Story ausführen muss.

### 1. Veranstaltung anlegen

| Komponente               | Aufgabe                                              | Anwendungsschicht |
| ------------------------ | ---------------------------------------------------- | ----------------: |
| createEvent.xhtml        | Darstellung des Formulars                            |      Präsentation |
| CreateEventRequest.java  | Steuerung des Formulars                              |    Geschäftslogik |
| SessionContext.java      | Authentifizierung                                    |    Geschäftslogik |
| ManagementOperation.java | Autorisierung: Ist der angemeldete User ein Manager? |    Geschäftslogik |
| EventService.java        | Historisierung                                       |        Persistenz |
| Event.java               | Datenmodell                                          |        Persistenz |

> Der Manager wählt nach dem Login zunächst im linken Navigationsbereich den Eintrag `Veranstaltung anlegen` aus. Danach erscheint ein Formular in dem die nötigen Angaben zum Erstellen einer Veranstaltung abgefragt werden. Ist der Manager fertig mit der Eingabe der benötigten Daten, kann die Veranstaltung über den gleichnamigen Button erstellt werden.

### 2. Veranstaltung veröffentlichen

| Komponente               | Aufgabe                                                       | Anwendungsschicht |
| ------------------------ | ------------------------------------------------------------- | ----------------: |
| publishEvents.xhtml      | Auflistung seiner noch nicht veröffentlichten Veranstaltungen |      Präsentation |
| SessionContext.java      | Authentifizierung                                             |    Geschäftslogik |
| ManagementOperation.java | Autorisierung: Ist der angemeldete User ein Manager?          |    Geschäftslogik |
| EventService.java        | Durchführen der Veröffentlichung inklusive Historisierung     |        Persistenz |
| Event.java               | Datenmodell                                                   |        Persistenz |

> Der Manager wählt nach dem Login zunächst im linken Navigationsbereich den Eintrag `Veranstaltung veröffentlichen` aus. Danach erscheint eine Liste der noch nicht veröffentlichten Veranstaltungen. Hier hat der Manager die Option eine Veranstaltung aus der Liste direkt zu veröffentlichen. Alternativ hierzu kann er sich auch zunächst die Details anzeigen lassen um vorher noch noch einmal die eingegebenen Daten näher zu kontrollieren und ggf. zu bearbeiten.

### 3. Veranstaltung bearbeiten

| Komponente           | Aufgabe                                                                  | Anwendungsschicht |
| -------------------- | ------------------------------------------------------------------------ | ----------------: |
| changeEvent.xhtml    | Darstellung des Formulars                                                |      Präsentation |
| ProcessEvent.java    | Steuerung des Formulars                                                  |    Geschäftslogik |
| SessionContext.java  | Authentifizierung                                                        |    Geschäftslogik |
| SecurityContext.java | Autorisierung: Ist der angemeldete User der Ersteller der Veranstaltung? |    Geschäftslogik |
| EventService.java    | Historisierung                                                           |        Persistenz |
| Event.java           | Datenmodell                                                              |        Persistenz |

> Der Manager wählt nach dem Login zunächst im linken Navigationsbereich den Eintrag `Meine Veranstaltungen` aus. Aus der Liste der Veranstaltungen wählt (`Details`) er dann eine aus, die er gerne bearbeiten möchte. Hier bekommt der Manager zunächst eine Übersicht über die aktuellen Eigenschaften der Veranstaltung und hat die Option, die `Veranstaltung zu bearbeiten`. Die Anpassungen bestätigt der Manager mit einem Klick auf den Button `Änderungen speichern`, dadurch wird er zurück auf die Übersicht seiner Veranstaltungen geleitet.

### 4. Veranstaltung suchen

| Komponente          | Aufgabe                   | Anwendungsschicht |
| ------------------- | ------------------------- | ----------------: |
| search.xhtml        | Darstellung des Formulars |      Präsentation |
| SearchRequest.java  | Steuerung des Formulars   |    Geschäftslogik |
| SessionContext.java | Authentifizierung         |    Geschäftslogik |
| EventService.java   | Durchführen der Abfrage   |        Persistenz |
| Event.java          | Datenmodell               |        Persistenz |

> Der Anwender wählt nach dem Login zunächst im oberen Navigationsbereich den Eintrag `Suche` aus. Dort gibt er einen Suchbegriff ein, dies kann beispielsweise der *Ort* `Berlin` sein. Nach der Bestätigung seiner Eingabe werden ihm alle Veranstaltungen die zu seiner Suche passen aufgelistet. Dabei wird der *Name*, die *Beschreibung* und der *Ort* der Veranstaltung berücksichtigt.

> Alternative: Darüber hinaus kann der Anwender bei jeder Auflistung von Veranstaltungen über die Filter unter den Spaltenüberschriften nach spezifischen Veranstaltungen suchen. Hierzu werden Filter für den *Namen*, die *Art* und den *Ort* der Veranstaltung angeboten.

### 5. Veranstaltung ansehen

| Komponente          | Aufgabe                        | Anwendungsschicht |
| ------------------- | ------------------------------ | ----------------: |
| events.xhtml        | Auflistung der Veranstaltungen |      Präsentation |
| event.xhtml         | Darstellung der Details        |      Präsentation |
| SessionContext.java | Authentifizierung              |    Geschäftslogik |
| EventService.java   | Durchführen der Abfrage        |        Persistenz |
| Event.java          | Datenmodell                    |        Persistenz |

> Der Anwender erhält nach dem Login eine Übersicht der veröffentlichten Veranstaltungen, die in Zukunft stattfinden werden. Hierbei wird ihm *Name*, *Art*, *Ort*, *Datum* und das noch zur Verfügung stehende *Kontingent an Tickets* der Veranstaltung angezeigt. Nach einem Klick auf `Details` neben der Veranstaltung wird ihm zusätzlich eine weiterführende *Beschreibung* der Veranstaltung angezeigt.

### 6. Ticketreservierung

| Komponente              | Aufgabe                   | Anwendungsschicht |
| ----------------------- | ------------------------- | ----------------: |
| bookEvent.xhtml         | Darstellung des Formulars |      Präsentation |
| BookEventRequest.java   | Steuerung des Formulars   |    Geschäftslogik |
| SessionContext.java     | Authentifizierung         |    Geschäftslogik |
| ReservationService.java | Historisierung            |        Persistenz |
| Reservation.java        | Datenmodell               |        Persistenz |

> Der Anwender hat nach dem Login eine Veranstaltung aus der Übersicht ausgewählt, die er gerne besuchen würde. In der Detail-Ansicht erhält er die Option `Ticket reservieren`. Hierbei wird ihm eingeblendet wieviele Karten aktuell noch zur Verfügung stehen. Nach einer Eingabe der gewünschten Anzahl an Tickets, kann der Anwender die `Reservierung bestätigen` oder den Vorgang `abbrechen`. Bestätigt er seinen Reservierungswunsch, so wird - wenn noch genügend Tickets zur Verfügung stehen - die Reservierung bestätigt.

### 7. Reservierungsbestätätigung

### 8. Reservierungsübersicht

### 9. Noch reservierbare Tickets

## Sprint-Backlog

> mit allen erledigten Tasks und der Person, die den Task Umgesetzt haben. (Task, Beschreibung, Story, Entwickler)

## Datenmodell

### Klassendiagramm

### Beschreibung der Datenbankstruktur
