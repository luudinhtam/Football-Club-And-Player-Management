# Football Club & Player Management System

## Overview

This project is a console-based application developed in Java for managing football clubs and players. The system allows users to perform CRUD operations, search, sort, filter data, and save/load information from text files.

## Features

### Club Management

* Display all clubs
* Add a new club
* Search for a club by ID
* Update club information by ID
* List clubs with budget less than or equal to a specified value

### Player Management

* Display players sorted by club name and shirt number
* Search players by partial name
* Add a new player
* Remove a player by ID
* Update player information by ID
* List players by position

### Data Persistence

* Load data from text files
* Save data to text files

## Project Structure

```
src/
│
├── core/
│   ├── Club.java
│   ├── ClubList.java
│   ├── Player.java
│   └── PlayerList.java
│
├── tool/
│   └── ConsoleInputter.java
│
└── MainManager.java
```

## Data Files

### clubs.txt

Stores club information:

```
clubId,clubName,sponsorBrand,budget
```

### players.txt

Stores player information:

```
playerId,clubId,playerName,position,shirtNumber
```

## Main Classes

| Class           | Responsibility                          |
| --------------- | --------------------------------------- |
| Club            | Represents a football club              |
| ClubList        | Manages club collection                 |
| Player          | Represents a player                     |
| PlayerList      | Manages player collection               |
| ConsoleInputter | Handles validated user input            |
| MainManager     | Program entry point and menu management |

## Technologies

* Java
* Object-Oriented Programming (OOP)
* ArrayList Collection Framework
* File I/O
* Regular Expressions (Regex)

## How to Run

1. Compile all Java source files.
2. Ensure `clubs.txt` and `players.txt` are located in the project directory.
3. Run `MainManager`.
4. Select functions from the menu.

## Author

Student Management Project for practicing Object-Oriented Programming and File Handling in Java.
