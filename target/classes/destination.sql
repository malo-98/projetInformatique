-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 18 nov. 2019 à 08:47
-- Version du serveur :  10.4.6-MariaDB
-- Version de PHP :  7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mobiliti_bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `destination`
--

CREATE TABLE `destination` (
  `id_destination` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Ville` varchar(100) NOT NULL,
  `Pays` varchar(100) NOT NULL,
  `Description` varchar(500) NOT NULL,
  `Domaine` varchar(100) NOT NULL,
  `Nombre_de_place` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `destination`
--

INSERT INTO `destination` (`id_destination`, `Nom`, `Ville`, `Pays`, `Description`, `Domaine`, `Nombre_de_place`) VALUES
(1, 'testNom1', 'testVille1', 'testPays1', 'LA vies est belle', 'testDomaine1', 4),
(2, 'testNom2', 'testVille2', 'testPays2', 'test description 2', 'testDomaine2', 11),
(11, 'Univ du champs', 'Paris', 'France', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\r\n                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 'ITI', 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id_destination`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `destination`
--
ALTER TABLE `destination`
  MODIFY `id_destination` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
