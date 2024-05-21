

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";



--
-- Base de données: `emploidutemps_db`
--

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `enseignant_cours`
--
CREATE TABLE IF NOT EXISTS `enseignant_cours` (
`matricule` varchar(20)
,`nom` varchar(150)
,`contact` varchar(50)
,`id` int(11)
,`classe` varchar(30)
,`matiere` varchar(80)
,`num_jour` smallint(6)
,`jour` varchar(20)
,`heure` varchar(20)
);
-- --------------------------------------------------------

--
-- Structure de la table `tb_cours`
--

CREATE TABLE IF NOT EXISTS `tb_cours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classe` varchar(30) NOT NULL,
  `matiere` varchar(80) NOT NULL,
  `num_jour` smallint(6) DEFAULT NULL,
  `Jour` varchar(20) NOT NULL,
  `heure` varchar(20) NOT NULL,
  `matricule_ens` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk` (`matricule_ens`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=32 ;

--
-- Contenu de la table `tb_cours`
--

INSERT INTO `tb_cours` (`id`, `classe`, `matiere`, `num_jour`, `Jour`, `heure`, `matricule_ens`) VALUES
(1, 'TIC1A', 'MATH', 1, 'LUNDI', '3H', 'ens2'),
(2, 'TIC1A', 'FRAN', 1, 'LUNDI', '3H', 'ens1'),
(3, 'TIC1B', 'Développement Web JavaScript EC6', 1, 'LUNDI', '3H', 'ens5'),
(4, 'TIC1A', 'Systèmes exploitation Linux', 2, 'MARDI', '3H', 'ens7'),
(5, 'TIC1B', 'MATH', 2, 'MARDI', '3H', 'ens2'),
(6, 'TIC1A', 'Modélisation UML', 2, 'MARDI', '3H', 'ens8'),
(7, 'TIC1C', 'MATH', 3, 'MERCREDI', '3H', 'ens2'),
(8, 'TIC1A', 'Développement Web JavaScript EC6', 3, 'MERCREDI', '3H', 'ens5'),
(9, 'TIC1C', 'FRAN', 3, 'MERCREDI', '3H', 'ens1'),
(10, 'TIC1D', 'FRAN', 4, 'JEUDI', '3H', 'ens1'),
(11, 'TIC1B', 'Systèmes exploitation Linux', 4, 'JEUDI', '3H', 'ens7'),
(12, 'TIC1A', 'ANG', 3, 'MERCREDI', '3H', 'ens6'),
(13, 'TIC1B', 'ANG', 5, 'VENDREDI', '3H', 'ens6'),
(14, 'TIC1B', 'FRAN', 1, 'LUNDI', '3H', 'ens1'),
(15, 'TIC1D', 'MATH', 1, 'LUNDI', '3H', 'ens2'),
(16, 'TIC1B', 'Modélisation UML', 1, 'LUNDI', '3H', 'ens8'),
(17, 'TIC1E', 'MATH', 2, 'MARDI', '3H', 'ens2'),
(18, 'TIC1E', 'FRAN', 2, 'MARDI', '3H', 'ens1'),
(19, 'TIC1E', 'ANG', 3, 'MERCREDI', '3H', 'ens6'),
(20, 'TIC1F', 'MATH', 3, 'MERCREDI', '3H', 'ens2'),
(21, 'TIC1E', 'Modélisation UML', 3, 'MERCREDI', '3H', 'ens8'),
(22, 'TIC1E', 'Développement Web JavaScript EC6', 4, 'JEUDI', '3H', 'ens5'),
(23, 'TIC1F', 'FRAN', 4, 'JEUDI', '3H', 'ens1'),
(24, 'TIC1F', 'Développement Web JavaScript EC6', 2, 'MARDI', '3H', 'ens5');

-- --------------------------------------------------------

--
-- Structure de la table `tb_enseignant`
--

CREATE TABLE IF NOT EXISTS `tb_enseignant` (
  `matricule` varchar(20) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `contact` varchar(50) NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `tb_enseignant`
--

INSERT INTO `tb_enseignant` (`matricule`, `nom`, `contact`) VALUES
('ens1', 'AHMED', '66891219'),
('ens2', 'SARAH', '66214512'),
('ens3', 'RANIA', '66420833 / 99541240'),
('ens4', 'BALKIS', '66014345'),
('ens5', 'AMIN', '62080911'),
('ens6', 'AZIZ', '60990909'),
('ens7', 'RIM', '66478901'),
('ens8', 'WISSEM', '66801717');

-- --------------------------------------------------------

--
-- Structure de la vue `enseignant_cours`
--
DROP TABLE IF EXISTS `enseignant_cours`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `enseignant_cours` AS select `tb_enseignant`.`matricule` AS `matricule`,`tb_enseignant`.`nom` AS `nom`,`tb_enseignant`.`contact` AS `contact`,`tb_cours`.`id` AS `id`,`tb_cours`.`classe` AS `classe`,`tb_cours`.`matiere` AS `matiere`,`tb_cours`.`num_jour` AS `num_jour`,`tb_cours`.`Jour` AS `jour`,`tb_cours`.`heure` AS `heure` from (`tb_enseignant` join `tb_cours` on((`tb_enseignant`.`matricule` = `tb_cours`.`matricule_ens`)));

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `tb_cours`
--
ALTER TABLE `tb_cours`
  ADD CONSTRAINT `fk` FOREIGN KEY (`matricule_ens`) REFERENCES `tb_enseignant` (`matricule`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
