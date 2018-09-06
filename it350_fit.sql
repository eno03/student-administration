-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2018 at 06:44 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `it350_fit`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`username`, `password`) VALUES
('julija.cirkovic', 'curica123'),
('matej.petrovic', 'ckode123'),
('nikola.obucina', 'decko123');

-- --------------------------------------------------------

--
-- Table structure for table `evidencija`
--

CREATE TABLE `evidencija` (
  `evidencija_id` int(11) NOT NULL,
  `rok_oznaka` varchar(10) NOT NULL,
  `indeks` smallint(6) NOT NULL,
  `nacin_polaganja_id` int(11) NOT NULL,
  `predmet_id` int(11) NOT NULL,
  `datum_odrzavanja` date NOT NULL,
  `ocena` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evidencija`
--

INSERT INTO `evidencija` (`evidencija_id`, `rok_oznaka`, `indeks`, `nacin_polaganja_id`, `predmet_id`, `datum_odrzavanja`, `ocena`) VALUES
(0, 'April2017', 666, 0, 1, '2017-04-25', 9),
(1, 'Dec2018', 1223, 1, 2, '2018-12-20', 6),
(2, 'JanA2016', 1342, 0, 4, '2016-01-28', 5),
(3, 'JanB2016', 2000, 1, 5, '2016-02-19', 10),
(4, 'JanB2018', 3107, 0, 7, '2018-02-19', 8),
(5, 'JunA2014', 3234, 0, 8, '2014-06-26', 7),
(6, 'NovA2017', 3224, 1, 8, '2017-11-05', 5),
(7, 'SepA2015', 3107, 0, 9, '2015-09-10', 5),
(8, 'JunB2016', 2000, 1, 6, '2016-07-18', 5),
(9, 'JunA2014', 2342, 1, 4, '2014-06-11', 10);

-- --------------------------------------------------------

--
-- Table structure for table `ispitni_rok`
--

CREATE TABLE `ispitni_rok` (
  `rok_oznaka` varchar(10) NOT NULL,
  `rok_trajanje` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ispitni_rok`
--

INSERT INTO `ispitni_rok` (`rok_oznaka`, `rok_trajanje`) VALUES
('April2017', 8),
('Dec2018', 6),
('JanA2016', 5),
('JanB2016', 5),
('JanB2018', 7),
('JunA2014', 5),
('JunB2016', 6),
('JunB2018', 6),
('NovA2017', 5),
('SepA2015', 6);

-- --------------------------------------------------------

--
-- Table structure for table `nacin_polaganja`
--

CREATE TABLE `nacin_polaganja` (
  `nacin_polaganja_id` int(11) NOT NULL,
  `nacin_polaganja_info` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nacin_polaganja`
--

INSERT INTO `nacin_polaganja` (`nacin_polaganja_id`, `nacin_polaganja_info`) VALUES
(0, 'Usmeno'),
(1, 'Pismeno');

-- --------------------------------------------------------

--
-- Table structure for table `predmet`
--

CREATE TABLE `predmet` (
  `predmet_id` int(11) NOT NULL,
  `predmet_oznaka` varchar(5) NOT NULL,
  `predmet_naziv` varchar(50) NOT NULL,
  `predmet_espb` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `predmet`
--

INSERT INTO `predmet` (`predmet_id`, `predmet_oznaka`, `predmet_naziv`, `predmet_espb`) VALUES
(0, 'CS101', 'Uvod u objektno-orijentisano programiranje', 10),
(1, 'CS102', 'Objekti i apstrakcija podataka', 5),
(2, 'CS220', 'Arhitektura racunara', 8),
(3, 'NT111', 'Engleski 1', 4),
(4, 'IT101', 'Osnove informacionih tehnologija', 8),
(5, 'IT210', 'Sistemi infomacionih tehnologija', 8),
(6, 'MK150', 'Osnovi menadzmenta', 5),
(7, 'IT120', 'Razvoj aplikacija', 8),
(8, 'CS323', 'C/C++ programski jezik', 10),
(9, 'MA101', 'Matematika 1', 10);

-- --------------------------------------------------------

--
-- Table structure for table `smer`
--

CREATE TABLE `smer` (
  `smer_id` int(11) NOT NULL,
  `smer_naziv` varchar(50) NOT NULL,
  `smer_oznaka` varchar(5) NOT NULL,
  `smer_espb` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smer`
--

INSERT INTO `smer` (`smer_id`, `smer_naziv`, `smer_oznaka`, `smer_espb`) VALUES
(0, 'Racunarske igre', 'RI', 242),
(1, 'Softversko inzenjerstvo', 'SI', 242),
(2, 'Informacione tehnologije', 'IT', 240),
(3, 'Infomacioni sistemi', 'IS', 242);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(11) NOT NULL,
  `status_opis` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_opis`) VALUES
(0, 'Budzet'),
(1, 'Samofinansiranje');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `indeks` smallint(6) NOT NULL,
  `smer_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `ime` text NOT NULL,
  `prezime` text NOT NULL,
  `datum_rodjenja` date NOT NULL,
  `mesto_rodjenja` varchar(30) DEFAULT NULL,
  `staratelj` varchar(30) DEFAULT NULL,
  `datum_upisa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`indeks`, `smer_id`, `status_id`, `ime`, `prezime`, `datum_rodjenja`, `mesto_rodjenja`, `staratelj`, `datum_upisa`) VALUES
(666, 1, 1, 'Aleksandra', 'Arsic', '1990-12-13', 'Pozarevac', '', '2009-12-21'),
(1223, 2, 0, 'Petar', 'Djuricic', '1995-12-13', '', 'Momir', '2015-12-15'),
(1342, 2, 1, 'Mina', 'Mitrovic', '1994-12-26', 'Kragujevac', 'Petar', '2013-12-27'),
(2000, 0, 1, 'Ivana', 'Peric', '1998-12-05', 'Paracin', 'Milan', '2015-12-12'),
(2342, 3, 0, 'Nikola', 'Milosevic', '1996-12-28', 'Pancevo', 'Goran', '2015-12-19'),
(3107, 1, 1, 'Milos', 'Caranovic', '1997-12-04', 'Valjevo', 'Mihajlo', '2016-12-06'),
(3224, 3, 0, 'Milica', 'Popovic', '1990-07-18', 'Beograd', '', '2016-09-12'),
(3234, 1, 0, 'Mihajlo', 'Ivankovic', '1996-12-26', '', '', '2015-12-27');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `evidencija`
--
ALTER TABLE `evidencija`
  ADD PRIMARY KEY (`evidencija_id`),
  ADD KEY `fk_ima` (`rok_oznaka`),
  ADD KEY `fk_ima_3` (`nacin_polaganja_id`),
  ADD KEY `fk_polaze` (`predmet_id`),
  ADD KEY `fk_polaze2` (`indeks`);

--
-- Indexes for table `ispitni_rok`
--
ALTER TABLE `ispitni_rok`
  ADD PRIMARY KEY (`rok_oznaka`);

--
-- Indexes for table `nacin_polaganja`
--
ALTER TABLE `nacin_polaganja`
  ADD PRIMARY KEY (`nacin_polaganja_id`);

--
-- Indexes for table `predmet`
--
ALTER TABLE `predmet`
  ADD PRIMARY KEY (`predmet_id`);

--
-- Indexes for table `smer`
--
ALTER TABLE `smer`
  ADD PRIMARY KEY (`smer_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`indeks`),
  ADD KEY `fk_ima_status` (`status_id`),
  ADD KEY `fk_pohadja` (`smer_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `evidencija`
--
ALTER TABLE `evidencija`
  ADD CONSTRAINT `fk_ima` FOREIGN KEY (`rok_oznaka`) REFERENCES `ispitni_rok` (`rok_oznaka`),
  ADD CONSTRAINT `fk_ima_3` FOREIGN KEY (`nacin_polaganja_id`) REFERENCES `nacin_polaganja` (`nacin_polaganja_id`),
  ADD CONSTRAINT `fk_polaze` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`predmet_id`),
  ADD CONSTRAINT `fk_polaze2` FOREIGN KEY (`indeks`) REFERENCES `student` (`indeks`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_ima_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `fk_pohadja` FOREIGN KEY (`smer_id`) REFERENCES `smer` (`smer_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
