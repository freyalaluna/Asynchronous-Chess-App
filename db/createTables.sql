-- Tables must be in order based on dependencies, ie. if your table has a foreign key, the table that it references
-- should come before it


-- CREATE TABLE user comes before this
CREATE TABLE notifications (
    notificationID int PRIMARY KEY,
    recipientID int NOT NULL,
    senderID int NOT NULL,
    messageType varchar(30) NOT NULL,
    FOREIGN KEY (recipientID) REFERENCES user(userID),
    FOREIGN KEY (senderID) REFERENCES user(userID)
);

CREATE TABLE ongoingMatch (
    matchID int PRIMARY KEY,
    playerTurn boolean NOT NULL,
    gameStateFEN text,
    FOREIGN KEY (whitePlayer) REFERENCES user(userID),
    FOREIGN KEY (blackPlayer) REFERENCES user(userID)
);

CREATE TABLE matchRecordTable (
    userID int NOT NULL,
    matchID int NOT NULL,
    beginTime varchar(30) NOT NULL,
    endTime varchar(30) NOT NULL,
    outcome varchar(4) NOT NULL,
    opponent int NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (opponent) REFERENCES user(userID),
    PRIMARY KEY (userID, matchID)
);

create TABLE moveLogTable (
    matchID int NOT NULL,
    moveSequence int NOT NULL,
    userID int NOT NULL,
    beginTime varchar(30) NOT NULL,
    endTime varchar(30) NOT NULL,
    gameState varchar(90) NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID),
    PRIMARY KEY (matchID, userID)
);
