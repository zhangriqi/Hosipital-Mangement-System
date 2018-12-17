-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 17, 2018 at 04:00 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `HMS_DATABASE.db`
--

-- --------------------------------------------------------

--
-- Table structure for table `Appointment`
--

CREATE TABLE `Appointment` (
  `a_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `department` varchar(40) NOT NULL,
  `a_time` varchar(40) NOT NULL,
  `doctor` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Appointment`
--

INSERT INTO `Appointment` (`a_id`, `id`, `department`, `a_time`, `doctor`) VALUES
(1, 0, 'test', 'test', 'test'),
(2, 12, 'Dental', 'Afternoon (1-4)', 'Department'),
(3, 12, 'Internal Medicine', 'Morning (9-12)', 'Department'),
(4, 12, 'Internal Medicine', 'Morning (9-12)', 'Expert'),
(5, 12, 'Internal Medicine', 'Morning (9-12)', 'Department'),
(6, 12, 'Dental', 'Morning (9-12)', 'Expert'),
(7, 0, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `Patient`
--

CREATE TABLE `Patient` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `gender` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Patient`
--

INSERT INTO `Patient` (`id`, `username`, `password`, `name`, `gender`) VALUES
(1, 'patient1', '123', 'zzz', 'F'),
(2, 'patient2', '234', 'rrr', 'M'),
(3, 'patient3', '345', 'qqq', 'M'),
(4, 'p4', '456', 'patient4', 'M'),
(8, 'zrq', '123', 'zrq', 'gende'),
(9, '', '', '', 'gende'),
(10, 'zrq1', '123', 'zrq1', 'gende'),
(11, 'zrq2', '123', 'zrq2', 'gende'),
(12, 'zrq3', '123', 'zrq3', 'gende'),
(16, 'p5password=567name=p', '', '', ''),
(17, 'p6', '678', 'p6', ''),
(18, 'p7', '789', 'p7', ''),
(19, 'p8', '8910', 'p8', ''),
(20, 'p9', '91011', 'p9', ''),
(21, 'p10', '101112', 'p10', ''),
(22, 'p11', '111213', 'p11', ''),
(23, 'p12', '121314', 'p12', ''),
(24, 'patient13', '131415', 'p13', ''),
(25, 'Patient12', '121314', 'p12', ''),
(26, 'patient19', '192021', 'p19', ''),
(27, '', '', '', ''),
(28, '', '', '', ''),
(29, '', '', '', ''),
(30, 'test17', '171819', 'test17', 'F');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Appointment`
--
ALTER TABLE `Appointment`
  ADD PRIMARY KEY (`a_id`);

--
-- Indexes for table `Patient`
--
ALTER TABLE `Patient`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Appointment`
--
ALTER TABLE `Appointment`
  MODIFY `a_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `Patient`
--
ALTER TABLE `Patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
