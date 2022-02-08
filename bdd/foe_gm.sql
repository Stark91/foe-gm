DROP DATABASE IF EXISTS foe_gm;
CREATE DATABASE foe_gm DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE foe_gm;

CREATE TABLE IF NOT EXISTS Age (
    idAge int NOT NULL,
    nomAge varchar(50) NOT NULL,
    PRIMARY KEY (idAge)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS CompetenceGM (
    idCompetenceGM int NOT NULL,
    nomCompetenceGM varchar(100) NOT NULL,
    descriptionCompetenceGM varchar(500),
    imageCompetenceGM varchar(50),
    PRIMARY KEY (idCompetenceGM)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS GM (
    idGM int NOT NULL,
    nomGM varchar(100) NOT NULL,
    idAge int,
	imageGM varchar(50),
	tailleGM varchar(10),
	idCompetenceGM1 int,
	idCompetenceGM2 int,
    PRIMARY KEY (idGM),
    FOREIGN KEY (idAge) REFERENCES Age(idAge),
    FOREIGN KEY (idCompetenceGM1) REFERENCES CompetenceGM(idCompetenceGM),
    FOREIGN KEY (idCompetenceGM2) REFERENCES CompetenceGM(idCompetenceGM)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS RecompensesGM (
    idRecompensesGM int NOT NULL,
    idAge int,
    niveau int,
    total int,
    p1 int,
    p2 int,
    p3 int,
    p4 int,
    p5 int,
    PRIMARY KEY (idRecompensesGM),
    FOREIGN KEY (idAge) REFERENCES Age(idAge)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;