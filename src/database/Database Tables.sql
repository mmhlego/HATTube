-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql11.freesqldatabase.com
-- Generation Time: Jul 05, 2021 at 03:02 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql11423044`
--

-- --------------------------------------------------------

--
-- Table structure for table `Channels`
--

CREATE TABLE `Channels` (
  `ID` text COLLATE utf8_bin,
  `OwnerID` text COLLATE utf8_bin,
  `Name` text COLLATE utf8_bin,
  `Contents` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `Comments`
--

CREATE TABLE `Comments` (
  `ID` text COLLATE utf8_bin,
  `WriterID` text COLLATE utf8_bin,
  `ContentID` text COLLATE utf8_bin,
  `Text` text COLLATE utf8_bin,
  `Date` date DEFAULT NULL,
  `Likes` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `Contents`
--

CREATE TABLE `Contents` (
  `ID` text CHARACTER SET utf8 COLLATE utf8_bin,
  `Name` text CHARACTER SET utf8 COLLATE utf8_bin,
  `Description` text CHARACTER SET utf8 COLLATE utf8_bin,
  `Rate` text CHARACTER SET utf8 COLLATE utf8_bin,
  `Genres` text CHARACTER SET utf8 COLLATE utf8_bin,
  `Score` double DEFAULT NULL,
  `Views` bigint(20) DEFAULT NULL,
  `Likes` bigint(20) DEFAULT NULL,
  `Info` text CHARACTER SET utf8 COLLATE utf8_bin,
  `Visibility` tinyint(1) DEFAULT NULL,
  `Poster` text CHARACTER SET utf8 COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Links`
--

CREATE TABLE `Links` (
  `ID` text COLLATE utf8_bin,
  `ContentID` text COLLATE utf8_bin,
  `Url` text COLLATE utf8_bin,
  `Name` text COLLATE utf8_bin,
  `Description` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `ID` text COLLATE utf8_bin,
  `FirstName` text COLLATE utf8_bin,
  `LastName` text COLLATE utf8_bin,
  `BirthDate` date DEFAULT NULL,
  `Phone` text COLLATE utf8_bin,
  `Email` text COLLATE utf8_bin,
  `Username` text COLLATE utf8_bin,
  `Password` text COLLATE utf8_bin,
  `ChannelID` text COLLATE utf8_bin,
  `AccessID` int(11) DEFAULT NULL,
  `Subscription` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
