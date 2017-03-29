# Semesteraufgabe: Java EE Architektur SS 2017

## Kurzbeschreibung der Anwendung

Die entwickelte Softwarelösung orientiert sich an der Model 2-Architektur. Die Komponenten der Anwendung lassen sich demnach unterschiedlichen Schichten zuordnen.

Die erste Schicht behandelt die Darstellung der Inhalte und wird daher im folgenden als Präsentationsschicht bezeichnet. Zu ihr zählen die Views welche im Verzeichnis src/main/webapp/ liegen.

Die Schicht der Geschäftslogik hingegen beschäftigt sich mit der konkreten Implementierung jeglicher Funktionalitäten zur Realisierung der User-Stories. Zu ihr zählen daher die Controller- und Request-Komponenten.

Die letzte Schicht beschäftigt sich mit der Speicherung der Daten und wird daher als Persistenzschicht bezeichnet. Zu ihr zählen die Datenmodelle und die Service-Komponenten, welche die Historisierung in H2 realisieren.

### Tabelle 1: Architektur der Anwendung

| Schicht        | Beschreibung                                                  |
| -------------- | ------------------------------------------------------------- |
| Präsentation   | Views für die Abbildung der User-Stories                      |
| Geschäftslogik | Steuerung der Präsentation und Abbildung von Funktionalitäten |
| Persistenz     | Datenmodell und Historisierung in H2                          |

## Umsetzung der User-Stories

> Liste aller beteiligten Komponenten und Klassen, sowie deren Aufgaben und Zugehörigkeit der Anwendungsschicht.
> Kurze Beschreibung der Schritte, die ein Nutzer fur die Anwendung der Story ausführen muss.

### 1. Veranstaltung anlegen

| Komponente      | Aufgabe      | Anwendungsschicht |
| --------------- | ------------ | ----------------: |
| Eine Komponente | Ihre Aufgabe |      Ihre Schicht |

> Der Nutzer muss...

### 2. Veranstaltung veröffentlichen

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
