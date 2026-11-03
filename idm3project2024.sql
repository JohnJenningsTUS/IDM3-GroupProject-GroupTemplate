-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2024 at 02:42 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `idm3project2024`
--

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `ProjectID` bigint(20) NOT NULL,
  `ProjectName` varchar(255) DEFAULT NULL,
  `ProjectDescription` varchar(500) NOT NULL,
  `UserID` bigint(20) DEFAULT NULL,
  `Category` varchar(255) DEFAULT NULL,
  `ProjectDescriptionSummary` varchar(255) DEFAULT NULL,
  `ProjectHeroImage` varchar(255) DEFAULT NULL,
  `CreationDate` date NOT NULL DEFAULT (curdate()),
  `additionalDoc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`ProjectID`, `ProjectName`, `ProjectDescription`, `UserID`, `Category`, `ProjectDescriptionSummary`, `ProjectHeroImage`, `CreationDate`, `additionalDoc`) VALUES
(1, 'sea life', 'The oceans are home to some of the most magnificent creatures both great and small, wild and wonderful. Yet human interference is damaging marine ecosystems around the world causing a vast decline in marine life and the health of the oceans. But there are a few inspiring individuals out there who have dedicated their lives to ocean conservation and helping the creatures of the great wild blue. ', 1, 'Digital Media', 'a look at sea life in winter', 'p1sea.jpg', '2023-09-22', 'x.doc'),
(2, 'winter view', 'If you think wintertime is an excuse to put your camera away and stay warm indoors, then you need to read this article! Winter photography can be one of the most rewarding challenges in photography, whether you are interested in landscapes, cityscapes, seascapes, wildlife, or even portraits!\r\n\r\nIn this article, we will cover a variety of tips, ideas, and examples; everything from the technical to the creative! You might be surprised, too–some of these tips go against the conventional wisdom you’', 2, 'Digital Media', 'winter in the lock down', 'p2snow.jpg', '2023-09-23', 'y.doc'),
(3, '3D cartoons', '3D character modeling is a graphic design technique that creates a three-dimensional digital representation of a surface or an object. Artists use specific software, start with a simple shape, and slowly enrich it with more details', 1, 'IT', 'a collection of 3D Disney characters', 'p3disney.jpg', '2023-09-23', 'x.doc'),
(6, 'Man-in-the-Moon', 'What is organic chemistry? Organic chemistry is the field of chemistry over the study of organic substances and compounds – that is, those that contain carbon in their molecular structure, combined with other elements such as hydrogen, nitrogen, oxygen, and sulfur.', 1, 'organic chemistry', 'The Effect of Gamma Rays on Man-in-the-Moon Marigolds', 'p6artshowcase.jpg', '2023-09-23', 'p.doc'),
(7, 'lizs best project', 'great project', 4, 'ART', 'more details', 'small_cute_cat.png', '2024-11-03', 'no');

-- --------------------------------------------------------

--
-- Table structure for table `showcase`
--

CREATE TABLE `showcase` (
  `ShowcaseId` bigint(20) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `showcase`
--

INSERT INTO `showcase` (`ShowcaseId`, `Name`, `Description`, `Image`, `Status`) VALUES
(1, 'Art Showcase', 'a collection of art pieces to celebrate the beauty of winter', 'chemshowcase.jpg', 'PENDING'),
(2, 'bio-pharma showcase', 'A look at bio-pharma in limerick', 'chemshowcase.jpg', 'LIVE'),
(3, 'Sea times', 'a collection of immersive digital pieces to celebrate the beauty of the sea ', 'artshowcase.jpg', 'LIVE'),
(4, 'Politics showcase', 'a collection of 3rd year work.', 'politicshowcase.jpg', 'LIVE'),
(9, 'spring', 'nice spring pictures', 'artshowcase.jpg', 'PENDING'),
(16, 'liztest', 'bla bla', 'disney.jpg', 'LIVE');

-- --------------------------------------------------------

--
-- Table structure for table `showcaseproject`
--

CREATE TABLE `showcaseproject` (
  `ShowcaseProjectID` bigint(20) NOT NULL,
  `ProjectId` bigint(20) DEFAULT NULL,
  `ShowcaseID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `showcaseproject`
--

INSERT INTO `showcaseproject` (`ShowcaseProjectID`, `ProjectId`, `ShowcaseID`) VALUES
(1, 1, 3),
(2, 3, 4),
(3, 2, 4),
(4, 3, 4),
(5, 3, 2),
(6, 1, 1),
(7, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` bigint(20) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `surname` varchar(225) DEFAULT NULL,
  `EmailAddress` varchar(100) NOT NULL,
  `UserType` varchar(50) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `password` varchar(225) DEFAULT NULL,
  `organization` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `FirstName`, `surname`, `EmailAddress`, `UserType`, `UserName`, `password`, `organization`) VALUES
(1, 'TRYANxxx', 'Ryanxxxxx', 'TRyan@gmail.comx', 'STUDENT', 'TRYAN', 'pass123', 'litx'),
(2, 'Mary', 'Smith', 'MS@homail.com', 'STUDENT', 'MSith', '123pass', ''),
(3, 'Peter', 'Carr', 'PC@somebusiness.ie', 'VIEWER', 'PCbus', 'PC123', 'buzz tech - 3d modelling'),
(4, 'Elizabeth', 'Bourke', 'Elizabeth.Bourke@TUS.ie', 'Admin', 'EBourke', 'Bourke123', ''),
(6, 'Tom', 'Smith', 'xx@jj.com', 'ADMIN', 'tsmith', 'pass123', ''),
(28, 'Elizabeth', 'Bourke', 'Elizabeth_Bourke@hotmail.com', '', 'EB', '123', 'lit'),
(29, 'Elizabeth2', 'Bourke', 'Elizabeth_Bourke2@hotmail.com', '', '22', '22', '22'),
(30, 'l', 'l', 'Elizabeth_Bourkejj@hotmail.com', '', 'l', 'l', 'l'),
(31, 'Elizabeth', 'Bourke', 'Elizabeth33_Bourke@hotmail.com', '', '33', '33', '33'),
(32, 'Elizabeth', 'Bourke', 'Elizabeth_Bourke66@hotmail.com', 'STUDENT', '66', '66', '66'),
(33, 'Elizabeth', 'Bourke', 'Elizabethmmm_Bourke@hotmail.com', 'STUDENT', 'mm', 'mm', 'mm'),
(34, 'Elizabeth', 'Bourke', 'Elizabethttt_Bourke@hotmail.com', 'STUDENT', 'EB444', '123', 'lit');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`ProjectID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `showcase`
--
ALTER TABLE `showcase`
  ADD PRIMARY KEY (`ShowcaseId`);

--
-- Indexes for table `showcaseproject`
--
ALTER TABLE `showcaseproject`
  ADD PRIMARY KEY (`ShowcaseProjectID`),
  ADD KEY `ShowcaseID` (`ShowcaseID`),
  ADD KEY `ProjectID` (`ProjectId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `ProjectID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `showcase`
--
ALTER TABLE `showcase`
  MODIFY `ShowcaseId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `showcaseproject`
--
ALTER TABLE `showcaseproject`
  MODIFY `ShowcaseProjectID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FK84avpir3s8alvr5yud0yh8d7b` FOREIGN KEY (`UserID`) REFERENCES `user` (`userid`);

--
-- Constraints for table `showcaseproject`
--
ALTER TABLE `showcaseproject`
  ADD CONSTRAINT `FK6ge3kqk7na2e01frc8rj08y9w` FOREIGN KEY (`ShowcaseID`) REFERENCES `showcase` (`ShowcaseId`),
  ADD CONSTRAINT `FKgt6y2lqndsh5b1gs0nxci6wk5` FOREIGN KEY (`ProjectId`) REFERENCES `project` (`ProjectID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
