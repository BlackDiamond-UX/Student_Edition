-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 28 déc. 2020 à 13:07
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `skills`
--


drop schema if exists skills;

create schema skills;

use skills;
-- --------------------------------------------------------

--
-- Structure de la table `competence`
--



CREATE TABLE `competences` (
  `id` int(10) NOT NULL auto_increment,
  `title` varchar(100) NOT NULL,
  `reference` varchar(100) NOT NULL,
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL auto_increment,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role` varchar(15) NOT NULL DEFAULT 'apprenant',
  `reference` varchar(100) not NULL,
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

CREATE TABLE `niveaux` (
  `id_user` int(10) NOT NULL,
  `id_competence` int(10) NOT NULL,
  `niveau` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (id_user, id_competence),
  UNIQUE INDEX (id_user, id_competence),
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `id_competence` FOREIGN KEY (`id_competence`) REFERENCES `competences` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into competences(id,title,reference) values
(1,'C1. Maquetter une application','Développeur⋅se web et web mobile'),
(2,'C2. Réaliser une interface utilisateur web statique et adaptable','Développeur⋅se web et web mobile'),
(3,'C3. Développer une interface utilisateur web dynamique','Développeur⋅se web et web mobile'),
(4,'C4. Réaliser une interface utilisateur avec une solution de gestion de contenu ou e-commerce','Développeur⋅se web et web mobile'),
(5,'C5. Créer une base de données','Développeur⋅se web et web mobile'),
(6,'C6. Développer des composants d`accès aux données','Développeur⋅se web et web mobile'),
(7,'C7. Développer la partie back-end d’une application web ou web mobile','Développeur⋅se web et web mobile'),
(8,'C8. Élaborer et mettre en œuvre des composants dans une application de gestion de contenu ou e-commerce','Développeur⋅se web et web mobile'),
(9,'Développer une interface utilisateur de type desktop','Concepteur⋅rice développeur⋅se d`applications'),
(10,'Développer des composants d’accès aux données','Concepteur⋅rice développeur⋅se d`applications'),
(11,'Développer la partie front-end d’une interface utilisateur web','Concepteur⋅rice développeur⋅se d`applications'),
(12,'Développer la partie back-end d’une interface utilisateur web','Concepteur⋅rice développeur⋅se d`applications'),
(13,'Concevoir une base de données','Concepteur⋅rice développeur⋅se d`applications'),
(14,'Mettre en place une base de données','Concepteur⋅rice développeur⋅se d`applications'),
(15,'Développer des composants dans le langage d’une base de données','Concepteur⋅rice développeur⋅se d`applications'),
(16,'Collaborer à la gestion d’un projet informatique et à l’organisation de l’environnement de développement','Concepteur⋅rice développeur⋅se d`applications'),
(17,'Concevoir une application','Concepteur⋅rice développeur⋅se d`applications'),
(18,'Développer des composants métier','Concepteur⋅rice développeur⋅se d`applications'),
(19,'Construire une application organisée en couches','Concepteur⋅rice développeur⋅se d`applications'),
(20,'Développer une application mobile','Concepteur⋅rice développeur⋅se d`applications'),
(21,'Préparer et exécuter les plans de tests d’une application','Concepteur⋅rice développeur⋅se d`applications'),
(22,'Préparer et exécuter le déploiement d’une application','Concepteur⋅rice développeur⋅se d`applications');



DROP TRIGGER IF EXISTS ajouter_competence_user;
DELIMITER //
Create Trigger ajouter_competence_user 
after insert 
on users
For each ROW 
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE ids INT;
  DECLARE cur CURSOR FOR SELECT id FROM competences WHERE reference = 'Développeur⋅se web et web mobile';
  DECLARE cur1 CURSOR FOR SELECT id FROM competences WHERE reference = new.reference;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  IF new.role = 'apprenant' THEN
    IF new.reference='Concepteur⋅rice développeur⋅se d`applications' THEN
      OPEN cur;
          ins_loop: LOOP
              FETCH cur INTO ids;
              IF done THEN
                  LEAVE ins_loop;
              END IF;
              INSERT INTO niveaux(id_user, id_competence,niveau) VALUES (new.id,ids,3);
          END LOOP;
      CLOSE cur;
    END IF;

    SET done = FALSE;
    
    OPEN cur1;

        ins_loop: LOOP
            FETCH cur1 INTO ids;
            IF done THEN
                LEAVE ins_loop;
            END IF;
            INSERT INTO niveaux(id_user, id_competence) VALUES (new.id,ids);
        END LOOP;
    CLOSE cur1;
  END IF;
END; //
DELIMITER ;


DELIMITER $$
DROP PROCEDURE IF EXISTS change_user_reference;$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE change_user_reference (id1 INT) 
BEGIN
	DECLARE done INT DEFAULT FALSE;
  	DECLARE ids INT;
  	DECLARE cur CURSOR FOR SELECT id FROM competences WHERE reference = 'Concepteur⋅rice développeur⋅se d`applications';
  	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	update users set reference='Concepteur⋅rice développeur⋅se d`applications' where id=id1;
      OPEN cur;
          ins_loop: LOOP
              FETCH cur INTO ids;
              IF done THEN
                  LEAVE ins_loop;
              END IF;
              INSERT INTO niveaux(id_user, id_competence) VALUES (id1,ids);
          END LOOP;
      CLOSE cur;
END $$
DELIMITER ;


INSERT into users (`nom`,`prenom`,`email`,`password`,`role`) values('elwahabi','hanae','helwahabi@simplon.co','aaaaaaaa','staff');

INSERT into users (`nom`,`prenom`,`email`,`password`,`reference`) values('noman','jamal eddine','jamalnoman@gmail.com','aaaaaaaa','Développeur⋅se web et web mobile');

ALTER TABLE users ADD UNIQUE (email);

