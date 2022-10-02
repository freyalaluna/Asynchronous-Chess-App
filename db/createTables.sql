-- Tables must be in order based on dependencies, ie. if your table has a foreign key, the table that it references
-- should come before it


-- CREATE TABLE user comes before this
CREATE TABLE notifications (
    notificationID int PRIMARY KEY,
    recipientID int NOT NULL.
    senderID int NOT NULL,
    messageType String NOT NULL,
    FOREIGN KEY (recipientID) REFERENCES user(userID),
    FOREIGN KEY (senderID) REFERENCES user(userID)
);

CREATE TABLE matchRecordTable(
    userID int NOT NULL,
    matchID int NOT NULL,
    beginTime String NOT NULL,
    endTime String NOT NULL,
    outcome String NOT NULL,
    opponent int NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (opponent) REFERENCES user(userID),
    PRIMARY KEY (userID, matchID)
);