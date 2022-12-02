-- Tables must be in order based on dependencies, ie. if your table has a foreign key, the table that it references
-- should come before it

CREATE TABLE users (
    userID int PRIMARY KEY AUTO_INCREMENT,
    username varchar(64) NOT NULL UNIQUE,
    pass char(60) NOT NULL,
    email varchar(320) NOT NULL UNIQUE
);

-- CREATE TABLE user comes before this
CREATE TABLE notifications (
    notificationID int PRIMARY KEY,
    recipientID int NOT NULL,
    senderID int NOT NULL,
    messageType varchar(30) NOT NULL,
    FOREIGN KEY (recipientID) REFERENCES users(userID),
    FOREIGN KEY (senderID) REFERENCES users(userID)
);

CREATE TABLE ongoingMatch (
    matchID int PRIMARY KEY AUTO_INCREMENT,
    playerTurn boolean NOT NULL,
    gameStateFEN text,
    capturedPieces text,
    whitePlayer int NOT NULL,
    blackPlayer int NOT NULL,
    FOREIGN KEY (whitePlayer) REFERENCES users(userID),
    FOREIGN KEY (blackPlayer) REFERENCES users(userID)
);

CREATE TABLE matchRecordTable (
    userID int NOT NULL,
    matchID int NOT NULL,
    beginTime varchar(30) NOT NULL,
    endTime varchar(30) NOT NULL,
    outcome varchar(4) NOT NULL,
    opponent int NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (opponent) REFERENCES users(userID),
    PRIMARY KEY (userID, matchID)
);

create TABLE moveLogTable (
    matchID int NOT NULL,
    moveSequence int NOT NULL,
    userID int NOT NULL,
    beginTime varchar(30) NOT NULL,
    endTime varchar(30) NOT NULL,
    gameState varchar(90) NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID),
    PRIMARY KEY (matchID, userID)
);
