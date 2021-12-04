DROP TABLE IF EXISTS pessoa;
DROP TABLE IF EXISTS dead;

CREATE TABLE pessoa (
                        id INT AUTO_INCREMENT  PRIMARY KEY,
                        name VARCHAR(250) NOT NULL,
                        age INT NOT NULL,
                        career VARCHAR(250) DEFAULT NULL
);

CREATE TABLE dead (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  file VARCHAR(250) NOT NULL,
  error VARCHAR(250) NOT NULL
);
