CREATE TABLE AppUser (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    passwordHash VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    etunimi VARCHAR(255),
    sukunimi VARCHAR(255),
    sahkoposti VARCHAR(255)
);

CREATE TABLE Tuote (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tuoteNimi VARCHAR(255) NOT NULL,
    tyyppi VARCHAR(255),
    vari VARCHAR(255),
    koko VARCHAR(255),
    hinta DOUBLE NOT NULL,
    maara INT,
    valmistaja_id BIGINT,
    FOREIGN KEY (valmistaja_id) REFERENCES Valmistaja(id)
);

CREATE TABLE Valmistaja (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nimi VARCHAR(255) NOT NULL
);