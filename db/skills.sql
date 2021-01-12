-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 12 jan. 2021 à 09:47
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.6

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

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `change_user_reference` (`id1` INT)  BEGIN
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
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `competences`
--

CREATE TABLE `competences` (
  `id` int(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `reference` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `competences`
--

INSERT INTO `competences` (`id`, `title`, `reference`) VALUES
(1, 'C1. Maquetter une application', 'Développeur⋅se web et web mobile'),
(2, 'C2. Réaliser une interface utilisateur web statique et adaptable', 'Développeur⋅se web et web mobile'),
(3, 'C3. Développer une interface utilisateur web dynamique', 'Développeur⋅se web et web mobile'),
(4, 'C4. Réaliser une interface utilisateur avec une solution de gestion de contenu ou e-commerce', 'Développeur⋅se web et web mobile'),
(5, 'C5. Créer une base de données', 'Développeur⋅se web et web mobile'),
(6, 'C6. Développer des composants d`accès aux données', 'Développeur⋅se web et web mobile'),
(7, 'C7. Développer la partie back-end d’une application web ou web mobile', 'Développeur⋅se web et web mobile'),
(8, 'C8. Élaborer et mettre en œuvre des composants dans une application de gestion de contenu ou e-comme', 'Développeur⋅se web et web mobile'),
(9, 'Développer une interface utilisateur de type desktop', 'Concepteur⋅rice développeur⋅se d`applications'),
(10, 'Développer des composants d’accès aux données', 'Concepteur⋅rice développeur⋅se d`applications'),
(11, 'Développer la partie front-end d’une interface utilisateur web', 'Concepteur⋅rice développeur⋅se d`applications'),
(12, 'Développer la partie back-end d’une interface utilisateur web', 'Concepteur⋅rice développeur⋅se d`applications'),
(13, 'Concevoir une base de données', 'Concepteur⋅rice développeur⋅se d`applications'),
(14, 'Mettre en place une base de données', 'Concepteur⋅rice développeur⋅se d`applications'),
(15, 'Développer des composants dans le langage d’une base de données', 'Concepteur⋅rice développeur⋅se d`applications'),
(16, 'Collaborer à la gestion d’un projet informatique et à l’organisation de l’environnement de dévelo', 'Concepteur⋅rice développeur⋅se d`applications'),
(17, 'Concevoir une application', 'Concepteur⋅rice développeur⋅se d`applications'),
(18, 'Développer des composants métier', 'Concepteur⋅rice développeur⋅se d`applications'),
(19, 'Construire une application organisée en couches', 'Concepteur⋅rice développeur⋅se d`applications'),
(20, 'Développer une application mobile', 'Concepteur⋅rice développeur⋅se d`applications'),
(21, 'Préparer et exécuter les plans de tests d’une application', 'Concepteur⋅rice développeur⋅se d`applications'),
(22, 'Préparer et exécuter le déploiement d’une application', 'Concepteur⋅rice développeur⋅se d`applications');

-- --------------------------------------------------------

--
-- Structure de la table `niveaux`
--

CREATE TABLE `niveaux` (
  `id_user` int(10) NOT NULL,
  `id_competence` int(10) NOT NULL,
  `niveau` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `niveaux`
--

INSERT INTO `niveaux` (`id_user`, `id_competence`, `niveau`) VALUES
(2, 1, 0),
(2, 2, 0),
(2, 3, 0),
(2, 4, 0),
(2, 5, 0),
(2, 6, 0),
(2, 7, 0),
(2, 8, 0);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role` varchar(15) NOT NULL DEFAULT 'apprenant',
  `reference` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `email`, `password`, `role`, `reference`) VALUES
(1, 'rouiha', 'ayoub', 'rouiha594@gmail.com', 'Winners05', 'staff', ''),
(2, 'soufiane', 'el kadiri', 'soufiane@gmail.com', '123456789', 'apprenant', 'Développeur⋅se web et web mobile');

--
-- Déclencheurs `users`
--
DELIMITER $$
CREATE TRIGGER `ajouter_competence_user` AFTER INSERT ON `users` FOR EACH ROW BEGIN
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
END
$$
DELIMITER ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `competences`
--
ALTER TABLE `competences`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `niveaux`
--
ALTER TABLE `niveaux`
  ADD PRIMARY KEY (`id_user`,`id_competence`),
  ADD UNIQUE KEY `id_user` (`id_user`,`id_competence`),
  ADD KEY `id_competence` (`id_competence`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `competences`
--
ALTER TABLE `competences`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `niveaux`
--
ALTER TABLE `niveaux`
  ADD CONSTRAINT `id_competence` FOREIGN KEY (`id_competence`) REFERENCES `competences` (`id`),
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
